package by.bsuir.wt3.service.impl;

import by.bsuir.wt3.dao.*;
import by.bsuir.wt3.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public UserStatus getUserStatus(String login, int passwordHash) {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();
		
		return dao.getUserStatus(login, passwordHash);
	}

}
