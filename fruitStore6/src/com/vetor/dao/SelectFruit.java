package com.vetor.dao;

import java.util.ArrayList;

import com.vetor.been.Fruit;
import com.vetor.service.ReadData;

public class SelectFruit {
	public static void selectFruit(String path){
		ArrayList<Fruit> arr=new ArrayList<Fruit>();
		try {
			ReadData.readFruit(path, arr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(arr.size()==0){
			System.out.println("没有水果的信息！");
			return;
		}
		System.out.println("名称\t价格\t单位\t数量"); //\t是Tab键的意思
		for(int x=0;x<arr.size();x++){
			Fruit show =arr.get(x);              
			
			System.out.println(show.getName()+"\t"+show.getPrice()+"\t"+show.getUnit()+"\t"+show.getCount()); 
		}
	}
}

