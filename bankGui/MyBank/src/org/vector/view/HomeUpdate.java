package org.vector.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import org.vector.been.User;
import org.vector.service.UserService;

public class HomeUpdate extends JDialog{
			// 重置密码
	public void HomeUpdate(User user) {
		this.setTitle("重置密码");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setBounds(width / 2 - 80, height / 2 - 130, 460, 410);

		Container c = this.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.GRAY);
		
		JLabel j1 = new JLabel("请输入新密码");
		j1.setFont(new Font("宋体", Font.PLAIN, 18));
		j1.setBounds(50, 90, 110, 40);

		JLabel j2 = new JLabel("确认新密码");
		j2.setFont(new Font("宋体", Font.PLAIN, 18));
		j2.setBounds(50, 160, 110, 40);
		
		JLabel j7 = new JLabel("(6-9位)");
		j7.setFont(new Font("宋体", Font.PLAIN, 14));
		j7.setBounds(390, 90, 110, 40);

		JButton j4 = new JButton("确认修改");
		j4.setBounds(150, 250, 120, 60);
		j4.setFont(new Font("宋体", Font.PLAIN, 20));

		JPasswordField j5 = new JPasswordField();
		j5.setBounds(210, 95, 150, 30);
		j5.setFont(new Font("宋体", Font.PLAIN, 18));

		JPasswordField j6 = new JPasswordField();
		j6.setBounds(210, 165, 150, 30);
		j6.setFont(new Font("宋体", Font.PLAIN, 18));

		c.add(j1);
		c.add(j2);
		c.add(j4);
		c.add(j5);
		c.add(j6);
		c.add(j7);
		j4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub  确认键
				String str1 = j5.getText();
				String str2 = j6.getText();
				UserService us = new UserService();
				user.setCord(str1);
				
				if(str1.length()>=6 || str1.length()<=9) {
					if(str1.equals(str2) && us.UpdateCord(user)==true) {
						JOptionPane.showMessageDialog(null, "重置密码成功", "提示", JOptionPane.WARNING_MESSAGE);
						setVisible(false);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "密码错误.", "提示", JOptionPane.ERROR_MESSAGE);
			}
		});
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		this.setResizable(false);
		this.setVisible(true);
	}
}
