import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pedido } from '../model/pedido.model';

@Injectable({
  providedIn: 'root'
})
export class PedidoConsultaService {
  apiUrl: string = "http://localhost:8081/pedidos";

  constructor(private httpClient: HttpClient) { }

  findById(id: number): Observable<Pedido> {
    return this.httpClient.get<Pedido>(this.apiUrl + "/" + id);
  }

  findAll(): Observable<Pedido[]> {
    return this.httpClient.get<Pedido[]>(this.apiUrl);
  }
}