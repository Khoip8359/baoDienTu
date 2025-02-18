package assignment.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.dao.NewsDAO;
import assignment.dao.NewsletterDAO;
import assignment.dao.impl.NewsDAOImpl;
import assignment.dao.impl.NewsletterDAOImpl;
import assignment.entity.Newsletter;

@WebServlet({"/layout/top5-news"})
public class Top5NewsServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewsDAO dao = new NewsDAOImpl();
		req.setAttribute("hotnews", dao.findTop5HotNews());
		req.setAttribute("lastnews", dao.findTop5LastestNews());
		req.getRequestDispatcher("/site/shared/top5-news.jsp").include(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		NewsletterDAO nldao = new NewsletterDAOImpl();
		Newsletter nl = new Newsletter();
		String email=req.getParameter("email");
		nl.setEmail(email);
		nldao.create(nl);
		req.getRequestDispatcher("/site/shared/top5-news.jsp").include(req, resp);
	}
}
