package com.mphasis.hrms.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AssociateDaoImpl implements AssociateDao {
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yy");
	private Connection getConn() throws ClassNotFoundException, SQLException
	{
		return ConnectionFactory.getConnection();
	}
	@Override
	public int create(Associate associate) throws ClassNotFoundException, SQLException
	{
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("INSERT INTO Associate VALUES(?,?,?,?,?,?)");
		st.setLong(1, associate.getAssociateId());
		st.setString(2, associate.getFirstName());
		st.setString(3, associate.getLastName());
		
		st.setString(4, sdf.format(associate.getDateOfJoining()));
		st.setString(5, associate.getGender());
		st.setBytes(6, associate.getPicture());
		int no=st.executeUpdate();
		return no;
	}
	@Override
	public List<Associate> read() throws ClassNotFoundException, SQLException, ParseException
	{
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("SELECT * FROM Associate");
		ResultSet rs = st.executeQuery();
		List<Associate> associateList=new ArrayList<Associate>();
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while(rs.next())
		{
			Associate associate=new Associate(rs.getLong(1), rs.getString(2), rs.getString(3), sdf1.parse(rs.getString(4)), rs.getString(5), rs.getBytes(6));
			associateList.add(associate);
		}
		return associateList;
	}
	@Override
	public Associate read(Long associateId) throws ClassNotFoundException, SQLException, ParseException
	{
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("SELECT * FROM Associate WHERE associateId=?");
		st.setLong(1, associateId);
		ResultSet rs = st.executeQuery();
		Associate associate=null;
		if(rs.next())
			associate=new Associate(rs.getLong(1), rs.getString(2), rs.getString(3), sdf1.parse(rs.getString(4)), rs.getString(5), rs.getBytes(6));
		return associate;
	}
	@Override
	public int update(Associate associate) throws ClassNotFoundException, SQLException
	{
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("UPDATE Associate SET firstName=?, lastName=?, dateOfJoining=?, gender=?, picture=? WHERE associateId=?");
				
		st.setString(1, associate.getFirstName());
		st.setString(2, associate.getLastName());
		st.setString(3, sdf.format(associate.getDateOfJoining()));
		st.setString(4, associate.getGender());
		st.setBytes(5, associate.getPicture());
		st.setLong(6, associate.getAssociateId());

		int no=st.executeUpdate();
		return no;
	}
	@Override
	public int delete(Long associateId) throws ClassNotFoundException, SQLException
	{
		Connection con = getConn();
		PreparedStatement st = con.prepareStatement("DELETE FROM Associate WHERE associateId=?");
				
		st.setLong(1, associateId);

		int no=st.executeUpdate();
		return no;
	}

}
