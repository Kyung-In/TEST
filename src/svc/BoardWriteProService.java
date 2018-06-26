package svc;

import vo.Board;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
public class BoardWriteProService {

	public boolean writeArticle(Board article) {
		// TODO Auto-generated method stub
		boolean writeSuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int insertCount = boardDAO.insertArticle(article);
		if(insertCount > 0){
			writeSuccess = true;
			commit(con);
		}
		else{
			rollback(con);
		}
		close(con);
		return writeSuccess;
	}

}







