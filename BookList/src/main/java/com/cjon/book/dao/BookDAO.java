package com.cjon.book.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cjon.book.dto.BookDTO;

public class BookDAO {


		public String select(BookDTO entity){
			
			Connection con;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String result = null;
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/library";
				String id = "jQuery";
				String pw = "jQuery";
				con = DriverManager.getConnection(url, id, pw);
				
				String sql = "select * from book where btitle like ?";
				pstmt= con.prepareStatement(sql);
				pstmt.setString(1, "%" + entity.getBtitle() + "%");
				
				rs = pstmt.executeQuery();
				JSONArray arr = new JSONArray();
				
				while(rs.next()) {
					JSONObject obj = new JSONObject();
					obj.put("isbn", rs.getString("bisbn"));
					obj.put("title", rs.getString("btitle"));
					obj.put("img", rs.getString("bimgurl"));
					obj.put("author", rs.getString("bauthor"));
					obj.put("price", rs.getString("bprice"));
					arr.add(obj);
				}
				result = arr.toJSONString();
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					rs.close();
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
			
			return result;
		}
}
