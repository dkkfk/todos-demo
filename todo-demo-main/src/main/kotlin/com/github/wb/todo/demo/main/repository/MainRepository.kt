package com.github.wb.todo.demo.main.repository

import com.github.wb.todo.demo.main.model.InlineResponse200DTO
import com.github.wb.todo.demo.main_2cpo.tables.pojos.TodosPO
import javax.validation.constraints.NotNull


interface MainRepository {
    fun findTodo(status: Int?, createdTime: String?, title: String?): MutableList<InlineResponse200DTO>?
    fun addTodo(po: TodosPO):Int?
    fun updateTodo(id: Int, po: TodosPO)
    fun deleteTodo(id: Int)
}
