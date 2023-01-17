package com.qntcore.clancy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
	String key();
	String name();
	String prefix() default "/";
	String category() default "";
	String[] aliases() default "";
	String permission() default "";
	String usage() default "";
	int priority() default 0;
}
