package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.abc.asms.utils.DBUtils;

public class S0020Service {

	public Map<Integer, String> getMap(String idName,String getName,String tableName) throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		Map<Integer, String> map = new HashMap<Integer, String>();
		try{
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT "+idName+","+getName+" FROM "+tableName+" ORDER BY ?";
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			ps.setString(1,idName);
			rs = ps.executeQuery();

			while(rs.next()) {
				int id = rs.getInt(idName);
				String name = rs.getString(getName);
				map.put(id, name);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return map;
	}

	public int checkListSize(String[] date,String accountId,String[] categoryId,String tradeName,String note) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		int length = 0;
		List<Object> holder = new ArrayList<>();
		try{
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT count(sale_id) FROM sales where 1=1 ";

			if(!date[0].equals("")) {
				sql += "AND sale_date >= ? ";
				holder.add(date[0]);
			}
			if(!date[1].equals("")) {
				sql += "AND sale_date <= ? ";
				holder.add(date[1]);
			}
			if(accountId!=null&&!accountId.equals("")) {
				sql += "AND account_id = ? ";
				holder.add(accountId);
			}
			if(categoryId!=null) {
				sql += "AND category_id in(-1";
				for(String cId:categoryId) {
					sql +=",?";
					holder.add(cId);
				}
				sql += ") ";
			}
			if(tradeName!=null&&!tradeName.equals("")) {
				sql += "AND trade_name like ? ";
				holder.add("%"+tradeName+"%");
			}
			if(note!=null&&!note.equals("")) {
				sql += "AND note like ? ";
				holder.add("%"+note+"%");
			}
			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			for(int i = 0;i<holder.size();i++) {
				ps.setObject(i+1, holder.get(i));
			}
			rs = ps.executeQuery();

			rs.next();
			length = rs.getInt("count(sale_id)");

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}
		return length;
	}
}
