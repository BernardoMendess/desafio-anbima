package com.desafio_backend.modulo_b.DAO;

import com.anbima.model.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PedidoConsultaDAO extends CrudRepository<Pedido, Long> {

    List<Pedido> findAll();
}
