package by.bsuir.wt3.service;

import by.bsuir.wt3.service.impl.*;

public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private final CaseService caseService = new CaseServiceImpl();
	private final UserService userService = new UserServiceImpl();
	
	private ServiceFactory() {}

	public CaseService getCaseService() {
		return caseService;
	}
	
	public UserService getUserService()
	{
		return userService;
	}

	public static ServiceFactory getInstance() {
		return instance;
	}
}
