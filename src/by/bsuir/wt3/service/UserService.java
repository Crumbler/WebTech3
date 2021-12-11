package by.bsuir.wt3.service;

import by.bsuir.wt3.dao.UserStatus;

public interface UserService {
	UserStatus getUserStatus(String login, int passwordHash);
}
