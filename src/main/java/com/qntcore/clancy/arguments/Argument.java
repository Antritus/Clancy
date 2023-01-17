package com.qntcore.clancy.arguments;

@CommandArgument(isSuper = true)
public abstract class Argument<T> {
	protected String argument;
	protected T type;
	protected Class<?> classType;
	public Argument(String argument, Class<?> classType){
		this.argument = argument;
		this.classType = classType;
	}

	public abstract boolean parse();
	public abstract T getParsed();
	public String get(){
		return argument;
	}

	public Class<?> getTypeClass(){
		return classType;
	}
}
