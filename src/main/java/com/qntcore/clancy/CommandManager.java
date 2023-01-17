package com.qntcore.clancy;

import com.qntcore.clancy.arguments.*;
import com.qntcore.clancy.entity.Entity;
import com.qntcore.clancy.exceptions.*;
import com.qntcore.clancy.exceptions.arguments.register.ArgumentParseAbstractClassException;
import com.qntcore.clancy.exceptions.arguments.ArgumentParseConstructorException;
import com.qntcore.clancy.exceptions.arguments.ArgumentParseException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CommandManager {
	private List<Command> commands = new ArrayList<>();
	private List<String> categories = new ArrayList<>();
	private List<Class<?>> arguments = new ArrayList<>();
	private HashMap<Command, CommandExecutor> commandExecutors = new HashMap<>();

	Comparator<Command> SortLowestHighest = new Comparator<Command>() {
		public int compare(Command cmd1, Command cmd2) {
			return cmd1.priority() - cmd2.priority();
		}
	};

	{
		try {
			registerArgument(ArgumentCharacter.class);
			registerArgument(ArgumentDouble.class);
			registerArgument(ArgumentFloat.class);
			registerArgument(ArgumentInteger.class);
			registerArgument(ArgumentLong.class);
			registerArgument(ArgumentString.class);
		} catch (ArgumentParseConstructorException e) {
			throw new RuntimeException(e);
		} catch (ArgumentParseAbstractClassException e) {
			throw new RuntimeException(e);
		}
	}

	public void register(SimpleCommand... commands) throws CommandAlreadyRegisteredException, SimpleCommandNullException, CommandAnointmentMissingException {
		for (SimpleCommand simpleCommand : commands) {
			register(simpleCommand);
		}
	}

	public void register(SimpleCommand command) throws CommandAlreadyRegisteredException, CommandAnointmentMissingException, SimpleCommandNullException {
		if (command == null) {
			throw new SimpleCommandNullException();
		}
		if (command.getClass().getAnnotation(Command.class) == null) {
			if (command.getClass().getSuperclass().getAnnotation(Command.class) != null) {
				throw new SimpleCommandNullException(true);
			} else {
				throw new CommandAnointmentMissingException(command.getClass());
			}
		}
		Command anCommand = command.getClass().getAnnotation(Command.class);
		boolean foundCategory = false;
		for (Command command1 : commands) {
			if ((command1.key()).equalsIgnoreCase(anCommand.key()) && command1.name().equalsIgnoreCase(anCommand.name())) {
				throw new CommandAlreadyRegisteredException(anCommand);
			}
			if (command1.category().equalsIgnoreCase(anCommand.category())) {
				foundCategory = true;
			}
		}
		if (!foundCategory) {
			categories.add(anCommand.category());
		}
		commands.add(anCommand);
		commandExecutors.put(anCommand, command);
	}

	public void register(Command command, CommandExecutor commandExecutor) throws CommandAlreadyRegisteredException, CommandAnointmentMissingException {
		if (command == null) {
			throw new CommandAnointmentMissingException(null);
		}
		boolean foundCategory = false;
		for (Command command1 : commands) {
			if ((command1.key()).equalsIgnoreCase(command.key()) && command1.name().equalsIgnoreCase(command.name())) {
				throw new CommandAlreadyRegisteredException(command);
			}
			if (command1.category().equalsIgnoreCase(command.category())) {
				foundCategory = true;
			}
		}
		if (!foundCategory) {
			categories.add(command.category());
		}
		commands.add(command);
		commandExecutors.put(command, commandExecutor);
	}
	public void registerArgument(@NotNull Class<?> argument) throws ArgumentParseConstructorException, ArgumentParseAbstractClassException {
		if (argument.getAnnotation(CommandArgument.class) == null){
			return;
		}
		try {
			Constructor<?> constructor = argument.getConstructor(String.class);
			try {
				Object object = constructor.newInstance("class");
				if (object instanceof Argument<?>){
					for (Class<?> clazz : arguments){
						if (clazz.toString().equalsIgnoreCase(argument.toString())){
							throw new ArgumentParseException(argument, "the argument has been already registered!");
						}
					}
					arguments.add(argument);
				}
			} catch (InvocationTargetException e) {
				throw new ArgumentParseException(argument, "the constructor threw an exception!");
			} catch (InstantiationException e) {
				throw new ArgumentParseAbstractClassException(argument);
 			} catch (IllegalAccessException e) {
				throw new ArgumentParseException(argument, "the constructor is not public!");
			}
		} catch (NoSuchMethodException | ArgumentParseException e) {
			throw new ArgumentParseConstructorException(argument);
		}
	}

	public List<Command> commands() {
		return commands;
	}

	public HashMap<Command, CommandExecutor> executors() {
		return commandExecutors;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void execute(Entity entity, Command command, List<Argument<?>> args) throws CommandNotFoundException {
		if (command == null) {
			throw new CommandNotFoundException((QntKey) null);
		}
		CommandExecutor commandExecutor = commandExecutors.get(command);
		if (!commandExecutor.parse(entity, args)) {
			return;
		}
		commandExecutor.trigger(entity, command.name(), args);
	}

	public List<Command> getCommands(String... categories) throws CommandNotFoundException {
		if (categories.length == 0) {
			return commands();
		} else {
			List<Command> commands = new ArrayList<>();
			for (Command command : commands()) {
				if (!command.category().equalsIgnoreCase("")) {
					for (String category : categories) {
						if (command.category().equalsIgnoreCase(category)) {
							commands.add(command);
							break;
						}
					}
				}
			}
			if (commands.size() == 0) {
				throw new CommandNotFoundException("categories", categories);
			}
			return commands;
		}
	}

	public List<Command> getCommandsByKey(String... keys) throws CommandNotFoundException {
		List<Command> commands = new ArrayList<>();
		for (Command command : commands()) {
			for (String key : keys) {
				if (command.key().equalsIgnoreCase(key)) {
					commands.add(command);
				}
			}
		}
		if (commands.size() == 0) {
			throw new CommandNotFoundException("keys", keys);
		}
		return commands;
	}

	public Command getCommand(QntKey key) throws CommandNotFoundException {
		for (Command command : commands()) {
			if (command.key().equalsIgnoreCase(key.getKey())) {
				if (command.name().equalsIgnoreCase(key.getName())) {
					return command;
				}
			}
		}
		throw new CommandNotFoundException(key);
	}

	public String getCommandInfo(Command command) {
		if (command == null) {
			return null;
		}
		String aliases = "\"aliases\":\"$none\"";
		if (command.aliases().length > 1) {
			aliases = "\"aliases\":" + Arrays.toString(command.aliases());
		}
		return "{\"key\":{\"key\":\"" + command.key() + "\",\"name\":\"" + command.name() + "\"}," + "\"category\":\"" + command.category() + "\",\"permission\":\"" + command.permission() + "\",\"usage\":\"" + command.usage() + "\"," + aliases + ",\"prefix\":\"" + command.prefix() + "\",\"priority\":" + command.priority() + "}";
	}


	public @Nullable Command parseCommand(@NotNull String command) {
		List<Command> commands = new ArrayList<>();
		for (Command cmd : commands()) {
			String prefix = cmd.prefix();
			if (!command.startsWith(prefix)) {
				continue;
			}
			// /qntcore:command
			// /command
			String command2 = cmd.prefix() + cmd.key() + ":" + cmd.name();
			if (!command.equalsIgnoreCase(command2)) {
				command2 = cmd.prefix() + cmd.name();
				if (!command.equalsIgnoreCase(command2)) {
					continue;
				}
			}
			commands.add(cmd);
		}
		if (commands.isEmpty()) {
			return null;
		}
		if (commands.size() > 1) {
			commands.sort(SortLowestHighest);
		}
		if (commands.get(commands.size() -1) == null){
			return null;
		}
		return commands.get(commands.size() - 1);
	}

	public List<Argument<?>> parseArgument(Entity entity, Command command, List<String> inArgs) throws ArgumentParseException {
		CommandArguments commandArguments = commandExecutors.get(command);
		HashMap<Integer, Argument<?>> outArgs = new HashMap<>();
		for (int i = 0; i < inArgs.size(); i++) {
			if (i > 0){
				outArgs.putIfAbsent(i - 1, null);
			}
			if (commandArguments.getArguments().get(i) instanceof Map<?, ?>){
				for (Object key : ((Map<?, ?>) commandArguments.getArguments().get(i)).keySet()){
					outArgs.put(i, parseArgument(entity, command, outArgs, i, inArgs.get(i), ((Map<?, ?>) commandArguments.getArguments().get(i)).get(key)));
					if (outArgs.get(i) == null){
						continue;
					}
					break;
				}
			} else if (commandArguments.getArguments().get(i) instanceof List<?>){
				for (Object obj : (List<?>) commandArguments.getArguments().get(i)){
					outArgs.put(i, parseArgument(entity, command, outArgs, i, inArgs.get(i), obj));
					if (outArgs.get(i) == null){
						continue;
					}
					break;
				}
			} else if (commandArguments.getArguments().get(i) instanceof Class<?>){
				outArgs.put(i, parseArgument(entity, command, outArgs, i, inArgs.get(i), commandArguments.getArguments().get(i)));
				if (outArgs.get(i) == null){
					continue;
				}
			} else if (commandArguments.getArguments().get(i) instanceof Argument<?>){
			} else {
			}
		}
		return new ArrayList<>(outArgs.values());
	}
	private @Nullable Argument<?> parseArgument(Entity entity, Command command, HashMap<Integer, Argument<?>> outArgs, int i, String inArg, Object obj) throws ArgumentParseException {
		if (obj instanceof Class<?>){
			for (Class<?> clazz : arguments) {
				try {
					Object object = ((Class<?>) obj).getConstructor(String.class).newInstance(inArg);
					Object objectArguments = clazz.getConstructor(String.class).newInstance(inArg);
					if (object instanceof Argument<?>) {
						if ((((Argument<?>)objectArguments).getTypeClass()) != (((Argument<?>)object).getTypeClass())) {
							continue;
						}
						if (((Argument<?>) object).parse()) {
							List<Argument<?>> args = new ArrayList<>();
							for (Argument<?> argument : outArgs.values()) {
								if (argument != null) {
									args.add(argument);
								}
							}
							args.add((Argument<?>) object);
							if (commandExecutors.get(command).parse(entity, args)) {
								if (i > 0){
									CommandExecutor executor = (CommandExecutor) commandExecutors.get(command);
									HashMap<Integer, Object> map = executor.getArguments();
									if (map.get(i-1) instanceof List<?>){
										for (Object objectSuper : (List<?>) map.get(i-1)){
											if (objectSuper instanceof Class<?>){
												return (Argument<?>) object;
											}
										}
									} else if (map.get(i-1) instanceof Map<?, ?>){
										for (Object key : ((Map<?, ?>) map.get(i-1)).keySet().toArray()){
											if (key instanceof Class<?>){
												if (((Class<?>) ((Map<?, ?>) map).get(key)).toString().equalsIgnoreCase(outArgs.get(i-1).getClass().toString())){
													return (Argument<?>) object;
												}
											} else {
												throw new ClassCastException();
											}
										}
									} else if (map.get(i-1) instanceof Class<?>){
										return (Argument<?>) object;
									} else {
										throw new ClassCastException();
									}
								}
								return (Argument<?>) object;
							}
						}
					} else {
						throw new ArgumentParseException((Class<?>) obj, "class is not instance of Argument<?>");
					}
				} catch (InvocationTargetException e) {
					throw new ArgumentParseException((Class<?>) obj, "the constructor threw an exception!");
				} catch (InstantiationException e) {
					throw new ArgumentParseAbstractClassException((Class<?>) obj);
				} catch (IllegalAccessException e) {
					throw new ArgumentParseException((Class<?>) obj, "the constructor is not public!");
				} catch (NoSuchMethodException e) {
					throw new ArgumentParseConstructorException(clazz);
				}
			}
		} else {
			throw new ClassCastException();
		}
		return null;
	}

}