package com.vetor.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.vetor.been.Fruit;

//输出流的方法

public class WriteData {
	public static void writeData(String path,ArrayList<Fruit> arr){
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			for(int x=0;x<arr.size();x++){
				Fruit s=arr.get(x);
				StringBuilder sb=new StringBuilder();
				sb.append(s.getName()+","+s.getPrice()+","+s.getUnit()+","+s.getCount());
				bw.write(sb.toString());
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
