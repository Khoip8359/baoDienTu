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
import assignment.utils.XCookie;

@WebServlet({
	"/news/list-by-cates/*", 
	"/news/detail/*"
})
public class NewsServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewsDAO ndao = new NewsDAOImpl();
		
		String path = req.getServletPath();
		if(path.contains("list")) {
			String cateId = req.getPathInfo().substring(1);
			List<News> nlist = ndao.findByCategoryID(cateId);
			req.setAttribute("news", nlist);
			req.setAttribute("view", "/site/news/list.jsp");
		} else if(path.contains("detail")){
			String id= req.getPathInfo().substring(1);
			News entity = ndao.findById(id);
			entity.setViewCount(entity.getViewCount()+1);
			ndao.update(entity);
			String value =XCookie.getValue("ids", req);
			if(value.contains(id)) {
				value = value.replace(","+id,"")+","+id; 
			}else {
				value = value +","+id;
			}
			String[] ids = value.substring(1).split("[,]+");
			if(ids.length>5) {
				int i = value.indexOf(",",1);
				value = value.substring(i);
			}
			XCookie.add("ids", value, 5*24*60*60, resp);
			req.setAttribute("item", entity);
			req.setAttribute("view", "/site/news/detail.jsp");
		}
		req.getRequestDispatcher("/site/shared/layout.jsp").forward(req, resp);
	}
}
