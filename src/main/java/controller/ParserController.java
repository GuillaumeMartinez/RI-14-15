package main.java.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import main.java.dao.DAO;
import main.java.dao.DAOFactory;
import main.java.dao.OperationsSQLite;
import main.java.model.DescRelations;
import main.java.model.Descriptors;
import main.java.model.Documents;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;

public class ParserController {

	private File corpusDir;
	
	
	
	
	
	private DAO<Documents> daoDocuments = DAOFactory.getDocumentsDAO();
	
	private DAO<Descriptors> daoDescriptors = DAOFactory.getDescriptorsDAO();
	
	private DAO<DescRelations> daoDescRelations = DAOFactory.getDescRelationsDAO();
	
	
	public ParserController(final String corpusPath) {
		
		this.corpusDir = new File(corpusPath);

		if (!corpusDir.exists() || !corpusDir.isDirectory()) {
			System.exit(1);
		}
	}
	
	public void parseAll(){
		for (File f : corpusDir.listFiles()) {
			Documents doc = new Documents();
			doc.setTitle(f.getName());
			doc.setUrl(f.getAbsolutePath());
			daoDocuments.create(doc);
			parseDoc(f,OperationsSQLite.getLastId("Documents"));
		}
	}
	
	public void parseDoc(File f, int docId) {
		Document doc = null;
		int position = 0;
		try {
			doc = Jsoup.parse(new File(f.getAbsolutePath()),"UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Descriptors desc = new Descriptors();
		DescRelations descRel = new DescRelations();
		for (Element el : doc.select("html").select("*")) {
			if (el.tagName().equals("meta")) {
				for (String s : el.attr("content").trim().toLowerCase().split("[ ,;:!?.'/\"]")) { // faire un tree tagger sur
					if (!s.isEmpty() && !StopArray.getStopArray().contains(s)){						// s (ou sur tout le fichier
					//	s																			// je sais pas ce qui vaut mieux)
					//	desc.setWord(s);
						daoDescriptors.create(desc);
						
						descRel.setIdDocumentRef(docId);
						descRel.setIdDescriptorRef(OperationsSQLite.findWord(s));
						descRel.setImportance(1);										// set importance en fonction de la 
						descRel.setPosition(position);									// balise ? (pas forcement très important)
						daoDescRelations.create(descRel);
						position++;
					}
				}
			} else {
				for (TextNode node : el.textNodes()) {
					
					for (String s : node.text().trim().toLowerCase().split("[ ,;:!?.'()\"/]")) {
						if (!s.isEmpty() && !StopArray.getStopArray().contains(s)){

							desc.setWord(s);
							daoDescriptors.create(desc);
							
							descRel.setIdDocumentRef(docId);
							descRel.setIdDescriptorRef(OperationsSQLite.getLastId("Descriptors"));
							descRel.setImportance(1);
							descRel.setPosition(position);
							daoDescRelations.create(descRel);
							position++;
						}
					}
				}
			}
		}
	}
		 
}
