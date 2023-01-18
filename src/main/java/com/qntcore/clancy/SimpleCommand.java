package com.qntcore.clancy;

import com.qntcore.clancy.entity.Entity;
import com.qntcore.clancy.entity.Permissionable;

import java.util.HashMap;

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
		if (entity instanceof Permissionable){
			return ((Permissionable) entity).hasPermission(permission);
		}
		return true;
	}

	@Override
	public IPermission getPermission() {
		return permission;
	}
}
