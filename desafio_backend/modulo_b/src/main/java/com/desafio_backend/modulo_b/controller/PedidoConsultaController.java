package com.desafio_backend.modulo_b.controller;

import com.anbima.model.Pedido;
import com.desafio_backend.modulo_b.service.PedidoConsultaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoConsultaController {

    @Autowired
    private PedidoConsultaService pedidoConsultaService;

    @GetMapping
    public ResponseEntity<List<Pedido>> list(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pedidoConsultaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> get(@PathVariable long id){
        return pedidoConsultaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
