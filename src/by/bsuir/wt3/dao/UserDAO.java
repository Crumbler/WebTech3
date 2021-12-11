package by.bsuir.wt3.dao;

public interface UserDAO {
	UserStatus getUserStatus(String login, int passwordHash);
}
