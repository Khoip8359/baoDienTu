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

import assignment.dao.CategoryDAO;
import assignment.dao.impl.CategoryDAOImpl;
import assignment.entity.Category;
import assignment.entity.User;

@WebServlet({ "/admin/category/index", "/admin/category/create", "/admin/category/update", "/admin/category/delete",
		"/admin/category/reset", "/admin/category/edit/*" })
public class CategoryAdminServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		if(user==null || user.isRole()==false) {
			String ctx = req.getContextPath();
			resp.sendRedirect(ctx+"/account/login");
			return;
		}
		
		CategoryDAO cdao = new CategoryDAOImpl();

		Category category = new Category();
		try {
			DateTimeConverter converter = new DateConverter(new Date());
			converter.setPatterns(new String[] { "dd-MM-yyyy", "MM/dd/yyyy", "yyyy-MM-dd" });
			ConvertUtils.register(converter, Date.class);
			BeanUtils.populate(category, req.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String path = req.getServletPath();
		if (path.contains("create")) {
			cdao.create(category);
		} else if (path.contains("update")) {
			cdao.update(category);
		} else if (path.contains("delete")) {
			String id = req.getParameter("id");
			cdao.deleteById(id);
		} else if (path.contains("edit")) {
			String id = req.getPathInfo().substring(1);
			category = cdao.findById(id);
		} else {
			category = new Category();
		}

		req.setAttribute("form", category);

		List<Category> categorys = cdao.findAll();
		req.setAttribute("list", categorys);

		req.setAttribute("view", "/admin/category/index.jsp");
		req.getRequestDispatcher("/admin/shared/layout.jsp").forward(req, resp);
	}
}
