package com.vetor.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.vetor.been.Fruit;

public class ReadData {
//输入流的方法
	public static void readFruit(String path,ArrayList<Fruit> arr) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
			try {
				while((line=br.readLine())!=null){
					String[] s=line.split(",");
					Fruit stu=new Fruit();
					stu.setName(s[0]);
					stu.setPrice(s[1]);
					stu.setUnit(s[2]);
					stu.setCount(s[3]);
					arr.add(stu);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       try {
		br.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}

