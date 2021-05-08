package com.vetor.view;

import java.io.IOException;
import java.util.Scanner;
import com.vetor.dao.AddFruit;
import com.vetor.dao.DeleteFruit;
import com.vetor.dao.FindFruit;
import com.vetor.dao.SelectFruit;
import com.vetor.dao.UpdateFruit;

public class FruitView {

	public void fruitView() throws IOException {
		// TODO Auto-generated method stub
		 String path ="fruit.txt";
			while(true){
				System.out.println("-----欢迎来到水果仓库管理系统-----");
				System.out.println("1、查看所有水果信息");
				System.out.println("2、添加水果信息");
				System.out.println("3、删除水果信息");
				System.out.println("4、修改水果信息");
				System.out.println("5、查找水果信息");
				System.out.println("6、退出系统");
				System.out.println("");
				System.out.println("请输入你的选择");
				@SuppressWarnings("resource")
				Scanner s=new Scanner(System.in);
				String choice=s.nextLine();
			
				switch(choice){
				case "1":
					SelectFruit.selectFruit(path);
					break;
				case "2":
					AddFruit.addFruit(path);
					break;
				case "3":
					DeleteFruit.deleteFruit(path);
					break;
				case "4":
					UpdateFruit.updateFruit(path);
					break;
				case "5":
					FindFruit.findFruit(path);
					break;
				case "6":
					
				default:
					System.out.println("谢谢您的使用！");
					System.exit(0);
				}
			}
	}
}

