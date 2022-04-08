package com.ho.lec24.poi.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.ho.lec24.poi.ExcelData;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class ExcelDao {

	private JdbcTemplate template;

	@Autowired
	public ExcelDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	public void excelInsert(List<ExcelData> list) {
		final String sql = "INSERT INTO user_table(user_seq, name, phone, mail, id, pw) "
				+ "values(user_seq.nextval, ?, ?, ?, ?, ?)";

		for (ExcelData excelData : list) {
			template.update(sql, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					int lastIndex = excelData.getEmail().indexOf("@");
					String id = excelData.getEmail().substring(0, lastIndex);

					pstmt.setString(1, excelData.getName());
					pstmt.setString(2, excelData.getPhone());
					pstmt.setString(3, excelData.getEmail());
					pstmt.setString(4, id);
					pstmt.setString(5, "1234");

				}
			});
		}
	}

}
