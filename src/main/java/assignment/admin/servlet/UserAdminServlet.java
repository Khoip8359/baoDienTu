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

import assignment.dao.UserDAO;
import assignment.dao.impl.UserDAOImpl;
import assignment.entity.User;

@WebServlet({
	"/admin/user/index",
	"/admin/user/create",
	"/admin/user/update",
	"/admin/user/delete",
	"/admin/user/reset",
	"/admin/user/edit/*"
})
public class UserAdminServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = (User) req.getSession().getAttribute("user");
		if(user==null || user.isRole()==false) {
			String ctx = req.getContextPath();
			resp.sendRedirect(ctx+"/account/login");
			return;
		}
		
		UserDAO udao = new UserDAOImpl();
		
		user = new User();
		try {
			DateTimeConverter converter = new DateConverter(new Date());
			converter.setPatterns(new String[] {"dd-MM-yyyy","MM/dd/yyyy","yyyy-MM-dd"});
			ConvertUtils.register(converter, Date.class);
			BeanUtils.populate(user, req.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String path = req.getServletPath();
		if(path.contains("create")) {
			udao.create(user);
		}else if(path.contains("update")) {
			udao.update(user);
		}else if(path.contains("delete")) {
			String id = req.getParameter("id");
			udao.deleteById(id);
		}else if(path.contains("edit")) {
			String id = req.getPathInfo().substring(1);
			user = udao.findById(id);
		}else {
			user = new User();
		}
		
		req.setAttribute("form", user);
		
		List<User> users = udao.findAll();
		req.setAttribute("list", users);
		
		req.setAttribute("view", "/admin/user/index.jsp");
		req.getRequestDispatcher("/admin/shared/layout.jsp").forward(req, resp);
		
	}
}
