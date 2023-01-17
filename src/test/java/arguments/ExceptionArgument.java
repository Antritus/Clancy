package arguments;

import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.arguments.CommandArgument;

@CommandArgument(isSuper = true)
public class ExceptionArgument extends Argument<Exception> {
	public ExceptionArgument(String argument) {
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
