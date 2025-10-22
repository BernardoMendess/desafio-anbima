import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PedidoService } from '../../service/pedido.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-novo-pedido',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './novo-pedido.component.html',
  styleUrls: ['./novo-pedido.component.css']
})
export class NovoPedidoComponent {
  public pedido: string = '';

  constructor(
    private pedidoService: PedidoService,
    private router: Router,
  ) { }

  salvarPedido() {
     this.pedidoService.save(this.pedido).pipe()
     .subscribe({
      next: () => {
        alert('Pedido cadastrado com sucesso!');
        this.router.navigate(['/pedidos']);
      },
      error: () => {
        alert('Ocorreu um erro. Tente novamente.');
      }
    });
  }
  
}
