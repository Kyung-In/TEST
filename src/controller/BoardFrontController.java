package controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BoardContentAction;
import action.BoardDeleteProAction;
import action.BoardListAction;
import action.BoardModifyFormAction;
import action.BoardModifyProAction;
import action.BoardWriteFormAction;
import action.BoardWriteProAction;
import vo.ActionForward;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestURI = request.getRequestURI(); // 1) 요청 파악
		// http://localhost:8088/boardProject/boardWriteForm.bo
		//requestURI ===> /boardProject/boardWriteForm.bo
		
		String contextPath = request.getContextPath();
		//contextPath ===> /boardProject
		
		String command = requestURI.substring(contextPath.length());
		// command ===> /boardWriteForm.bo
		
		Action action = null; // Action Interface 
		ActionForward forward = null; // 어떤 url로 포워딩할지
		
		if(command.equals("/boardWriteForm.bo")){
			action = new BoardWriteFormAction();
			try {
				forward = action.execute(request, response);
				//action 객체에서 해당 요청의 비지니스 로직을 수행하고
				//최종적으로 포워딩 정보를 담고 있는 ActionForward 
				//객체를 리턴함
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardWritePro.bo")){
			action = new BoardWriteProAction();
			try {
				forward = action.execute(request, response);
				//action 객체에서 해당 요청의 비지니스 로직을 수행하고
				//최종적으로 포워딩 정보를 담고 있는 ActionForward 
				//객체를 리턴함
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardList.bo")){
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
				//action 객체에서 해당 요청의 비지니스 로직을 수행하고
				//최종적으로 포워딩 정보를 담고 있는 ActionForward 
				//객체를 리턴함
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardContent.bo")){
			action = new BoardContentAction();
			try {
				forward = action.execute(request, response);
				//action 객체에서 해당 요청의 비지니스 로직을 수행하고
				//최종적으로 포워딩 정보를 담고 있는 ActionForward 
				//객체를 리턴함
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(command.equals("/modifyForm.bo")){
			action = new BoardModifyFormAction();
			try {
				forward = action.execute(request, response);
				//action 객체에서 해당 요청의 비지니스 로직을 수행하고
				//최종적으로 포워딩 정보를 담고 있는 ActionForward 
				//객체를 리턴함
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(command.equals("/boardModifyPro.bo")){
			action = new BoardModifyProAction();
			try {
				forward = action.execute(request, response);
				//action 객체에서 해당 요청의 비지니스 로직을 수행하고
				//최종적으로 포워딩 정보를 담고 있는 ActionForward 
				//객체를 리턴함
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(command.equals("/removeForm.bo")){
			action = new BoardRemoveFormAction();
			try {
				forward = action.execute(request, response);
				//action 객체에서 해당 요청의 비지니스 로직을 수행하고
				//최종적으로 포워딩 정보를 담고 있는 ActionForward 
				//객체를 리턴함
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(command.equals("/deletePro.bo")){
			action = new BoardDeleteProAction();
			try {
				forward = action.execute(request, response);
				//action 객체에서 해당 요청의 비지니스 로직을 수행하고
				//최종적으로 포워딩 정보를 담고 있는 ActionForward 
				//객체를 리턴함
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	
		
		if(forward != null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getUrl());
			}
			else{
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getUrl());
				dispatcher.forward(request, response);
			}
		}
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}

}
