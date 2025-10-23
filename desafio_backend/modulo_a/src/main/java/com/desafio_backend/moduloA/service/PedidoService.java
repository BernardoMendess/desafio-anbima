package com.desafio_backend.moduloA.service;

import com.anbima.model.Pedido;
import com.anbima.model.PedidoMessageDTO;
import com.anbima.model.Status;
import com.desafio_backend.moduloA.DAO.PedidoDAO;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PedidoService {

    private PedidoDAO pedidoDAO;

    private RabbitMQService rabbitMQService;

    public Pedido salvaPedido(String linha) {
        validarLinha(linha);

        val pedido = new Pedido();
        pedido.setTipoLanche(linha.substring(0, 10).trim());
        pedido.setProteina(linha.substring(10, 20).trim());
        pedido.setAcompanhamento(linha.substring(20, 30).trim());
        pedido.setQuantidade(Integer.parseInt(linha.substring(30, 32).trim()));
        pedido.setBebida(linha.substring(32, 40).trim());
        pedido.setValor(calcularValorPedido(pedido));
        pedido.setStatus(Status.RECEBIDO);
        pedido.setCriadoEm(LocalDateTime.now());

        val pedidoSalvo = pedidoDAO.save(pedido);

        rabbitMQService.enviaMensagem("pedidos.recebidos", new PedidoMessageDTO(pedidoSalvo.getId()));

        return pedidoSalvo;
    }

    private void validarLinha(String linha) {
        if (linha == null || linha.length() != 40) {
            throw new IllegalArgumentException("Linha posicional deve ter exatamente 40 caracteres.");
        }
        val qtdString = linha.substring(30, 32);
        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Quantidade '" + qtdString + "' não é um número válido.");
        }
        if (quantidade < 1 || quantidade > 99) {
            throw new IllegalArgumentException("Quantidade deve ser entre 01 e 99.");
        }
    }

    private BigDecimal calcularValorPedido(Pedido pedido){
        BigDecimal valor = new BigDecimal(0);
        if ("HAMBURGUER".equalsIgnoreCase(pedido.getTipoLanche())) {
            valor = valor.add(new BigDecimal("20.00"));
            if ("CARNE".equalsIgnoreCase(pedido.getProteina()) &&
                    "SALADA".equalsIgnoreCase(pedido.getAcompanhamento())) {
                valor = valor.multiply(new BigDecimal("0.9"));
            }
        } else if ("PASTEL".equalsIgnoreCase(pedido.getTipoLanche())) {
            valor = valor.add(new BigDecimal("15.00"));
        } else {
            valor = valor.add(new BigDecimal("12.00"));
        }
        return valor.multiply(new BigDecimal(pedido.getQuantidade()))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
