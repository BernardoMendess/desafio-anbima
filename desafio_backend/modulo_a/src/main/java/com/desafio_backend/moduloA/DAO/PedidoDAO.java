package com.desafio_backend.moduloA.DAO;

import com.anbima.model.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface PedidoDAO extends CrudRepository<Pedido, Long> {
}
