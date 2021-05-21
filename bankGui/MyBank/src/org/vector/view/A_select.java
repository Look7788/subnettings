package org.vector.view;

import java.awt.Container;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.vector.been.Transfer;
import org.vector.been.User;
import org.vector.dao.FindAllSql;

public class A_select extends JDialog{

	public void a_select() {
		Container c = this.getContentPane();
		this.setTitle("查看用户");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setBounds(width / 2 - 200, height / 2 - 220, 900, 300);
		// 创建表格
		Object[][] obj = new Object[37][8];
		String a = null;
		String b = null;
		String d = null;
		String e = null;
		String f = null;
		String g = null;
		double h = 0.0;
		String n = null;
		
		FindAllSql uc = new FindAllSql();
		List list = uc.findAllUser();
		for (int i = 0; i < list.size(); i++) {
			User flow = (User) list.get(i);
			a = flow.getName();
			 b = flow.getCord();
			 d = flow.getId();
			 e = flow.getNumber();
			 f = flow.getPayment();
			 g = flow.getPhone();
			 h = flow.getBalance();
			 n = flow.getAcount();
			for (int j = 0; j < 8; j++) {
				switch (j) {
				case 0:
					obj[i][j] = a;
					break;
				case 1:
					obj[i][j] =b;
					break;
				case 2:
					obj[i][j] = d;
					break;
				case 3:
					obj[i][j] = e;
					break;
				case 4:
					obj[i][j] = f;
					break;
				case 5:
					obj[i][j] = g;
					break;
				case 6:
					obj[i][j] = h;
					break;
				case 7:
					obj[i][j] = n;
					break;
				}
			}
		}
		String[] columnNames = { "用户名", "密码", "身份证号", "卡号", "支付密码","电话号","余额","账号" };
		JTable j1 = new JTable(obj, columnNames);
		System.out.println(list);
		JScrollPane j2 = new JScrollPane();
		j2.setViewportView(j1);
		j2.transferFocusUpCycle();
		
		c.add(j2);
		this.setResizable(false);
		this.setVisible(true);
	}
}
