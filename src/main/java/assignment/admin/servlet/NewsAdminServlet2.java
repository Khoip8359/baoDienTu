package assignment.admin.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import assignment.dao.NewsDAO;
import assignment.dao.NewsletterDAO;
import assignment.dao.impl.NewsDAOImpl;
import assignment.dao.impl.NewsletterDAOImpl;
import assignment.entity.News;
import assignment.entity.User;
import assignment.utils.Mailer;

@WebServlet({
	"/admin/news/index",
	"/admin/news/create",
	"/admin/news/update",
	"/admin/news/delete",
	"/admin/news/reset",
	"/admin/news/edit/*"
})
public class NewsAdminServlet2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		User user = (User) req.getSession().getAttribute("user");
		if(user==null) {
			String ctx = req.getContextPath();
			resp.sendRedirect(ctx+"/account/login");
			return;
		}
		
		NewsDAO ndao = new NewsDAOImpl();
		
		News news = new News();
		try {
			DateTimeConverter converter = new DateConverter(new Date());
			converter.setPatterns(new String[] {"dd-MM-yyyy","MM/dd/yyyy","yyyy-MM-dd"});
			ConvertUtils.register(converter, Date.class);
			BeanUtils.populate(news, req.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String path = req.getServletPath();
		if(path.contains("create")) {
			ndao.create(news);
			NewsletterDAO nldao = new NewsletterDAOImpl();
			Mailer.sendSimpleText(news.getTitle(), "http://localhost:8080/assignment/news/detail/"+news.getId(), null, null, nldao.findAllEnable());
		}else if(path.contains("update")) {
			ndao.update(news);
		}else if(path.contains("delete")) {
			String id = req.getParameter("id");
			ndao.deleteById(id);
		}else if(path.contains("edit")) {
			String id = req.getPathInfo().substring(1);
			news = ndao.findById(id);
		}else {
			news = new News();
		}
		
		req.setAttribute("form", news);
		
		List<News> newss = null;
		if(user.isRole()) {
			newss = ndao.findAll100Char();
		}else {
			String username = user.getId();
			newss = ndao.findByAuthorId(username);
		}
		req.setAttribute("list", newss);
		
		req.setAttribute("view", "/admin/news/index.jsp");
		req.getRequestDispatcher("/admin/shared/layout.jsp").forward(req, resp);
		
	}
}
