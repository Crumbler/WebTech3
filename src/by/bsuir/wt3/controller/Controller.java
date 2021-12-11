package by.bsuir.wt3.controller;

import by.bsuir.wt3.controller.command.Command;
import by.bsuir.wt3.controller.command.CommandProvider;
import by.bsuir.wt3.controller.command.impl.WrongRequest;

public class Controller {
	private static final char paramDelimiter = ' ';
	private static final Command wrongRequest = new WrongRequest();
	
	public static String executeTask(String request)
	{
		System.out.println("Received request " + request);
		
		int splitIndex = request.indexOf(paramDelimiter);
		
		if (splitIndex == -1)
		{
			System.out.println("No command specified");
			
			return wrongRequest.execute(null);
		}
		
		String commandName = request.substring(0, splitIndex);
		commandName = commandName.toUpperCase();
		System.out.println("Command name: " + commandName);
		
		Command command = CommandProvider.getCommand(commandName);
		
		if (command == null)
		{
			System.out.println("Command not found");
			return wrongRequest.execute(null);
		}
		
		System.out.println("Command: " + command);
		
		String actualRequest = request.substring(splitIndex + 1);
		
		return command.execute(actualRequest);
	}
}
