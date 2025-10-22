package com.desafio_backend.moduloA.controller;

import com.desafio_backend.moduloA.connections.RabbitMQConnection;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private RabbitMQConnection rabbitMQConnection;

    @Test
    @Rollback
    @Transactional
    public void quando_tenta_salvar_pedido_entao_ok() throws Exception {

        mockMvc.perform(post("/pedidos/posicional")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("HAMBURGUERCARNE     SALADA    02SUCO    "))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipoLanche").value("HAMBURGUER"))
                .andExpect(jsonPath("$.proteina").value("CARNE"))
                .andExpect(jsonPath("$.acompanhamento").value("SALADA"))
                .andExpect(jsonPath("$.quantidade").value(2))
                .andExpect(jsonPath("$.bebida").value("SUCO"))
                .andExpect(jsonPath("$.valor").value(36.00))
                .andExpect(jsonPath("$.status").value("RECEBIDO"));
    }

    @Test
    @Rollback
    @Transactional
    public void quando_tenta_salvar_pedido_mas_dados_invalidos_entao_falha() throws Exception {

        mockMvc.perform(post("/pedidos/posicional")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("HAMBURGUER CARNE SALADA 02SUCO"))
                .andExpect(status().isBadRequest());
    }
}
