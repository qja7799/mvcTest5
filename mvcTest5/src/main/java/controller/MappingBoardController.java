package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardDeleteCommand;
import service.BoardInputCommand;
import service.BoardInterface;
import service.BoardListCommand;
import service.BoardUpdateCommand;

@SuppressWarnings("serial")
@WebServlet("*.do")
public class MappingBoardController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardInterface command = null;
		String viewPage = "/WEB-INF/mapping";
		String com = request.getRequestURI();
//		StringBuffer imsi2 = (StringBuffer) request.getRequestURL();
		
// 		/mvcTest5/boardInput.do <= 여기에서 /부터 .앞까지 잘라옴 => /boardInput
		com = com.substring(com.lastIndexOf("/"),com.lastIndexOf("."));
		
		if(com.equals("/boardList")) {
			command = new BoardListCommand();
			command.execute(request, response);
			viewPage += "/boardList.jsp";
		}
		else if(com.equals("/boardInput")) {
			command = new BoardInputCommand();
			command.execute(request, response);
			viewPage += "/boardInput.jsp";
		}
		else if(com.equals("/boardUpdate")) {
			command = new BoardUpdateCommand();
			command.execute(request, response);
			viewPage += "/boardUpdate.jsp";
		}
		else if(com.equals("/boardDelete")) {
			command = new BoardDeleteCommand();
			command.execute(request, response);
			viewPage += "/boardDelete.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	
}
