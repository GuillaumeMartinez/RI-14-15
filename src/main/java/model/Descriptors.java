package main.java.model;

public class Descriptors {
	private int id;
	private String word;
	
	public Descriptors(){}
	
	

	public Descriptors(int id, String word) {
		super();
		this.id = id;
		this.word = word;
	}



	public int getId() {
		return id;
	}

	public String getWord() {
		return word;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setWord(String word) {
		this.word = word;
	}
}
