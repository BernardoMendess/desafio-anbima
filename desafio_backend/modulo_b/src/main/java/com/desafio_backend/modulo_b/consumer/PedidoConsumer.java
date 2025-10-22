package com.desafio_backend.modulo_b.consumer;

import com.anbima.model.PedidoMessageDTO;
import com.desafio_backend.modulo_b.service.PedidoConsultaService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PedidoConsumer {

    private PedidoConsultaService pedidoConsultaService;

    @RabbitListener(queues = "pedidos.recebidos")
    private void consumidor(PedidoMessageDTO pedidoMessageDTO) {
        pedidoConsultaService.atualizaStatusPedido(pedidoMessageDTO.pedidoId());
    }
}
