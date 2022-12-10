package web.mutbrocha.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import web.mutbrocha.validation.util.AttributesRelation;
import web.mutbrocha.validation.validator.BigDecimalAttributesRelationValidator;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BigDecimalAttributesRelationValidator.class)
@Documented
public @interface BigDecimalAttributesRelation {

	String attribute1();

	String attribute2();

	AttributesRelation relation();

	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		BigDecimalAttributesRelation[] value();
	}

}
