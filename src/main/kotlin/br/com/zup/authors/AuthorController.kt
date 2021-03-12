package br.com.zup.authors

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("api/v1/authors")
class AuthorController(val entityManager: EntityManager) {

    @Post
    @Transactional
    fun registerAuthor(@Body @Valid request: NewAuthorRequest): HttpResponse<Unit> {
        request.toModel()
            .also { author: Author ->
                entityManager.persist(author)
            }
        return HttpResponse.ok()
    }
}