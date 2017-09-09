package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.entity.Goods;
import com.entity.Supplier;

public class StockGoodsDb {
	Connection        conn  = null;
	PreparedStatement pstmt = null;
	ResultSet 		  rs    = null;	
	
	public static void main(String[] args) {
		/*ArrayList<Supplier> array= new StockGoodsDb().displaySupplier();
		for(int i=0;i<array.size();i++){
			System.out.println(array.get(i).getSupName());
		}*/	
		System.out.println(new StockGoodsDb().getSuppNum());
	}
	
	public int getSuppNum()//获取当前数量
	{
		conn = DbConnect.getconn();
		String sql = "select * from supplier";
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
	
	public   ArrayList<Supplier> displaySupplier()//显示列表
	{
		ArrayList<Supplier> suppList = new ArrayList<Supplier>();
		conn = DbConnect.getconn(); 
		String sql = "select * from supplier";	
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs =  pstmt.executeQuery();
			while (rs.next())
			{
				int gId = rs.getInt(1);
				String gName = rs.getString(2);
				Supplier supp =new Supplier(gId,gName);
				suppList.add(supp);
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
		return suppList;
	}
}
