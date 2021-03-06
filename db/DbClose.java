package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class DbClose
{
	//关闭 添加功能 资源
	public static void addClose(PreparedStatement pstmt, Connection conn)
	{
		try
		{
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		try
		{
			if (conn != null)
				conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
		 
	//关闭资源	
	public static void queryClose(PreparedStatement pstmt, ResultSet rs, Connection conn)
	{
		try
		{
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		
		try
		{
			if (rs != null )
				rs.close();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		
		try
		{
			if (conn != null)
				conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}	
		
}