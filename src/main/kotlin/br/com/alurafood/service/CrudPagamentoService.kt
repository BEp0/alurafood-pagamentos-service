package br.com.alurafood.service

import br.com.alurafood.dto.PagamentoDTO
import br.com.alurafood.model.Status.CRIADO
import br.com.alurafood.repository.PagamentoRepository
import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@AllArgsConstructor
@Service
class CrudPagamentoService(
    @Autowired
    private val pagamentoRepository: PagamentoRepository,
) {

    fun obterTodos(@PageableDefault(size = 5) pageable: Pageable): Page<PagamentoDTO> {
        return pagamentoRepository.findAllByStatus(CRIADO, pageable)
            .map { PagamentoDTO.toDTO(it) }
    }

    fun obterPorId(id: Long): PagamentoDTO {
        val pagamento = pagamentoRepository.findByIdAndStatus(id, CRIADO)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado um pagamento com o id ${id}") }
        return PagamentoDTO.toDTO(pagamento)
    }

    fun criarPagamento(pagamentoDTO: PagamentoDTO): PagamentoDTO {
        TODO("")
    }
}