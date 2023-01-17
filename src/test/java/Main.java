import com.qntcore.clancy.Command;
import com.qntcore.clancy.CommandManager;
import com.qntcore.clancy.SimpleCommand;
import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.arguments.custom.ArgumentEntity;
import com.qntcore.clancy.entity.*;
import com.qntcore.clancy.exceptions.CommandAlreadyRegisteredException;
import com.qntcore.clancy.exceptions.CommandAnointmentMissingException;
import com.qntcore.clancy.exceptions.CommandNotFoundException;
import com.qntcore.clancy.exceptions.SimpleCommandNullException;
import com.qntcore.clancy.exceptions.arguments.ArgumentParseException;
import commands.arguments.ArgumentCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main extends CommandManager{
	boolean testCommands = false, exceptionCommands = false, argumentTest = true;
	public Main() throws CommandAlreadyRegisteredException, CommandNotFoundException, CommandAnointmentMissingException, SimpleCommandNullException, ArgumentParseException {
		registerArgument(ArgumentEntity.class);
		SimpleCommand argumentCommand = new ArgumentCommand();
		register(argumentCommand);
		Scanner scanner = new Scanner(System.in);
		Console console =new Console();
		EntitySuper.entities.add(console);
		String next;
		Command command = null;
		while (true){
			next = scanner.nextLine();
			boolean cont = true;
			if (next.contains(" ")) {
				command = parseCommand(next.split(" ")[0]);
			}
			else {
				command = parseCommand(next);
			}
			if (command == null){
				cont = false;
			}
			List<String> args = new ArrayList<>();
			if (next.contains(" ")){
				if (next.split(" ").length == 2){
					args.add(next.split(" ")[1]);
				}else {
					args.addAll(Arrays.asList(next.split(" ")).subList(1, next.split(" ").length - 1));
				}
			}
			if (cont) {
				List<Argument<?>> arguments = parseArgument(console, command, args);
				if (executors().get(command).parse(console, arguments)) {
					System.out.println(" > " + next);
					executors().get(command).trigger(console, null, arguments);
				} else {
					System.out.println(arguments);
					System.out.println(" Parse exception!");
					System.out.println(" > " + next);
				}
			} else {
				System.out.println(" Unknown Command!");
				System.out.println(" > " + next);
			}
		}
	}




	public static void main(String[] args) throws CommandAlreadyRegisteredException, SimpleCommandNullException, CommandAnointmentMissingException, CommandNotFoundException, ArgumentParseException {
		new Main();
	}
}
