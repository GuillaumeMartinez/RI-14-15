package main.java.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.model.DescRelations;

public class DescRelationsDAO extends DAO<DescRelations> {

	@Override
	public DescRelations find(int id) {
		DescRelations descR = new DescRelations();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM DescRelations WHERE id = " + id);
			if (result.first())
				descR = new DescRelations(id, result.getInt("idDocumentRef"),
						result.getInt("idDescriptorRef"),
						result.getInt("position"), result.getInt("importance"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return descR;

	}

	@Override
	public DescRelations create(DescRelations obj) {

		PreparedStatement prepare;
		try {
			prepare = this.connect
					.prepareStatement("INSERT INTO DescRelations (idDocumentRef, idDescriptorRef,position,importance) VALUES(?,?,?,?)");

			prepare.setLong(1, obj.getIdDocumentRef());
			prepare.setLong(2, obj.getIdDescriptorRef());
			prepare.setInt(3, obj.getPosition());
			prepare.setInt(4, obj.getImportance());
			prepare.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public DescRelations update(DescRelations obj) {
		try {

			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"UPDATE DescRelations SET idDocumentRef = "
							+ obj.getIdDocumentRef() + ",idDescriptorRef = "
							+ obj.getIdDescriptorRef() + ", position = "
							+ obj.getPosition() + ", importance = "
							+ obj.getImportance() + " WHERE id = "
							+ obj.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void delete(DescRelations obj) {
		try {
			
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                	ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"DELETE FROM DescRelations WHERE id = " + obj.getId()
                 );

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	}

}
