package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@WebServlet("/GBC")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 파라미터 action의 값을 꺼내온다
		String action = request.getParameter("action");
		System.out.println(action);

		if ("list".equals(action)) {
			GuestDao guestDao = new GuestDao();
			List<GuestVo> guestList = guestDao.guestSelect("");

			// request data를 넣는다
			request.setAttribute("gList", guestList);

			// addList.jsp 에게 시킨다 (포워드)
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp"); // jsp파일 위치를 적어넣어야함
			rd.forward(request, response);

		} else if ("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestVo guestVo = new GuestVo();
			guestVo.setName(name);
			guestVo.setPassword(password);
			guestVo.setContent(content);
			
			GuestDao guestDao = new GuestDao();
			guestDao.guestInsert(guestVo);
			
			// 모두 처리됐으면 리스트로 돌아가기
			response.sendRedirect("./GBC?action=list");
			
		} else if ("dFrom".equals(action)) {
			System.out.println("a=dFrom");
			request.getParameter("no");
			
			// dFrom.jsp 에게 시킨다 (포워드)
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/dFrom.jsp"); // jsp파일 위치를 적어넣어야함
			rd.forward(request, response);
		
		} else if ("delete".equals(action)) {	
			
			System.out.println("a=delete");
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			GuestDao guestDao = new GuestDao();
			guestDao.guestDelete(no, password);
			
			// 모두 처리됐으면 리스트로 돌아가기
			response.sendRedirect("./GBC?action=list");			
			
		} else {
			System.out.println("나머지");
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
