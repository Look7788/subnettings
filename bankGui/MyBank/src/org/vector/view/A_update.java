package org.vector.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.vector.dao.AdministratorDao;

public class A_update extends JFrame{

	public void a_update() {
		this.setTitle("修改页面");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setBounds(width / 2 - 200, height / 2 - 180, 500, 400);
		JPanel c = (JPanel)this.getContentPane();
		c.setOpaque(false);
		this.setLayout(null);
		ImageIcon jpg = new ImageIcon("Image/7.jpg");
		jpg.setImage(jpg.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
		JLabel s1 = new JLabel(jpg);
		s1.setBounds(0, 0, 500, 400);
		this.getLayeredPane().add(s1,new Integer(Integer.MIN_VALUE));
		
		JTextField name = new JTextField();
		name.setBounds(130, 55, 150, 30);
		name.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JPasswordField password = new JPasswordField();
		password.setBounds(130, 170, 150, 30);
		password.setFont(new Font("宋体", Font.PLAIN, 18));

		JTextField acount = new JTextField();
		acount.setBounds(130, 110, 150, 30);
		acount.setFont(new Font("宋体", Font.PLAIN, 18));

		JLabel a1 = new JLabel("姓名");
		a1.setFont(new Font("宋体", Font.BOLD, 20));
		a1.setBounds(70, 30, 80, 80);
		JLabel a2 = new JLabel("修改账号");
		a2.setFont(new Font("宋体", Font.BOLD, 20));
		a2.setBounds(20, 80, 120, 80);
		JLabel a4 = new JLabel("修改密码");
		a4.setFont(new Font("宋体", Font.BOLD, 20));
		a4.setBounds(20, 150, 120, 80);
		JLabel a3 = new JLabel("请确定要修改吗？");
		a3.setFont(new Font("宋体", Font.BOLD, 28));
		a3.setBounds(100, 200, 300, 50);
		a3.setForeground(Color.red);
		
		JButton m1 = new JButton("取消");
		m1.setFont(new Font("宋体", Font.BOLD, 20));
		m1.setBounds(30, 250, 100, 50);
		JButton m2 = new JButton("确定");
		m2.setFont(new Font("宋体", Font.BOLD, 20));
		m2.setBounds(300, 250, 100, 50);
		
		this.add(a1);
		this.add(a2);
		this.add(a3);
		this.add(a4);
		this.add(password);
		this.add(name);
		this.add(acount);
		this.add(m1);
		this.add(m2);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		this.setResizable(false);
		this.setVisible(true);
		
		m2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 确定按钮
				String str1 = name.getText();
				String str2 = acount.getText();
				String str3 = password.getText();
				
				AdministratorDao admin = new AdministratorDao();
				int ad = admin.UpdateCord(str1, str2, str3);
				if(ad==0)
					JOptionPane.showMessageDialog(null, "修改失败","提示",JOptionPane.WARNING_MESSAGE);
				else {
					JOptionPane.showMessageDialog(null, "修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					A_homeMain homeMain = new A_homeMain();
					homeMain.a_homeMain();
				}
					
			}

		});
		
		m1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 取消按钮
				A_homeMain homeMain = new A_homeMain();
				homeMain.a_homeMain();
				setVisible(false);
			}

		});
		
	}
}
