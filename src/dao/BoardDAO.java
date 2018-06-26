package dao;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Board;
public class BoardDAO {
	private Connection con;
	private static BoardDAO boardDAO;
	private BoardDAO() {
		// TODO Auto-generated constructor stub
	}
	public static BoardDAO getInstance(){
		if(boardDAO == null){
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}	
	public void setConnection(Connection con){
		this.con = con;
	}
	public int insertArticle(Board article){
		int insertCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//�亯�� ó���� �ʿ��� �����͵� ������
		int num = article.getNum();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		
		//������ �ۼ����� �� ���ο� ���ñ� ��ȣ�� ������ ���� ����
		int number = 0;
		String sql = "";
		
		try {
			
			//���ο� ���ñ� ��ȣ ������
			pstmt = con.prepareStatement("SELECT MAX(num) FROM board");
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				//������ �ۼ��� ���� ������...
				number = rs.getInt(1) + 1;
			}
			else{
				number = 1;
			}
			
			if(num != 0){
				//�亯���� �ۼ�������...
				sql = "UPDATE board SET re_step = re_step + 1 "
						+ "WHERE ref = ? AND re_step > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				
				re_step = re_step + 1;
				re_level = re_level + 1;
				
			}
			else{
				//������ �ۼ�������
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			
			sql = "INSERT INTO board(num,writer,email,subject,passwd,reg_date"
					+ ",ref,re_step,re_level,content)"
					+ " VALUES(board_seq.nextval,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPasswd());
			pstmt.setTimestamp(5, article.getReg_date());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, re_step);
			pstmt.setInt(8, re_level);
			pstmt.setString(9, article.getContent());
			
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	
	public int selectArticleCount(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int articleCount = 0;
		try {
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM board");
			rs = pstmt.executeQuery();
			if(rs.next()){
				articleCount = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			close(rs);
			close(pstmt);
		}
		return articleCount;
	}
	
	public ArrayList<Board> selectArticleList(int startRow,int pageSize){
		//���� �������� ��µ� ���������� ���ϳ� ������ Board ��ü�� ��Ƽ�
		//�� Board ��ü���� ArrayList �� ��Ƽ� �����ϴ� �޼ҵ�
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> articleList = null;
		Board article = null;
		try {
			pstmt = con.prepareStatement("SELECT list2.* FROM (SELECT "
					+ "rownum r,list1.* "
					+ "FROM (SELECT * FROM board ORDER BY ref DESC, "
					+ "re_step ASC) list1)"
					+ " list2 WHERE r BETWEEN ? AND ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, startRow + pageSize - 1);
			rs = pstmt.executeQuery();
			if(rs.next()){
				articleList = new ArrayList<Board>();
				do {
					article = new Board();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setRe_level(rs.getInt("re_level"));
					article.setRe_step(rs.getInt("re_step"));
					article.setContent(rs.getString("content"));
					
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	public Board selectArticle(int num){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board article = null;
		try {
			pstmt = con.prepareStatement("UPDATE board SET readcount = readcount + 1 WHERE num = ?");
			pstmt.setInt(1, num);
			
			int updateCount = pstmt.executeUpdate();
			if(updateCount > 0){
				commit(con);
			}
			else{
				rollback(con);
			}
			
			pstmt = con.prepareStatement("SELECT * FROM board WHERE num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
					article = new Board();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setRe_level(rs.getInt("re_level"));
					article.setRe_step(rs.getInt("re_step"));
					article.setContent(rs.getString("content"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			close(rs);
			close(pstmt);
		}
		return article;
	}
	public Board selectUpdateArticle(int num){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board article = null;
		try {
			
			pstmt = con.prepareStatement("SELECT * FROM board WHERE num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
					article = new Board();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setRe_level(rs.getInt("re_level"));
					article.setRe_step(rs.getInt("re_step"));
					article.setContent(rs.getString("content"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			close(rs);
			close(pstmt);
		}
		return article;
	}
	
	public int updateArticle(Board article){
		int updateCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbPasswd = "";
		//��񿡼� ������ ��й�ȣ�� �ӽ÷� ������ ����
		String sql = "";
		try {
			
			//�Է��� ��й�ȣ�� ��ġ�� ���� �� ���� �����ϰ� ó��
			pstmt = con.prepareStatement("SELECT passwd FROM board WHERE num = ?");
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dbPasswd = rs.getString("passwd");
				if(dbPasswd.equals(article.getPasswd())){
					sql = "UPDATE board SET email=?,subject=?,passwd=?"
							+ ",content=? WHERE num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, article.getEmail());
					pstmt.setString(2, article.getSubject());
					pstmt.setString(3, article.getPasswd());
					pstmt.setString(4, article.getContent());
					pstmt.setInt(5, article.getNum());
					updateCount = pstmt.executeUpdate();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			close(rs);
			close(pstmt);
		}
		return updateCount;
	}
	
	public int deleteArticle(int num,String passwd){
		int deleteCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbPasswd = "";
		//��񿡼� ������ ��й�ȣ�� �ӽ÷� ������ ����
		String sql = "";
		try {
			con = getConnection();
			
			//�Է��� ��й�ȣ�� ��ġ�� ���� �� ���� �����ϰ� ó��
			pstmt = con.prepareStatement("SELECT passwd FROM board WHERE num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dbPasswd = rs.getString("passwd");
				if(dbPasswd.equals(passwd)){
					sql = "DELETE board WHERE num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					deleteCount = pstmt.executeUpdate();
					if(deleteCount > 0){
						commit(con);
					}
					else{
						rollback(con);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			close(rs);
			close(pstmt);
			close(con);
		}
		return deleteCount;
	}
}










