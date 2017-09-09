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
		while(b){//��֤����Ϊ����
			try{
				m=sc.nextFloat();
				b=false;
			}
			catch(Exception e){
				System.out.print("��������:");
				sc.nextLine();//��֤����ѭ��
			}
		}
		return m; 
	}
	
	public static void shoppingSettlementPage(int salesManSid)//����
	{
		System.out.println("*******�������*******");
		do
		{	
			System.out.println("����Y��ʼ�������.��N�����˻���¼����.");
			Scanner sc=new Scanner(System.in);
			String choice = sc.next();
			if ("N".equals(choice)||"n".equals(choice))
			{
				MainPage.loginPage();
				
			}
			else if("Y".equals(choice)||"y".equals(choice)) 
			{
				System.out.println("\n--��������Ʒ�ؼ���--");						
				int gid =new GoodsDb().getKeyGoodsState();
				switch (gid)
				{
					case -3://�޴���Ʒ
						break;
					case -1://�ۿ�
						System.err.println("����Ʒ���ۿ�.");
						break;	
					default:
						System.out.println("--����ƷIDѡ����Ʒ--");
						int shoppingGid = new Getint().getint();					
						ArrayList<Goods> goodsList = new GoodsDb().getGoodsInfo(shoppingGid, null);
						if (goodsList == null || goodsList.size() == 0)
						{
							System.err.println("���޴���Ʒ ��");
						}
						else 
						{
							Goods goods = goodsList.get(0);
							int gNum = goods.getG_num();	
							float gPrice = goods.getG_price(); 							
							System.out.print("�����빺������:");
							do
							{
								int choicegoodsNum = new Getint().getint();//��ȡ�û�Ҫ���������									
								if (choicegoodsNum > gNum)
								{
									System.out.println("�ֿⴢ�����㣡");
									System.out.print("���������빺������:");
								}
								else//�����û�������
								{
									float allPrice = choicegoodsNum*goods.getG_price();
									System.out.println("���ﳵ����:");
									System.out.println("---------------------------------------------------");
									System.out.println("��Ʒ����\t��Ʒ����\t��������\t�ܼ�");
									System.out.println(goods.getG_name()+"\t"+gPrice+" $\t"+choicegoodsNum+"\t"+allPrice+" $");
									System.out.println("---------------------------------------------------");
									do
									{
										System.out.println("ȷ�Ϲ���Y/N");
										String choShopping = sc.next();
										if ("y".equals(choShopping) || "Y".equals(choShopping))
										{
											System.out.println("�ܼۣ�"+allPrice+" $");
											System.out.println("ʵ�ʽɷѽ��");
											do
											{
												float balance = getfloat();
												float sub = balance-allPrice; //���
												if (sub < 0)
												{
													System.err.println("���ɽ��㣡");
													System.out.print("������������ɽ��:");
												}else
												{																														
													//��sales�����
													GSales gSales = new GSales(goods.getG_id(),salesManSid,choicegoodsNum);
													boolean insert = new GSalesDb().addGSales(gSales);															
													//��goods�����
													int goodsNewNum = gNum - choicegoodsNum; //����goods���и���Ʒ����
													Goods newGoods = new Goods(goods.getG_id(),goods.getG_name(),goods.getG_price(),goodsNewNum);
													boolean update = new GoodsDb().updateGoods(3,newGoods);										
													if (update && insert)
													{
														System.out.println("���㣺"+sub);													
													}
													else 
													{
														System.err.println("��֧��ʧ�ܣ�");//���ݿ����ʧ��
													}
													shoppingSettlementPage(salesManSid);//�����ת�����������ҳ��		
												}
											} while (true);	
														
										}
										else if ("N".equals(choShopping) || "n".equals(choShopping)) 
										{
											shoppingSettlementPage(salesManSid);
										}
										System.err.println("��������");
									} while (true);
								}
							} while (true);
						}					
						break;
				}
			}
			else
			{
				System.err.println("��������");
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
	
	public float getallPrice(){//����ܼ�
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
			System.out.println("\n********�û���������***********");
			System.out.println("----------------------------");
			System.out.println("1.�ؼ��ֲ���");
			System.out.println("2.�б����");
			System.out.println("3.�鿴���ﳵ");
			System.out.println("----------------------------");
			System.out.print("����ѡ��");
			choice = sc.next();
			String regex = "[0-3]";
			if (choice.matches(regex))
			{ 
				int info = Integer.parseInt(choice);
				switch(info){
					case 1:						
					case 2:
						if(info==1){
							System.out.println("\n--��������Ʒ�ؼ���--");						
							int gid =new GoodsDb().getKeyGoodsState();
							
						}
						else{
							new GoodsPage().displayGoodsPage(2);
						}
						System.out.print("--����ƷIDѡ����Ʒ--\n:");
						int shoppingGid = new Getint().getint();					
						ArrayList<Goods> goodsList = new GoodsDb().getGoodsInfo(shoppingGid, null);
						if (goodsList == null || goodsList.size() == 0)
						{
							System.err.println("���޴���Ʒ ��");
						}
						else 
						{
							Goods goods = goodsList.get(0);
							int gNum = goods.getG_num();
							if(gNum==0){
								System.out.println("����ƷΪ�գ�");
							}
							else{
								System.out.print("�����빺������:");		
								Getint getInt=new Getint();
								int choicegoodsNum = getInt.getint();							
								while(choicegoodsNum > gNum){
									System.out.println("�ֿⴢ�����㣡");
									System.out.print("���������빺������:");
									choicegoodsNum=getInt.getint();
								}
								if(choicegoodsNum!=0){
									//GSales�����
									GSales gSales = new GSales(goods.getG_id(),sId,choicegoodsNum);
									boolean insert = new GSalesDb().addGSales(gSales);	
									//��goods�����
									int goodsNewNum = gNum - choicegoodsNum; //����goods���и���Ʒ����
									Goods newGoods = new Goods(goods.getG_id(),goods.getG_name(),goods.getG_price(),goodsNewNum);
									boolean update = new GoodsDb().updateGoods(3,newGoods);										
									if (!update||!insert)
									{
										System.err.println("���ݿ����ʧ��");
									}
									addcartList(gSales);//���
									System.out .println("�Ѽ��빺�ﳵ.");
								}
								else
									System.out.println("����ʧ�ܣ�");
							}
							
						}
						break;
					case 3:
						if(cartList.size()<=0){
							System.out.println("���ﳵΪ�գ�");
						}
						else{
							System.out.println("���ﳵ�б�:");
							System.out.println("---------------------------------------------------");
							System.out.println("��Ʒ����\t��Ʒ����\t��������");
							for (int i = 0,length = cartList.size(); i < length; i++)
							{
								ArrayList<Goods> array = new GoodsDb().getGoodsInfo(cartList.get(i).getGId(), null);
								Goods goods=array.get(0);
								System.out.println(goods.getG_name()+"\t"+goods.getG_price()+"$\t"+cartList.get(i).getSNum());
							}
							System.out.println("---------------------------------------------------");
							System.out.println("ȷ�����н��㣺Y/N����ɾ��������O:");
							String choShopping = sc.next();
							if ("y".equals(choShopping) || "Y".equals(choShopping))
							{
								float allPrice= new GSalesPage().getallPrice();
								System.out.println("�ܼۣ�"+allPrice);
								System.out.println("ʵ�ʽɷѽ��:");
								float balance = getfloat();
								while(balance<allPrice){
									System.out.println("���ɽ��㣡");
									System.out.print("������������ɽ��:");
									 balance = getfloat();
								}
								float sub = balance-allPrice;//���
								System.out.println("���㣺"+sub);
								cartList=null;
								System.out.println("����0������ҳ�����������˳���");
								String next=sc.next();
								if(next.equals("0")){
									MainPage.loginPage();
								}
								System.out.println("------------------");
							 	System.err.println("���Ѿ��˳�ϵͳ!");
							 	System.exit(1);
							}
							if ("o".equals(choShopping) || "O".equals(choShopping)){
								System.out.println("ϵͳ�����У�");
							}
						}
						break;		
				}
			}
		}while(true);
	}

}
