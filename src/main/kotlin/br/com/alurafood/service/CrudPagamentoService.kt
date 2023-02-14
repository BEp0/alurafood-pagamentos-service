package br.com.alurafood.service

import br.com.alurafood.dto.PagamentoDTO
import br.com.alurafood.exceptions.ExceptionsHandler
import br.com.alurafood.model.Pagamento
import br.com.alurafood.model.Status
import br.com.alurafood.model.Status.CRIADO
import br.com.alurafood.repository.PagamentoRepository
import br.com.alurafood.validation.PagamentoValidation
import jakarta.transaction.Transactional
import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Service

@AllArgsConstructor
@Service
class CrudPagamentoService(
    @Autowired
    private val pagamentoRepository: PagamentoRepository,
    @Autowired
    private val pagamentoValidation: PagamentoValidation
) {
    private val STATUS_BUSCA: Status = CRIADO

    fun obterTodos(@PageableDefault(size = 5) pageable: Pageable): Page<PagamentoDTO> {
        return pagamentoRepository.findAllByStatus(CRIADO, pageable)
            .map { PagamentoDTO.toDTO(it) }
    }

    fun obterPorId(id: Long): PagamentoDTO {
        val pagamento = pagamentoRepository.findById(id)
            .orElseThrow { ExceptionsHandler.notFound(id) }
        return PagamentoDTO.toDTO(pagamento)
    }

    @Transactional
    fun criar(pagamentoDTO: PagamentoDTO): PagamentoDTO {

        val pagamento = PagamentoDTO.toEntityCriado(pagamentoDTO)
        val pagamentoSalvo = pagamentoRepository.save(pagamento)

        return PagamentoDTO.toDTO(pagamentoSalvo)
    }

    @Transactional
    fun atualizar(id: Long, pagamentoDTO: PagamentoDTO): PagamentoDTO {

        pagamentoValidation.validarPorId(id)
        pagamentoValidation.validarStatus(pagamentoDTO.status)

        val pagamentoAtualizado = Pagamento.atualizar(id, PagamentoDTO.toEntity(pagamentoDTO))
        val pagamentoSalvo = pagamentoRepository.save(pagamentoAtualizado)

        return PagamentoDTO.toDTO(pagamentoSalvo)
    }

    fun deletar(id: Long) {
        pagamentoRepository.deleteById(id)
    }
}