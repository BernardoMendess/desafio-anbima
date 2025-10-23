import { Routes } from '@angular/router';
import { NovoPedidoComponent } from './components/novo-pedido/novo-pedido.component'
import { ListaPedidosComponent } from './components/lista-pedidos/lista-pedidos.component'

export const routes: Routes = [
    {
        path:"pedidos/novo",
        component: NovoPedidoComponent
    }, 
    {
        path:"pedidos",
        component: ListaPedidosComponent
    }
];
