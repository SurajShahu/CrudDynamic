package in.co.rays.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import in.co.rays.bean.UserBean;

import java.util.ArrayList;

public class UserModel {
	public void add(UserBean bean) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/advance11","root","root");
		PreparedStatement pstmt = conn.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
		int pk = nextPk();
		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getFirstName());
		pstmt.setString(3, bean.getLastName());
		pstmt.setString(4, bean.getLoginId());
		pstmt.setString(5, bean.getPassword());
		pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(7, bean.getAddress());
		pstmt.executeUpdate();
		System.out.println("Data inserted " + pk);
	}

	public void update(UserBean bean) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/advance11","root","root");
		PreparedStatement pstmt = conn.prepareStatement(
				"update user set first_name=?,last_name=?,login_id=?,password=?,dob=?,address=? where id=?");
		pstmt.setString(1, bean.getFirstName());
		pstmt.setString(2, bean.getLastName());
		pstmt.setString(3, bean.getLoginId());
		pstmt.setString(4, bean.getPassword());
		pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(6, bean.getAddress());
		pstmt.setInt(7, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("Data updated " + i);
	}

	public void delete(int id) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/advance11","root","root");
		PreparedStatement pstmt = conn.prepareStatement("delete from user where id=?");
		pstmt.setInt(1, id);
		int i = pstmt.executeUpdate();
		System.out.println("Data deleted " + i);
	}


	public List search(UserBean bean, int pageNo, int pageSize) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/advance11","root","root");
		StringBuffer sql = new StringBuffer("select * from user where 1=1");
		List list = new ArrayList();
		// UserBean bean=new UserBean();
		if(bean!=null) {
			if(bean.getLastName()!=null)
			sql.append(" and last_name like '"+bean.getLastName()+"%'");
			
			if(bean.getDob()!=null && bean.getDob().getTime()>0) {
				System.out.println("dob="+bean.getDob());
				sql.append(" and dob like '"+new java.sql.Date(bean.getDob().getTime()) +"%'");
			}
			
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("SQL==>" + sql);
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setAddress(rs.getString(7));
			list.add(bean);
		}
		return list;

	}

	public Integer nextPk() throws Exception

	{
		int pk = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/advance11","root","root");
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from user");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getInt(1);
		}

		return pk + 1;
	}

	
	public UserBean findByPk(int id) throws Exception {
		UserBean bean = new UserBean();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/advance11","root","root");
		PreparedStatement pstmt = conn.prepareStatement("select * from user where id=?");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setAddress(rs.getString(7));
		}
		return bean;
	}

}
