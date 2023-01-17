package com.qntcore.clancy.arguments;

@CommandArgument
public class ArgumentDouble extends Argument<Double> {
	public ArgumentDouble(String argument) {
		super(argument, Double.class);
	}

	@Override
	public boolean parse() {
		try {
			type = Double.parseDouble(argument);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}

	@Override
	public Double getParsed() {
		if (type == null) {
			if (!parse()){
				return null;
			}
		}
		return type;
	}
}
