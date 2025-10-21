package com.desafio_backend.moduloA.controller;

import com.anbima.model.Pedido;
import com.desafio_backend.moduloA.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/posicional")
    public ResponseEntity<Pedido> pedidoPosicional(@RequestBody String pedido) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoService.toPedido(pedido));
    }
}
