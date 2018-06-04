package com.tsstu.console.model;

import java.util.Date;
import com.tsstu.common.model.Model;

public class Demo extends Model{

	private int test_id;
	
	private String test_varchar;
	
	private String test_numeric;
	
	private Date test_datetime;

	public int getTest_id() {
		return test_id;
	}

	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}

	public String getTest_varchar() {
		return test_varchar;
	}

	public void setTest_varchar(String test_varchar) {
		this.test_varchar = test_varchar;
	}

	public String getTest_numeric() {
		return test_numeric;
	}

	public void setTest_numeric(String test_numeric) {
		this.test_numeric = test_numeric;
	}

	public Date getTest_datetime() {
		return test_datetime;
	}

	public void setTest_datetime(Date test_datetime) {
		this.test_datetime = test_datetime;
	}

}
