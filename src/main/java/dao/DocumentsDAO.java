package main.java.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.model.Descriptors;
import main.java.model.Documents;

public class DocumentsDAO extends DAO<Documents> {

	@Override
	public Documents find(int id) {
		Documents documents = new Documents();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM Documents WHERE id = " + id);
			if (result.first())
				documents = new Documents(id, result.getString("title"),
						result.getString("url"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return documents;
	}

	@Override
	public Documents create(Documents obj) {
		PreparedStatement prepare;
		try {
			prepare = this.connect
					.prepareStatement("INSERT INTO Documents (title, url) VALUES(?,?)");

			prepare.setString(1, obj.getTitle());
			prepare.setString(2, obj.getUrl());
			prepare.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Documents update(Documents obj) {
		try {

			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"UPDATE Documents SET title = '" + obj.getTitle()
							+ "',url='" + obj.getUrl() + "'" + " WHERE id = "
							+ obj.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void delete(Documents obj) {
		try {

			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"DELETE FROM Documents WHERE id = " + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
