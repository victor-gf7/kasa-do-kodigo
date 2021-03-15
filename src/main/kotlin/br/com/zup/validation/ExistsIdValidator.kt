package br.com.zup.validation

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.transaction.annotation.TransactionalAdvice
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.persistence.EntityManager

@Singleton
@TransactionalAdvice
class ExistsIdValidator(private val entityManager: EntityManager) : ConstraintValidator<ExistsId, Any> {
    override fun isValid(
        value: Any?,
        annotationMetadata: AnnotationValue<ExistsId>,
        context: ConstraintValidatorContext
    ): Boolean {
        if (value == null)
            return true
        val domainAttribute = annotationMetadata.values.getValue("fieldName")
        val klass = annotationMetadata.values.getValue("domainClass")
        return entityManager
            .createQuery("select 1 from $klass where $domainAttribute = :value")
            .setParameter("value", value)
            .resultList
            .isNotEmpty()
    }
}