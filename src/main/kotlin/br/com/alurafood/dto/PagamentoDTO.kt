package br.com.alurafood.dto

import br.com.alurafood.model.Pagamento
import br.com.alurafood.model.Status
import java.math.BigDecimal

data class PagamentoDTO (
    val id: Long?,
    val valor: BigDecimal,
    val nome: String,
    val numero: String,
    val expiracao: String,
    val codigo: String,
    val status: Status,
    val pedidoId: Long,
    val formaDePagamento: Long
) {
    companion object{
        fun toDTO(pagamento: Pagamento) = PagamentoDTO(
            pagamento.id,
            pagamento.valor,
            pagamento.nome,
            pagamento.numero,
            pagamento.expiracao,
            pagamento.codigo,
            pagamento.status,
            pagamento.pedidoId,
            pagamento.formaDePagamento
        )
        fun toEntity(pagamentoDTO: PagamentoDTO) = Pagamento(
            pagamentoDTO.id,
            pagamentoDTO.valor,
            pagamentoDTO.nome,
            pagamentoDTO.numero,
            pagamentoDTO.expiracao,
            pagamentoDTO.codigo,
            pagamentoDTO.status,
            pagamentoDTO.pedidoId,
            pagamentoDTO.formaDePagamento
        )
    }
}