package com.qntcore.clancy.tests;

import com.qntcore.clancy.Command;
import com.qntcore.clancy.CommandExecutor;
import com.qntcore.clancy.CommandManager;
import com.qntcore.clancy.SimpleCommand;
import com.qntcore.clancy.arguments.Argument;
import com.qntcore.clancy.exceptions.CommandAlreadyRegisteredException;
import com.qntcore.clancy.exceptions.CommandAnointmentMissingException;
import com.qntcore.clancy.exceptions.CommandNotFoundException;
import com.qntcore.clancy.exceptions.SimpleCommandNullException;
import com.qntcore.clancy.exceptions.arguments.ArgumentParseConstructorException;
import com.qntcore.clancy.exceptions.arguments.ArgumentParseException;
import com.qntcore.clancy.exceptions.arguments.register.ArgumentParseAbstractClassException;
import com.qntcore.clancy.tests.arguments.ArgumentEntity;
import com.qntcore.clancy.tests.commands.ArgumentCommand;
import com.qntcore.clancy.tests.commands.GetKeyCommand;
import com.qntcore.clancy.tests.commands.ListCommandsCommand;
import com.qntcore.clancy.tests.entities.Console;
import com.qntcore.clancy.tests.entities.EntitySuper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main extends CommandManager{
	private static Main instance;
	public static Main getInstance(){
		return instance;
	}

	private void registerCommands(){
		try {
			SimpleCommand argumentCommand = new ArgumentCommand();
			register(argumentCommand);
			SimpleCommand getKeyCommand = new GetKeyCommand();
			register(getKeyCommand);
			CommandExecutor listCommand = new ListCommandsCommand(this);
			register(listCommand.getClass().getAnnotation(Command.class), listCommand);
		} catch (CommandAlreadyRegisteredException | CommandAnointmentMissingException | SimpleCommandNullException e) {
			throw new RuntimeException(e);
		}
	}
	private void registerArguments(){
		try {
			registerArgument(ArgumentEntity.class);
			registerArgument(com.qntcore.clancy.tests.arguments.ArgumentCommand.class);
		} catch (ArgumentParseConstructorException | ArgumentParseAbstractClassException e) {
			throw new RuntimeException(e);
		}
	}
	private void registerEntities(){
		Console console =new Console();
		EntitySuper.entities.add(console);
	}
	public Main() throws CommandNotFoundException, ArgumentParseException {
		instance = this;
		registerEntities();
		registerCommands();
		registerArguments();
		Scanner scanner = new Scanner(System.in);
		String next;
		Command command;
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
				String[] split = next.split(" ");
				if (next.split(" ").length == 2){
					if (next.split(" ")[1] != null){
						args.add(next.split(" ")[1]);
					}
				}else {
					for (int i = 1; i < split.length-1; i++){
						if (split[i] != null){
							args.add(split[i]);
						}
					}
				}
			}
			if (cont) {
				List<Argument<?>> arguments = parseArgument(EntitySuper.entities.get(0), command, args);
				if (executors().get(command).parse(EntitySuper.entities.get(0), arguments)) {
					System.out.println(" > " + next);
					execute(EntitySuper.entities.get(0), command, arguments);
				} else {
					System.out.println(" Parse exception!");
					System.out.println(" > " + next);
				}
			} else {
				System.out.println(" Unknown Command!");
				System.out.println(" > " + next);
			}
		}
	}
	public static void main(String[] args) throws CommandNotFoundException, ArgumentParseException {
		new Main();
	}
}
