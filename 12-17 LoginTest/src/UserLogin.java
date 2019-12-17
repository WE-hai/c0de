
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UserLogin extends JFrame implements ActionListener {
	JLabel jl1, jl2, jl3;
	JTextField jtf, jtfMm;
	JButton jbtOk, jbtEsc;

	public UserLogin() {
		// ���ô��������
		this.setTitle("��½");
		this.setSize(295, 340);
		this.setLocation(400, 180);
		// ��ȡ������������
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));
		// cp.setLayout(null);
		// �������

		jl2 = new JLabel("��ӭʹ�ñ�ϵͳ��", JLabel.CENTER);
		jl2.setFont(new Font("����", Font.BOLD, 24));
		jl2.setForeground(Color.BLUE);

		jl1 = new JLabel("�û�����", JLabel.RIGHT);
		jl3 = new JLabel("��   �룺", JLabel.RIGHT);
		jtf = new JTextField(15);
		jtfMm = new JTextField(15);
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
		
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String ur1 = "jdbc:sqlserver://localhost:1433;DatabaseName=user";
		
		//String driver = "com.mysql.jdbc.Driver";
		//String url = "jdbc:mysql://127.0.0.1:3306/user";
		
		try {
			// 1����������
			Class.forName(driver);
			
			// 2����������
			con = DriverManager.getConnection(ur1,"sa","123456");
			stmt = con.createStatement();
			
			//con = DriverManager.getConnection(url,"root","");
			//stmt = con.createStatement();
			
			rs = stmt.executeQuery("select * from uer");
			while(rs.next()) {
				System.out.println(rs.getInt("uid")+" "+rs.getString("uname"));
			}
			/*
			// 3������Statement
			if (e.getActionCommand().equals("ע��")) {// 4������sql���
				this.setTitle("�����ע�ᰴť");
				
					int n=stmt.executeUpdate("INSERT INTO user1 VALUES('"	+ name + "','"+ passW + "')");
					// 5���Խ�������д���
					
					if (n==1) {
						JOptionPane.showMessageDialog(this, "��Ϣע��ɹ���");
					} else
						JOptionPane.showMessageDialog(this, "ע��ʧ�ܣ�");
					
					// �ر�����
					rs.close();
					stmt.close();
					con.close();
			}
			*/
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception err) {
			err.printStackTrace();
			
		}

		
		 

		}
	}


