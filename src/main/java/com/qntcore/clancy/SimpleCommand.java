package com.qntcore.clancy;

import com.qntcore.clancy.arguments.*;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Command(key = "qntcore", name = "simple_command", prefix = "$")
public abstract class SimpleCommand implements CommandExecutor {
	HashMap<Integer, Object> args = new HashMap<>();
	QntKey qntKey;
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

}
