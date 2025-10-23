import { Routes } from '@angular/router';
import { NovoPedidoComponent } from './components/novo-pedido/novo-pedido.component'
import { ListaPedidosComponent } from './components/lista-pedidos/lista-pedidos.component'
import { VerPedidoComponent } from './components/ver-pedido/ver-pedido.component'

export const routes: Routes = [
    {
        path:"pedidos/novo",
        component: NovoPedidoComponent
    }, 
    {
        path:"pedidos/:id",
        component: VerPedidoComponent
    }, 
    {
        path:"pedidos",
        component: ListaPedidosComponent
    },
    {
        path: '',
        redirectTo: 'pedidos',
        pathMatch: 'full' 
    },
    {
        path: '**',
        redirectTo: 'pedidos' 
    }
];
