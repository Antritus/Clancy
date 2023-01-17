package commands.arguments;

import com.qntcore.clancy.Command;
import com.qntcore.clancy.SimpleCommand;
import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.arguments.ArgumentInteger;
import com.qntcore.clancy.arguments.ArgumentString;
import com.qntcore.clancy.entity.Entity;

import java.util.ArrayList;
import java.util.List;

@Command(key = "qntcore", name = "test-command")
public class TestCommand1 extends SimpleCommand {
	public TestCommand1(){
		List<Class<?>> list = new ArrayList<>();
		list.add(ArgumentInteger.class);
		list.add(ArgumentString.class);
		getArguments().put(0, list);
	}
	@Override
	public boolean parse(Entity entity, List<Argument<?>> args) {
		return false;
	}

	@Override
	public void trigger(Entity entity, String name, List<Argument<?>> arguments) {

	}
}
