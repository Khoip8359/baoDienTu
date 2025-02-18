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

import assignment.dao.NewsletterDAO;
import assignment.dao.impl.NewsletterDAOImpl;
import assignment.entity.Newsletter;
import assignment.entity.User;

@WebServlet({
	"/admin/newsletter/index",
	"/admin/newsletter/create",
	"/admin/newsletter/update",
	"/admin/newsletter/delete",
	"/admin/newsletter/reset",
	"/admin/newsletter/edit/*"
})
public class NewsLetterAdminServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		if(user==null || user.isRole()==false) {
			String ctx = req.getContextPath();
			resp.sendRedirect(ctx+"/account/login");
			return;
		}
		
		NewsletterDAO nldao = new NewsletterDAOImpl();

		Newsletter newsletter = new Newsletter();
		try {
			DateTimeConverter converter = new DateConverter(new Date());
			converter.setPatterns(new String[] { "dd-MM-yyyy", "MM/dd/yyyy", "yyyy-MM-dd" });
			ConvertUtils.register(converter, Date.class);
			BeanUtils.populate(newsletter, req.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String path = req.getServletPath();
		if (path.contains("create")) {
			nldao.create(newsletter);
		} else if (path.contains("update")) {
			nldao.update(newsletter);
		} else if (path.contains("delete")) {
			String email = req.getParameter("email");
			nldao.deleteById(email);
		} else if (path.contains("edit")) {
			String email = req.getPathInfo().substring(1);
			newsletter = nldao.findByEmail(email);
		} else {
			newsletter = new Newsletter();
		}

		req.setAttribute("form", newsletter);

		List<Newsletter> newsletters = nldao.findAll();
		req.setAttribute("list", newsletters);

		req.setAttribute("view", "/admin/newsletter/index.jsp");
		req.getRequestDispatcher("/admin/shared/layout.jsp").forward(req, resp);
	}
}
