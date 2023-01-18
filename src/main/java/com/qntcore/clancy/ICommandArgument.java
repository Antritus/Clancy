package com.qntcore.clancy;
import java.util.Map;

public interface ICommandArgument {
	/**
	 * @return arguments of command executor
	 */
	Map<Integer, Object> getArguments();
}
