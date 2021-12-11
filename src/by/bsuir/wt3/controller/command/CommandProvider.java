package by.bsuir.wt3.controller.command;

import java.util.Hashtable;

import by.bsuir.wt3.controller.command.impl.*;

public final class CommandProvider {
	private static final Hashtable<CommandName, Command> repository = new Hashtable<>();
	
	CommandProvider()
	{
		repository.put(CommandName.SIGN_IN, new SignIn());
		repository.put(CommandName.GET, new Get());
		repository.put(CommandName.SET, new Set());
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
	}
	
	public static Command getCommand(String name)
	{
		CommandName commandName = null;
		Command command = null;
		
		try
		{
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		}
		catch (IllegalArgumentException | NullPointerException e)
		{
			System.out.println("Failed to get command by name");
			command = repository.get(CommandName.WRONG_REQUEST);
		}
		
		return command;
	}
}
