Sistema de processamento de pedidos implementado em arquitetura de microsserviços orientada a eventos. O projeto consiste em dois módulos Spring Boot (Gateway e Processor) e um frontend Angular, comunicando-se via RabbitMQ.

Arquitetura
Frontend (Angular): Envia uma string posicional para o Módulo A.

Módulo A (Gateway): POST /pedidos/posicional. Recebe a string, valida, calcula o valor, salva no banco (status=RECEBIDO) e publica o {pedidoId} na fila pedidos.recebidos.

RabbitMQ: Atua como Message Broker para a comunicação assíncrona.

Módulo B (Processor): Consome a fila, busca o pedido pelo ID, e atualiza o status para ENTREGUE.

Módulo B (API): Implementa os endpoints de consulta GET /pedidos e GET /pedidos/{id}.
