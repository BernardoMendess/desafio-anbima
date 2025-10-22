package com.desafio_backend.moduloA.controller;

import com.desafio_backend.moduloA.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {

    private PedidoService pedidoService;

    @PostMapping("/posicional")
    public ResponseEntity<?> pedidoPosicional(@RequestBody String pedido) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(pedidoService.salvaPedido(pedido));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
