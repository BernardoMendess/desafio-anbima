import { Component, OnInit} from '@angular/core';
import { PedidoConsultaService } from '../../service/pedido-consulta.service';
import { Pedido } from '../../model/pedido.model';
import { DatePipe, CurrencyPipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router'; 

@Component({
  selector: 'app-ver-pedido',
  imports: [DatePipe, CurrencyPipe],
  templateUrl: './ver-pedido.component.html',
  styleUrl: './ver-pedido.component.css'
})
export class VerPedidoComponent implements OnInit{

  public pedidoId: number = 0;
  public pedido: Pedido | null = null;

  constructor(
    private pedidoConsultaService: PedidoConsultaService,
    private route: ActivatedRoute 
  ) { 

  }
  
  ngOnInit() {
    const idFromRoute = this.route.snapshot.paramMap.get('id');

    if (idFromRoute) {
      this.pedidoId = Number(idFromRoute); 

      this.pedidoConsultaService.findById(this.pedidoId).subscribe({
        next: (pedido) => {
          this.pedido = pedido;
        },
        error: () => {
          alert('Ocorreu um erro ao carregar o pedido. Tente novamente.');
        }
      });
    } 
  }

  voltar() {
    window.history.back();
  }
}