package com.page;

import java.util.ArrayList;
import java.util.Scanner;

import com.db.GoodsDb;
import com.db.StockGoodsDb;
import com.entity.Goods;
import com.entity.Supplier;
import com.shopping_management_system.Getint;

public class GoodsPage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public static float getfloat(){
		Scanner sc=new Scanner(System.in);
		float m=0;
		boolean b=true;
		while(b){//保证输入为整数
			try{
				m=sc.nextFloat();
				b=false;
			}
			catch(Exception e){
				System.out.print("重新输入:");
				sc.nextLine();//保证正常循环
			}
		}
		return m; 
	}
	
	public void stockGoods(){
		System.out.println("*******进货操作*******");
		System.out.println("请输入商品ID");
		Scanner sc=new Scanner(System.in);
		int gId=new Getint().getint();
		GoodsDb goodsdb=new GoodsDb();
		ArrayList<Goods> goodsList =goodsdb.getGoodsInfo(gId, null);
		if (goodsList == null || goodsList.size() == 0)
		{
			System.out.println("查无此商品 ！");
			System.out.println("");
			stockGoods();	
		}
		System.out.println("商品信息:");
		System.out.println("------------------------------------------");
		System.out.println("商品ID\t商品姓名\t商品价格\t商品数量");					
		Goods goods = goodsList.get(0);
		System.out.println(goods.getG_id()+"\t"+goods.getG_name()+"\t"+goods.getG_price()+"\t"+goods.getG_num());
		System.out.println("------------------------------------------");
		System.out.println("进货商名单：");
		ArrayList<Supplier> array= new StockGoodsDb().displaySupplier();
		System.out.println("------------------------------------------");
		System.out.println("进货商ID\t进货商名称");			
		for(int i=0;i<array.size();i++){
			System.out.println(array.get(i).getSupId()+"\t"+array.get(i).getSupName());
		}
		System.out.println("------------------------------------------");
		System.out.println("输入进货商ID选择进货商：");
		Getint getInt=new Getint();
		int choice = getInt.getint();
		int supp_num= new StockGoodsDb().getSuppNum();
		while(choice<0||choice>supp_num){
			System.out.println("输入超出范围！");
			System.out.println("请重新输入：");
			choice = getInt.getint();
		}
		System.out.println("输入进货数量：");
		int stock_num = getInt.getint();
		System.out.println("输入Y进行确定，输入其它返回上一页.");
		String choStock=sc.next();
		if("y".equals(choStock) || "Y".equals(choStock))
		{
			int new_num=goods.getG_num()+stock_num;
			Goods good =new Goods(gId,goods.getG_name(),goods.getG_price(),new_num);
			boolean bool=goodsdb.updateGoods(3, good);
			if(bool){
				System.out.println("进货成功！");	
			}
			else
				System.out.println("进货失败！");
			do{
				System.out.print("输0返回上一级菜单,1退出：");
				sc=new Scanner(System.in);
				String choice_ =sc.next();
				if ("0".equals(choice_))
				{
					MainPage.goodsManagementPage();
				}
				if ("1".equals(choice_))
				{
					System.out.println("----------------------------");
				 	System.out.println("您已经退出系统!");
				 	System.exit(1);
				}
				System.out.println("输入有误！");
			} while (true);
		}
		else{
			MainPage.goodsManagementPage();
		}
	}
	
	public static void  addGoodsPage()//添加商品
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("*******添加商品操作*******");
		System.out.print("输入商品名称：");
		String goodsName = sc.next();
		System.out.print("输入商品价格：");
		float goodsPrice = getfloat();
		System.out.print("输入商品数量：");
		int goodsNumber = new Getint().getint();
		int id=new GoodsDb().getGoodsNum()+1;
		//System.out.println(id+goodsName+goodsPrice+goodsNumber);
		Goods goods  = new Goods(id,goodsName,goodsPrice,goodsNumber);
		boolean bool = new GoodsDb().addGoods(goods);	
		if (bool)
			System.out.println("成功添加商品!");			
		else 
			System.out.println("添加商品失败！");
		System.out.println("**********************");
		do{
			System.out.print("输0返回上一级菜单,1退出：");
			sc=new Scanner(System.in);
			String choice_ =sc.next();
			if ("0".equals(choice_))
			{
				MainPage.goodsOpPage();
			}
			if ("1".equals(choice_))
			{
				System.out.println("----------------------------");
			 	System.out.println("您已经退出系统!");
			 	System.exit(1);
			}
			System.out.println("输入有误！");
		} while (true);
	}

	public static void  upateGoodsPage()
	{
		System.out.println("*******修改商品操作*******");
		System.out.println("请输入商品ID");
		int gId=new Getint().getint();
		/////////////////////////////////////////////////
		ArrayList<Goods> goodsList =new GoodsDb().getGoodsInfo(gId, null);
		if (goodsList.size() <= 0)
		{
			System.err.println("输入有误！");	
			MainPage.goodsOpPage();
		}		
		else
		{
			System.out.println("商品信息:");
			System.out.println("------------------------------------------");
			System.out.println("商品ID\t商品姓名\t商品价格\t商品数量");					
			Goods goods = goodsList.get(0);
			System.out.println(goods.getG_id()+"\t"+goods.getG_name()+"\t"+goods.getG_price()+"\t"+goods.getG_num());
			System.out.println("------------------------------------------");
			System.out.println("---请选择您要更改的内容----");;
			System.out.println("1.更改商品名称");
			System.out.println("2.更改商品价格");
			System.out.println("3.更改商品数量");
			System.out.println("-------------------");
			System.out.print("选择：");
			Getint getInt= new Getint();		
			int choice=getInt.getint();
			while(choice<1||choice>3){
				System.out.println("输入超出范围！");
				choice=getInt.getint(); 
			}
			switch (choice)
			{
				 case 1:
					 Scanner sc=new Scanner(System.in);
					 System.out.print("商品新名称:");
					 String gname = sc.next();
					 Goods goodsName = new Goods(goods.getG_id(),gname,goods.getG_price(),goods.getG_num());
					 boolean boolName = new GoodsDb().updateGoods(1, goodsName);
					 if (boolName)
						 System.err.println("成功更新商品名！");
					 else 
					 {
						 System.err.println("更新商品名失败！");
					 }
					 break;
				 case 2:
					 System.out.print("商品新价格： ");
					 float gprice = getfloat();
					 Goods  goodsPrice = new Goods(goods.getG_id(),goods.getG_name(),gprice,goods.getG_num());
					 boolean boolPrice = new GoodsDb().updateGoods(2,goodsPrice);			
					 if (boolPrice)
					 {
						 System.err.println("成功更新商品价格！");
					 }
					 else 
					 {
						 System.err.println("更新商品价格失败！");
					 }
					 break;
				 case 3:
					 System.out.print("商品新数量: ");
					 int gNum = new Getint().getint();
					 Goods  goodsNum= new Goods(goods.getG_id(),goods.getG_name(),goods.getG_price(),gNum);
					 boolean boolNum = new GoodsDb().updateGoods(3,goodsNum);
					 if (boolNum)
					 {
						 System.out.println("成功更新商品数量！");
					 }
					 else 
					 {
						 System.out.println("更新商品数量失败！");
					 }
			
					 break;
				 default:
					 System.err.println("请输入正确的选择！");
					 break;
			 }		
			do{
				System.out.print("输0返回上一级菜单,1退出：");
				Scanner sc=new Scanner(System.in);
				String choice_ =sc.next();
				if ("0".equals(choice_))
				{
					MainPage.goodsOpPage();
				}
				if ("1".equals(choice_))
				{
					System.out.println("----------------------------");
				 	System.out.println("您已经退出系统!");
				 	System.exit(1);
				}
				System.out.println("输入有误！");
			} while (true);
		}
		
	}
	

	public static void deleteGoodsPage()
	{
		System.out.println("********* 删除商品操作*********");
		System.out.print("请输入想要删除的商品ID:");
		int gId = new Getint().getint();		
		ArrayList<Goods> goodsList = new GoodsDb().getGoodsInfo(gId, null);
		if (goodsList.size() <= 0){
			System.err.println("输入有误！");
			MainPage.goodsOpPage();
		}
		else 
		{			
			System.out.println("删除商品信息:");
			System.out.println("------------------------------------------");
			System.out.println("商品ID\t商品名称\t商品价格\t商品数量");	
			Goods goods = goodsList.get(0);
			System.out.println(goods.getG_id()+"\t"+goods.getG_name()+"\t"+goods.getG_price()
					+"\t"+goods.getG_num());
			System.out.println("------------------------------------------");
		}
		System.out.println("确认删除该商品：Y/N");
		Scanner sc=new Scanner(System.in);
		String choice = sc.next();
		while(!"y".equals(choice)&&!"Y".equals(choice)
				&&!"N".equals(choice)&&!"n".equals(choice)){
			System.err.println("输入有误,请重新输入!!");
			choice = sc.next();
		}
		if ("y".equals(choice) || "Y".equals(choice))
		{
			boolean boolsDelete = new GoodsDb().deleteGoods(gId);				
			if (boolsDelete)
				System.out.println("成功刪除该商品！");
			else 
				System.out.println("刪除该商品失败！");
		}
		else if("N".equals(choice) || "n".equals(choice)) 
			MainPage.salesManManagementPage();
		do{
			System.out.print("\n输0返回上一级菜单,1退出：");
			sc=new Scanner(System.in);
			String choice_ =sc.next();
			if ("0".equals(choice_))
			{
				MainPage.goodsOpPage();
			}
			if ("1".equals(choice_))
			{
				System.out.println("----------------------------");
			 	System.out.println("您已经退出系统!");
			 	System.exit(1);
			}
			System.out.println("输入有误！");
		} while (true);
	}

	public static void queryGoodsPage()
	{
		System.out.println("*******查询商品操作*******");
		System.out.println("1.商品数量升序");
		System.out.println("2.商品价格升序");
		System.out.println("3.商品关键字查询");
		System.out.println("输入0返回上一层.");	
		System.out.println("**********************");
		System.out.print("选择：");
		Getint getInt= new Getint();		
		int choice=getInt.getint();
		while(choice<0||choice>3){
			System.out.println("输入超出范围！");
			choice=getInt.getint(); 
		}
		switch(choice)
		{
			 case 0:
				 System.out.println("----------------------------");
				 MainPage.goodsOpPage();
				 System.exit(1);
			 case 1:
			 case 2:
			 case 3:
				 if(choice == 3)
				 {					 
					 System.out.println("---商品关键字查询----");
					 System.out.print("请输入商品关键字:");					 
				 }
				 ArrayList<Goods> goodsList = new GoodsDb().queryGoods(choice);
				 System.out.println(goodsList.get(0).getG_name());
				 if (goodsList == null || goodsList.size() <= 0)
				 {
					 System.err.println("查询的商品不存在");
					 queryGoodsPage();
				 } 
				 else
				 {
					if (choice == 1)	
						System.out.println("商品数量升序列表:");					
					else if (choice == 2) 					
						 System.out.println("商品价格升序列表:");					
					else					
						System.out.println("您所查找的商品如下:");					
					System.out.println("商品编号\t商品名称\t商品价格\t商品数量\t备注");
					for (int i = 0,length = goodsList.size(); i < length; i++)
					{
						Goods goods = goodsList.get(i);
						System.out.print(goods.getG_id()+"\t"+goods.getG_name()+"\t"+goods.getG_price()+"\t"+goods.getG_num());
						int gnum = goods.getG_num();
						if (gnum ==0)
						{
							System.out.println("\t该商品已售空！");
						}
						else if (gnum<10)
						{
							System.out.println("\t该商品已不足10件");
						}
						else
							System.out.println();
					}
				 }					
				 break;
			 default:
			 break;
		}
		do{
			System.out.print("\n输0返回上一级菜单,1退出：");
			Scanner sc=new Scanner(System.in);
			String choice_ =sc.next();
			if ("0".equals(choice_))
			{
				MainPage.goodsOpPage();
			}
			if ("1".equals(choice_))
			{
				System.out.println("----------------------------");
			 	System.out.println("您已经退出系统!");
			 	System.exit(1);
			}
			System.out.println("输入有误！");
		} while (true);
	}

	public static void displayGoodsPage(int side)
	{
		System.out.println("所有商品列表：");
		System.out.println("-----------------------------------------------------------");
		ArrayList<Goods> goodsList = new GoodsDb().displayGoods();
		if (goodsList.size() <= 0)
		{
			System.err.println("库存为空！");
		}
		else 
		{
			System.out.println("商品编号\t商品名称\t商品价格\t商品数量\t备注");
			for (int i = 0,length = goodsList.size(); i < length; i++)
			{
				Goods goods = goodsList.get(i);
				System.out.print(goods.getG_id()+"\t"+goods.getG_name()+"\t"+goods.getG_price()+"$\t"+goods.getG_num());					
				int gNum = goods.getG_num();
				if (gNum==0)
				{
					System.out.println("\t该商品已售空！");
				}
				else if (gNum<10) 
				{
					System.out.println("\t该商品已不足10件");
				}
				else
				{
					System.out.println();
				}
			}
			System.out.println("-----------------------------------------------------------");
			if(side==1){
				do
				{
					System.out.print("输0返回上一级菜单,1退出：");
					Scanner sc=new Scanner(System.in);
					String choice =sc.next();
					if ("0".equals(choice))
					{
						MainPage.goodsOpPage();
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
}
