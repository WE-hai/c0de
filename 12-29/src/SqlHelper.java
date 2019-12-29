import java.sql.*;

public class SqlHelper {
	//========���ݿ�
	private Connection ct = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String ur1 = "jdbc:sqlserver://localhost:1433;DatabaseName=studentMan";
	//String driver = "com.mysql.jdbc.Driver";
	//String url = "jdbc:mysql://127.0.0.1:3306/user";
	//private String user = "root";
	//private String passwd = "";
	private String user = "sa";
	private String passwd = "123456";
	//========��ѯ
	public ResultSet queryExecute(String sql, String []paras)
	{
		try {
			//========1����������
			Class.forName(driver);
			//========2������
			ct = DriverManager.getConnection(ur1, user, passwd);
			//========3������PreparedStatement
			ps = ct.prepareStatement(sql);
			//========4�����ʺŸ�ֵ
			if(paras != null) {
				for(int i = 0; i < paras.length; i++) {
					ps.setString(i + 1, paras[i]);
				}
			}
			//========5��ִ��
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			//this.close();
		}
		//========����ֵ
		return rs;
	}
	
	//========��ɾ��
	public boolean cudExecute(String sql, String []paras)
	{
		boolean b = true;
		try {
			//========1����������
			Class.forName(driver);
			//========2������
			ct = DriverManager.getConnection(ur1, user, passwd);
			//========3������PreparedStatement
			ps = ct.prepareStatement(sql);
			//========4�����ʺŸ�ֵ
			for(int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			//========5��ִ��
			if(ps.executeUpdate() != 1) b = false;
		} catch (Exception e) {
			// TODO: handle exception
			b = false;
			e.printStackTrace();
		} finally {
			this.close();
		}
		//========����ֵ
		return b;
	}
	
	//========�ر���Դ
 	public void close()
	{
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}
	
}