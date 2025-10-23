import { HttpClient, HttpParams } from '@angular/common/http';
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

  findAll(status?: string): Observable<Pedido[]> {
    let params = new HttpParams();
    if (status) {
      params = params.set('status', status);
    }
    return this.httpClient.get<Pedido[]>(this.apiUrl, { params: params });
  }
}