package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;

import com.entity.Goods;

public class GoodsDb {
	Connection	conn  = null;
	PreparedStatement	pstmt = null;
	ResultSet	rs = null;
	
	public int getGoodsNum()//获取当前数量
	{
		conn = DbConnect.getconn();
		String sql = "SELECT GID FROM GOODS";
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
	
	public int getMaxGoodsId()
	{
		conn = DbConnect.getconn();
		String sql = "SELECT MAX(GID) FROM GOODS";
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next()){
				return rs.getInt(1);
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
		return 0;
	}
	
	public boolean addGoods(Goods goods)//添加商品
	{
		boolean bool = false;
		conn = DbConnect.getconn();
		String sql = "INSERT INTO GOODS(GID,GNAME,GPRICE,GNUM) VALUES(?,?,?,?)";
		int num=new GoodsDb().getMaxGoodsId()+1;
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			pstmt.setString(2,goods.getG_name());
			pstmt.setFloat(3,goods.getG_price());
			pstmt.setInt(4,goods.getG_num());	
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
	
	public boolean deleteGoods(int gid)//删除
	{
		boolean bool = false;
		conn = DbConnect.getconn();
		String sql = "DELETE FROM GOODS WHERE GID=?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,gid);
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
	
	public int getKeyGoodsState(){//当商品件数有且只有一件时返回商品gid号，商品已售空时返回 -1. >1件时返回-2 . 查无此商品时返回-3
		int state=-1;
		ArrayList<Goods> goodsList =new GoodsDb().queryGoods(3);//关键字
		if(goodsList.size()==0||goodsList==null){
			System.out.println("无该商品！");
		}
		else{
			System.out.println("---------------------------------------------------");
			System.out.println("商品编号\t商品名称\t商品价格\t商品数量\t备注");
			for (int i = 0; i < goodsList.size(); i++)
			{
				Goods goods = goodsList.get(i);
				if (goods.getG_num() >=0)// 数量大于0
				{
					System.out.print(goods.getG_id()+"\t"+goods.getG_name()+"\t"+goods.getG_price()+"\t"+goods.getG_num());								
					if (goods.getG_num()==0)
					{
						System.out.println("\t该商品已售空");
					}
					else if (goods.getG_num()<10)
					{
						System.out.println("\t该商品已不足10件");
					}
					else 
					{
						System.out.println();
					}
					if (goodsList.size()==1)
					{
						state = goods.getG_id(); //将商品编号返回给调用者
					}
					else 
					{
						state = -2;
					}
				}
			}
			System.out.println("---------------------------------------------------");
		}
		return state;
	}
	
	public ArrayList<Goods> getGoodsInfo(int gId,String gName)//获取商品信息
	{										
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		conn = DbConnect.getconn();		
		String sql = "SELECT * FROM GOODS WHERE GID=? OR GNAME=?"; 
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gId);
			pstmt.setString(2, gName);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				int gid = rs.getInt(1);
				String gname = rs.getString(2);
				float gprice = rs.getFloat(3);
				int gnum = rs.getInt(4);
				Goods goods = new Goods(gid,gname,gprice,gnum);
				goodsList.add(goods);
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbClose.queryClose(pstmt,rs,conn);
		}
		return goodsList;
	}	
	
	public boolean updateGoods(int key,Goods goods)//商品更新
	{
		boolean bool = false;
		conn = DbConnect.getconn();
		switch(key)
		{
			case 1://更改商品名称
				String sql_name = "UPDATE GOODS SET GNAME=? WHERE GID=?";
				try
				{
					pstmt = conn.prepareStatement(sql_name);
					pstmt.setString(1, goods.getG_name());
					pstmt.setInt(2,goods.getG_id());							
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
			case 2://更改商品价格
				String sql_price = "UPDATE GOODS SET GPRICE=? WHERE GID=?";	
				try
				{
					pstmt = conn.prepareStatement(sql_price);
					pstmt.setDouble(1, goods.getG_price());
					pstmt.setInt(2,goods.getG_id());						
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
			case 3://更改商品数量
				String sqlNum = "UPDATE GOODS SET GNUM=? WHERE GID=?";		
				try
				{
					pstmt = conn.prepareStatement(sqlNum);
					pstmt.setInt(1, goods.getG_num());
					pstmt.setInt(2,goods.getG_id());
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
	
	public ArrayList<Goods> queryGoods(int key)//排序搜索
	{										
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		conn = DbConnect.getconn();	
		switch(key)
		{
			case 1://商品数量升序查询					
				String sql_gnum = "SELECT * FROM GOODS ORDER BY GNUM ASC";
				try
				{
					pstmt = conn.prepareStatement(sql_gnum);
					rs = pstmt.executeQuery();
					while (rs.next())
					{
						int gid = rs.getInt(1);
						String gname = rs.getString(2);
						float gprice = rs.getFloat(3);
						int gnum = rs.getInt(4);		
						Goods goods = new Goods(gid,gname,gprice,gnum);
						goodsList.add(goods);
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
			case 2://商品价格升序查询
				String sql_gprice = "SELECT * FROM GOODS ORDER BY GPRICE ASC";
				try
				{
					pstmt = conn.prepareStatement(sql_gprice);
					rs = pstmt.executeQuery();
					while (rs.next())
					{
						int gid = rs.getInt(1);
						String gname = rs.getString(2);
						float gprice = rs.getFloat(3);
						int gnum = rs.getInt(4);	
						Goods goods = new Goods(gid,gname,gprice,gnum);
						goodsList.add(goods);
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
			case 3://商品 关键字 查询	
				Scanner sc =new Scanner(System.in);
				String keyword =sc.next();
				String sql_keyword = "SELECT * FROM GOODS WHERE GNAME LIKE '%"+keyword+"%'";
				try
				{
					pstmt = conn.prepareStatement(sql_keyword);
					//pstmt.setString(1, keyword);
					rs = pstmt.executeQuery();
					while (rs.next())
					{
						int gid = rs.getInt(1);
						String gname = rs.getString(2);
						float gprice = rs.getFloat(3);
						int gnum = rs.getInt(4);	
						Goods goods = new Goods(gid,gname,gprice,gnum);
						goodsList.add(goods);
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
		return goodsList;
	}
	
	public  ArrayList<Goods> displayGoods()//显示列表
	{
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		conn = DbConnect.getconn(); 
		String sql = "SELECT * FROM Goods";	
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs =  pstmt.executeQuery();
			while (rs.next())
			{
				int gId = rs.getInt(1);
				String gName = rs.getString(2);
				float gPrice=rs.getFloat(3);
				int gNum=rs.getInt(4);
				Goods good =new Goods(gId,gName,gPrice,gNum);
				goodsList.add(good);
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
		return goodsList;
	}

}
