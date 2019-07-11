package com.abc.asms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.abc.asms.forms.AccountConditionalForm;
import com.abc.asms.forms.S0041Form;
import com.abc.asms.utils.DBUtils;

public class S0041Service {

	public List<S0041Form> getDB(AccountConditionalForm acf) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String name = acf.getName();
		String mail = acf.getMail();
		String authority = acf.getAuthority();
		List<Object> holder = new ArrayList<>();
		List<S0041Form> list = new ArrayList<>();
		try{
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT account_id,name,mail,authority FROM accounts where 1=1 ";

			if(name!=null&&!name.equals("")) {
				sql += "AND name LIKE ? ";
				holder.add("%"+name+"%");
			}
			if(mail!=null&&!mail.equals("")) {
				sql += "AND mail = ? ";
				holder.add(mail);
			}
			sql += "AND authority REGEXP ? ORDER BY account_id";
			holder.add(authority);

			//SELECT命令の準備・実行
			ps = con.prepareStatement(sql);
			for(int i = 0;i<holder.size();i++) {
				ps.setObject(i+1, holder.get(i));
			}
			rs = ps.executeQuery();

			while(rs.next()) {
				String account_id = rs.getString("account_id");
				String getName = rs.getString("name");
				String getMail = rs.getString("mail");
				String getAuthority = setAuthority(rs.getString("authority"));
				list.add(new S0041Form(account_id, getName, getMail, getAuthority));
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps, rs);
		}

		return list;
	}
	/**
	 * authorityの値を対応する文字列に変換するメソッド
	 * @param authority [11,10,1,0]のいずれか
	 * @return HTMLに出力される文字
	 */
	public String setAuthority(String authority) {
		switch (authority) {
		case "0":
			return "権限なし";
		case "1":
			return "売上登録";
		case "10":
			return "アカウント登録";
		case "11":
			return "売上登録/アカウント登録";
		default:
			return "エラー";
		}
	}
}
