package com.centris.campus.util;

public class ReligionCasteCasteCategoryUtils {

	public static final String INSERT_STREAM_DETAILS = "INSERT INTO campus_classstream (classstream_id_int,classstream_name_var,description, createuser, createdate,modifyuser,modifydate) VALUES (?,?,?,?,?,?,?)";
	public static final String GET_STREAM_DETAILS = "select * from campus_classstream order by length(classstream_id_int),classstream_id_int";
	public static final String GET_STREAM_ID = "select * from campus_classstream where classstream_id_int = ?";
	/*public static final String UPDATE_STREAM_DETAILS = "update campus_classstream set classstream_name_var= ?,description=?,createuser=?,createdate=?,modifyuser=?,modifydate=? where classstream_id_int= ?";*/
	
	public static final String UPDATE_STREAM_DETAILS = "update campus_classstream set classstream_name_var= ?,description=?,modifyuser=?,modifydate=? where classstream_id_int= ?";
	
	public static final String DELETE_STREAM_DETAILS = "DELETE FROM campus_classstream WHERE classstream_id_int =?";
	public static final String SEARCH_STREAM_DETAILS = "select * from campus_classstream where  classstream_name_var like ? or description like ?";
	public static final String VALIDATE_STREAM_NAME_UPDATE = "select count(*)  from campus_classstream where classstream_name_var=? ";
	public static final String VALIDATE_STREAM_NAME_EDIT = "select count(*)  from campus_classstream where classstream_id_int!=? and classstream_name_var=?";
    public static final String  DELETE_STREAM_MAPPING= "select  count(*) classstream_id_int from campus_classdetail where classstream_id_int=?";
    
    //religion
    public static final String INSERT_RELIGION = "INSERT INTO campus_religion (religionId,religion,createdBy,createdTime) VALUES (?,?,?,?)";
    public static final String UPDATE_RELIGION = "update campus_religion set religion= ?,modifiedBy=?,modifiedTime=? where religionId= ?";
    public static final String VALIDATE_RELIGION_NAME = "select count(*),isActive  from campus_religion where religion=? ";
    public static final String SEARCH_RELIGION_DETAILS = "select * from campus_religion where  religion like ? and isActive=? order by religion";
    public static final String RELGION_DETAILS="select * from campus_religion where isActive='Y' order by religion";
    public static final String GET_SINGLE_RELIGION = "select * from campus_religion where religionId = ?";
    public static final String DELETE_RELIGION="delete from campus_religion where religionId=?";
    
    //caste
    public static final String CASTE_DETAILS="select * from campus_caste where religionId=? AND isActive='Y'";
    //public static final String SEARCH_CASTE_DETAILS = "select * from campus_caste where  caste like ?";
    public static final String LISTCASTE_DETAILS="select caste.*,rel.religion from campus_caste caste join campus_religion  rel on rel.religionId=caste.religionId where caste.isActive='Y' AND rel.isActive='Y' order by rel.religion,caste"; 
    public static final String SEARCH_CASTE_DETAILS = "select caste.*,rel.religion from campus_caste caste join campus_religion  rel on rel.religionId=caste.religionId where ( caste.caste like ? or rel.religion like ? ) AND caste.isActive=? AND rel.isActive='Y' order by rel.religion,caste ";
    public static final String DELETE_CASTE="delete from campus_caste where casteId=?";
    public static final String VALIDATE_CASTE_NAME = "select count(*),isActive from campus_caste where caste=? and religionId=?";
    public static final String INSERT_CASTE = "INSERT INTO campus_caste (casteId,caste,createdBy,createdTime,religionId) VALUES (?,?,?,?,?)";
    public static final String UPDATE_CASTE = "update campus_caste set caste= ?,modifiedBy=?,modifiedTime=?,religionId=? where casteId= ?";
    public static final String GET_SINGLE_CASTE = "select * from campus_caste where casteId = ?";
    
    //casteCategory
    public static final String SEARCH_CASTE_CATEGORY_DETAILS = "select a.isActive,a.remarks,a.castCatId,a.casteCategory,b.caste,c.religion from campus_religion as c,campus_caste as b,campus_caste_category as a where a.religionId=c.religionId and a.casteId=b.casteId and (a.casteCategory like ? OR c.religion like ? OR b.caste like ?) AND a.isActive=? AND b.isActive='Y' AND c.isActive='Y' order by c.religion,b.caste,a.casteCategory";
    public static final String CASTE_CATEGORY_DETAILS="select a.isActive,a.remarks,a.castCatId,a.casteCategory,b.caste,c.religion from campus_religion as c,campus_caste as b,campus_caste_category as a where a.religionId=c.religionId and a.casteId=b.casteId AND a.isActive='Y' AND c.isActive='Y' AND b.isActive='Y' order by c.religion,b.caste,a.casteCategory";    
    public static final String DELETE_CASTE_CATEGORY="delete from campus_caste_category where castCatId=?";
    public static final String VALIDATE_CASTE_CATEGORY_NAME = "select count(*),isActive from campus_caste_category where casteCategory=? and religionId=? and casteId=? ";
    public static final String INSERT_CASTE_CATEGORY = "INSERT INTO campus_caste_category (castCatId,casteCategory,religionId,casteId,createdBy,createdTime) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_CASTE_CATEGORY = "update campus_caste_category set casteCategory= ?,religionId=?,casteId=?,modifiedBy=?,modifiedTime=? where castCatId= ?";
    public static final String GET_SINGLE_CASTE_CATEGORY = "select a.castCatId,a.casteCategory,a.religionId,a.casteId,b.caste,c.religion from campus_religion as c,campus_caste as b,campus_caste_category as a where a.religionId=c.religionId and a.casteId=b.casteId and a.castCatId = ?";

    // public static final String INSERT_RELIGION = "INSERT INTO campus_religion (religionId,religion,createdBy,createdTime) VALUES (?,?,?,?)";
    public static final String INSERT_OCCUPATION = "insert into campus_occupation (occupationId,occupation,createdBy,createdTime) VALUES (?,?,?,?)";
    public static final String UPDATE_OCCUPATION = "update campus_occupation set occupation= ?,modifiedBy=?,modifiedTime=? where occupationId= ?";
    public static final String VALIDATE_OCCUPATION_NAME = "select count(*),isActive  from campus_occupation where occupation=? ";
    public static final String SEARCH_OCCUPATION_DETAILS = "select * from campus_occupation where  occupation like ? AND isActive=? ORDER BY occupation";
    public static final String OCCUPATION_DETAILS="select * from campus_occupation where isActive='Y' order by occupation";
    public static final String GET_SINGLE_OCCUPATION = "select * from campus_occupation where occupationId = ?";
    public static final String DELETE_OCCUPATION="delete from campus_occupation where occupationId=?";

    public static final String LISTOF_CASTE_DETAILS="select religionId,casteId,caste from campus_caste";
    public static final String SINGLE_CASTECATOGRY_DETAILS="select castCatId,casteCategory from campus_caste_category where casteId=? AND isActive='Y'";
	public static final String OCCUPATION_DETAILS_BY_STATUS ="select * from campus_occupation where isActive=? order by occupation";
	public static final String UPDATE_OCCUPATION_BY_STATUS = "UPDATE campus_occupation SET isActive=?,remarks=? WHERE occupationId=?";
	public static final String RELGION_DETAILS_BY_STATUS = "select * from campus_religion where isActive=? order by religion";
	public static final String ACTIVE_INACTIVE_RELIGION_BY_STATUS ="UPDATE campus_religion SET isActive=?,remarks=? WHERE religionId=?";
	public static final String ACTIVE_INACTIVE_CASTE_BY_RELIGION = "UPDATE campus_caste SET isActive=? WHERE religionId=?";
	public static final String LISTING_CASTE_DETAILS_BY_STATUS = "select caste.*,rel.religion from campus_caste caste join campus_religion  rel on rel.religionId=caste.religionId where caste.isActive=? AND rel.isActive='Y' order by rel.religion,caste";
	public static final String ACTIVE_INACTIVE_CASTE_BY_STATUS = "UPDATE campus_caste SET isActive=?,remarks=? WHERE casteId=?";
	public static final String LISTING_CASTECATEGORY_DETAILS_BY_STATUS ="SELECT a.isActive,a.remarks,a.castCatId,a.casteCategory,b.caste,c.religion FROM campus_religion AS c,campus_caste AS b,campus_caste_category AS a WHERE a.religionId=c.religionId AND a.casteId=b.casteId AND a.isActive=? AND c.isActive='Y' AND b.isActive='Y' ORDER BY c.religion,b.caste,a.casteCategory";
	public static final String ACTIVE_INACTIVE_CASTE_CATEGORY_BY_STATUS ="UPDATE campus_caste_category SET isActive=?,remarks=? WHERE castCatId=?";
}
