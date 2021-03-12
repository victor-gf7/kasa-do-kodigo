package br.com.zup.authors

import br.com.zup.validation.UniqueValue
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NewAuthorRequest(
    @field:NotBlank val name: String,
    @field:NotBlank @field:Email @field:UniqueValue(
        domainClass = Author::class,
        fieldName = "email"
    ) val email: String,
    @field:NotBlank @field:Size(max = 400) val description: String
) {
    fun toModel(): Author {
        return Author(name, email, description)
    }
}
