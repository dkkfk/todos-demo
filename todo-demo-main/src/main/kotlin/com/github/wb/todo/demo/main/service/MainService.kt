package com.github.wb.todo.demo.main.service

import com.github.wb.todo.demo.main.api.DefaultApi
import com.github.wb.todo.demo.main.model.BodyDTO
import com.github.wb.todo.demo.main.model.InlineResponse200DTO
import com.github.wb.todo.demo.main.repository.MainRepository
import com.github.wb.todo.demo.main_2cpo.tables.pojos.TodosPO
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Service
class MainService: DefaultApi {
    @Autowired
    private lateinit var mainRepository: MainRepository
    override fun deleteTodosId(@ApiParam(required = true, value = "") @PathVariable(value = "id") @NotNull id: @NotNull String) {
        mainRepository.deleteTodo(id.toInt())
    }

    override fun postTodos(@ApiParam(value = "") @Valid @NotNull @RequestBody body: @Valid @NotNull BodyDTO): Int? {
        var po = TodosPO()
        po.title = body.title
        po.content = body.content
        po.status = body.status
        po.createdtime = Date().time.toString()
        return mainRepository.addTodo(po)
    }

    override fun putTodosId(@ApiParam(required = true, value = "") @PathVariable(value = "id") @NotNull id: @NotNull String, @ApiParam(value = "") @Valid @RequestParam(required = false, value = "content") content: @Valid String?, @ApiParam(value = "") @Valid @RequestParam(required = false, value = "status") status: @Valid String?) {
        var po = TodosPO()
        po.content = content
        if (status != null) { po.status = status.toInt() }
        mainRepository.updateTodo(id.toInt(),po)
    }

    override fun getTodos(@ApiParam(value = "") @Valid @RequestParam(required = false, value = "title") title: @Valid String?, @ApiParam(value = "") @Valid @RequestParam(required = false, value = "status") status: @Valid Int?, @ApiParam(value = "") @Valid @RequestParam(required = false, value = "createdTime") createdTime: @Valid String?): MutableList<InlineResponse200DTO>? {
       return mainRepository.findTodo(status,createdTime,title)
    }


}
