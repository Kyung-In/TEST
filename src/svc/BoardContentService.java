package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.BoardDAO;
import vo.Board;

public class BoardContentService {

	public Board getArticle(int num) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		Board article = boardDAO.selectArticle(num);
		close(con);
		return article;
	}

}
