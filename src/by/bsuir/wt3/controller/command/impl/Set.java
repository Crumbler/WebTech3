package by.bsuir.wt3.controller.command.impl;

import by.bsuir.wt3.controller.command.Command;
import by.bsuir.wt3.dao.UserStatus;
import by.bsuir.wt3.service.*;

public class Set implements Command {
	private static final char paramDelimiter = '|';
	
	@Override
	public String execute(String request) {
		if (request == null)
		{
			return "Unauthorized request.";
		}
		
		String[] splits = request.split("\\" + paramDelimiter);
		
		String login = splits[0];
		int passwordHash = Integer.parseInt(splits[1]);
		int caseId = Integer.parseInt(splits[2]);
		String caseValue = splits[3];
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		CaseService caseService = factory.getCaseService();
		
		UserStatus userStatus = userService.getUserStatus(login, passwordHash);
		
		if (userStatus == UserStatus.NotFound)
		{
			return "Unauthorized request.";
		}
		
		return caseService.setCase(caseId, caseValue);
	}

}
