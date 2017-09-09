package com.page;


import java.util.Scanner;

import com.db.GSalesDb;
import com.shopping_management_system.Getint;
import com.shopping_management_system.Login;

public class MainPage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loginPage();
	}
	
	public static void loginPage(){//第一界面
		System.out.println("************超市系统***********");
		System.out.println("1.管理员登录");
		System.out.println("2.收银员登录");
		System.out.println("3.用户自助购买");
		System.out.println("输入0退出.");
		System.out.println("******************************");
		System.out.print("选择：");
		Getint getInt= new Getint();		
		int choice=getInt.getint();
		while(choice<0||choice>3){
			System.out.println("输入超出范围！");
			choice=getInt.getint(); 
		}
		String Id;
		switch(choice){
			case 1:
			case 2:
				Login lg=new Login(choice);
				Id=lg.login();
				if(choice==1)
					adminPage();
				else
					GSalesPage.shoppingSettlementPage(Integer.parseInt(Id));
				break;
			case 3:
				GSalesPage.settlementPage(666);
				break;
			case 0:
				System.out.println("------------------");
			 	System.err.println("您已经退出系统!");
			 	System.exit(1);
			 	break;
			default:
				break;
		}
	}
	
	public static void adminPage(){
		System.out.println("------—----管理员界面----------");
		System.out.println("1.商品维护");
		System.out.println("2.商品管理");
		System.out.println("3.售货员管理");
		System.out.println("输入0退出.");
		System.out.println("----------------------------");
		System.out.print("选择：");
		Getint getInt= new Getint();		
		int choice=getInt.getint();
		while(choice<0||choice>3){
			System.out.println("输入超出范围！");
			choice=getInt.getint(); 
		}
		switch(choice){
			case 0:
				System.out.println("----------------------------");
				System.out.println("您已经退出系统!");
				System.exit(1);
			case 1:goodsOpPage();break;
			case 2:goodsManagementPage();break;
			case 3:salesManManagementPage();break;
			default:
				break;
		}
	}
	
	public static void goodsOpPage()//商品操作界面
	{	
		System.out.println("----------------------------");
		System.out.println("1.添加商品");
		System.out.println("2.更改商品");
		System.out.println("3.删除商品");
		System.out.println("4.查询商品");
		System.out.println("5.显示所有商品");
		System.out.println("输入0返回上一层.");
		System.out.println("----------------------------");
		System.out.print("选择：");
		Getint getInt= new Getint();		
		int choice=getInt.getint();
		while(choice<0||choice>5){
			System.out.println("输入超出范围！");
			choice=getInt.getint(); 
		}
		GoodsPage gp=new GoodsPage();
		switch(choice){
		case 0:
			System.out.println("----------------------------");
			adminPage();
			System.exit(1);
		case 1:gp.addGoodsPage();break;
		case 2:gp.upateGoodsPage();break;
		case 3:gp.deleteGoodsPage();break;
		case 4:gp.queryGoodsPage();break;
		case 5:gp.displayGoodsPage(1);break;
		}
	}

	public static void goodsManagementPage()//商品管理界面
	{
		System.out.println("----------------------------");
		System.out.println("1.显示销售列表");
		System.out.println("2.进货操作");
		System.out.println("----------------------------");
		System.out.println("输入0返回上一层.");		
		System.out.print("选择：");
		Getint getInt= new Getint();		
		int choice=getInt.getint();
		while(choice<0||choice>2){
			System.out.println("输入超出范围！");
			choice=getInt.getint(); 
		}
		
		switch(choice){
		case 0:
			adminPage();
			System.exit(1);
		case 1: 
			new  GSalesDb().displayGSales();
			do{
				System.out.print("\n输0返回上一级菜单,1退出：");
				Scanner sc=new Scanner(System.in);
				String choice_ =sc.next();
				if ("0".equals(choice_))
				{
					MainPage.adminPage();
				}
				if ("1".equals(choice_))
				{
					System.out.println("----------------------------");
				 	System.out.println("您已经退出系统!");
				 	System.exit(1);
				}
				System.out.println("输入有误！");
			} while (true);
		case 2:
			new GoodsPage().stockGoods();
			break;
		default:break;
		}
	} 
	
	public static void salesManManagementPage()//售货员管理界面
	{
		System.out.println("----------------------------");
		System.out.println("1.添加售货员");
		System.out.println("2.更改售货员");
		System.out.println("3.删除售货员");
		System.out.println("4.查询售货员");
		System.out.println("5.显示所有售货员");
		System.out.println("输入0返回上一层.");	
		System.out.println("----------------------------");
		System.out.println("选择：");
		Getint getInt= new Getint();		
		int choice=getInt.getint();
		while(choice<0||choice>5){
			System.out.println("输入超出范围！");
			choice=getInt.getint(); 
		}
		SalesManPage sp= new SalesManPage();
		switch(choice){
			case 0:
				adminPage();
				System.exit(1);		
			case 1:sp.addSalesManPage();break;
			case 2:sp.updateSalesManPage();break;
			case 3:sp.deleteSalesManPage();break;
			case 4:sp.searchSalesManPage();break;
			case 5:sp.displaySalesManPage();break;
		}
	}
}
