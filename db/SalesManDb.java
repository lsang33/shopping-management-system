package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.entity.SalesMan;

public class SalesManDb {
	Connection	conn  = null;
	PreparedStatement	pstmt = null;
	ResultSet	rs = null;
	public ArrayList<SalesMan> checkLog(String sID)
	{
 		ArrayList<SalesMan> salesManInfo = new ArrayList<SalesMan>();
 		conn = DbConnect.getconn();
		String sql = "SELECT SID,SNAME,SPSD FROM SALESMAN WHERE SID=?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,sID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				String sId = rs.getString(1);
				String sName =rs.getString(2);
				String sPassWord = rs.getString(3);				
				SalesMan salesMan = new SalesMan(sId,sName,sPassWord); 
				salesManInfo.add(salesMan);						
			}
		} 
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			DbClose.queryClose(pstmt,rs,conn);
		}
		return salesManInfo;
	}
	
	public int getSalesManNum()//��ȡ��ǰ����
	{
		conn = DbConnect.getconn();
		String sql = "SELECT SID FROM SALESMAN";
		int count=0;
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next()){
				count++;
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbClose.queryClose(pstmt,rs,conn);
		}
		return count;
	}
	
	public boolean addSalesMan(SalesMan salesman)//���
	{
		boolean bool = false;
		conn = DbConnect.getconn();
		String sql = "INSERT INTO SALESMAN(SID,SNAME,SPSD) VALUES(?,?,?)";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,salesman.getsId());
			pstmt.setString(2,salesman.getsName());
			pstmt.setString(3,salesman.getsPsd());
			int rs = pstmt.executeUpdate();
			if (rs > 0)//executeUpdate(sql)�ķ���ֵ�Ǹ��µ�����
			{
				bool = true;
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbClose.queryClose(pstmt,rs,conn);
		}
		return bool;
	}

	public boolean updateSalesMan(int key,SalesMan salesman)//ͨ��keyֵȷ��Ҫ�޸ĵ�ֵ
	{
		boolean bool = false;
		conn = DbConnect.getconn();
		switch (key)
		{
			case 1://name
				String sql_id = "UPDATE SALESMAN SET SNAME=? WHERE SID=?";
				try
				{
					pstmt = conn.prepareStatement(sql_id);
					pstmt.setString(1, salesman.getsName());
					pstmt.setString(2,salesman.getsId());
					int rs = pstmt.executeUpdate();
					if (rs > 0)
					{
						bool = true;
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
				finally
				{
					DbClose.queryClose(pstmt,rs,conn);
				}
				break;
			case 2://psd
				String sql_psd = "UPDATE SALESMAN SET SPSD=? WHERE SID=?";	
				try
				{
					pstmt = conn.prepareStatement(sql_psd);
					pstmt.setString(1, salesman.getsPsd());
					pstmt.setString(2, salesman.getsId());	
					int rs = pstmt.executeUpdate();
					if (rs > 0)
					{
						bool = true;
					}
				} 
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				finally
				{
					DbClose.queryClose(pstmt,rs,conn);
				}
				break;
			default:
				break;
		}
		return bool;
	}

	public boolean deleteSalesMan(String sId)//ɾ��
	{
		boolean bool = false;
		conn = DbConnect.getconn();
		String sql = "DELETE FROM SALESMAN WHERE SID=?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,sId);
			int rs = pstmt.executeUpdate();
			if (rs > 0)
			{
				bool = true;
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbClose.queryClose(pstmt,rs,conn);
		}
		return bool;
	}
	
	public ArrayList<SalesMan> getSalesMan(String sId)//id����
	{
		ArrayList<SalesMan> SalesManList = new ArrayList<SalesMan>();
		conn = DbConnect.getconn();	
		String sql = "SELECT * FROM SALESMAN WHERE SID=?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sId);
			rs = pstmt.executeQuery();
			while (rs.next())
			{	
				String sName= rs.getString(2);
				String sPsd = rs.getString(3);
				SalesMan salesMan = new SalesMan(sId,sName,sPsd);
				SalesManList.add(salesMan);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbClose.queryClose(pstmt,rs,conn);
		}
		return SalesManList;
	}
	
	public  ArrayList<SalesMan> displaySalesMan()//��ʾ�б�
	{
		ArrayList<SalesMan> salesManList = new ArrayList<SalesMan>();
		conn = DbConnect.getconn(); 
		String sql = "SELECT * FROM SALESMAN";	
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs =  pstmt.executeQuery();
			while (rs.next())
			{
				String sId = rs.getString(1);
				String sName = rs.getString(2);
				String sPsd = rs.getString(3);				
				SalesMan salesMan = new SalesMan(sId,sName,sPsd);
				salesManList.add(salesMan);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbClose.queryClose(pstmt,rs,conn);
		}
		return salesManList;
	}
}