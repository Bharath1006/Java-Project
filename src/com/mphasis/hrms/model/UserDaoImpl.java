package com.mphasis.hrms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl {
	public int create(User user) throws ClassNotFoundException, SQLException
	{
		//signup
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement st = con.prepareStatement("INSERT INTO USER_MASTER VALUES(?,?,?,?,?,?,?)");
		st.setString(1, user.getUserId());
		st.setString(2, user.getPassword());
		st.setString(3, user.getFirstName());
		st.setString(4, user.getLastName());
		st.setString(5, user.getRole());
		st.setString(6, user.getStatus());
		st.setString(7, user.getEmail());
		int no=st.executeUpdate();
		return no;
	}
	public void read()
	{
		//under constructor
	}
	public User read(String userId, String password) throws ClassNotFoundException, SQLException
	{
		//signin
		System.out.println(userId+" and "+password);
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM USER_MASTER WHERE userId=? AND password=?");
		st.setString(1, userId);
		st.setString(2, password);
		ResultSet rs = st.executeQuery();
		int no=0;
		User user=null;
		
		while(rs.next())
		{
			
			user=new User(rs.getString("userId"), rs.getString("password"), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7));
			System.out.println(user);
			no++;
		}
		if(no!=1)
			return null;
		else
			return user;
	}
	public void update()
	{
		//under construction
	}
	public void delete()
	{
		//under construction
	}
}
