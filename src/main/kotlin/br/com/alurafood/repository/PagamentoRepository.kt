package br.com.alurafood.repository

import br.com.alurafood.model.Pagamento
import br.com.alurafood.model.Status
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PagamentoRepository : JpaRepository<Pagamento, Long> {
    fun findAllByStatus(status: Status, pageable: Pageable): Page<Pagamento>
    fun findByIdAndStatus(id: Long, status: Status): Optional<Pagamento>
}