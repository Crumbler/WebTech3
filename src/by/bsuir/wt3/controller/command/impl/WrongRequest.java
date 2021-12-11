package by.bsuir.wt3.controller.command.impl;

import by.bsuir.wt3.controller.command.Command;

public class WrongRequest implements Command {

	@Override
	public String execute(String request) {
		return "Unknown request.";
	}

}
