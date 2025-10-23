import { Component } from '@angular/core';
import { PedidoConsultaService } from '../../service/pedido-consulta.service';
import { NgFor, CurrencyPipe } from '@angular/common';
import { Pedido } from '../../model/pedido.model';
import { OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lista-pedidos',
  standalone: true,
  imports: [NgFor, CurrencyPipe, FormsModule],
  templateUrl: './lista-pedidos.component.html',
  styleUrls: ['./lista-pedidos.component.css']
})
export class ListaPedidosComponent implements OnInit {

  public pedidos: Pedido[] = [];
  public statusFiltro: string = '';

  constructor(
    private pedidoConsultaService: PedidoConsultaService,
    private router: Router
  ) { }

  ngOnInit() {
    this.pesquisar();
  }

  pesquisar() {
    this.pedidoConsultaService.findAll(this.statusFiltro).subscribe({
      next: (data: Pedido[]) => {
        this.pedidos = data;
      },
      error: () => {
        alert('Ocorreu um erro ao carregar os pedidos. Tente novamente.');
      }
    });
  }

  pesquisarSemFiltros() {
    this.statusFiltro = '';
    this.pesquisar();
  }

  novoPedido() {
    this.router.navigate(['/pedidos/novo']);
  }

  verPedido(pedidoId: number) {
    this.router.navigate(['/pedidos', pedidoId]);
  }
}
