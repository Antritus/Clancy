package com.qntcore.clancy.commands;

import com.qntcore.clancy.Command;
import com.qntcore.clancy.SimpleCommand;
import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.entity.Entity;

import java.util.List;

@Command(key = "qntcore", name = "example")
public class CommandExample extends SimpleCommand {
	@Override
	public boolean parse(Entity entity, List<Argument<?>> args) {
		return false;
	}

	@Override
	public void trigger(Entity entity, String name, List<Argument<?>> arguments) {
		System.out.println("Exampled!");
	}
}
