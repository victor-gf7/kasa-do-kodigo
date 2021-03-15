package br.com.zup.books

import br.com.zup.authors.Author
import br.com.zup.categories.Category
import br.com.zup.validation.ExistsId
import br.com.zup.validation.UniqueValue
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.DateCreated
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.validation.constraints.*

@Introspected
data class NewBookRequest(
    @field:NotBlank @field:UniqueValue(
        domainClass = Book::class,
        fieldName = "title"
    ) val title: String,
    @field:NotBlank @field:Size(max = 500) val resume: String,
    private val summary: String,
    @field:Min(value = 20) @field:NotNull val price: BigDecimal,
    @field:NotNull @field:Min(value = 100) val numPages: Int,
    @field:NotBlank @UniqueValue(
        domainClass = Book::class,
        fieldName = "isbn"
    ) val isbn: String,
    @field:Future @field:DateCreated val publishDate: LocalDate,
    @field:NotNull @field:ExistsId(
        domainClass = Category::class,
        fieldName = "id"
    ) val categoryId: Long,
    @field:NotNull @field:ExistsId(
        domainClass = Author::class,
        fieldName = "id"
    ) val authorId: Long
) {
    fun toModel(entityManager: EntityManager): Book {
        val category: Category = entityManager.find(Category::class.java, categoryId)
        val author: Author = entityManager.find(Author::class.java, authorId)

        return Book(
            title = title,
            resume = resume,
            summary = summary,
            price = price,
            numPages = numPages,
            isbn = isbn,
            publishDate = publishDate,
            category = category,
            author = author
        )
    }
}
