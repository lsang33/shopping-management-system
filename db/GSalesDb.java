package com.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;

import com.entity.GSales;
import com.entity.Goods;

public class GSalesDb {
	Connection        conn  = null;
	PreparedStatement pstmt = null;
	ResultSet 		  rs    = null;	
	
	public boolean addGSales(GSales gSales)
	{
		boolean bool = false;
		java.util.Date utilDate=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
		conn = DbConnect.getconn();
		String sql = "INSERT INTO GOODSAlES(GID,SID,SNUM,SDATE) VALUES(?,?,?,?)";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,gSales.getGId());
			pstmt.setInt(2,gSales.getSId());
			pstmt.setInt(3,gSales.getSNum());
			pstmt.setDate(4, sqlDate);		
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
		return bool;
	}
	
	public boolean displayGSales(){
		conn = DbConnect.getconn();
		String sql = "select *from goodsales order by sdate asc";
		try{
			pstmt =conn.prepareStatement(sql);
			rs=pstmt.executeQuery(sql);
			System.out.println("-------------------------------------------------------------------");
			System.out.println("商品名称\t\t售货员ID\t\t销售数量\t\t销售时间");
			while(rs.next()){
				ArrayList<Goods> arrayList = new GoodsDb().getGoodsInfo(rs.getInt(1), null);
				if(arrayList.size()>0){
					System.out.println(arrayList.get(0).getG_name()+"\t\t"+rs.getInt(2)+"\t\t"+rs.getInt(3)+"\t\t"+rs.getDate(4));
				}
			}		
			System.out.println("-------------------------------------------------------------------");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally
		{
			DbClose.queryClose(pstmt,rs,conn);
		}
		return true;
	}

}
