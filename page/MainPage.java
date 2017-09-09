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
	
	public static void loginPage(){//��һ����
		System.out.println("************����ϵͳ***********");
		System.out.println("1.����Ա��¼");
		System.out.println("2.����Ա��¼");
		System.out.println("3.�û���������");
		System.out.println("����0�˳�.");
		System.out.println("******************************");
		System.out.print("ѡ��");
		Getint getInt= new Getint();		
		int choice=getInt.getint();
		while(choice<0||choice>3){
			System.out.println("���볬����Χ��");
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
			 	System.err.println("���Ѿ��˳�ϵͳ!");
			 	System.exit(1);
			 	break;
			default:
				break;
		}
	}
	
	public static void adminPage(){
		System.out.println("------��----����Ա����----------");
		System.out.println("1.��Ʒά��");
		System.out.println("2.��Ʒ����");
		System.out.println("3.�ۻ�Ա����");
		System.out.println("����0�˳�.");
		System.out.println("----------------------------");
		System.out.print("ѡ��");
		Getint getInt= new Getint();		
		int choice=getInt.getint();
		while(choice<0||choice>3){
			System.out.println("���볬����Χ��");
			choice=getInt.getint(); 
		}
		switch(choice){
			case 0:
				System.out.println("----------------------------");
				System.out.println("���Ѿ��˳�ϵͳ!");
				System.exit(1);
			case 1:goodsOpPage();break;
			case 2:goodsManagementPage();break;
			case 3:salesManManagementPage();break;
			default:
				break;
		}
	}
	
	public static void goodsOpPage()//��Ʒ��������
	{	
		System.out.println("----------------------------");
		System.out.println("1.�����Ʒ");
		System.out.println("2.������Ʒ");
		System.out.println("3.ɾ����Ʒ");
		System.out.println("4.��ѯ��Ʒ");
		System.out.println("5.��ʾ������Ʒ");
		System.out.println("����0������һ��.");
		System.out.println("----------------------------");
		System.out.print("ѡ��");
		Getint getInt= new Getint();		
		int choice=getInt.getint();
		while(choice<0||choice>5){
			System.out.println("���볬����Χ��");
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

	public static void goodsManagementPage()//��Ʒ�������
	{
		System.out.println("----------------------------");
		System.out.println("1.��ʾ�����б�");
		System.out.println("2.��������");
		System.out.println("----------------------------");
		System.out.println("����0������һ��.");		
		System.out.print("ѡ��");
		Getint getInt= new Getint();		
		int choice=getInt.getint();
		while(choice<0||choice>2){
			System.out.println("���볬����Χ��");
			choice=getInt.getint(); 
		}
		
		switch(choice){
		case 0:
			adminPage();
			System.exit(1);
		case 1: 
			new  GSalesDb().displayGSales();
			do{
				System.out.print("\n��0������һ���˵�,1�˳���");
				Scanner sc=new Scanner(System.in);
				String choice_ =sc.next();
				if ("0".equals(choice_))
				{
					MainPage.adminPage();
				}
				if ("1".equals(choice_))
				{
					System.out.println("----------------------------");
				 	System.out.println("���Ѿ��˳�ϵͳ!");
				 	System.exit(1);
				}
				System.out.println("��������");
			} while (true);
		case 2:
			new GoodsPage().stockGoods();
			break;
		default:break;
		}
	} 
	
	public static void salesManManagementPage()//�ۻ�Ա�������
	{
		System.out.println("----------------------------");
		System.out.println("1.����ۻ�Ա");
		System.out.println("2.�����ۻ�Ա");
		System.out.println("3.ɾ���ۻ�Ա");
		System.out.println("4.��ѯ�ۻ�Ա");
		System.out.println("5.��ʾ�����ۻ�Ա");
		System.out.println("����0������һ��.");	
		System.out.println("----------------------------");
		System.out.println("ѡ��");
		Getint getInt= new Getint();		
		int choice=getInt.getint();
		while(choice<0||choice>5){
			System.out.println("���볬����Χ��");
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
