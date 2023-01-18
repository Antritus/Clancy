package com.qntcore.clancy.tests.arguments;

import com.qntcore.clancy.Command;
import com.qntcore.clancy.CommandManager;
import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.arguments.CommandArgument;
import com.qntcore.clancy.tests.Main;

@CommandArgument(isCustom = true)
public class ArgumentCommand extends Argument<Command> {
	CommandManager manager = Main.getInstance();
	public ArgumentCommand(String argument) {
		super(argument, Command.class);
	}

	@Override
	public boolean parse() {
		type = manager.parseCommand(argument, true);
		System.out.println(type);
		return type != null;
	}

	@Override
	public Command getParsed() {
		if (type == null){
			if (!parse()){
				return null;
			}
		}
		return type;
	}
}
