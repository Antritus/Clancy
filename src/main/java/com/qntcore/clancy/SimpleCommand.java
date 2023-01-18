package com.qntcore.clancy;

import com.qntcore.clancy.arguments.*;
import com.qntcore.clancy.entity.Entity;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class SimpleCommand implements CommandExecutor {
	HashMap<Integer, Object> args = new HashMap<>();
	QntKey qntKey;
	IPermission permission;
	public SimpleCommand() {
		qntKey = new QntKey(this.getClass().getAnnotation(Command.class).key(), this.getClass().getAnnotation(Command.class).name());
	}
	@Override
	public QntKey getKey() {
		return qntKey;
	}

	@Override
	public HashMap<Integer, Object> getArguments() {
		return args;
	}

	@Override
	public boolean hasPermission(Entity entity) {
		if (permission == null){
			return true;
		}
		return  entity.hasPermission(permission);
	}

	@Override
	public IPermission getPermission() {
		return permission;
	}
}
