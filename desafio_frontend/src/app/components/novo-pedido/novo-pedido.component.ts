import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PedidoService } from '../../service/pedido.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

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
     if(!this.validaPedido()) return alert('A linha deve ter exatamente 40 caracteres.');
     this.pedidoService.save(this.pedido).pipe()
     .subscribe({
      next: () => {
        alert('Pedido cadastrado com sucesso!');
        this.router.navigate(['/pedidos']);
      },
       error: (err: HttpErrorResponse) => {
        if (err.status === 400 && err.error) {
          alert(err.error);
        } else {
          alert("Ocorreu um erro desconhecido. Tente novamente.");
        }      
      }
    });
  }

  validaPedido(): boolean {
    return this.pedido.length == 40;
  }

  voltar() {
    window.history.back();
  }
}
