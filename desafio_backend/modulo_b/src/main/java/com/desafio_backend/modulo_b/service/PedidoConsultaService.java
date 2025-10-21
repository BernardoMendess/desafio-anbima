package com.desafio_backend.modulo_b.service;

import com.anbima.model.Pedido;
import com.desafio_backend.modulo_b.DAO.PedidoConsultaDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidoConsultaService {

    private PedidoConsultaDAO pedidoConsultaDAO;

    public List<Pedido> findAll(){
        return pedidoConsultaDAO.findAll();
    }

    public Optional<Pedido> findById(long id){
        return pedidoConsultaDAO.findById(id);
    }

}
