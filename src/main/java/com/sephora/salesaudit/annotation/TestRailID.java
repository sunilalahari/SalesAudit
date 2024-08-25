package com.sephora.salesaudit.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *This is a custom annotation that facilitates to store test case id.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, TYPE })
public @interface TestRailID {

	/**
	 * This allows user to provide a test case id or multiple test case ids
	 */
	public String[] id() default {};

}