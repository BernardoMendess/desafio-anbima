package com.desafio_backend.modulo_b.controller;

import com.anbima.model.Pedido;
import com.desafio_backend.modulo_b.service.PedidoConsultaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoConsultaController {

    private PedidoConsultaService pedidoConsultaService;

    @GetMapping
    public ResponseEntity<List<Pedido>> list(@RequestParam(required = false) String status){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pedidoConsultaService.findAll(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> get(@PathVariable long id){
        return pedidoConsultaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
