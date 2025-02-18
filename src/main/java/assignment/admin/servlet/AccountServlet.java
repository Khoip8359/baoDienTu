package assignment.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.dao.UserDAO;
import assignment.dao.impl.UserDAOImpl;
import assignment.entity.User;

@WebServlet("/account/login")
public class AccountServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("view", "/admin/account/login.jsp");
		req.getRequestDispatcher("/admin/shared/layout.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserDAO udao = new UserDAOImpl();
		User user = udao.findById(username);
		if(user==null) {
			req.setAttribute("message", "Invalid username!");
		}else if(!user.getPassword().equals(password)) {
			req.setAttribute("message", "Invalid password!");			
		}else {
			req.setAttribute("message", "Login successfully!");
			req.getSession().setAttribute("user", user);
		}
		req.setAttribute("view", "/admin/account/login.jsp");
		req.getRequestDispatcher("/admin/shared/layout.jsp").forward(req, resp);
	}
}
