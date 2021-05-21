package org.vector.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.vector.been.Transfer;
import org.vector.been.User;
import org.vector.utils.BaseUtils;

public class FindAllSql {
	//查询所有转账记录
		public List findAll(String str) {
			Transfer transfer = null;
			String sql = "select * from transfer where transfer_people=?";		
			QueryRunner qr = BaseUtils.getQueryRunner();
			 List<Transfer>  List = null;
			 BeanListHandler<Transfer> blhl = new BeanListHandler<>(Transfer.class);
			try {
				List = qr.query(sql, blhl ,str);
				return List;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	//查询所有用户
		public List findAllUser() {
			User user = null;
			String sql = "select * from user";		
			QueryRunner qr = BaseUtils.getQueryRunner();
			 List<User>  List = null;
			 BeanListHandler<User> blhl = new BeanListHandler<>(User.class);
			try {
				List = qr.query(sql, blhl);
				return List;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
}
