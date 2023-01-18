package com.qntcore.clancy.tests.commands;

import com.qntcore.clancy.tests.arguments.ArgumentCommand;
import com.qntcore.clancy.Command;
import com.qntcore.clancy.SimpleCommand;
import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.entity.Entity;

import java.util.List;


@Command(key = "qntcore", name ="getkey", category = "admin", usage = "<(prefix)command>")
public class GetKeyCommand extends SimpleCommand {
	public GetKeyCommand(){
		getArguments().put(0, ArgumentCommand.class);
	}
	@Override
	public boolean parse(Entity entity, List<Argument<?>> args) {
		if (args.size() > 2){
			return false;
		}
		if (args.size()== 0 || args.get(0) == null){
			return false;
		}
		return args.get(0) instanceof ArgumentCommand;
	}
	@Override
	public void trigger(Entity entity, List<Argument<?>> arguments) {
		ArgumentCommand arg = (ArgumentCommand) arguments.get(0);
		entity.sendMessage(arg.getParsed().key()+":"+arg.getParsed().name());
	}

	@Override
	public boolean hasUnlimitedArguments() {
		return false;
	}

	@Override
	public Class<?> getUnlimitedArguments() {
		return null;
	}
}
