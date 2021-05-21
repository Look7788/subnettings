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
import javax.swing.JPanel;

public class A_homeMain extends JFrame{

	public void a_homeMain() {
		
		this.setTitle("管理员页面");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setBounds(width / 2 - 200, height / 2 - 180, 500, 400);
		JPanel c = (JPanel)this.getContentPane();
		c.setOpaque(false);
		this.setLayout(null);
		ImageIcon jpg = new ImageIcon("Image/4.jpg");
		jpg.setImage(jpg.getImage().getScaledInstance(650, 500, Image.SCALE_DEFAULT));
		JLabel s1 = new JLabel(jpg);
		s1.setBounds(0, 0, 500, 400);
		this.getLayeredPane().add(s1,new Integer(Integer.MIN_VALUE));
		JLabel jLabel = new JLabel("欢迎管理员登陆系统！");
		jLabel.setFont(new Font("宋体", Font.BOLD, 26));
		jLabel.setBounds(70, 30, 500, 50);
		
		JButton a1 = new JButton("添加用户");
		a1.setFont(new Font("宋体", Font.BOLD, 20));
		a1.setBounds(30, 100, 180, 50);
		JButton a2 = new JButton("删除用户");
		a2.setForeground(Color.red);
		a2.setFont(new Font("宋体", Font.BOLD, 20));
		a2.setBounds(30, 180, 180, 50);
		
		JButton a3 = new JButton("修改用户");
		a3.setFont(new Font("宋体", Font.BOLD, 20));
		a3.setBounds(300, 100, 180, 50);
		JButton a4 = new JButton("查看用户");
		a4.setFont(new Font("宋体", Font.BOLD, 20));
		a4.setBounds(300, 180, 180, 50);
		this.add(a1);
		this.add(a2);
		this.add(a3);
		this.add(a4);
		this.add(jLabel);
		
		a1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 添加按钮
				HomeRegist homeRegist = new HomeRegist();
				homeRegist.RegisterDialog();
				setVisible(false);
			}

		});
		
		a2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 删除按钮
				A_delect delect = new A_delect();
				delect.a_delect();
				setVisible(false);
			}

		});
		
		a3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 修改按钮
				A_update update = new A_update();
				update.a_update();
				setVisible(false);
			}

		});
		
		a4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 查看按钮
				A_select select = new A_select();
				select.a_select();
			}

		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
}
