package com.qntcore.clancy;

import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.entity.Entity;

import java.util.List;


public interface CommandExecutor extends CommandArguments {
	QntKey getKey();
	boolean parse(Entity entity, List<Argument<?>> args);
	void trigger(Entity entity, String name, List<Argument<?>> arguments);
}
