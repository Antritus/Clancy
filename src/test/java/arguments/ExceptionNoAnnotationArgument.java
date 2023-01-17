package arguments;

import com.qntcore.clancy.arguments.Argument;

public class ExceptionNoAnnotationArgument extends Argument<Exception> {

	public ExceptionNoAnnotationArgument(String argument) {
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
