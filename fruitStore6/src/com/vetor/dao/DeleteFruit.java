package com.vetor.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.vetor.been.Fruit;
import com.vetor.service.ReadData;
import com.vetor.service.WriteData;

public class DeleteFruit {
	public static void deleteFruit(String path)throws IOException{
		ArrayList<Fruit> arr=new ArrayList<Fruit>();
		ReadData.readFruit(path, arr);
		@SuppressWarnings("resource")
		Scanner sc =new Scanner(System.in);
		System.out.println("请输入你要删除的水果名字");
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
				arr.remove(index);
				WriteData.writeData(path,arr);
				System.out.println("删除成功");
				break;
			}
			else{
				System.out.println("没有此水果，请重新输入");
			}
		}
		
		
	}
}
