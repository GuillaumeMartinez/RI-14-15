package main.java.model;

public class DescRelations {

	private int id;
	private int idDocumentRef;
	private int idDescriptorRef;
	private int position;
	private int importance;

	public DescRelations(){}
	
	public DescRelations(int id, int idDocumentRef, int idDescriptorRef,
			int position, int importance) {
		super();
		this.id = id;
		this.idDocumentRef = idDocumentRef;
		this.idDescriptorRef = idDescriptorRef;
		this.position = position;
		this.importance = importance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdDocumentRef() {
		return idDocumentRef;
	}

	public void setIdDocumentRef(int idDocumentRef) {
		this.idDocumentRef = idDocumentRef;
	}

	public int getIdDescriptorRef() {
		return idDescriptorRef;
	}

	public void setIdDescriptorRef(int idDescriptorRef) {
		this.idDescriptorRef = idDescriptorRef;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

}
