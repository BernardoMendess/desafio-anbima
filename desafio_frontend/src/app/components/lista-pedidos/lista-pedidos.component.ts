import { Component } from '@angular/core';
import { PedidoConsultaService } from '../../service/pedido-consulta.service';
import { NgFor } from '@angular/common';
import { Pedido } from '../../model/pedido.model';
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-lista-pedidos',
  imports: [NgFor],
  templateUrl: './lista-pedidos.component.html',
  styleUrl: './lista-pedidos.component.css'
})
export class ListaPedidosComponent implements OnInit {

  public pedidos: Pedido[] = [];

  constructor(
    private pedidoConsultaService: PedidoConsultaService,
  ) { }

  ngOnInit() {
    this.pedidoConsultaService.findAll().subscribe({
      next: (data: Pedido[]) => {
        this.pedidos = data;
      },
      error: () => {
        alert('Ocorreu um erro ao carregar os pedidos. Tente novamente.');
      }
    });
  }
}
