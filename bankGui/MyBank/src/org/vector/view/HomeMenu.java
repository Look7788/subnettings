package org.vector.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.vector.been.User;
import org.vector.service.UserService;

public class HomeMenu extends JFrame{
	// 登陆后界面
	public void HomeMenu(User user) {
		this.setTitle("菜单");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setBounds(width / 2 - 80, height / 2 - 130, 610, 610);
		JPanel c = (JPanel)this.getContentPane();
		c.setOpaque(false);
		this.setLayout(null);
		ImageIcon jpg = new ImageIcon("Image/5.jpg");
		jpg.setImage(jpg.getImage().getScaledInstance(610, 610, Image.SCALE_DEFAULT));
		JLabel a1 = new JLabel(jpg);
		a1.setBounds(0, 0, 610, 610);
		this.getLayeredPane().add(a1,new Integer(Integer.MIN_VALUE));
		this.setResizable(false);
		this.setVisible(true);
		
		JLabel m1 = new JLabel("账号：");
		// m1.setForeground(Color.red);
		m1.setFont(new Font("宋体", Font.BOLD, 20));
		m1.setBounds(100, 60, 100, 40);
		JLabel m2 = new JLabel("用户名：");
		// m2.setForeground(Color.red);
		m2.setFont(new Font("宋体", Font.BOLD, 20));
		m2.setBounds(100, 120, 100, 40);
		JLabel m3 = new JLabel("银行卡号：");
		// m3.setForeground(Color.red);
		m3.setFont(new Font("宋体", Font.BOLD, 20));
		m3.setBounds(80, 180, 150, 40);
		JLabel m4 = new JLabel("电话号：");
		// m4.setForeground(Color.red);
		m4.setFont(new Font("宋体", Font.BOLD, 20));
		m4.setBounds(100, 240, 150, 40);
		JLabel  m5 = new JLabel("余额：");
		// m5.setForeground(Color.red);
		m5.setFont(new Font("宋体", Font.BOLD, 20));
		m5.setBounds(100, 300, 150, 40);
		JButton m6 = new JButton("存款");
		m6.setBounds(170, 400, 100, 40);
		m6.setFont(new Font("宋体", Font.BOLD, 15));
		JButton m7 = new JButton("取款");
		m7.setBounds(330, 400, 100, 40);
		m7.setFont(new Font("宋体", Font.BOLD, 15));
		JButton m8 = new JButton("转账");
		m8.setBounds(170, 470, 100, 40);
		m8.setFont(new Font("宋体", Font.BOLD, 15));
		JButton m9 = new JButton("查看流水");
		m9.setBounds(330, 470, 100, 40);
		m9.setFont(new Font("宋体", Font.BOLD, 15));
		JButton m10 = new JButton("注销");
		m10.setBounds(420, 60, 80, 40);
		m10.setFont(new Font("宋体", Font.BOLD, 15));
		JButton m11 = new JButton("刷新");
		m11.setBounds(420, 180, 80, 40);
		m11.setFont(new Font("宋体", Font.BOLD, 15));

		this.add(m1);
		this.add(m2);
		this.add(m3);
		this.add(m4);
		this.add(m5);
		this.add(m6);
		this.add(m7);
		this.add(m8);
		this.add(m9);
		this.add(m10);
		this.add(m11);
		
		JLabel s1 = new JLabel(user.getAcount());

		s1.setBounds(190,60, 150, 30);
		s1.setFont(new Font("宋体", Font.BOLD, 25));
		JLabel s2 = new JLabel(user.getName());
		
		s2.setBounds(190,120, 150, 30);
		s2.setFont(new Font("宋体", Font.BOLD, 25));
		JLabel s3 = new JLabel(user.getNumber());

		s3.setBounds(190, 180, 200, 30);
		s3.setFont(new Font("宋体", Font.BOLD, 25));
		JLabel s4 = new JLabel(user.getPhone());

		s4.setBounds(190, 240, 150, 30);
		s4.setFont(new Font("宋体", Font.BOLD, 25));
		JLabel s5 = new JLabel(""+user.getBalance());

		s5.setBounds(190, 300, 150, 30);
		s5.setFont(new Font("宋体", Font.BOLD, 25));
		this.add(s1);
		this.add(s2);
		this.add(s3);
		this.add(s4);
		this.add(s5);
		
		m10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 注销按钮
				setVisible(false);
				HomePage homePage = new HomePage();
				homePage.home();
			}

		}

		);
		m6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 存款按钮
				HomeDposit homeDposit = new HomeDposit();
				homeDposit.Homedposit(user);
			}

		});

		m7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 取款按钮
				HomeDrawal homeDrawal = new HomeDrawal();
				homeDrawal.Homedrawal(user);
			}

		});
		
		m8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 转账按钮
				HomeTransfer homeTransfer = new HomeTransfer();
				homeTransfer.Hometransfer(user);
				
			}
			
		}
		);
		
		m9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 查看流水
				Bank_slip bank_slip = new Bank_slip();
				bank_slip.bank_slip(user);
				
			}
			
		}
		);
		m11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 刷新按钮
				UserService userService = new UserService();
				User user2 = null;
				try {
					user2 = userService.findCount(user.getAcount());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s5.setText("");
				s5.setText(""+user2.getBalance());
			}

		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

	}

}
