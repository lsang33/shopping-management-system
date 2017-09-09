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
	
	public void stockGoods(){
		System.out.println("*******��������*******");
		System.out.println("��������ƷID");
		Scanner sc=new Scanner(System.in);
		int gId=new Getint().getint();
		GoodsDb goodsdb=new GoodsDb();
		ArrayList<Goods> goodsList =goodsdb.getGoodsInfo(gId, null);
		if (goodsList == null || goodsList.size() == 0)
		{
			System.out.println("���޴���Ʒ ��");
			System.out.println("");
			stockGoods();	
		}
		System.out.println("��Ʒ��Ϣ:");
		System.out.println("------------------------------------------");
		System.out.println("��ƷID\t��Ʒ����\t��Ʒ�۸�\t��Ʒ����");					
		Goods goods = goodsList.get(0);
		System.out.println(goods.getG_id()+"\t"+goods.getG_name()+"\t"+goods.getG_price()+"\t"+goods.getG_num());
		System.out.println("------------------------------------------");
		System.out.println("������������");
		ArrayList<Supplier> array= new StockGoodsDb().displaySupplier();
		System.out.println("------------------------------------------");
		System.out.println("������ID\t����������");			
		for(int i=0;i<array.size();i++){
			System.out.println(array.get(i).getSupId()+"\t"+array.get(i).getSupName());
		}
		System.out.println("------------------------------------------");
		System.out.println("���������IDѡ������̣�");
		Getint getInt=new Getint();
		int choice = getInt.getint();
		int supp_num= new StockGoodsDb().getSuppNum();
		while(choice<0||choice>supp_num){
			System.out.println("���볬����Χ��");
			System.out.println("���������룺");
			choice = getInt.getint();
		}
		System.out.println("�������������");
		int stock_num = getInt.getint();
		System.out.println("����Y����ȷ������������������һҳ.");
		String choStock=sc.next();
		if("y".equals(choStock) || "Y".equals(choStock))
		{
			int new_num=goods.getG_num()+stock_num;
			Goods good =new Goods(gId,goods.getG_name(),goods.getG_price(),new_num);
			boolean bool=goodsdb.updateGoods(3, good);
			if(bool){
				System.out.println("�����ɹ���");	
			}
			else
				System.out.println("����ʧ�ܣ�");
			do{
				System.out.print("��0������һ���˵�,1�˳���");
				sc=new Scanner(System.in);
				String choice_ =sc.next();
				if ("0".equals(choice_))
				{
					MainPage.goodsManagementPage();
				}
				if ("1".equals(choice_))
				{
					System.out.println("----------------------------");
				 	System.out.println("���Ѿ��˳�ϵͳ!");
				 	System.exit(1);
				}
				System.out.println("��������");
			} while (true);
		}
		else{
			MainPage.goodsManagementPage();
		}
	}
	
	public static void  addGoodsPage()//�����Ʒ
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("*******�����Ʒ����*******");
		System.out.print("������Ʒ���ƣ�");
		String goodsName = sc.next();
		System.out.print("������Ʒ�۸�");
		float goodsPrice = getfloat();
		System.out.print("������Ʒ������");
		int goodsNumber = new Getint().getint();
		int id=new GoodsDb().getGoodsNum()+1;
		//System.out.println(id+goodsName+goodsPrice+goodsNumber);
		Goods goods  = new Goods(id,goodsName,goodsPrice,goodsNumber);
		boolean bool = new GoodsDb().addGoods(goods);	
		if (bool)
			System.out.println("�ɹ������Ʒ!");			
		else 
			System.out.println("�����Ʒʧ�ܣ�");
		System.out.println("**********************");
		do{
			System.out.print("��0������һ���˵�,1�˳���");
			sc=new Scanner(System.in);
			String choice_ =sc.next();
			if ("0".equals(choice_))
			{
				MainPage.goodsOpPage();
			}
			if ("1".equals(choice_))
			{
				System.out.println("----------------------------");
			 	System.out.println("���Ѿ��˳�ϵͳ!");
			 	System.exit(1);
			}
			System.out.println("��������");
		} while (true);
	}

	public static void  upateGoodsPage()
	{
		System.out.println("*******�޸���Ʒ����*******");
		System.out.println("��������ƷID");
		int gId=new Getint().getint();
		/////////////////////////////////////////////////
		ArrayList<Goods> goodsList =new GoodsDb().getGoodsInfo(gId, null);
		if (goodsList.size() <= 0)
		{
			System.err.println("��������");	
			MainPage.goodsOpPage();
		}		
		else
		{
			System.out.println("��Ʒ��Ϣ:");
			System.out.println("------------------------------------------");
			System.out.println("��ƷID\t��Ʒ����\t��Ʒ�۸�\t��Ʒ����");					
			Goods goods = goodsList.get(0);
			System.out.println(goods.getG_id()+"\t"+goods.getG_name()+"\t"+goods.getG_price()+"\t"+goods.getG_num());
			System.out.println("------------------------------------------");
			System.out.println("---��ѡ����Ҫ���ĵ�����----");;
			System.out.println("1.������Ʒ����");
			System.out.println("2.������Ʒ�۸�");
			System.out.println("3.������Ʒ����");
			System.out.println("-------------------");
			System.out.print("ѡ��");
			Getint getInt= new Getint();		
			int choice=getInt.getint();
			while(choice<1||choice>3){
				System.out.println("���볬����Χ��");
				choice=getInt.getint(); 
			}
			switch (choice)
			{
				 case 1:
					 Scanner sc=new Scanner(System.in);
					 System.out.print("��Ʒ������:");
					 String gname = sc.next();
					 Goods goodsName = new Goods(goods.getG_id(),gname,goods.getG_price(),goods.getG_num());
					 boolean boolName = new GoodsDb().updateGoods(1, goodsName);
					 if (boolName)
						 System.err.println("�ɹ�������Ʒ����");
					 else 
					 {
						 System.err.println("������Ʒ��ʧ�ܣ�");
					 }
					 break;
				 case 2:
					 System.out.print("��Ʒ�¼۸� ");
					 float gprice = getfloat();
					 Goods  goodsPrice = new Goods(goods.getG_id(),goods.getG_name(),gprice,goods.getG_num());
					 boolean boolPrice = new GoodsDb().updateGoods(2,goodsPrice);			
					 if (boolPrice)
					 {
						 System.err.println("�ɹ�������Ʒ�۸�");
					 }
					 else 
					 {
						 System.err.println("������Ʒ�۸�ʧ�ܣ�");
					 }
					 break;
				 case 3:
					 System.out.print("��Ʒ������: ");
					 int gNum = new Getint().getint();
					 Goods  goodsNum= new Goods(goods.getG_id(),goods.getG_name(),goods.getG_price(),gNum);
					 boolean boolNum = new GoodsDb().updateGoods(3,goodsNum);
					 if (boolNum)
					 {
						 System.out.println("�ɹ�������Ʒ������");
					 }
					 else 
					 {
						 System.out.println("������Ʒ����ʧ�ܣ�");
					 }
			
					 break;
				 default:
					 System.err.println("��������ȷ��ѡ��");
					 break;
			 }		
			do{
				System.out.print("��0������һ���˵�,1�˳���");
				Scanner sc=new Scanner(System.in);
				String choice_ =sc.next();
				if ("0".equals(choice_))
				{
					MainPage.goodsOpPage();
				}
				if ("1".equals(choice_))
				{
					System.out.println("----------------------------");
				 	System.out.println("���Ѿ��˳�ϵͳ!");
				 	System.exit(1);
				}
				System.out.println("��������");
			} while (true);
		}
		
	}
	

	public static void deleteGoodsPage()
	{
		System.out.println("********* ɾ����Ʒ����*********");
		System.out.print("��������Ҫɾ������ƷID:");
		int gId = new Getint().getint();		
		ArrayList<Goods> goodsList = new GoodsDb().getGoodsInfo(gId, null);
		if (goodsList.size() <= 0){
			System.err.println("��������");
			MainPage.goodsOpPage();
		}
		else 
		{			
			System.out.println("ɾ����Ʒ��Ϣ:");
			System.out.println("------------------------------------------");
			System.out.println("��ƷID\t��Ʒ����\t��Ʒ�۸�\t��Ʒ����");	
			Goods goods = goodsList.get(0);
			System.out.println(goods.getG_id()+"\t"+goods.getG_name()+"\t"+goods.getG_price()
					+"\t"+goods.getG_num());
			System.out.println("------------------------------------------");
		}
		System.out.println("ȷ��ɾ������Ʒ��Y/N");
		Scanner sc=new Scanner(System.in);
		String choice = sc.next();
		while(!"y".equals(choice)&&!"Y".equals(choice)
				&&!"N".equals(choice)&&!"n".equals(choice)){
			System.err.println("��������,����������!!");
			choice = sc.next();
		}
		if ("y".equals(choice) || "Y".equals(choice))
		{
			boolean boolsDelete = new GoodsDb().deleteGoods(gId);				
			if (boolsDelete)
				System.out.println("�ɹ��h������Ʒ��");
			else 
				System.out.println("�h������Ʒʧ�ܣ�");
		}
		else if("N".equals(choice) || "n".equals(choice)) 
			MainPage.salesManManagementPage();
		do{
			System.out.print("\n��0������һ���˵�,1�˳���");
			sc=new Scanner(System.in);
			String choice_ =sc.next();
			if ("0".equals(choice_))
			{
				MainPage.goodsOpPage();
			}
			if ("1".equals(choice_))
			{
				System.out.println("----------------------------");
			 	System.out.println("���Ѿ��˳�ϵͳ!");
			 	System.exit(1);
			}
			System.out.println("��������");
		} while (true);
	}

	public static void queryGoodsPage()
	{
		System.out.println("*******��ѯ��Ʒ����*******");
		System.out.println("1.��Ʒ��������");
		System.out.println("2.��Ʒ�۸�����");
		System.out.println("3.��Ʒ�ؼ��ֲ�ѯ");
		System.out.println("����0������һ��.");	
		System.out.println("**********************");
		System.out.print("ѡ��");
		Getint getInt= new Getint();		
		int choice=getInt.getint();
		while(choice<0||choice>3){
			System.out.println("���볬����Χ��");
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
					 System.out.println("---��Ʒ�ؼ��ֲ�ѯ----");
					 System.out.print("��������Ʒ�ؼ���:");					 
				 }
				 ArrayList<Goods> goodsList = new GoodsDb().queryGoods(choice);
				 System.out.println(goodsList.get(0).getG_name());
				 if (goodsList == null || goodsList.size() <= 0)
				 {
					 System.err.println("��ѯ����Ʒ������");
					 queryGoodsPage();
				 } 
				 else
				 {
					if (choice == 1)	
						System.out.println("��Ʒ���������б�:");					
					else if (choice == 2) 					
						 System.out.println("��Ʒ�۸������б�:");					
					else					
						System.out.println("�������ҵ���Ʒ����:");					
					System.out.println("��Ʒ���\t��Ʒ����\t��Ʒ�۸�\t��Ʒ����\t��ע");
					for (int i = 0,length = goodsList.size(); i < length; i++)
					{
						Goods goods = goodsList.get(i);
						System.out.print(goods.getG_id()+"\t"+goods.getG_name()+"\t"+goods.getG_price()+"\t"+goods.getG_num());
						int gnum = goods.getG_num();
						if (gnum ==0)
						{
							System.out.println("\t����Ʒ���ۿգ�");
						}
						else if (gnum<10)
						{
							System.out.println("\t����Ʒ�Ѳ���10��");
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
			System.out.print("\n��0������һ���˵�,1�˳���");
			Scanner sc=new Scanner(System.in);
			String choice_ =sc.next();
			if ("0".equals(choice_))
			{
				MainPage.goodsOpPage();
			}
			if ("1".equals(choice_))
			{
				System.out.println("----------------------------");
			 	System.out.println("���Ѿ��˳�ϵͳ!");
			 	System.exit(1);
			}
			System.out.println("��������");
		} while (true);
	}

	public static void displayGoodsPage(int side)
	{
		System.out.println("������Ʒ�б�");
		System.out.println("-----------------------------------------------------------");
		ArrayList<Goods> goodsList = new GoodsDb().displayGoods();
		if (goodsList.size() <= 0)
		{
			System.err.println("���Ϊ�գ�");
		}
		else 
		{
			System.out.println("��Ʒ���\t��Ʒ����\t��Ʒ�۸�\t��Ʒ����\t��ע");
			for (int i = 0,length = goodsList.size(); i < length; i++)
			{
				Goods goods = goodsList.get(i);
				System.out.print(goods.getG_id()+"\t"+goods.getG_name()+"\t"+goods.getG_price()+"$\t"+goods.getG_num());					
				int gNum = goods.getG_num();
				if (gNum==0)
				{
					System.out.println("\t����Ʒ���ۿգ�");
				}
				else if (gNum<10) 
				{
					System.out.println("\t����Ʒ�Ѳ���10��");
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
					System.out.print("��0������һ���˵�,1�˳���");
					Scanner sc=new Scanner(System.in);
					String choice =sc.next();
					if ("0".equals(choice))
					{
						MainPage.goodsOpPage();
					}
					if ("1".equals(choice))
					{
						System.out.println("----------------------------");
					 	System.out.println("���Ѿ��˳�ϵͳ!");
					 	System.exit(1);
					}
					System.out.println("��������");
				} while (true);
			}
		}
	}
}
