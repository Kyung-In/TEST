package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//데이터베이스 작업을 할 때 반복적으로 수행해야 하는 작업을 정의하는 클래스
public class JdbcUtil {
	/*static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Class.forName : 인자로 주어진 클래스를 메모리로 읽어들이는 역할을 하는 메소드
	}*/
	
	public static Connection getConnection(){
//		Connection 객체를 생성해서 반환하는 메소드
		Connection con = null;
		try {
			Context initCtx = new InitialContext();
			//Tomcat Server 자체의 Context를 얻어옴
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			//lookup 메소드의 리턴타입은 Object
			//context.xml 에 정의한 리소스에 관한 Context
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
	
	//트렌젝션 관련 메소드 추가
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











