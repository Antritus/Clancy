package arguments;

import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.arguments.CommandArgument;

@CommandArgument(isCustom = false)
public class ExceptionCustomArgument extends Argument<Exception> {
	public ExceptionCustomArgument(String argument) {
		super(argument, Exception.class);
	}

	@Override
	public boolean parse() {
		return false;
	}

	@Override
	public Exception getParsed() {
		return null;
	}
}
