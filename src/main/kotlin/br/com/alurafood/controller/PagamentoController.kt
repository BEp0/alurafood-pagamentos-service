package br.com.alurafood.controller

import br.com.alurafood.dto.PagamentoDTO
import br.com.alurafood.service.CrudPagamentoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pagamentos")
class PagamentoController(
    @Autowired
    val crudPagamentoService: CrudPagamentoService
){

    @GetMapping
    fun obterTodos(pageable: Pageable): Page<PagamentoDTO> = crudPagamentoService.obterTodos(pageable)

    @GetMapping("/{id}")
    fun obterPorId(@PathVariable id: Long): ResponseEntity<PagamentoDTO> {

        val pagamentoResponse = crudPagamentoService.obterPorId(id)
        return ResponseEntity.ok(pagamentoResponse)
    }
}