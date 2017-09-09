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
		System.out.println("*******����ۻ�Ա����*******");
		System.out.println("����ۻ�Ա-����");
		String sName = sc.next();		
		System.out.println("����ۻ�Ա-����");
		String sPsd = sc.next();		
		System.out.println("***************************");
		SalesManDb sDb =new SalesManDb();
		int count=sDb.getSalesManNum()+1;
		String sId=""+count;
		SalesMan salesMan = new SalesMan(sId,sName,sPsd);
		boolean bool = new SalesManDb().addSalesMan(salesMan);	
		if (bool)
			System.out.println("��ӳɹ�!");
		else 
			System.out.println("����ۻ�Աʧ��");	
		do
		{
			System.out.print("��0������һ���˵�,1�˳���");
			String choice =sc.next();
			if ("0".equals(choice))
			{
				MainPage.salesManManagementPage();
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
	
	public static void updateSalesManPage()
	{
		Scanner sc =new Scanner(System.in);
		System.out.println("*******�����ۻ�Ա����*******");
		System.out.print("�����ۻ�ԱID:");
		String sId = sc.next();
		ArrayList<SalesMan> salesManList = new SalesManDb().getSalesMan(sId);
		if (salesManList.size() <= 0)
		{
			System.out.println("��������");
		}else 
		{
			//��ʾ��Ϣ
			System.out.println("�ۻ�Ա��Ϣ:");
			System.out.println("------------------------------------------");
			System.out.println("�ۻ�ԱID\t�ۻ�Ա����\t�ۻ�Ա����");					
			SalesMan salesMan = salesManList.get(0); //����ľ�ȷ�����У�ֻ�ܷ���һ����ֵ�����������
			System.out.println(salesMan.getsId()+"\t"+salesMan.getsName()+"\t\t"+salesMan.getsPsd());
			System.out.println("------------------------------------------");
			System.out.println("---��ѡ����Ҫ���ĵ�����----");
			System.out.println("1.�����ۻ�Ա����");
			System.out.println("2.�����ۻ�Ա����");
			System.out.print("����ѡ��");
			Getint getInt= new Getint();		
			int choice=getInt.getint();
			while(choice<1||choice>2){
				System.out.println("���볬����Χ��");
				choice=getInt.getint(); 
			}
			switch (choice)
			{
				case 1:
					System.out.print("��������:");
					String sNewName = sc.next();					
					SalesMan salesmanName = new SalesMan(salesMan.getsId(),sNewName,salesMan.getsPsd());
					boolean boolsName = new SalesManDb().updateSalesMan(1, salesmanName);							
					if (boolsName)								
						System.out.println("�ɹ������ۻ�Ա����!");								
					else 							
						System.err.println("�����ۻ�Ա����ʧ��!");									
					break;
				case 2:
					System.out.print("��������:");
					String sNewPsd = sc.next();									
					SalesMan salesmanPsd= new SalesMan(salesMan.getsId(),salesMan.getsName(),sNewPsd);
					boolean boolsPsd = new SalesManDb().updateSalesMan(2, salesmanPsd);								
					if (boolsPsd)
						System.out.println("�ɹ������ۻ�Ա����!");
					else 
						System.err.println("�����ۻ�Ա����ʧ��!");
					break;
				default:					
					break;
				}
			}
		System.out.println("-------------------");
		do
		{
			System.out.print("��0������һ���˵�,1�˳���");
			String choice =sc.next();
			if ("0".equals(choice))
			{
				MainPage.salesManManagementPage();
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

	
	public static void deleteSalesManPage()
	{
		System.out.println("********* ɾ���ۻ�Ա����*********");
		System.out.print("��������Ҫɾ�����ۻ�ԱID:");
		Scanner sc =new Scanner(System.in);
		String sId = sc.next();		
		ArrayList<SalesMan> salesManList = new SalesManDb().getSalesMan(sId);
		if (salesManList.size() <= 0){
			System.out.println("��������");	
			MainPage.salesManManagementPage();
		}
		else 
		{			
			System.out.println("ɾ���ۻ�Ա��Ϣ:");
			System.out.println("------------------------------------------");
			System.out.println("�ۻ�ԱID\t�ۻ�Ա����\t�ۻ�Ա����");	
			SalesMan salesMan = salesManList.get(0);
			System.out.println(salesMan.getsId()+"\t"+salesMan.getsName()+"\t"+salesMan.getsPsd());
			System.out.println("------------------------------------------");
			System.out.println("ȷ��ɾ�����ۻ�Ա��Y/N");
			String choice = sc.next();
			while(!"y".equals(choice)&&!"Y".equals(choice)
					&&!"N".equals(choice)&&!"n".equals(choice)){
				System.out.println("��������,����������!!");
				choice = sc.next();
			}
			if ("y".equals(choice) || "Y".equals(choice))
			{
				boolean boolsDelete = new SalesManDb().deleteSalesMan(sId);					
				if (boolsDelete)
					System.out.println("�ɹ��h�����ۻ�Ա��");
				else 
					System.out.println("�h�����ۻ�Աʧ�ܣ�");
			}
			else if("N".equals(choice) || "n".equals(choice)) 
				MainPage.salesManManagementPage();
		}
		do
		{
			System.out.print("��0������һ���˵�,1�˳���");
			String choice =sc.next();
			if ("0".equals(choice))
			{
				MainPage.salesManManagementPage();
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

	

	public static void searchSalesManPage()
	{
		System.out.println("********* ��ѯ�ۻ�Ա����*********");
		System.out.println("Ҫ��ѯ���ۻ�ԱID");
		Scanner sc =new Scanner(System.in);
		String sId = sc.next();		
		ArrayList<SalesMan> salesManList = new SalesManDb().getSalesMan(sId);			
		if (salesManList.size() <=0)
			System.err.println("û�ˣ�");
		else 
		{
			System.out.println("�����ۻ�Ա��Ϣ:");
			System.out.println("------------------------------------------");
			System.out.println("�ۻ�Ա���\t�ۻ�Ա����\t�ۻ�Ա����");	
			SalesMan salesMan = salesManList.get(0);
			System.out.println(salesMan.getsId()+"\t\t"+salesMan.getsName()+"\t\t"+salesMan.getsPsd());
			System.out.println("------------------------------------------");
		}
		do
		{
			System.out.print("��0������һ���˵�,1�˳���");
			String choice =sc.next();
			if ("0".equals(choice))
			{
				MainPage.salesManManagementPage();
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


	public static void displaySalesManPage()//�ۻ�Ա�б�
	{
		ArrayList<SalesMan> salesManList = new SalesManDb().displaySalesMan();
		if (salesManList.size() <= 0)
		{
			System.err.println("�ۻ�Ա�б�Ϊ�գ���");
			MainPage.salesManManagementPage();		
		}
		else 
		{ 
			System.out.println("�����ۻ�Ա�б�:");
			System.out.println("------------------------------------------");
			System.out.println("�ۻ�Ա���\t�ۻ�Ա����\t�ۻ�Ա����");
			for (int i = 0,length = salesManList.size(); i < length; i++)
			{
				SalesMan salesMan = salesManList.get(i);
				System.out.println(salesMan.getsId()+"\t\t"+salesMan.getsName()+"\t\t"+salesMan.getsPsd());
			}
			System.out.println("------------------------------------------");	
			do
			{
				System.out.print("��0������һ���˵�,1�˳���");
				Scanner sc=new Scanner(System.in);
				String choice =sc.next();
				if ("0".equals(choice))
				{
					MainPage.salesManManagementPage();
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


