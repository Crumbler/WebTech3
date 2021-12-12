package by.bsuir.wt3.dao;

import by.bsuir.wt3.dao.impl.*;

public final class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private final UserDAO userDAO = 
			new UserDAOimpl("src/by/bsuir/wt3/main/resources/user_db.xml");
	
	private final CaseDAO caseDAO =
			new CaseDAOimpl("src/by/bsuir/wt3/main/resources/case_db.xml");
	
	private DAOFactory() {}

	public UserDAO getUserDAO() 
	{
		return userDAO;
	}
	
	public CaseDAO getCaseDAO()
	{
		return caseDAO;
	}

	public static DAOFactory getInstance() {
		return instance;
	}
}