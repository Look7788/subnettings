package com.vetor.dao;

import java.util.ArrayList;
import java.util.Scanner;

import com.vetor.been.Fruit;
import com.vetor.service.ReadData;
import com.vetor.service.WriteData;

public class UpdateFruit {
	public static void updateFruit(String path){
		ArrayList<Fruit> arr=new ArrayList<Fruit>();
		try {
			ReadData.readFruit(path, arr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("resource")
		Scanner sc =new Scanner(System.in);
		System.out.println("请输入你要修改的水果名字");
		int index=-1;
		while(true){
			String name =sc.nextLine();
			for(int x=0;x<arr.size();x++){
			    Fruit s =arr.get(x);
				if(s.getName().equals(name)){
					index=x;
					break;
				}
			}
			if(index>=0){
				System.out.println("请输入新价格");
				String price =sc.nextLine();
				System.out.println("请输入新单位");
				String unit=sc.nextLine();
				System.out.println("请输入新数量");
				String count =sc.nextLine();
			  Fruit s=new Fruit();
				s.setName(name);
				s.setPrice(price);
				s.setUnit(unit);
				s.setCount(count);
				arr.set(index, s);
				WriteData.writeData(path,arr);
				System.out.println("修改成功");
				break;
			}
			else{
				System.out.println("没有此名字，请重新输入");
			}
		}
		
	}
}
