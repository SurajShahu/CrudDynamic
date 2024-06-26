package in.co.rays.ctl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.UserBean;
import in.co.rays.model.UserModel;

@WebServlet("/UserCtl")
public class UserCtl extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		if (id!=null) {
			UserModel model=new UserModel();
			try {
				UserBean bean=model.findByPk(Integer.parseInt(id));
				req.setAttribute("bean", bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rd=req.getRequestDispatcher("UserView.jsp");
		rd.forward(req, resp);
		//resp.sendRedirect("UserView.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id=req.getParameter("id");
		String fname=req.getParameter("firstName");
		String lname=req.getParameter("lastName");
		String login=req.getParameter("loginId");
		String pass=req.getParameter("password");
		String dob=req.getParameter("dob");
		String address=req.getParameter("address");
		String op=req.getParameter("operation");
		
		UserBean bean=new UserBean();
		if(id!=null && !id.equals(""))
		{
			bean.setId(Integer.parseInt(id));
		}
		
		bean.setFirstName(fname);
		bean.setLastName(lname);
		bean.setLoginId(login);
		bean.setPassword(pass);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			bean.setDob(sdf.parse(dob));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		bean.setAddress(address);
		UserModel model=new UserModel();
		
		if(op.equals("update"))
		{
			
			try {
				model.update(bean);
				bean=model.findByPk(bean.getId());
				req.setAttribute("bean", bean);
				req.setAttribute("msg", "data Updated");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if(op.equals("save"))
		{
			try {
				model.add(bean);
				req.setAttribute("msg", "User Added..");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		RequestDispatcher rd=req.getRequestDispatcher("UserView.jsp");
		rd.forward(req, resp);
	}
}
