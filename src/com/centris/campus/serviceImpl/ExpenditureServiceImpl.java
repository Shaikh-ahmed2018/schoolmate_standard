package com.centris.campus.serviceImpl;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.centris.campus.actions.AdminMenuAction;
import com.centris.campus.dao.ExpenditureDAO;
import com.centris.campus.daoImpl.ExpenditureDAOIMPL;
import com.centris.campus.service.ExpenditureService;
import com.centris.campus.vo.ExpenditureVO;

public class ExpenditureServiceImpl implements ExpenditureService{
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);
	
	
	ExpenditureDAO expnd=new ExpenditureDAOIMPL();

	public ArrayList<ExpenditureVO> getExpenditureDetails(ExpenditureVO vo) {
		return expnd.getExpenditureDetails(vo);
	}

	public String insertExpenditure(ExpenditureVO vo) {
		return expnd.insertExpenditure(vo);
	}

	public ExpenditureVO editExpenditure(ExpenditureVO vo) {
		System.out.println("Service IMPL: "+expnd.editExpenditure(vo).getExpenditureTitle());
		System.out.println("Service IMPL: "+expnd.editExpenditure(vo).getDescription());
		return expnd.editExpenditure(vo);
	}

	public String deleteExpenditure(ExpenditureVO vo, String button) {
		 
		return expnd.deleteExpenditure(vo,button);
	}









	


}
