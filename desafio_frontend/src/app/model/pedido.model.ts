export interface Pedido {
  id: number;
  tipoLanche: string;
  proteina: string;
  acompanhamento: string;
  quantidade: number;
  bebida: string;
  valor: number;
  status: Status;
  criadoEm: string; 
}

export type Status = 'RECEBIDO' | 'ENTREGUE';
