package org.vector.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.vector.been.Administrator;
import org.vector.been.User;
import org.vector.utils.BaseUtils;

public class AdministratorDao {

	//找到用户和密码
		public Administrator findNameAndCord(String str1, String str2) {
			// TODO Auto-generated method stub
            String sql = "select * from administrator where A_name=? and A_cord=?";
			
			QueryRunner qr = BaseUtils.getQueryRunner();
			try {
				return qr.query(sql, new BeanHandler<Administrator>(Administrator.class),str1,str2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	//删除用户
		public int delectUser(String str1, String str2) {
			// TODO Auto-generated method stub
			String sql = "delete from user where name=? and acount=?";
			QueryRunner qr = BaseUtils.getQueryRunner();
			try {
				int i =qr.update(sql,str1,str2);
				return i;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
		
	//更新姓名 账号 密码
		public int UpdateCord(String str1,String str2,String str3) {
			String sql = "update user set acount=?,cord=? where name=?";
			QueryRunner qr = BaseUtils.getQueryRunner();
			try {
				int i =qr.update(sql,str2,str3,str1);
				return i;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
	
}
