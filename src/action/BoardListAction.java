package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardListService;
import vo.ActionForward;
import vo.Board;
import vo.PageInfo;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		//�ش� �������� ��µ� ù��° ���ڵ� ��ȣ
		int count = 0;
		//�� ���� ������ ������ ����
		int number = 0;
		//�ش� �������� ��µǴ� ù ��° ���� ��ȣ
		
		ArrayList<Board> articleList = null;
		//�ش� �������� ��µ� �� ����� ��� �ִ� �÷���
		
		BoardListService boardListService
		= new BoardListService();

		count = boardListService.getArticleCount();
		if(count > 0){
			articleList = boardListService.getArticleList(startRow,pageSize);
		}
		
		number = count - (currentPage - 1) * pageSize;
		//���� �������� ��µǴ� ù��° ���� �۹�ȣ�� ���ϴ� ��
		//134 - (1- 1) * 10 ---> 134
		int pageCount = 0;
		int startPage = 0;
		int endPage = 0;
		if(count > 0){
			//���� �ϳ��� ������ ������ ��ȣ ���
			pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			int pageBlock = 10;
			startPage = (currentPage-1)/pageSize * pageBlock + 1;
			endPage = startPage + pageBlock - 1;
			if(endPage > pageCount) endPage = pageCount;
			//������ ������ �׷��� ��� ����
		}
		
		for (int i = 0; i < articleList.size(); i++) {
			if(articleList.get(i).getSubject().length() > 6){
				String subject = articleList.get(i).getSubject().substring(0, 6) + "...";
				articleList.get(i).setSubject(subject);
			}
		}
		request.setAttribute("articleList", articleList);
		
		
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCount(count);
		pageInfo.setCurrentPage(currentPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setPageCount(pageCount);
		pageInfo.setNumber(number);
		request.setAttribute("pageInfo", pageInfo);
		
		ActionForward forward = new ActionForward();
		forward.setUrl("list.jsp");
		
		return forward;
	}

}






