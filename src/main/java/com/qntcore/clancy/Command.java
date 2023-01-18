package com.qntcore.clancy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
	/**
	 * The key of the command (ex: qntcore)
	 * @default null
	 * @return name of the key
	 */
	String key();
	/**
	 * The name of the command (ex: debug)
	 * @default null
	 * @return name of the key
	 */
	String name();
	/**
	 * The prefix of the command (ex: /)
	 * @default /
	 * @return prefix of the command
	 */
	String prefix() default "/";

	/**
	 * The category of the command (ex: staff)
	 * @default ""
	 * @return category if the command
	 */
	String category() default "";
	/**
	 * The aliases of the command (ex: staff)
	 * @default [""]
	 * @return aliases of the command
	 */
	String[] aliases() default "";

	/**
	 * The permission to use the command (ex: qntcore.debug)
	 * @default ""
	 * @return permission of the command
	 */
	String permission() default "";

	/**
	 * The usage of the command. (ex: )
	 * This is used as following /command ((usage) <integer>)
	 * @default ""
	 * @return the usage of command
	 */
	String usage() default "";

	/**
	 * The priority of the command when parsing command. (ex: 0)
	 * The command priority only is used when there is multiple command with same name,
	 * but with different key. Priority chooses the command with most priority to be the winner.
	 * @default 0
	 * @return priority of the command
	 */
	int priority() default 0;
}
