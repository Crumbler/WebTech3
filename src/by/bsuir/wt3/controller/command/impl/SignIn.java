package by.bsuir.wt3.controller.command.impl;

import by.bsuir.wt3.controller.command.Command;
import by.bsuir.wt3.dao.UserStatus;
import by.bsuir.wt3.service.ServiceFactory;
import by.bsuir.wt3.service.UserService;

public class SignIn implements Command {
	private static final char paramDelimiter = '|';
	
	@Override
	public String execute(String request) {
		if (request == null)
		{
			return "User not found.";
		}
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		int splitIndex = request.indexOf(paramDelimiter);
		
		String login = request.substring(0, splitIndex);
		
		String passwordHashString = request.substring(splitIndex + 1);
		
		int passwordHash;
		try
		{
			passwordHash = Integer.parseInt(passwordHashString);
		}
		catch (NumberFormatException e)
		{
			return "User not found.";
		}
		
		UserStatus userStatus = userService.getUserStatus(login, passwordHash);
		
		if (userStatus == UserStatus.NotFound)
		{
			return "User not found.";
		}
		else
		{
			return "Login successful.";
		}
	}

}
