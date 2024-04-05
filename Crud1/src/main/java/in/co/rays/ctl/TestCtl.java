package in.co.rays.ctl;

import java.text.SimpleDateFormat;

import in.co.rays.bean.UserBean;
import in.co.rays.model.UserModel;

public class TestCtl {
	public static void main(String[] args) throws Exception {
		//testDelete();
		//testUpdate();
		testAdd();
	}

	private static void testAdd() throws Exception {
		UserModel model=new UserModel();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dob="1990-12-01";
		UserBean bean=new UserBean();
		int id=model.nextPk();
		bean.setId(id);
		bean.setFirstName("suuu");
		bean.setLastName("ssss");
		bean.setLoginId("jjjj");
		bean.setPassword("1234");
		bean.setDob(sdf.parse(dob));
		bean.setAddress("abc");
		model.add(bean);
		
	}

	private static void testUpdate() throws Exception{
		UserModel model=new UserModel();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dob="1990-12-01";
		UserBean bean=new UserBean();
		bean.setId(5);
		bean.setFirstName("suuu");
		bean.setLastName("ssss");
		bean.setLoginId("jjjj");
		bean.setPassword("1234");
		bean.setDob(sdf.parse(dob));
		bean.setAddress("abc");
		model.update(bean);
		
	}

	private static void testDelete() throws Exception {
		UserModel model=new UserModel();
		model.delete(12);
	}
}
