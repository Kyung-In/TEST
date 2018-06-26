package action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardModifyService;
import vo.ActionForward;
import vo.Board;

public class BoardModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Board article = new Board();
		article.setContent(request.getParameter("content"));
		article.setEmail(request.getParameter("email"));
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setPasswd(request.getParameter("passwd"));
		article.setSubject(request.getParameter("subject"));
		article.setWriter(request.getParameter("writer"));
		article.setReg_date(new Timestamp(System.currentTimeMillis()));

		String pageNum = request.getParameter("pageNum");
		BoardModifyService boardModifyService
		= new BoardModifyService();

		boolean modifySuccess = boardModifyService.modifyArticle(article);
		ActionForward forward = null;
		if(modifySuccess){
			forward = new ActionForward();
			forward.setUrl("boardList.bo");
			forward.setRedirect(true);
		}
		else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
