package com.qntcore.clancy.tests.arguments;

import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.arguments.CommandArgument;
import com.qntcore.clancy.entity.Entity;
import com.qntcore.clancy.tests.entities.EntitySuper;

@CommandArgument(isCustom = true)
public class ArgumentEntity extends Argument<Entity> {
	public ArgumentEntity(String argument) {
		super(argument, Entity.class);
	}

	@Override
	public boolean parse() {
		for (Entity entity : EntitySuper.entities){
			if (entity.getName().equalsIgnoreCase(get())){
				type = entity;
				return true;
			}
		}
		type = null;
		return false;
	}

	@Override
	public Entity getParsed() {
		if (type == null){
			if (!parse()){
				return null;
			}
		}
		return type;
	}
}
