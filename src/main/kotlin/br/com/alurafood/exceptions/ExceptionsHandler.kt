package br.com.alurafood.exceptions

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.server.ResponseStatusException

class ExceptionsHandler {

    companion object {
        fun notFound(id: Long): ResponseStatusException {
            return ResponseStatusException(NOT_FOUND, "Não foi encontrado um pagamento com o id ${id}")
        }

        fun badRequest(mensagem: String): ResponseStatusException {
            return ResponseStatusException(BAD_REQUEST, mensagem)
        }
    }
}