package com.qntcore.clancy.tests.commands;

import com.qntcore.clancy.Command;
import com.qntcore.clancy.SimpleCommand;
import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.arguments.ArgumentInteger;
import com.qntcore.clancy.arguments.ArgumentString;
import com.qntcore.clancy.tests.arguments.ArgumentEntity;
import com.qntcore.clancy.entity.Entity;

import java.util.ArrayList;

import java.util.List;

@Command(key = "qntcore", name="test", category = "random", usage = "<entity> [integer|entity] [entity|string]")
public class ArgumentCommand extends SimpleCommand {
	public ArgumentCommand(){
		super();
		List<Object> newArgs = new ArrayList<>();
		newArgs.add(ArgumentEntity.class);
		getArguments().put(0, newArgs);
		getArguments().put(1, ArgumentInteger.class);
		newArgs = new ArrayList<>();
		newArgs.add(ArgumentEntity.class);
		newArgs.add(ArgumentString.class);
		getArguments().put(2, newArgs);
	}

	@Override
	public boolean parse(Entity entity, List<Argument<?>> args) {
		if (args.size() == 0){
			return true;
		}
		if (args.get(0) instanceof ArgumentEntity) {
			if (args.size() == 1) {
				return true;
			} else {
				if (args.get(1) instanceof ArgumentInteger) {
					return true;
				} else {
					if (args.size() == 2) {
						return args.get(1) instanceof ArgumentEntity;
					} else {
						if (args.get(2) instanceof ArgumentEntity){
							return true;
						} else return args.get(2) instanceof ArgumentString;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void trigger(Entity entity, List<Argument<?>> arguments) {
		System.out.println("Idk do something!");
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
