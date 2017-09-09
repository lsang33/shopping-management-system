package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.entity.Admin;

public class AdminDb {
	public ArrayList<Admin> checkLog(String aID)//¼ì²éµÇÂ¼
	{
		Connection	conn  = null;
		PreparedStatement	pstmt = null;
		ResultSet	rs = null;
 		ArrayList<Admin> adminInfo = new ArrayList<Admin>();
		conn = DbConnect.getconn();
		String sql = "select AID,APSD from adminster where AID=?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,aID);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String aPsd = rs.getString("APSD");
				String aId = rs.getString("AId");
				Admin admin=new Admin(aId,aPsd);
				adminInfo.add(admin);						
			}
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
	 return adminInfo;
	}
}
