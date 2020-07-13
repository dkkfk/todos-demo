package com.github.wb.todo.demo.main

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoDemoMainApplication

fun main(args: Array<String>) {
    runApplication<TodoDemoMainApplication>(*args)
}
