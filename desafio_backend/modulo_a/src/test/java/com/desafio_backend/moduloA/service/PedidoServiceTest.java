package com.desafio_backend.moduloA.service;

import com.anbima.model.PedidoMessageDTO;
import com.anbima.model.Status;
import jakarta.transaction.Transactional;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;

    @MockBean
    private AmqpAdmin amqpAdmin;

    @MockBean
    private RabbitMQService rabbitMQService;

    @Test
    @Rollback
    @Transactional
    public void quando_cria_pedido_e_linha_valida_entao_ok() throws Exception {

        val pedido = "HAMBURGUERCARNE     BATATA    02SUCO    ";
        val pedidoSalvo = pedidoService.salvaPedido(pedido);

        int quantidade = Integer.parseInt(pedido.substring(30, 32).trim());

        assertEquals(pedido.substring(0, 10).trim(), pedidoSalvo.getTipoLanche());
        assertEquals(pedido.substring(10, 20).trim(), pedidoSalvo.getProteina());
        assertEquals(pedido.substring(20, 30).trim(), pedidoSalvo.getAcompanhamento());
        assertEquals(quantidade, pedidoSalvo.getQuantidade());
        assertEquals(pedido.substring(32, 40).trim(), pedidoSalvo.getBebida());
        assertEquals(new BigDecimal("40.00"), pedidoSalvo.getValor());
        assertEquals(Status.RECEBIDO, pedidoSalvo.getStatus());
        verify(rabbitMQService, times(1))
                .enviaMensagem(eq("pedidos.recebidos"), any(PedidoMessageDTO.class));
    }

    @Test
    @Rollback
    @Transactional
    public void quando_cria_pedido_com_promocao_e_linha_valida_entao_ok() throws Exception {

        val pedido = "HAMBURGUERCARNE     SALADA    02SUCO    ";
        val pedidoSalvo = pedidoService.salvaPedido(pedido);

        int quantidade = Integer.parseInt(pedido.substring(30, 32).trim());

        assertEquals(pedido.substring(0, 10).trim(), pedidoSalvo.getTipoLanche());
        assertEquals(pedido.substring(10, 20).trim(), pedidoSalvo.getProteina());
        assertEquals(pedido.substring(20, 30).trim(), pedidoSalvo.getAcompanhamento());
        assertEquals(quantidade, pedidoSalvo.getQuantidade());
        assertEquals(pedido.substring(32, 40).trim(), pedidoSalvo.getBebida());
        assertEquals(new BigDecimal("36.00"), pedidoSalvo.getValor());
        assertEquals(Status.RECEBIDO, pedidoSalvo.getStatus());
        verify(rabbitMQService, times(1))
                .enviaMensagem(eq("pedidos.recebidos"), any(PedidoMessageDTO.class));
    }

    @Test
    @Rollback
    @Transactional
    public void quando_cria_pedido_e_linha_com_tamanho_invalido_entao_falha() throws Exception {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> pedidoService.salvaPedido("HAMBURGUER CARNE BATATA 02SUCO"))
                .withMessageContaining("Linha posicional deve ter exatamente 40 caracteres.")
                .withNoCause();
    }

    @Test
    @Rollback
    @Transactional
    public void quando_cria_pedido_e_quantidade_invalida_entao_falha() throws Exception {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> pedidoService.salvaPedido("HAMBURGUERCARNE     SALADA    00SUCO    "))
                .withMessageContaining("Quantidade deve ser entre 01 e 99.")
                .withNoCause();
    }

    @Test
    @Rollback
    @Transactional
    public void quando_cria_pedido_e_caracteres_quantidade_invalidos_entao_falha() throws Exception {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> pedidoService.salvaPedido("HAMBURGUERCARNE     SALADA    ABSUCO    "))
                .withMessageContaining("Quantidade 'AB' não é um número válido.")
                .withNoCause();
    }
}
