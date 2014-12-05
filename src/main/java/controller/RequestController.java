package main.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.dao.OperationsSQLite;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class RequestController {

	private int corpusPathLength;
	private String reqPath;
	private ArrayList<ArrayList<String>> keyWordTable = new ArrayList<ArrayList<String>>();
	private Document doc;
	private ArrayList<List<Map.Entry<Integer, Integer>>> relevanceTable = new ArrayList<List<Map.Entry<Integer, Integer>>>();

	Comparator<Map.Entry<Integer, Integer>> byMapValues = new Comparator<Map.Entry<Integer, Integer>>() {
		@Override
		public int compare(Map.Entry<Integer, Integer> left,
				Map.Entry<Integer, Integer> right) {
			return -left.getValue().compareTo(right.getValue());
		}
	};

	public RequestController(String reqPath, String corpusPath) {
		super();
		this.reqPath = reqPath;
		this.corpusPathLength = 20;// new File(corpusPath).list().length;
	}

	public void parseRequest() {
		try {
			doc = Jsoup.parse(new File(reqPath), "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Element el : doc.select("dl")) {
			ArrayList<String> keyWordList = new ArrayList<String>();
			for (String s : el.getElementsByIndexEquals(1).text().toLowerCase()
					.split(", ")) {
				keyWordList.add(s);
			}
			keyWordTable.add(keyWordList);
		}
	}

	public void executeRequest() {
		for (ArrayList<String> keyWordList : keyWordTable) {
			int[][] matDoc = new int[corpusPathLength][keyWordList.size()];
			int[] req = new int[keyWordList.size()];

			for (int i = 0; i < req.length; i++) {
				req[i] = 1;
			}

			int indexWord = 0;
			int idWord = 0;
			for (String keyWord : keyWordList) {
/*
				if (keyWord.contains(" ")) {
					int indexPosition = 0;
					for (String s : keyWord.toLowerCase().split(" ")) {
						indexPosition++;
						if (!s.isEmpty()
								&& !StopArray.getStopArray().contains(s)) {
							int idKeyword = OperationsSQLite.findWord(s);
							if (idKeyword != -1) {
								idWord = idKeyword;

								for (int indexDoc = 0; indexDoc < corpusPathLength; indexDoc++) {
									matDoc[indexDoc][indexWord] = OperationsSQLite
											.findWeightFromDesc(indexDoc + 1,
													idWord);
								}
								indexWord++;
							}
						}
					}
				}
*/
				int idKeyword = OperationsSQLite.findWord(keyWord);
				if (idKeyword != -1) {
					idWord = idKeyword;

					for (int indexDoc = 0; indexDoc < corpusPathLength; indexDoc++) {
						matDoc[indexDoc][indexWord] = OperationsSQLite
								.findWeightFromDesc(indexDoc + 1, idWord);
					}
					indexWord++;
				}
			}

			Map<Integer, Integer> relevanceList = new HashMap<Integer, Integer>();
			for (int indexDoc = 0; indexDoc < corpusPathLength; indexDoc++) {
				relevanceList
						.put(indexDoc, innerProduct(matDoc[indexDoc], req));
			}

			List<Map.Entry<Integer, Integer>> relevanceListSorted = new ArrayList<Map.Entry<Integer, Integer>>();
			relevanceListSorted.addAll(relevanceList.entrySet());
			Collections.sort(relevanceListSorted, byMapValues);

			getRelevanceTable().add(relevanceListSorted);
		}
	}

	public void print() {
		int i = 1;
		for (List<Map.Entry<Integer, Integer>> h : getRelevanceTable()) {
			System.out.println("requete " + i++);
			for (Map.Entry<Integer, Integer> e : h) {
				System.out.println((1 + e.getKey()) + "  " + e.getValue());
			}
		}
	}

	public int innerProduct(int[] vectorDoc, int[] vectorReq) {
		int result = 0;
		for (int i = 0; i < vectorDoc.length; i++) {
			result += vectorDoc[i] * vectorReq[i];
		}
		return result;
	}

	public ArrayList<List<Map.Entry<Integer, Integer>>> getRelevanceTable() {
		return relevanceTable;
	}

	public void setRelevanceTable(
			ArrayList<List<Map.Entry<Integer, Integer>>> relevanceTable) {
		this.relevanceTable = relevanceTable;
	}

}
