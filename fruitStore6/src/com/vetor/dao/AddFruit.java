package com.vetor.dao;

import java.util.ArrayList;
import java.util.Scanner;

import com.vetor.been.Fruit;
import com.vetor.service.ReadData;
import com.vetor.service.WriteData;

public class AddFruit {
	public static void addFruit(String path){
		ArrayList<Fruit> arr=new ArrayList<Fruit>();
		try {
			ReadData.readFruit(path, arr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		String name;
		boolean flag=false;
		System.out.println("请输入名字");
		while(true){
			name=sc.nextLine();
			for(int x=0;x<arr.size();x++){
				if(arr.get(x).getName().equals(name)){
					flag=true;
					break;
				}
				else{
					flag=false;
				}
			}
			if(flag){
				System.out.println("名称重复，请重新输入名字");
			}
			else{
				break;
			}
		}
		
		System.out.println("请输入价格");
		String price=sc.nextLine();
		System.out.println("请输入单位");
		String unit = sc.nextLine();
		System.out.println("请输入数量");
		String count=sc.nextLine();
		Fruit s=new Fruit();
		s.setName(name);
		s.setPrice(price);
		s.setUnit(unit);
		s.setCount(count);
		arr.add(s);
		WriteData.writeData(path,arr);
		System.out.println("添加成功");
	}
}
