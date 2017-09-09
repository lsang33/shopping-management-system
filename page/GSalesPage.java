package com.page;

import java.util.ArrayList;
import java.util.Scanner;

import com.shopping_management_system.Getint;
import com.entity.*;
import com.db.*;

public class GSalesPage {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//settlementPage(1);
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
	
	public static void shoppingSettlementPage(int salesManSid)//结账
	{
		System.out.println("*******购物结算*******");
		do
		{	
			System.out.println("输入Y开始购物结算.按N返回账户登录界面.");
			Scanner sc=new Scanner(System.in);
			String choice = sc.next();
			if ("N".equals(choice)||"n".equals(choice))
			{
				MainPage.loginPage();
				
			}
			else if("Y".equals(choice)||"y".equals(choice)) 
			{
				System.out.println("\n--请输入商品关键字--");						
				int gid =new GoodsDb().getKeyGoodsState();
				switch (gid)
				{
					case -3://无此商品
						break;
					case -1://售空
						System.err.println("该商品已售空.");
						break;	
					default:
						System.out.println("--按商品ID选择商品--");
						int shoppingGid = new Getint().getint();					
						ArrayList<Goods> goodsList = new GoodsDb().getGoodsInfo(shoppingGid, null);
						if (goodsList == null || goodsList.size() == 0)
						{
							System.err.println("查无此商品 ！");
						}
						else 
						{
							Goods goods = goodsList.get(0);
							int gNum = goods.getG_num();	
							float gPrice = goods.getG_price(); 							
							System.out.print("请输入购买数量:");
							do
							{
								int choicegoodsNum = new Getint().getint();//获取用户要购买的数量									
								if (choicegoodsNum > gNum)
								{
									System.out.println("仓库储备不足！");
									System.out.print("请重新输入购买数量:");
								}
								else//大于用户购买量
								{
									float allPrice = choicegoodsNum*goods.getG_price();
									System.out.println("购物车结算:");
									System.out.println("---------------------------------------------------");
									System.out.println("商品名称\t商品单价\t购买数量\t总价");
									System.out.println(goods.getG_name()+"\t"+gPrice+" $\t"+choicegoodsNum+"\t"+allPrice+" $");
									System.out.println("---------------------------------------------------");
									do
									{
										System.out.println("确认购买：Y/N");
										String choShopping = sc.next();
										if ("y".equals(choShopping) || "Y".equals(choShopping))
										{
											System.out.println("总价："+allPrice+" $");
											System.out.println("实际缴费金额");
											do
											{
												float balance = getfloat();
												float sub = balance-allPrice; //差价
												if (sub < 0)
												{
													System.err.println("缴纳金额不足！");
													System.out.print("请重新输入缴纳金额:");
												}else
												{																														
													//对sales表操作
													GSales gSales = new GSales(goods.getG_id(),salesManSid,choicegoodsNum);
													boolean insert = new GSalesDb().addGSales(gSales);															
													//对goods表操作
													int goodsNewNum = gNum - choicegoodsNum; //现在goods表中该商品数量
													Goods newGoods = new Goods(goods.getG_id(),goods.getG_name(),goods.getG_price(),goodsNewNum);
													boolean update = new GoodsDb().updateGoods(3,newGoods);										
													if (update && insert)
													{
														System.out.println("找零："+sub);													
													}
													else 
													{
														System.err.println("！支付失败！");//数据库操作失败
													}
													shoppingSettlementPage(salesManSid);//最后跳转到到购物结算页面		
												}
											} while (true);	
														
										}
										else if ("N".equals(choShopping) || "n".equals(choShopping)) 
										{
											shoppingSettlementPage(salesManSid);
										}
										System.err.println("输入有误！");
									} while (true);
								}
							} while (true);
						}					
						break;
				}
			}
			else
			{
				System.err.println("输入有误！");
			}
		} while (true);
	}
	
	static ArrayList<GSales> cartList=new ArrayList();
	
	public static int addcartList(GSales gsales){
		for (int i = 0,length = cartList.size(); i < length; i++)
		{
			if(cartList.get(i).getGId()==gsales.getGId()){
				int num=cartList.get(0).getSNum()+gsales.getSNum();
				cartList.get(i).setSNum(num);
				return 1;
			}
		}
		cartList.add(gsales);
		return 1;
	}
	
	public float getallPrice(){//获得总价
		float allPrice=0;
		for (int i = 0,length = cartList.size(); i < length; i++)
		{			
			GoodsDb gdb =new GoodsDb();
			ArrayList<Goods> arrayList=gdb.getGoodsInfo(cartList.get(i).getGId(), null);
			Goods good =arrayList.get(0);
			allPrice+=good.getG_price()*cartList.get(i).getSNum();
		}
		return allPrice;
	}
	
	public static void settlementPage(int sId){	
		Scanner sc=new Scanner(System.in);
		String choice;
		do{
			System.out.println("\n********用户自助购买***********");
			System.out.println("----------------------------");
			System.out.println("1.关键字查找");
			System.out.println("2.列表查找");
			System.out.println("3.查看购物车");
			System.out.println("----------------------------");
			System.out.print("输入选择：");
			choice = sc.next();
			String regex = "[0-3]";
			if (choice.matches(regex))
			{ 
				int info = Integer.parseInt(choice);
				switch(info){
					case 1:						
					case 2:
						if(info==1){
							System.out.println("\n--请输入商品关键字--");						
							int gid =new GoodsDb().getKeyGoodsState();
							
						}
						else{
							new GoodsPage().displayGoodsPage(2);
						}
						System.out.print("--按商品ID选择商品--\n:");
						int shoppingGid = new Getint().getint();					
						ArrayList<Goods> goodsList = new GoodsDb().getGoodsInfo(shoppingGid, null);
						if (goodsList == null || goodsList.size() == 0)
						{
							System.err.println("查无此商品 ！");
						}
						else 
						{
							Goods goods = goodsList.get(0);
							int gNum = goods.getG_num();
							if(gNum==0){
								System.out.println("该商品为空！");
							}
							else{
								System.out.print("请输入购买数量:");		
								Getint getInt=new Getint();
								int choicegoodsNum = getInt.getint();							
								while(choicegoodsNum > gNum){
									System.out.println("仓库储备不足！");
									System.out.print("请重新输入购买数量:");
									choicegoodsNum=getInt.getint();
								}
								if(choicegoodsNum!=0){
									//GSales表操作
									GSales gSales = new GSales(goods.getG_id(),sId,choicegoodsNum);
									boolean insert = new GSalesDb().addGSales(gSales);	
									//对goods表操作
									int goodsNewNum = gNum - choicegoodsNum; //现在goods表中该商品数量
									Goods newGoods = new Goods(goods.getG_id(),goods.getG_name(),goods.getG_price(),goodsNewNum);
									boolean update = new GoodsDb().updateGoods(3,newGoods);										
									if (!update||!insert)
									{
										System.err.println("数据库操作失败");
									}
									addcartList(gSales);//添加
									System.out .println("已加入购物车.");
								}
								else
									System.out.println("购买失败！");
							}
							
						}
						break;
					case 3:
						if(cartList.size()<=0){
							System.out.println("购物车为空！");
						}
						else{
							System.out.println("购物车列表:");
							System.out.println("---------------------------------------------------");
							System.out.println("商品名称\t商品单价\t购买数量");
							for (int i = 0,length = cartList.size(); i < length; i++)
							{
								ArrayList<Goods> array = new GoodsDb().getGoodsInfo(cartList.get(i).getGId(), null);
								Goods goods=array.get(0);
								System.out.println(goods.getG_name()+"\t"+goods.getG_price()+"$\t"+cartList.get(i).getSNum());
							}
							System.out.println("---------------------------------------------------");
							System.out.println("确定进行结算：Y/N进入删除操作：O:");
							String choShopping = sc.next();
							if ("y".equals(choShopping) || "Y".equals(choShopping))
							{
								float allPrice= new GSalesPage().getallPrice();
								System.out.println("总价："+allPrice);
								System.out.println("实际缴费金额:");
								float balance = getfloat();
								while(balance<allPrice){
									System.out.println("缴纳金额不足！");
									System.out.print("请重新输入缴纳金额:");
									 balance = getfloat();
								}
								float sub = balance-allPrice;//差价
								System.out.println("找零："+sub);
								cartList=null;
								System.out.println("输入0返回首页，输入其他退出！");
								String next=sc.next();
								if(next.equals("0")){
									MainPage.loginPage();
								}
								System.out.println("------------------");
							 	System.err.println("您已经退出系统!");
							 	System.exit(1);
							}
							if ("o".equals(choShopping) || "O".equals(choShopping)){
								System.out.println("系统升级中！");
							}
						}
						break;		
				}
			}
		}while(true);
	}

}
