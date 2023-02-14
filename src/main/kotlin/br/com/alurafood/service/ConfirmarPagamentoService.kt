package br.com.alurafood.service

import br.com.alurafood.exceptions.ExceptionsHandler
import br.com.alurafood.http.PedidoClient
import br.com.alurafood.model.Pagamento
import br.com.alurafood.repository.PagamentoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ConfirmarPagamentoService(
    @Autowired
    val repository: PagamentoRepository,
    @Autowired
    val pedido: PedidoClient,
) {

    fun confirmar(id: Long) {

        val pagamento = repository.findById(id)
            .orElseThrow { ExceptionsHandler.notFound(id) }

        val pagamentoConfirmado = Pagamento.confirmar(pagamento)
        repository.save(pagamentoConfirmado)
        pedido.atualizarPagamento(pagamentoConfirmado.pedidoId)
    }
}