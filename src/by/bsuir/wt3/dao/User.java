package by.bsuir.wt3.dao;

public class User {
	public String login;
	public int hash;
	public UserStatus status;
	
	public User(String login, int hash, UserStatus status)
	{
		this.login = login;
		this.hash = hash;
		this.status = status;
	}
}
