package br.com.alurafood

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class AlurafoodApplication

fun main(args: Array<String>) {
	runApplication<AlurafoodApplication>(*args)
}
