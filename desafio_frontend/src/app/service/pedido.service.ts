import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pedido } from '../model/pedido.model';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {
  apiUrl: string = "http://localhost:8080/pedidos";

  constructor(private httpClient: HttpClient) { }

  save(pedido: String): Observable<Pedido> {
    return this.httpClient.post<Pedido>(this.apiUrl + '/posicional', pedido);
  }
}