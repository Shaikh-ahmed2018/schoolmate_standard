package com.centris.campus.admin;
// Generated Aug 28, 2013 11:00:33 PM by Hibernate Tools 3.2.1.GA



import java.sql.Timestamp;

/**
 * LstmsTeachers generated by hbm2java
 */
public class LstmsTeachers  implements java.io.Serializable {


     private String teacherId;
     private String firstName;
     private String lastName;
     private String qualification;
     private String designation;
     private String address;
     private String mobileNo;
     private String password;
     private String userName;
     private String email;
     private String tstatus;
     private Timestamp createdate;
     private String validatedby;
     private String modifiedby;
     private String teachertype;

    public String getTeachertype() {
		return teachertype;
	}


	public void setTeachertype(String teachertype) {
		this.teachertype = teachertype;
	}


	public LstmsTeachers() {
    }

	
    public LstmsTeachers(String teacherId, String firstName, String password, String userName, Timestamp createdate) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.password = password;
        this.userName = userName;
        this.createdate = createdate;
    }
    public LstmsTeachers(String teacherId, String firstName, String lastName, String qualification, String designation, String address, String mobileNo, String password, String userName, String email, String tstatus, Timestamp createdate, String validatedby, String modifiedby) {
       this.teacherId = teacherId;
       this.firstName = firstName;
       this.lastName = lastName;
       this.qualification = qualification;
       this.designation = designation;
       this.address = address;
       this.mobileNo = mobileNo;
       this.password = password;
       this.userName = userName;
       this.email = email;
       this.tstatus = tstatus;
       this.createdate = createdate;
       this.validatedby = validatedby;
       this.modifiedby = modifiedby;
    }
   
    public String getTeacherId() {
        return this.teacherId;
    }
    
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getQualification() {
        return this.qualification;
    }
    
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public String getDesignation() {
        return this.designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getMobileNo() {
        return this.mobileNo;
    }
    
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTstatus() {
        return this.tstatus;
    }
    
    public void setTstatus(String tstatus) {
        this.tstatus = tstatus;
    }
    public Timestamp getCreatedate() {
        return this.createdate;
    }
    
    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }
    public String getValidatedby() {
        return this.validatedby;
    }
    
    public void setValidatedby(String validatedby) {
        this.validatedby = validatedby;
    }
    public String getModifiedby() {
        return this.modifiedby;
    }
    
    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }




}


