package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.abc.asms.forms.SaleConditionalForm;
import com.abc.asms.utils.DBUtils;

public class S0020Service {

	public Map<Integer, String> getMap(String idName, String getName, String tableName) throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		Map<Integer, String> map = new HashMap<Integer, String>();
		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT " + idName + "," + getName + " FROM " + tableName + " ORDER BY ?";
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			ps.setString(1, idName);
			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(idName);
				String name = rs.getString(getName);
				map.put(id, name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}

		return map;
	}

	public int checkListSize(SaleConditionalForm scf) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		int length = 0;
		String[] date = scf.getDate();
		String accountId = scf.getAccountId();
		String[] categoryId = scf.getCategoryId();
		String tradeName = scf.getTradeName();
		String note = scf.getNote();
		List<Object> holder = new ArrayList<>();
		try {

			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT count(sale_id) FROM sales where 1=1 ";

			if (!date[0].equals("")) {
				sql += "AND sale_date >= ? ";
				holder.add(date[0]);
			}
			if (!date[1].equals("")) {
				sql += "AND sale_date <= ? ";
				holder.add(date[1]);
			}
			if (!accountId.equals("")) {
				sql += "AND account_id = ? ";
				holder.add(accountId);
			}
			if (categoryId != null) {
				sql += "AND category_id in('false'";
				for (String cId : categoryId) {
					sql += ",?";
					holder.add(cId);
				}
				sql += ") ";
			}
			if (tradeName != null && !tradeName.equals("")) {
				sql += "AND trade_name like ? ";
				holder.add("%" + tradeName + "%");
			}
			if (note != null && !note.equals("")) {
				sql += "AND note like ? ";
				holder.add("%" + note + "%");
			}

			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			for (int i = 0; i < holder.size(); i++) {
				ps.setObject(i + 1, holder.get(i));
			}
			rs = ps.executeQuery();

			rs.next();
			length = rs.getInt("count(sale_id)");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return length;
	}

	/**
	 * 入力チェック
	 * @param date 入力された日付
	 * @param accountId 入力されたaccountId
	 * @param tradeName 入力されたtradeName
	 * @param note 入力されたnote
	 * @return エラーメッセージ
	 */
	public List<String> validate(String[] date, String accountId, String tradeName, String note) {
		List<String> error = new ArrayList<>();
		Date date1 = null;
		Date date2 = null;
		if (date == null || date.length != 2 || accountId == null || tradeName == null || note == null) {
			error.add("バリデーションエラー");
		} else {
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			try {
				if (!date[0].matches("[0-9]{4}/([0-9]{2}|[0-9])/([0-9]{2}|[0-9])")) {
					throw new Exception();
				}
				format.setLenient(false);
				date1 = format.parse(date[0]);
			} catch (Exception e) {
				error.add("販売日（検索開始日）を正しく入力して下さい。");
			}
			try {
				if (!date[1].matches("[0-9]{4}/([0-9]{2}|[0-9])/([0-9]{2}|[0-9])")) {
					throw new Exception();
				}
				format.setLenient(false);
				date2 = format.parse(date[1]);
			} catch (Exception e) {
				error.add("販売日（検索終了日）を正しく入力して下さい。");
			}
		}
		if (error.size() == 0) {
			if (date2.before(date1)) {
				error.add("販売日（検索開始日）が販売日（検索終了日）より後の日付となっています。");
			}
		}
		return error;
	}
}
