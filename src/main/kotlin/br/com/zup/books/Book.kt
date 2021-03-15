package br.com.zup.books

import br.com.zup.authors.Author
import br.com.zup.categories.Category
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.validation.constraints.*

@Entity
class Book (
    @field:NotBlank val title: String,//
    @field:NotBlank @field:Size(max = 500) val resume: String,
    val summary: String,
    @field:Min(value = 20) @field:NotNull val price: BigDecimal,
    @field:NotNull @field:Min(value = 100) val numPages: Int,
    @field:NotBlank val isbn: String,//
    @field:Future val publishDate: LocalDate,
    @field:NotNull @field:ManyToOne val category: Category,
    @field:NotNull @field:ManyToOne val author: Author
){
    @Id @GeneratedValue
    val id: Long? = null
}
