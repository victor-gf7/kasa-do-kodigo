package br.com.zup.categories

import br.com.zup.validation.UniqueValue
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class NewCategoryRequest(@field:NotBlank @UniqueValue(
    domainClass = Category::class,
    fieldName = "name"
) val name: String) {
    fun toModel(): Category {
        return Category(name = name)
    }
}
