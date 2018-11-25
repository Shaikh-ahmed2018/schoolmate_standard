package com.centris.campus.dao;


import java.util.ArrayList;


import com.centris.campus.vo.ExpenditureVO;

public interface ExpenditureDAO 

{

	ArrayList<ExpenditureVO> getExpenditureDetails(ExpenditureVO vo);

	String insertExpenditure(ExpenditureVO vo);

	ExpenditureVO editExpenditure(ExpenditureVO vo);

	String deleteExpenditure(ExpenditureVO vo, String button);  
 
	

}
