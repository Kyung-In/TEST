package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import vo.Board;

public class BoardModifyService {

	public boolean modifyArticle(Board article) {
		// TODO Auto-generated method stub
		boolean modifySuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int updateCount = boardDAO.updateArticle(article);
		if(updateCount > 0){
			modifySuccess = true;
			commit(con);
		}
		else{
			rollback(con);
		}
		close(con);
		return modifySuccess;
	}
	
}
