package com.desafio_backend.modulo_b.DAO;

import com.anbima.model.Pedido;
import com.anbima.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PedidoConsultaDAO extends CrudRepository<Pedido, Long> {

    List<Pedido> findAll();

    List<Pedido> findAllByStatus(Status status);
}
