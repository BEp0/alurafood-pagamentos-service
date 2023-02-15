# alurafood-pagamentos-service

Microsserviço desenvolvido com base no curso "Microsserviços na prática: implementando com Java e Spring" da Alura

## Chamadas implementadas:

`GET localhost:8083/pagamentos-ms/pagamentos`
- Retorna os pagamentos com status CRIADO e paginado

`GET localhost:8083/pagamentos-ms/pagamentos/1`
- Retorna o pagamento com o id passado e com qualquer status

`POST localhost:8083/pagamentos-ms/pagamentos`
- Criação de um pagamento
- Exemplo de body:

```JSON
{
    "valor": 20.50,
    "nome": "Pagamento X",
    "numero": "4111.1111.1111.1111",
    "expiracao": "20/2023",
    "codigo": "123",
    "pedidoId": 1,
    "formaDePagamento": 1
}
```

`PUT localhost:8083/pagamentos-ms/pagamentos/1`
- Alteração de algum pagamento
- Necessário passar o id no PathParam
- Exemplo de body:

```JSON
{
    "valor": 22.50,
    "nome": "Pagamento X",
    "numero": "4111.1111.1111.1111",
    "expiracao": "20/2023",
    "codigo": "123",
    "status": "CONFIRMADO",
    "pedidoId": 1,
    "formaDePagamento": 1
}
```

`PATCH localhost:8083/pagamentos-ms/pagamentos/1/confirmar`
- Altera o status do pedido no microsserviço `alurafood-pedidos`

`DELETE localhost:8083/pagamentos-ms/pagamentos/1`
- Deleta um pagamento :V   
