package commands.exceptions;

import com.qntcore.clancy.Command;
import com.qntcore.clancy.CommandExecutor;
import com.qntcore.clancy.QntKey;
import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.entity.Entity;

import java.util.HashMap;
import java.util.List;


@Command(key = "qntcore", name = "exception-command-anointment-missing")
public class AnointmentMissingCommand implements CommandExecutor, ExceptionCommand {
	@Override
	public QntKey getKey() {
		return null;
	}

	@Override
	public boolean parse(Entity entity, List<Argument<?>> args) {
		return false;
	}

	@Override
	public void trigger(Entity entity, String name, List<Argument<?>> arguments) {

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

	@Override
	public HashMap<Integer, Object> getArguments() {
		return null;
	}
}
