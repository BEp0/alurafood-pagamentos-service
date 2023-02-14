package br.com.alurafood.controller

import br.com.alurafood.dto.PagamentoDTO
import br.com.alurafood.service.ConfirmarPagamentoService
import br.com.alurafood.service.CrudPagamentoService
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pagamentos")
class PagamentosController(
    @Autowired
    private val crudPagamentoService: CrudPagamentoService,
    @Autowired
    private val confirmarPagamentoService: ConfirmarPagamentoService,
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
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoResponse)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody pagamentoDTO: PagamentoDTO): ResponseEntity<PagamentoDTO> {

        val pagamentoResponse = crudPagamentoService.atualizar(id, pagamentoDTO)
        return ResponseEntity.ok(pagamentoResponse)
    }

    @PatchMapping("/{id}/confirmar")
    @CircuitBreaker(name = "atualizaPedido", fallbackMethod = "integracaoPendente")
    fun confirmarPagamento(@PathVariable id: Long) {
        confirmarPagamentoService.confirmar(id)
    }

    fun integracaoPendente(id: Long, e: Exception) {
        confirmarPagamentoService.fallback(id)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        crudPagamentoService.deletar(id)
    }
}