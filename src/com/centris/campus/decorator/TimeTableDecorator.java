package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;
import com.centris.campus.vo.TimeTableVo;

public class TimeTableDecorator extends TableDecorator {

	public String getDayid() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();
		
		System.out.println("day id :: "+obj.getDayid().trim());

		String result = "<input type='hidden' maxlength='15' value='"
				+ obj.getDayid().trim() + "' name='dayid' ></input>";
		return result;
	}
	
	
	public String getPeriod1() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();
		
		String result = "<select  class='form-control subject' name='period1'>"
					+"<option  value='"+obj.getPeriodId1().trim()+"' selected>" + ""+obj.getPeriod1().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod1'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId1().trim()+"' />";
				
		return result;
	}

	

	public String getPeriod2() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<select  class='form-control subject' name='period2'>"
					+"<option  value='"+obj.getPeriodId2().trim()+"' selected>" + ""+obj.getPeriod2().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod2'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId2().trim()+"' />";
				
		return result;
	}

	public String getPeriod3() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<select  class='form-control subject' name='period3' >"
					+"<option  value='"+obj.getPeriodId3().trim()+"' selected>" + ""+obj.getPeriod3().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod3'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId3().trim()+"' />";
		return result;
	}

		public String getPeriod4() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<select  class='form-control subject' name='period4' >"
					+"<option  value='"+obj.getPeriodId4().trim()+"' selected>" + ""+obj.getPeriod4().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod4'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId4().trim()+"' />";
		return result;
	}

		public String getPeriod5() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<select  class='form-control subject' name='period5' >"
					+"<option  value='"+obj.getPeriodId5().trim()+"' selected>" + ""+obj.getPeriod5().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod5'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId5().trim()+"' />";
		return result;
	}

		public String getPeriod6() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<select  class='form-control subject' name='period6' >"
					+"<option  value='"+obj.getPeriodId6().trim()+"' selected>" + ""+obj.getPeriod6().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod6'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId6().trim()+"' />";
		return result;
	}

		public String getPeriod7() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<select  class='form-control subject' name='period7'>"
					+"<option  value='"+obj.getPeriodId7().trim()+"' selected>" + ""+obj.getPeriod7().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod7'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId7().trim()+"' />";
		return result;
	}

	public String getPeriod8() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<select  class='form-control subject' name='period8' >"
					+"<option  value='"+obj.getPeriodId8().trim()+"' selected>" + ""+obj.getPeriod8().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod8'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId8().trim()+"' />";
		return result;
	}


	public String getPeriod9() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<select  class='form-control subject' name='period9' >"
					+"<option  value='"+obj.getPeriodId9().trim()+"' selected>" + ""+obj.getPeriod9().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod9'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId9().trim()+"' />";
		return result;
	}

}
