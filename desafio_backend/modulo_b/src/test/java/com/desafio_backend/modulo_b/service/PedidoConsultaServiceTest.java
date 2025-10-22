package com.desafio_backend.modulo_b.service;

import com.anbima.model.Pedido;
import com.anbima.model.PedidoMessageDTO;
import com.anbima.model.Status;
import com.desafio_backend.modulo_b.DAO.PedidoConsultaDAO;
import com.desafio_backend.modulo_b.consumer.PedidoConsumer;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PedidoConsultaServiceTest {

    @InjectMocks
    private PedidoConsultaService pedidoConsultaService;

    @Mock
    private PedidoConsultaDAO pedidoConsultaDAO;

    @Test
    void quando_consumidor_recebe_mensagem_entao_chama_service_e_atualiza_status() {

        val pedido = new Pedido();
        pedido.setId(123L);
        pedido.setStatus(Status.RECEBIDO);

        when(pedidoConsultaDAO.findById(pedido.getId())).thenReturn(Optional.of(pedido));

        pedidoConsultaService.atualizaStatusPedido(pedido.getId());

        val pedidoAtualizado = pedidoConsultaService.findById(pedido.getId()).get();
        assertEquals(pedido.getId(), pedidoAtualizado.getId());
        assertEquals(Status.ENTREGUE, pedidoAtualizado.getStatus());
    }

}
