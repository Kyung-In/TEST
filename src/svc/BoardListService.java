package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.Board;
public class BoardListService {

	public int getArticleCount() {
		// TODO Auto-generated method stub
		int articleCount = 0;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		articleCount = boardDAO.selectArticleCount();
		close(con);
		return articleCount;
	}

	public ArrayList<Board> getArticleList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		ArrayList<Board> articleList = 
				boardDAO.selectArticleList(startRow, pageSize);
		close(con);
		return articleList;
	}

}





