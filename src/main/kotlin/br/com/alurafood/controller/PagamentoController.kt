package br.com.alurafood.controller

import br.com.alurafood.dto.PagamentoDTO
import br.com.alurafood.service.CrudPagamentoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pagamentos")
class PagamentoController(
    @Autowired
    val crudPagamentoService: CrudPagamentoService
) {

    @GetMapping
    fun obterTodos(pageable: Pageable): Page<PagamentoDTO> = crudPagamentoService.obterTodos(pageable)

    @GetMapping("/{id}")
    fun obterPorId(@PathVariable id: Long): ResponseEntity<PagamentoDTO> {

        val pagamentoResponse = crudPagamentoService.obterPorId(id)
        return ResponseEntity.ok(pagamentoResponse)
    }

    @PostMapping
    fun criar(@RequestBody pagamentoDTO: PagamentoDTO): ResponseEntity<PagamentoDTO> {

        val pagamentoResponse = crudPagamentoService.criar(pagamentoDTO)
        return ResponseEntity.status(CREATED).body(pagamentoResponse)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody pagamentoDTO: PagamentoDTO): ResponseEntity<PagamentoDTO> {

        val pagamentoResponse = crudPagamentoService.atualizar(id, pagamentoDTO)
        return ResponseEntity.ok(pagamentoResponse)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        crudPagamentoService.deletar(id)
    }
}