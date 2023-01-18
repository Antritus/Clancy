package com.qntcore.clancy.tests.commands;

import com.qntcore.clancy.*;
import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.arguments.ArgumentString;
import com.qntcore.clancy.entity.Entity;

import java.util.*;

@Command(key = "qntcore", name = "list", category = "admin", usage = "<categories>")
public class ListCommandsCommand implements CommandExecutor {
	QntKey key;
	CommandManager commandManager;
	Map<Integer, Object> arguments = new HashMap<>();
	public ListCommandsCommand(CommandManager commandManager){
		arguments.put(0, ArgumentString.class);
		key = new QntKey(getClass().getAnnotation(Command.class).key(), getClass().getAnnotation(Command.class).name());
		this.commandManager = commandManager;
	}

	@Override
	public QntKey getKey() {
		return key;
	}

	@Override
	public boolean parse(Entity entity, List<Argument<?>> args) {
		if (args.size() == 1){
			if (args.get(0) instanceof ArgumentString){
				return true;
			}
		} else {
			for (Argument<?> arg : args){
				if (!(arg instanceof ArgumentString)){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void trigger(Entity entity, String name, List<Argument<?>> arguments) {
		boolean found = false;
		List<String> args = new ArrayList<>();
		boolean fillList = true;
		for (Command command : commandManager.commands()) {
			if (arguments.isEmpty()) {
				entity.sendMessage(" H) " + command.prefix() + command.key() + ":" + command.name() + " " + command.usage() + " | " + command.category());
				found = true;
 			} else {
				for (Argument<?> argument : arguments) {
					ArgumentString arg = (ArgumentString) argument;
					if (fillList) {
						args.add(arg.getParsed());
					}
					if (command.category().equalsIgnoreCase(arg.getParsed())) {
						entity.sendMessage(" H) " + command.prefix() + command.key() + ":" + command.name() + " " + command.usage() + " | " + command.category());
						found=true;
					}
				}
				fillList = false;
			}
		}
		if (!found) {
			entity.sendMessage("Could not find commands with categor(y)(ies): " + Arrays.toString(args.toArray()));
		}
	}

	@Override
	public boolean hasPermission(Entity entity) {
		return true;
	}

	@Override
	public IPermission getPermission() {
		return null;
	}

	@Override
	public boolean hasUnlimitedArguments() {
		return true;
	}

	@Override
	public Class<?> getUnlimitedArguments() {
		return ArgumentString.class;
	}

	@Override
	public Map<Integer, Object> getArguments() {
		return arguments;
	}
}
