package com.vetor.dao;

import java.util.ArrayList;
import java.util.Scanner;

import com.vetor.been.Fruit;
import com.vetor.service.ReadData;
import com.vetor.service.WriteData;

public class FindFruit {
	public static void findFruit(String path){
		ArrayList<Fruit> arr=new ArrayList<Fruit>();
		try {
			ReadData.readFruit(path, arr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("resource")
		Scanner sc =new Scanner(System.in);
		System.out.println("请输入你要查看的水果名字");
		while(true){
			String name=sc.nextLine();
			int index=-1;
			for(int x=0 ; x<arr.size();x++){
				Fruit s=arr.get(x);
				if(s.getName().equals(name)){
					index=x;
					break;
				}
			}
			if(index>=0){
				Fruit s=arr.get(index);
				System.out.println(s.getName()+"\t"+s.getPrice()+"\t"+s.getUnit()+"\t"+s.getCount());
				WriteData.writeData(path,arr);
				//System.out.println(index.name+"\t"+index.price+"\t"+index.unit+"\t"+index.count);
				break;
			}
			else{
				System.out.println("没有此名字，请重新输入");
			}
		}
		
		
	}
}
