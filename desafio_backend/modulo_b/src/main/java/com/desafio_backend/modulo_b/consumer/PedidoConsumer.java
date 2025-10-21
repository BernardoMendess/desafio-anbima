package com.desafio_backend.modulo_b.consumer;

import com.anbima.model.PedidoMessageDTO;
import com.desafio_backend.modulo_b.service.PedidoConsultaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {

    @Autowired
    private PedidoConsultaService pedidoConsultaService;

    @RabbitListener(queues = "pedidos.recebidos")
    private void consumidor(PedidoMessageDTO pedidoMessageDTO) {
        pedidoConsultaService.atualizaStatusPedido(pedidoMessageDTO.pedidoId());
    }
}
