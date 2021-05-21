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
import javax.swing.JTextField;

import org.vector.been.User;
import org.vector.service.UserService;

public class HomeVerify extends JDialog{

	// 忘记/修改密码
	public void ForgetDialog(User user) {
		this.setTitle("忘记/修改密码");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setBounds(width / 2 - 80, height / 2 - 130, 410, 410);

		Container c = this.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.pink);
		JLabel m1 = new JLabel("验证信息");
		m1.setFont(new Font("宋体", Font.PLAIN, 24));
		m1.setBounds(150, 0, 100, 40);
		JLabel m2 = new JLabel("请输入账号");
		m2.setFont(new Font("宋体", Font.PLAIN, 20));
		m2.setBounds(70, 70, 100, 40);
		JLabel m3 = new JLabel("银行卡号");
		m3.setFont(new Font("宋体", Font.PLAIN, 20));
		m3.setBounds(90, 140, 100, 40);
		JLabel m4 = new JLabel("身份证号");
		m4.setFont(new Font("宋体", Font.PLAIN, 20));
		m4.setBounds(90, 210, 150, 40);
		JButton m5 = new JButton("取消");
		m5.setBounds(20, 313, 80, 40);
		m5.setFont(new Font("宋体", Font.PLAIN, 15));
		JButton m6 = new JButton("验证");
		m6.setBounds(303, 313, 80, 40);
		m6.setFont(new Font("宋体", Font.PLAIN, 15));
		JTextField m7 = new JTextField();
		m7.setBounds(200, 75, 150, 30);
		m7.setFont(new Font("宋体", Font.PLAIN, 18));
		JTextField m8 = new JTextField();
		m8.setBounds(200, 145, 150, 30);
		m8.setFont(new Font("宋体", Font.PLAIN, 18));
		JTextField m9 = new JTextField();
		m9.setBounds(200, 215, 150, 30);
		m9.setFont(new Font("宋体", Font.PLAIN, 18));
		c.add(m1);
		c.add(m2);
		c.add(m3);
		c.add(m4);
		c.add(m5);
		c.add(m6);
		c.add(m7);
		c.add(m8);
		c.add(m9);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		this.setResizable(false);
		this.setVisible(true);
		m5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//取消按钮
				setVisible(false);
				HomePage homePage = new HomePage();
				homePage.home();
			}

		});
		
		m6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//确认键
				String str1 = m7.getText();
				String str2 = m8.getText();	
				String str3 = m9.getText();
				UserService userService = new UserService();
				User user2 = userService.findIdAndNumberAndAcount(str3, str2, str1);
				if (user2==null) {
					JOptionPane.showMessageDialog(null, "信息错误.", "提示", JOptionPane.ERROR_MESSAGE);
				} else {
					HomePage homePage = new HomePage();
					homePage.home();
					Message message = new Message();
					message.message(user2);
					setVisible(false);
					}	
						
				}

		});
		
	}

}
