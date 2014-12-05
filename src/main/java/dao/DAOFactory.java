package main.java.dao;

import main.java.model.DescRelations;
import main.java.model.Descriptors;
import main.java.model.Documents;

public class DAOFactory {
	
	public static DAO<Documents> getDocumentsDAO(){
		return new DocumentsDAO();
	}
	
	public static DAO<Descriptors> getDescriptorsDAO(){
		return new DescriptorsDAO();
	}
	
	public static DAO<DescRelations> getDescRelationsDAO(){
		return new DescRelationsDAO();
	}

}
