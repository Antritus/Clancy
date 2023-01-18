package com.qntcore.clancy.arguments;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE})
public @interface CommandArgument {
	/**
	 * Used only in Argument<?>
	 * @see Argument
	 * @return is Super of arguments
	 */
	boolean isSuper() default false;
	/**
	 * Used in childs of Argument<?>
	 * @see Argument
	 * @return is custom child of arguments
	 */
	boolean isCustom() default false;
}
