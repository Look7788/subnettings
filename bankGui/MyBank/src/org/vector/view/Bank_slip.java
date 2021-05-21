package org.vector.view;

import java.awt.Container;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.vector.been.Transfer;
import org.vector.been.User;
import org.vector.service.UserService;

public class Bank_slip extends JFrame{
	public void bank_slip(User user) {
		Container c = this.getContentPane();
		this.setTitle("转账流水");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setBounds(width / 2 - 200, height / 2 - 220, 800, 300);
		// 创建表格
		Object[][] obj = new Object[37][5];
		String a = null;
		String b = null;
		double d = 0;
		String e = null;
		double f = 0;
		UserService uc = new UserService();
		List list = uc.findAll(user.getName());
		for (int i = 0; i < list.size(); i++) {
			Transfer flow = (Transfer) list.get(i);
			a = flow.getTransfer_people();
			 b = flow.getTransfer_object();
			 d = flow.getTransfer_money();
			 e = flow.getTransfer_data();
			 f = flow.getMoney();
			for (int j = 0; j < 5; j++) {
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
				}
			}
		}
		String[] columnNames = { "转账人", "转账对象", "转账金额", "转账日期", "余额" };
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