package org.vector.service;

import java.sql.SQLException;
import java.util.List;

import org.vector.been.Transfer;
import org.vector.been.User;
import org.vector.dao.FindAllSql;
import org.vector.dao.TransferInsertSql;
import org.vector.dao.UserDao;

public class UserService {

	public User findNameAndCord(String str1,String str2) {
		UserDao userDao = new UserDao();
		return userDao.findNameAndCord(str1,str2);
	}
	
	public User findIdAndNumberAndAcount(String str1,String str2,String str3) {
		UserDao userDao = new UserDao();
		return userDao.findIdAndNumberAndAcount(str1, str2, str3);
	}
	
	public int Insert(User user) {
		UserDao userDao = new UserDao();
		return userDao.Insert(user);
	}
	
	public User findPayment(String str2) throws SQLException {
		UserDao userDao = new UserDao();
		return userDao.findPayment(str2);
	}

	public User findCount(String str2) throws SQLException {
		UserDao userDao = new UserDao();
		return userDao.findCount(str2);
	}
	
	public boolean UpdateBlance(User user) {
		UserDao userDao = new UserDao();
		if(userDao.UpdateBlance(user)==0)
			return false;
		else
			return true;
	}

	public boolean UpdateCord(User user) {
		UserDao userDao = new UserDao();
		if(userDao.UpdateCord(user)==0)
			return false;
		else
			return true;
	}
	
	public List findAll(String str) {
		FindAllSql findAllSql = new FindAllSql();
		List<Transfer> list = findAllSql.findAll(str);
		if(list==null)
			return null;
		else
			return list;
	}
	
	public int Insert1(Transfer tf) {
		TransferInsertSql insert = new TransferInsertSql();
		return insert.Insert1(tf);
	}
}
