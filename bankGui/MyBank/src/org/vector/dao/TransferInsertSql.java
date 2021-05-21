package org.vector.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.vector.been.Transfer;
import org.vector.been.User;
import org.vector.utils.BaseUtils;

public class TransferInsertSql {

	//插入转账流水
	public int Insert1(Transfer transfer) {
		String sql = "insert into transfer values(?,?,?,?,?)";
		QueryRunner qr = BaseUtils.getQueryRunner();
		try {
			return qr.update(sql,transfer.getTransfer_people(),
					transfer.getTransfer_object(),transfer.getTransfer_money(),
					transfer.getTransfer_data(),transfer.getMoney());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
