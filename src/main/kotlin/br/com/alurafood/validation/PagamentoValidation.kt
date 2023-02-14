package br.com.alurafood.validation

import br.com.alurafood.exceptions.ExceptionsHandler
import br.com.alurafood.model.Status
import br.com.alurafood.repository.PagamentoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.Objects

@Component
class PagamentoValidation(
    @Autowired
    private val pagamentoRepository: PagamentoRepository
) {
    fun validarPorId(id: Long) {
        if (!pagamentoRepository.existsById(id)){
            throw ExceptionsHandler.notFound(id)
        }
    }

    fun validarStatus(status: Status?){
        if (Objects.isNull(status))
            throw ExceptionsHandler.badRequest("Status não pode ser nulo")
    }
}
