package com.desafio_backend.modulo_b.controller;

import com.anbima.model.Pedido;
import com.desafio_backend.modulo_b.service.PedidoConsultaService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PedidoConsultaControllerTest {

    @MockBean
    private PedidoConsultaService pedidoConsultaService;

    @MockBean
    private AmqpAdmin amqpAdmin;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void quando_get_pedidos_e_nao_existem_pedidos_entao_retorna_ok_e_vazio() throws Exception {

        when(pedidoConsultaService.findAll(null)).thenReturn(List.of());

        mockMvc.perform(get("/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void quando_get_pedidos_entao_retorna_ok_e_lista() throws Exception {
        val p1 = new Pedido();
        p1.setId(1L);
        p1.setTipoLanche("HAMBURGUER");

        val p2 = new Pedido();
        p2.setId(2L);
        p2.setTipoLanche("PASTEL");

        val listaDePedidos = List.of(p1, p2);

        when(pedidoConsultaService.findAll(null)).thenReturn(listaDePedidos);

        mockMvc.perform(get("/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].tipoLanche").value("HAMBURGUER"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].tipoLanche").value("PASTEL"));
    }

    @Test
    void quando_get_pedidos_por_id_entao_retorna_ok() throws Exception {
        val pedido = new Pedido();
        pedido.setId(1L);
        pedido.setTipoLanche("HAMBURGUER");

        when(pedidoConsultaService.findById(1L)).thenReturn(Optional.of(pedido));

        mockMvc.perform(get("/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.tipoLanche").value("HAMBURGUER"));
    }

    @Test
    void quando_get_pedidos_por_id_inexistente_entao_falha() throws Exception {
        mockMvc.perform(get("/pedidos/10000"))
                .andExpect(status().isNotFound());
    }
}
