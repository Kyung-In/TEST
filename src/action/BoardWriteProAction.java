package action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardWriteProService;
import vo.ActionForward;
import vo.Board;

public class BoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Board article = new Board();
		article.setContent(request.getParameter("content"));
		article.setEmail(request.getParameter("email"));
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setPasswd(request.getParameter("passwd"));
		article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setSubject(request.getParameter("subject"));
		article.setWriter(request.getParameter("writer"));
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
	    //System.currentTimeMillis() : 현재 시간을 밀리세컨드(millisecond 단위로 반환
	    
	    BoardWriteProService boardWriteProService = new BoardWriteProService();
	    
	    boolean writeSuccess = boardWriteProService.writeArticle(article);
	    ActionForward forward = null;
	    if(writeSuccess){
	    	forward = new ActionForward();
	    	forward.setUrl("boardList.bo");
	    	forward.setRedirect(true);
	    }
	    else{
	    	response.setContentType("text/html;charset=UTF-8");
	    	PrintWriter out = response.getWriter();
	    	out.println("<script>");
	    	out.println("alert('등록실패')");
	    	out.println("history.back()");
	    	out.println("</script>");
	    }
		return forward;
	}

}






