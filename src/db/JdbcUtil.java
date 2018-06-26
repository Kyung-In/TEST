package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//�����ͺ��̽� �۾��� �� �� �ݺ������� �����ؾ� �ϴ� �۾��� �����ϴ� Ŭ����
public class JdbcUtil {
	/*static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Class.forName : ���ڷ� �־��� Ŭ������ �޸𸮷� �о���̴� ������ �ϴ� �޼ҵ�
	}*/
	
	public static Connection getConnection(){
//		Connection ��ü�� �����ؼ� ��ȯ�ϴ� �޼ҵ�
		Connection con = null;
		try {
			Context initCtx = new InitialContext();
			//Tomcat Server ��ü�� Context�� ����
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			//lookup �޼ҵ��� ����Ÿ���� Object
			//context.xml �� ������ ���ҽ��� ���� Context
			DataSource ds = (DataSource)envCtx.lookup("jdbc/jsptest");
			con = ds.getConnection();
			con.setAutoCommit(false);
			System.out.println("connect succes");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con){
		try {
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt){
		try {
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs){
		try {
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//Ʈ������ ���� �޼ҵ� �߰�
	public static void commit(Connection con){
		try {
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void rollback(Connection con){
		try {
			con.rollback();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}











