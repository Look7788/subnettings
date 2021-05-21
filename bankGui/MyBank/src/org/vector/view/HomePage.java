package org.vector.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.vector.been.Administrator;
import org.vector.been.User;
import org.vector.dao.AdministratorDao;
import org.vector.service.UserService;

@SuppressWarnings("serial")
public class HomePage extends JFrame{
	public void home() {
	JFrame frame = new JFrame();
	Container c = frame.getContentPane();
	JTextField account = new JTextField();
	JPasswordField password = new JPasswordField();
	JButton record = new JButton("登录");
	JButton register = new JButton("注册");
	JButton forget = new JButton("忘记/修改密码");
	User user = new User();
	// 窗口设置
		frame.setTitle("欢迎进入银行");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		frame.setBounds(width / 2 - 200, height / 2 - 220, 650, 650);
		c.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
		
		JPanel jpanel = new JPanel();
		jpanel.setBackground(Color.cyan);
		jpanel.setLayout(null);
		JLabel Bank = new JLabel("Bank");
		Bank.setFont(new Font("宋体", Font.PLAIN, 30));
		jpanel.add(Bank);
		Bank.setBounds(280, 10, 100, 30);
		c.add(jpanel, "North");
		
		// 创建两个单选按钮
        JRadioButton radioBtn01 = new JRadioButton("管理员");
        radioBtn01.setFont(new Font("宋体", Font.PLAIN, 20));
		radioBtn01.setBounds(180, 400, 100, 20);
        JRadioButton radioBtn02 = new JRadioButton("用   户");
        radioBtn02.setFont(new Font("宋体", Font.PLAIN, 20));
		radioBtn02.setBounds(400, 400, 100, 20);
        // 创建按钮组，把两个单选按钮添加到该组
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(radioBtn01);
        btnGroup.add(radioBtn02);
        jpanel.add(radioBtn01);
        jpanel.add(radioBtn02);

		ImageIcon jpg = new ImageIcon("Image/1.jpg");
		jpg.setImage(jpg.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		JLabel a3 = new JLabel(jpg);
		a3.setBounds(240, 50, 200, 200);
		c.add(a3);
		
		JLabel a1 = new JLabel("账号:");
		a1.setFont(new Font("宋体", Font.PLAIN, 20));
		a1.setBounds(180, 280, 50, 20);
		JLabel a2 = new JLabel("密码:");
		a2.setFont(new Font("宋体", Font.PLAIN, 20));
		a2.setBounds(180, 360, 50, 20);
		jpanel.add(a1);
		jpanel.add(a2);
		account.setBounds(280, 275, 150, 30);
		account.setFont(new Font("宋体", Font.PLAIN, 18));

		password.setBounds(280, 355, 150, 30);
		password.setFont(new Font("宋体", Font.PLAIN, 18));

		jpanel.add(account);
		jpanel.add(password);
		c.add(jpanel, "Center");

		jpanel.add(register);
		jpanel.add(forget);
		register.setBounds(15, 550, 200, 40);
		register.setFont(new Font("宋体", Font.PLAIN, 15));
		forget.setBounds(425, 550, 200, 40);
		forget.setFont(new Font("宋体", Font.PLAIN, 15));
		c.add(jpanel);

		jpanel.add(record);
		record.setBounds(270, 450, 150, 75);
		record.setFont(new Font("宋体", Font.PLAIN, 20));
		c.add(jpanel);
		JLayeredPane jp = new JLayeredPane();
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 触发注册弹窗
				HomeRegist homeRegist = new HomeRegist();
				homeRegist.RegisterDialog();
				frame.setVisible(false);
			}

		});
		forget.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 触发忘记密码弹窗
                HomeVerify honVerify = new HomeVerify();
                honVerify.ForgetDialog(user);
                frame.setVisible(false);
			}

		});
		record.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 触发登录弹窗
				String str1 = account.getText();
				String str2 = password.getText();
				UserService userService = new UserService();
				AdministratorDao adminDao = new AdministratorDao();
				User user3 = userService.findNameAndCord(str1,str2);
				Administrator admin = adminDao.findNameAndCord(str1,str2);
				
					if(user3!=null && radioBtn02.isSelected()) {
						JOptionPane.showMessageDialog(null, "登录成功", "提示", JOptionPane.WARNING_MESSAGE);
						HomeMenu homeMenu = new HomeMenu();
						homeMenu.HomeMenu(user3);
						frame.setVisible(false);
					}
					else if(admin != null && radioBtn01.isSelected()){
						JOptionPane.showMessageDialog(null, "登录成功", "提示", JOptionPane.WARNING_MESSAGE);
						A_homeMain home = new A_homeMain();
						home.a_homeMain();
						frame.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "信息错误.", "提示", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
           );
	}
}
