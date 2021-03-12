package br.com.zup.authors

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Author(
    val name: String?,
    val email: String?,
    val description: String?
) {
    @Id @GeneratedValue
    var id: Long? = null

    val createdIn: LocalDateTime = LocalDateTime.now()
}
