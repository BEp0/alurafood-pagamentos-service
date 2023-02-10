package br.com.alurafood

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AlurafoodApplication

fun main(args: Array<String>) {
	runApplication<AlurafoodApplication>(*args)
}
