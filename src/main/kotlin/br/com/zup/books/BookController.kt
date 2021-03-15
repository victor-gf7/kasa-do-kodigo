package br.com.zup.books

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("api/v1/books")
class BookController(private val entityManager: EntityManager){

    @Post
    @Transactional
    fun registerBook(@Body @Valid request: NewBookRequest)  : HttpResponse<Unit> {
        request.toModel(entityManager = entityManager)
            .also { book: Book ->
                entityManager.persist(book)
            }
        return HttpResponse.ok()
    }
}