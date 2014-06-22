package com.snt.hr.web.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.ictact.webproceedings.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Documented
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueField {

	String methodName();

	Class<? extends JpaRepository<? extends BaseEntity, Long>> repositoryClass();

}
