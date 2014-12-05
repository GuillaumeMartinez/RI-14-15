package main.java.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.model.Descriptors;

public class DescriptorsDAO extends DAO<Descriptors> {

	@Override
	public Descriptors find(int id) {
		Descriptors descriptors = new Descriptors();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM Descriptors WHERE id = " + id);
			if (result.first())
				descriptors = new Descriptors(id, result.getString("word"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return descriptors;
	}
	

	@Override
	public Descriptors create(Descriptors obj) {
		PreparedStatement prepare;
		try {
			prepare = this.connect
					.prepareStatement("INSERT OR IGNORE INTO Descriptors (word) VALUES(?)");

			prepare.setString(1, obj.getWord());
			prepare.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Descriptors update(Descriptors obj) {
		try {

			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"UPDATE Descriptors SET word = " + obj.getWord()
							+ " WHERE id = " + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void delete(Descriptors obj) {
		try {

			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"DELETE FROM Descriptors WHERE id = " + obj.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
