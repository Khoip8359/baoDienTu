package assignment.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.dao.CategoryDAO;
import assignment.dao.impl.CategoryDAOImpl;
import assignment.entity.Category;

@WebServlet("/category/menu")
public class CategoryServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategoryDAO cdao = new CategoryDAOImpl();
		List<Category> clist = cdao.findAll();
		req.setAttribute("cates", clist);
		req.getRequestDispatcher("/site/shared/menu.jsp").include(req, resp);
	}
}
