package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardModifyFormService;
import vo.ActionForward;
import vo.Board;

public class BoardModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		BoardModifyFormService boardModifyFormService
		= new BoardModifyFormService();
		
		Board article = boardModifyFormService.getUpdateArticle(num);
		request.setAttribute("article", article);
		request.setAttribute("pageNum", pageNum);
		ActionForward forward = new ActionForward();
		forward.setUrl("modifyForm.jsp");
		return forward;
	}

}
