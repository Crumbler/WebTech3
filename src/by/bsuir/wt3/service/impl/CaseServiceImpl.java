package by.bsuir.wt3.service.impl;

import by.bsuir.wt3.dao.*;
import by.bsuir.wt3.service.CaseService;

public class CaseServiceImpl implements CaseService {

	@Override
	public String getCase(int id) {
		DAOFactory factory = DAOFactory.getInstance();
		CaseDAO dao = factory.getCaseDAO();

		return dao.getCase(id);
	}

	@Override
	public String setCase(int id, String caseString) {
		DAOFactory factory = DAOFactory.getInstance();
		CaseDAO dao = factory.getCaseDAO();
		
		return dao.setCase(id, caseString);
	}

}
