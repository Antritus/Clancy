package commands.exceptions;

import com.qntcore.clancy.Command;
import com.qntcore.clancy.SimpleCommand;
import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.entity.Entity;

import java.util.List;

@Command(key = "qntcore", name = "exception-command-registered")
public class AlreadyRegisterCommand extends SimpleCommand implements ExceptionCommand{
	@Override
	public boolean parse(Entity entity, List<Argument<?>> args) {
		return false;
	}

	@Override
	public void trigger(Entity entity, String name, List<Argument<?>> arguments) {
		System.out.println("Huh??");
	}


	private boolean enabled;
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(boolean v) {
		enabled = v;
	}
}
