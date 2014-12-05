package main.java;

import java.io.IOException;

import static java.util.Arrays.asList;


import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerException;
import org.annolab.tt4j.TreeTaggerWrapper;


public class main {

	private static final String CORPUSPATH = "/home/pzeroual/5A/REPCONN/CORPUS";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.setProperty("treetagger.home", "TreeTagger");
        TreeTaggerWrapper<String> tt = new TreeTaggerWrapper<String>();
        
        try {
                tt.setModel("TreeTagger/lib/french-utf8.par");
                tt.setHandler(new TokenHandler<String>() {
                        public void token(String token, String pos, String lemma) {
                                System.out.println(token + "\t" + pos + "\t" + lemma);
                        }
                });
                tt.process(asList(new String[] { "louis", "s","'","est", "tapé", "une", "moche" }));
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TreeTaggerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
                tt.destroy();
        }
		
		
		
		//OperationsSQLite.createTables();
		
		//ParserController parser = new ParserController(CORPUSPATH);
		//parser.createStopArray();
		//parser.parseAll();
		/*
		RequestController req = new RequestController("/home/pzeroual/5A/REPCONN/requetes.html", CORPUSPATH);
		req.parseRequest();
		req.executeRequest();
		req.print();
		
		Evaluator eval = new Evaluator(req.getRelevanceTable(),"/home/pzeroual/5A/REPCONN/qrels");
		eval.evaluate();*/
	}

}
