package br.com.zup.categories

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Category(val name: String?){

    @Id @GeneratedValue
    val id: Long? = null
}