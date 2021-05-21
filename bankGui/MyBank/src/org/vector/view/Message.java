package org.vector.view;

import java.awt.BorderLayout;
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
import org.vector.been.User;

public class Message extends JDialog{

	public void message(User user) {
		// 窗口设置
		Container c = this.getContentPane();
		this.setTitle("以下是您的信息");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setBounds(width / 2, height / 2 - 350, 500, 300);
		c.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
        c.setBackground(Color.orange);
        JLabel m1 = new JLabel("您的密码是：");
		m1.setFont(new Font("宋体", Font.PLAIN, 22));
		m1.setBounds(0, 0, 150, 100);
		JLabel m2 = new JLabel("您的余额为：");
		m2.setFont(new Font("宋体", Font.PLAIN, 22));
		m2.setBounds(0, 50, 150, 100);
		JLabel m5 = new JLabel("请记好您的密码，小艾为你服务！");
		m5.setFont(new Font("宋体", Font.PLAIN, 22));
		m5.setBounds(0, 120, 400, 50);
		c.add(m1);
		c.add(m2);
		c.add(m5);
		JLabel str1 = new JLabel(user.getCord());
		str1.setBounds(130, 30, 200, 30);
		str1.setFont(new Font("宋体", Font.PLAIN, 18));
		JLabel str2 = new JLabel(""+user.getBalance());
		str2.setBounds(130, 90, 200, 30);
		str2.setFont(new Font("宋体", Font.PLAIN, 18));
		c.add(str1);
		c.add(str2);
		
		JButton s1 = new JButton("取消");
		s1.setBounds(20, 200, 80, 40);
		s1.setFont(new Font("宋体", Font.PLAIN, 15));
		JButton s2 = new JButton("重置");
		s2.setBounds(400, 200, 80, 40);
		s2.setFont(new Font("宋体", Font.PLAIN, 15));
		c.add(s1);
		c.add(s2);
		
		s1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//取消按钮
				setVisible(false);
			}

		});
		
		s2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//确认键
				HomeUpdate homeUpdate = new HomeUpdate();
				homeUpdate.HomeUpdate(user);
				setVisible(false);
			}

		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
        
	}
}
