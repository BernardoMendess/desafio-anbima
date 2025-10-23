package com.desafio_backend.modulo_b.service;

import com.anbima.model.Pedido;
import com.anbima.model.Status;
import com.desafio_backend.modulo_b.DAO.PedidoConsultaDAO;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidoConsultaService {

    private PedidoConsultaDAO pedidoConsultaDAO;

    public List<Pedido> findAll(String status){
        if(status == null){
            return pedidoConsultaDAO.findAll();
        }
        return pedidoConsultaDAO.findAllByStatus(Status.valueOf(status));
    }

    public Optional<Pedido> findById(long id){
        return pedidoConsultaDAO.findById(id);
    }

    public void atualizaStatusPedido(long pedidoId) {
        val pedido = pedidoConsultaDAO.findById(pedidoId).get();
        pedido.setStatus(Status.ENTREGUE);
        pedidoConsultaDAO.save(pedido);
    }
}
