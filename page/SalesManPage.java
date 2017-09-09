package com.page;

import java.util.Scanner;
import java.util.ArrayList;

import com.db.SalesManDb;
import com.entity.SalesMan;
import com.shopping_management_system.Getint;

public class SalesManPage {
	public static void  addSalesManPage()
	{
		Scanner sc =new Scanner(System.in);	
		System.out.println("*******添加售货员操作*******");
		System.out.println("添加售货员-姓名");
		String sName = sc.next();		
		System.out.println("添加售货员-密码");
		String sPsd = sc.next();		
		System.out.println("***************************");
		SalesManDb sDb =new SalesManDb();
		int count=sDb.getSalesManNum()+1;
		String sId=""+count;
		SalesMan salesMan = new SalesMan(sId,sName,sPsd);
		boolean bool = new SalesManDb().addSalesMan(salesMan);	
		if (bool)
			System.out.println("添加成功!");
		else 
			System.out.println("添加售货员失败");	
		do
		{
			System.out.print("输0返回上一级菜单,1退出：");
			String choice =sc.next();
			if ("0".equals(choice))
			{
				MainPage.salesManManagementPage();
			}
			if ("1".equals(choice))
			{
				System.out.println("----------------------------");
			 	System.out.println("您已经退出系统!");
			 	System.exit(1);
			}
			System.out.println("输入有误！");
		} while (true);
	}
	
	public static void updateSalesManPage()
	{
		Scanner sc =new Scanner(System.in);
		System.out.println("*******更改售货员操作*******");
		System.out.print("输入售货员ID:");
		String sId = sc.next();
		ArrayList<SalesMan> salesManList = new SalesManDb().getSalesMan(sId);
		if (salesManList.size() <= 0)
		{
			System.out.println("输入有误！");
		}else 
		{
			//显示信息
			System.out.println("售货员信息:");
			System.out.println("------------------------------------------");
			System.out.println("售货员ID\t售货员姓名\t售货员密码");					
			SalesMan salesMan = salesManList.get(0); //上面的精确查找中，只能返回一组数值。无需遍历！
			System.out.println(salesMan.getsId()+"\t"+salesMan.getsName()+"\t\t"+salesMan.getsPsd());
			System.out.println("------------------------------------------");
			System.out.println("---请选择您要更改的内容----");
			System.out.println("1.更改售货员姓名");
			System.out.println("2.更改售货员密码");
			System.out.print("输入选择：");
			Getint getInt= new Getint();		
			int choice=getInt.getint();
			while(choice<1||choice>2){
				System.out.println("输入超出范围！");
				choice=getInt.getint(); 
			}
			switch (choice)
			{
				case 1:
					System.out.print("更改姓名:");
					String sNewName = sc.next();					
					SalesMan salesmanName = new SalesMan(salesMan.getsId(),sNewName,salesMan.getsPsd());
					boolean boolsName = new SalesManDb().updateSalesMan(1, salesmanName);							
					if (boolsName)								
						System.out.println("成功更新售货员名字!");								
					else 							
						System.err.println("更新售货员姓名失败!");									
					break;
				case 2:
					System.out.print("更改密码:");
					String sNewPsd = sc.next();									
					SalesMan salesmanPsd= new SalesMan(salesMan.getsId(),salesMan.getsName(),sNewPsd);
					boolean boolsPsd = new SalesManDb().updateSalesMan(2, salesmanPsd);								
					if (boolsPsd)
						System.out.println("成功更新售货员密码!");
					else 
						System.err.println("更新售货员密码失败!");
					break;
				default:					
					break;
				}
			}
		System.out.println("-------------------");
		do
		{
			System.out.print("输0返回上一级菜单,1退出：");
			String choice =sc.next();
			if ("0".equals(choice))
			{
				MainPage.salesManManagementPage();
			}
			if ("1".equals(choice))
			{
				System.out.println("----------------------------");
			 	System.out.println("您已经退出系统!");
			 	System.exit(1);
			}
			System.out.println("输入有误！");
		} while (true);
	}

	
	public static void deleteSalesManPage()
	{
		System.out.println("********* 删除售货员操作*********");
		System.out.print("请输入想要删除的售货员ID:");
		Scanner sc =new Scanner(System.in);
		String sId = sc.next();		
		ArrayList<SalesMan> salesManList = new SalesManDb().getSalesMan(sId);
		if (salesManList.size() <= 0){
			System.out.println("输入有误！");	
			MainPage.salesManManagementPage();
		}
		else 
		{			
			System.out.println("删除售货员信息:");
			System.out.println("------------------------------------------");
			System.out.println("售货员ID\t售货员姓名\t售货员密码");	
			SalesMan salesMan = salesManList.get(0);
			System.out.println(salesMan.getsId()+"\t"+salesMan.getsName()+"\t"+salesMan.getsPsd());
			System.out.println("------------------------------------------");
			System.out.println("确认删除该售货员：Y/N");
			String choice = sc.next();
			while(!"y".equals(choice)&&!"Y".equals(choice)
					&&!"N".equals(choice)&&!"n".equals(choice)){
				System.out.println("输入有误,请重新输入!!");
				choice = sc.next();
			}
			if ("y".equals(choice) || "Y".equals(choice))
			{
				boolean boolsDelete = new SalesManDb().deleteSalesMan(sId);					
				if (boolsDelete)
					System.out.println("成功刪除该售货员！");
				else 
					System.out.println("刪除该售货员失败！");
			}
			else if("N".equals(choice) || "n".equals(choice)) 
				MainPage.salesManManagementPage();
		}
		do
		{
			System.out.print("输0返回上一级菜单,1退出：");
			String choice =sc.next();
			if ("0".equals(choice))
			{
				MainPage.salesManManagementPage();
			}
			if ("1".equals(choice))
			{
				System.out.println("----------------------------");
			 	System.out.println("您已经退出系统!");
			 	System.exit(1);
			}
			System.out.println("输入有误！");
		} while (true);
	}

	

	public static void searchSalesManPage()
	{
		System.out.println("********* 查询售货员操作*********");
		System.out.println("要查询的售货员ID");
		Scanner sc =new Scanner(System.in);
		String sId = sc.next();		
		ArrayList<SalesMan> salesManList = new SalesManDb().getSalesMan(sId);			
		if (salesManList.size() <=0)
			System.err.println("没人！");
		else 
		{
			System.out.println("所有售货员信息:");
			System.out.println("------------------------------------------");
			System.out.println("售货员编号\t售货员姓名\t售货员密码");	
			SalesMan salesMan = salesManList.get(0);
			System.out.println(salesMan.getsId()+"\t\t"+salesMan.getsName()+"\t\t"+salesMan.getsPsd());
			System.out.println("------------------------------------------");
		}
		do
		{
			System.out.print("输0返回上一级菜单,1退出：");
			String choice =sc.next();
			if ("0".equals(choice))
			{
				MainPage.salesManManagementPage();
			}
			if ("1".equals(choice))
			{
				System.out.println("----------------------------");
			 	System.out.println("您已经退出系统!");
			 	System.exit(1);
			}
			System.out.println("输入有误！");
		} while (true);
	}


	public static void displaySalesManPage()//售货员列表
	{
		ArrayList<SalesMan> salesManList = new SalesManDb().displaySalesMan();
		if (salesManList.size() <= 0)
		{
			System.err.println("售货员列表为空！！");
			MainPage.salesManManagementPage();		
		}
		else 
		{ 
			System.out.println("所有售货员列表:");
			System.out.println("------------------------------------------");
			System.out.println("售货员编号\t售货员姓名\t售货员密码");
			for (int i = 0,length = salesManList.size(); i < length; i++)
			{
				SalesMan salesMan = salesManList.get(i);
				System.out.println(salesMan.getsId()+"\t\t"+salesMan.getsName()+"\t\t"+salesMan.getsPsd());
			}
			System.out.println("------------------------------------------");	
			do
			{
				System.out.print("输0返回上一级菜单,1退出：");
				Scanner sc=new Scanner(System.in);
				String choice =sc.next();
				if ("0".equals(choice))
				{
					MainPage.salesManManagementPage();
				}
				if ("1".equals(choice))
				{
					System.out.println("----------------------------");
				 	System.out.println("您已经退出系统!");
				 	System.exit(1);
				}
				System.out.println("输入有误！");
			} while (true);
		}
	}
}


