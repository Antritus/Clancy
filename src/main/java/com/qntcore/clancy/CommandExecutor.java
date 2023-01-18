package com.qntcore.clancy;

import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.entity.Entity;

import java.util.List;


public interface CommandExecutor extends ICommandArgument {
	QntKey getKey();
	boolean parse(Entity entity, List<Argument<?>> args);
	void trigger(Entity entity, String name, List<Argument<?>> arguments);
	boolean hasPermission(Entity entity);
	IPermission getPermission();

	boolean hasUnlimitedArguments();
	Class<?> getUnlimitedArguments();
}
