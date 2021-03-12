package br.com.zup.categories

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("api/v1/categories")
class CategoryController(private val entityManager: EntityManager) {

    @Post
    @Transactional
    fun registerCategory(@Body @Valid request: NewCategoryRequest): HttpResponse<Unit> {
        request.toModel()
            .also { category: Category ->
                entityManager.persist(category)
            }
        return HttpResponse.ok()
    }
}