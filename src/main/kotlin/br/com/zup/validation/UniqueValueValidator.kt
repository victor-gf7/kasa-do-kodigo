package br.com.zup.validation


import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.transaction.annotation.TransactionalAdvice
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.persistence.EntityManager

@Singleton
@TransactionalAdvice
class UniqueValueValidator(private val entityManager: EntityManager) : ConstraintValidator<UniqueValue, Any> {

    override fun isValid(
        value: Any?,
        annotationMetadata: AnnotationValue<UniqueValue>,
        context: ConstraintValidatorContext
    ): Boolean {
        val domainAttribute = annotationMetadata.values.getValue("fieldName")
        val klass = annotationMetadata.values.getValue("domainClass")
        return entityManager
            .createQuery("select 1 from $klass where $domainAttribute = '$value'")
            .resultList
            .isEmpty()
    }
}
