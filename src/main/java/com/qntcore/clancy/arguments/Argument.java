package com.qntcore.clancy.arguments;

import org.jetbrains.annotations.Nullable;

@CommandArgument(isSuper = true)
public abstract class Argument<T> {
	protected String argument;
	protected T type;
	protected Class<?> classType;

	/**
	 * A command argument which is used as the base of argument parsing.
	 * A command argument super must only have (Your Type).class as the constructor.
	 * Custom command arguments must use @CommandArgument(isCustom = true) to make sure the future versions do not break.
	 * @param argument Command argument (must be only object in child constructor)
	 * @param classType The class of Object extended with Argument<>
	 * @see com.qntcore.clancy.arguments
	 */
	public Argument(String argument, Class<?> classType){
		this.argument = argument;
		this.classType = classType;
	}


	/**
	 * Parses command argument and checks if it is the right format.
	 * Command argument parse is used in CommandManager and getParsed()
	 * @see com.qntcore.clancy.CommandManager
	 * @return is right format
	 */
	public abstract boolean parse();

	/**
	 * Parses command argument and returns null or type of T.
	 * Used in command argument parser
	 * @see com.qntcore.clancy.CommandManager
	 * @see com.qntcore.clancy.SimpleCommand
	 * @see com.qntcore.clancy.CommandExecutor
	 * @return Returns the parsed type or null
	 */
	public @Nullable T getParsed() {
		if (type == null){
			if (!parse()){
				return null;
			}
		}
		return type;
	}

	/**
	 * Returns the given argument in the constructor.
	 * @return argument given
	 */
	public String get(){
		return argument;
	}

	/**
	 * Returns the class of T if child class
	 * Used in command argument parser
	 * @return classType
	 */
	public Class<?> getTypeClass(){
		return classType;
	}
}
