package com.qntcore.clancy;

import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.entity.Entity;

import java.util.List;


@SuppressWarnings("unused")
public interface CommandExecutor extends ICommandArgument {
	/**
	 * The key of the command. (ex: qencode:command)
	 * @return key of the command
	 */
	QntKey getKey();

	/**
	 * Parses the command when so it will not have errors or such.
	 * @param entity The entity executing
	 * @param args The arguments parsed
	 * @return is right
	 */
	boolean parse(Entity entity, List<Argument<?>> args);

	/**
	 * Triggers the command.
	 * @param entity entity to trigger it
	 * @param arguments arguments of triggering
	 */
	void trigger(Entity entity, List<Argument<?>> arguments);

	/**
	 * Checks if entity can execute the command.
	 * @param entity Entity to check
	 * @return can execute
	 */
	boolean hasPermission(Entity entity);

	/**
	 * Gets the permission of the command null if none is set
	 * @return permission
	 */
	IPermission getPermission();

	/**
	 * @return has unlimited end args
	 */
	boolean hasUnlimitedArguments();

	/**
	 * Returns the argument last argument if not set.
	 * @return class of unlimited args,
	 */
	Class<?> getUnlimitedArguments();
}
