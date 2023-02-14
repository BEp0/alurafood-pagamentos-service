package br.com.alurafood.model

import br.com.alurafood.model.Status.CONFIRMADO
import br.com.alurafood.model.Status.CRIADO
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.math.BigDecimal

@AllArgsConstructor
@NoArgsConstructor
@Entity
data class Pagamento (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @NotNull @Positive
    val valor: BigDecimal,

    @NotBlank @Size(max = 100)
    val nome: String,

    @NotBlank @Size(max = 19)
    val numero: String,

    @NotBlank @Size(max = 7)
    val expiracao: String,

    @NotBlank @Size(max = 3)
    val codigo: String,

    @NotNull @Enumerated(STRING)
    val status: Status,

    @NotNull
    val pedidoId: Long,

    @NotNull
    val formaDePagamento: Long
) {
    companion object {
        fun atualizar(id: Long, pagamento: Pagamento) = Pagamento(
            id,
            pagamento.valor,
            pagamento.nome,
            pagamento.numero,
            pagamento.expiracao,
            pagamento.codigo,
            pagamento.status,
            pagamento.pedidoId,
            pagamento.formaDePagamento
        )
        fun confirmar(pagamento: Pagamento) = Pagamento(
            pagamento.id,
            pagamento.valor,
            pagamento.nome,
            pagamento.numero,
            pagamento.expiracao,
            pagamento.codigo,
            CONFIRMADO,
            pagamento.pedidoId,
            pagamento.formaDePagamento
        )
    }
}