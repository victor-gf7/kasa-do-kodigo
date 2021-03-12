package br.com.zup.validation


import javax.persistence.EntityManager
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import kotlin.reflect.KClass

class UniqueValueValidator(private val entityManager: EntityManager) : ConstraintValidator<UniqueValue, Any> {

    lateinit var domainAttribute: String
    lateinit var klass: KClass<*>

    override fun initialize(constraintAnnotation: UniqueValue?) {
        println("Initialize")
        domainAttribute = constraintAnnotation!!.fieldName
        klass = constraintAnnotation.domainClass
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        println("select 1 from ${klass.qualifiedName} where $domainAttribute = $value")
        return entityManager
            .createQuery("select 1 from ${klass.qualifiedName} where $domainAttribute = $value")
            .resultList
            .isEmpty()
    }
}