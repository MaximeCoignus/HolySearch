package com.holySearch.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeachSearcher {
	private IndexSearcher searcher;
	private QueryParser beachNameQueryParser;

	public BeachSearcher(String indexDir) throws IOException {
		searcher = new IndexSearcher(IndexReader.open(FSDirectory.open(new File(indexDir))));
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		beachNameQueryParser = new QueryParser(Version.LUCENE_36, BeachIndexItem.BEACHNAME, analyzer);
	}

	public List findByBeachName(String queryString, int numOfResults) throws ParseException, IOException {

		Query query = beachNameQueryParser.parse(queryString);

		ScoreDoc[] queryResults = searcher.search(query, numOfResults).scoreDocs;
		List results = new ArrayList();

		for (ScoreDoc scoreDoc : queryResults) {
			Document doc = searcher.doc(scoreDoc.doc);
			results.add(new BeachIndexItem(Integer.parseInt(doc.get(BeachIndexItem.ID)),
					doc.get(BeachIndexItem.BEACHNAME)));
		}
		return results;
	}

	public void close() throws IOException {
		searcher.close();
	}
}