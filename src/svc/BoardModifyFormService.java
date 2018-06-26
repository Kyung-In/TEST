package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.BoardDAO;
import vo.Board;

public class BoardModifyFormService {

	public Board getUpdateArticle(int num) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		Board article = boardDAO.selectUpdateArticle(num);
		close(con);
		return article;
	}
	
}
