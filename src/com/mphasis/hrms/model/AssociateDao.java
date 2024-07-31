package com.mphasis.hrms.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface AssociateDao {

	int create(Associate associate) throws ClassNotFoundException, SQLException;

	List<Associate> read() throws ClassNotFoundException, SQLException, ParseException;

	Associate read(Long associateId) throws ClassNotFoundException, SQLException, ParseException;

	int update(Associate associate) throws ClassNotFoundException, SQLException;

	int delete(Long associateId) throws ClassNotFoundException, SQLException;

}