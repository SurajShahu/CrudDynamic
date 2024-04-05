package in.co.rays.ctl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.UserBean;
import in.co.rays.model.UserModel;

@WebServlet("/UserListCtl")
public class UserListCtl extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pageNo = 1;
		int pageSize = 20;
		UserBean bean = null;
		UserModel model = new UserModel();
		List list = new ArrayList();
		List list1 = new ArrayList();
		try {
			list = model.search(bean, pageNo, pageSize);
			list1 = model.search(bean, pageNo, pageSize);
			// List nextList=model.search(bean, pageNo+1, pageSize);
			req.setAttribute("list", list);
			req.setAttribute("list1", list1);
			RequestDispatcher rd = req.getRequestDispatcher("UserListView.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserBean bean = null;
		UserModel model = new UserModel();
		int pageNo = 1;
		int pageSize = 15;
		String[] ids = req.getParameterValues("ids");
		String dob = req.getParameter("dob");

		try {
			List list1 = model.search(bean, pageNo, pageSize);
			req.setAttribute("list1", list1);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String delId = req.getParameter("input");
		String op = req.getParameter("operation");

		if (op.equals("add")) {
			resp.sendRedirect("UserCtl");
		}

		if (op.equals("search")) {
			bean = new UserBean();
			if (dob != null && dob.length() > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					bean.setDob(sdf.parse(dob));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			String lName = req.getParameter("preload");
			if (lName != null && lName.length() > 0 && !lName.contains("select")) {
				bean.setLastName(lName);
			}
		}

		if (op.equals("delete")) {
			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					try {
						model.delete(Integer.parseInt(id));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				req.setAttribute("msg", "Data deleted..!!");
			} else {
				req.setAttribute("msg", "Select atleast one record");
			}
		}
		/*
		 * try { if(delId!=null && delId.length()>0) { int id=Integer.parseInt(delId);
		 * model.delete(id); }
		 * 
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		try {
			
			List list = model.search(bean, pageNo, pageSize);
			if(list==null ||list.size()==0) {
				req.setAttribute("list", list);
			}
			req.setAttribute("list", list);
			RequestDispatcher rd = req.getRequestDispatcher("UserListView.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/*
	 * pageNo=1; String[] ids=req.getParameterValues("ids"); if(ids!=null) { for
	 * (String id : ids) { try { model.delete(Integer.parseInt(id)); } catch
	 * (Exception e) { e.printStackTrace(); } } }
	 */

}
