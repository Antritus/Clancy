package com.qntcore.clancy.arguments;

@CommandArgument
public class ArgumentCharacter extends Argument<Character> {
	public ArgumentCharacter(String argument) {
		super(argument, Character.class);
	}

	@Override
	public boolean parse() {
		if (argument.length() > 1){
			return false;
		}
		try {
			type = argument.toCharArray()[0];
			return true;
		} catch (IndexOutOfBoundsException e){
			return false;
		}
	}

	@Override
	public Character getParsed() {
		if (type == null){
			if (!parse()){
				return null;
			}
		}
		return type;
	}
}
