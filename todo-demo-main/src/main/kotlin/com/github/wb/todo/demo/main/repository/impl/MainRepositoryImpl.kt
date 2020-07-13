package com.github.wb.todo.demo.main.repository.impl

import com.github.wb.todo.demo.main.model.InlineResponse200DTO
import org.jooq.DSLContext
import org.jooq.Field
import org.jooq.impl.DSL
import com.github.wb.todo.demo.main.repository.MainRepository
import com.github.wb.todo.demo.main_2cpo.tables.TodosTable.T_TODOS
import com.github.wb.todo.demo.main_2cpo.tables.pojos.TodosPO
import me.jinuo.imf.hela.support.jooq.setIfNotNull
import me.jinuo.imf.hela.support.jooq.where
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.validation.constraints.NotNull

@Repository
class MainRepositoryImpl
@Autowired
constructor(private val dsl: DSLContext) : MainRepository {
    override fun findTodo(status: Int?, createdTime: String?, title: String?): MutableList<InlineResponse200DTO>? {
        return dsl.select()
                .from(T_TODOS)
                .where{
                    andIf(false,T_TODOS.STATUS.eq(status))
                    andIf(false, T_TODOS.CREATEDTIME.eq(createdTime))
                    andIf(false, T_TODOS.TITLE.eq(title))
                }
                .fetchInto(InlineResponse200DTO::class.java)
    }

    override fun addTodo(po: TodosPO):Int {
        return dsl.insertInto(T_TODOS)
                .columns(
                       T_TODOS.TITLE,
                        T_TODOS.STATUS,
                        T_TODOS.CONTENT,
                        T_TODOS.CREATEDTIME
                ).values(
                        po.title,
                        po.status,
                        po.content,
                        po.createdtime
                ).returning(T_TODOS.ID)
                .fetchOne().id
    }

    override fun updateTodo(id: Int, po: TodosPO) {
        dsl.update(T_TODOS)
                .setIfNotNull(T_TODOS.STATUS, po.status)
                .setIfNotNull(T_TODOS.CONTENT, po.content)
                .where(T_TODOS.ID.eq(id))
                .execute()
    }

    override fun deleteTodo(id: Int) {
        dsl.delete(T_TODOS).where(T_TODOS.ID.eq(id)).execute()
    }
}

