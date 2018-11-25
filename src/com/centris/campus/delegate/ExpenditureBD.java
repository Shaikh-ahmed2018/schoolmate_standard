package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.serviceImpl.ExpenditureServiceImpl;
import com.centris.campus.vo.ExpenditureVO;

public class ExpenditureBD 

{
		ExpenditureServiceImpl expnd = new ExpenditureServiceImpl();
		public ArrayList<ExpenditureVO> getExpenditureDetails(ExpenditureVO vo) {
			return expnd.getExpenditureDetails(vo);
}
		public String insertExpenditure(ExpenditureVO vo) {
			return expnd.insertExpenditure(vo);
		}
		public ExpenditureVO editExpenditure(ExpenditureVO vo) {
			return expnd.editExpenditure(vo);
		}
		public String deleteExpenditure(ExpenditureVO vo, String button) {
			return expnd.deleteExpenditure(vo,button);
		}
}
