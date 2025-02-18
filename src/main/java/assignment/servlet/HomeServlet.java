package assignment.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.dao.CategoryDAO;
import assignment.dao.NewsDAO;
import assignment.dao.impl.CategoryDAOImpl;
import assignment.dao.impl.NewsDAOImpl;
import assignment.entity.Category;
import assignment.entity.News;

@WebServlet({"/home/index", "/home/about", "/home/contact"})
public class HomeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewsDAO ndao = new NewsDAOImpl();
		List<News> list = ndao.findHomeNews();
		req.setAttribute("news", list);
		req.setAttribute("view", "/site/news/list.jsp");
		req.getRequestDispatcher("/site/shared/layout.jsp").forward(req, resp);
	}
}
