package br.com.zup.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [ExistsIdValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ExistsId(
    val message: String = "This value does not exist",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
    val fieldName: String,
    val domainClass: KClass<*>
)