package maths;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class UserLogin extends JFrame implements ActionListener {
	JLabel jl1, jl2, jl3;
	JTextField jtf, jtfMm;
	JButton jbtOk, jbtEsc;

	public UserLogin() {
		// ���ô��������
		this.setTitle("��½");
		this.setSize(320, 340);
		this.setLocation(400, 200);
		// ��ȡ������������
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));
		// cp.setLayout(null);
		// �������

		jl2 = new JLabel("��ӭʹ����������ϵͳ��", JLabel.CENTER);
		jl2.setFont(new Font("����", Font.BOLD, 24));
		jl2.setForeground(Color.BLUE);

		jl1 = new JLabel("�û�����", JLabel.RIGHT);
		jl3 = new JLabel("��   �룺", JLabel.RIGHT);
		jtf = new JTextField(15);
		jtfMm = new JPasswordField(15);
		jbtOk = new JButton("��½");
		jbtEsc = new JButton("ע��");

		// ������
		cp.add(jl2);
		cp.add(jl1);
		cp.add(jtf);
		cp.add(jl3);
		cp.add(jtfMm);
		cp.add(jbtOk);
		cp.add(jbtEsc);

		// ע�����
		jbtOk.addActionListener(this);
		jbtEsc.addActionListener(this);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		UserLogin uf = new UserLogin();

	}

	public void actionPerformed(ActionEvent e) {
		
		String name = jtf.getText().trim();
		String passW = jtfMm.getText().trim();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/user";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,"root","");
			stmt = con.createStatement();
			if (e.getActionCommand().equals("ע��")) {
				this.setTitle("�����ע�ᰴť");
					int n=stmt.executeUpdate("INSERT INTO user VALUES('"	+ name + "','"+ passW + "')");
					if (n==1) {
						JOptionPane.showMessageDialog(this, "��Ϣע��ɹ���");
					} else
						JOptionPane.showMessageDialog(this, "ע��ʧ�ܣ�");
					} else if(e.getActionCommand().equals("��½")) {
						//this.setTitle(title);
						rs = stmt.executeQuery("select uid from user where uname = '"+name+"'");
						if(rs.next()) {
							if(rs.getString("uid").equals(passW)) {
								  new MyExGUI();
							} else {
								JOptionPane.showMessageDialog(this,"�������");
							}
						} else {
							JOptionPane.showMessageDialog(this,"�û�������");
						}
					}
			
			} catch (Exception err) {
			err.printStackTrace();
			
			}
		}
	}