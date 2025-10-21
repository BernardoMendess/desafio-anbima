package com.desafio_backend.moduloA.service;

import com.anbima.model.Pedido;
import com.anbima.model.Status;
import com.desafio_backend.moduloA.DAO.PedidoDAO;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PedidoService {

    private PedidoDAO pedidoDAO;

    public Pedido toPedido(String linha) throws Exception {
        validaStringPosicional(linha);

        val pedido = new Pedido();
        pedido.setTipoLanche(linha.substring(0, 10).trim());
        pedido.setProteina(linha.substring(10, 20).trim());
        pedido.setAcompanhamento(linha.substring(20, 30).trim());
        pedido.setQuantidade(Integer.parseInt(linha.substring(30, 32).trim()));
        pedido.setBebida(linha.substring(32, 40).trim());
        pedido.setValor(calcularValorPedido(pedido));
        pedido.setStatus(Status.RECEBIDO);
        pedido.setCriadoEm(LocalDateTime.now());
        return pedidoDAO.save(pedido);
    }

    private void validaStringPosicional(String linha) throws Exception {
        if(linha.trim().isEmpty()){
            throw new RuntimeException();
        }
        if(linha.length() > 40){
            throw new RuntimeException();
        }
    }

    private BigDecimal calcularValorPedido(Pedido pedido){
        BigDecimal valor = new BigDecimal(0);
        if (Objects.equals(pedido.getTipoLanche(), "HAMBURGUER")){
            valor = valor.add(new BigDecimal("20.00"));
            if(Objects.equals(pedido.getProteina(), "CARNE") && Objects.equals(pedido.getAcompanhamento(), "SALADA")){
                valor = valor.multiply(new BigDecimal("0.9"));
            }
        } else if (Objects.equals(pedido.getTipoLanche(), "PASTEL")){
            valor = valor.add(new BigDecimal("15.00"));
        } else {
            valor = valor.add(new BigDecimal("12.00"));
        }
        return valor.multiply(new BigDecimal(pedido.getQuantidade()));
    }
}
