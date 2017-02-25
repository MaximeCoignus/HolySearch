package com.holySearch.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class DestinationSearcher {
	private IndexSearcher searcher;
	private QueryParser beachNameQueryParser;

	public DestinationSearcher(String indexDir) throws IOException {
		searcher = new IndexSearcher(IndexReader.open(FSDirectory.open(new File(indexDir))));
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		beachNameQueryParser = new QueryParser(Version.LUCENE_36, DestinationIndexItem.DESTINATIONFRENCHNAME, analyzer);
	}

	public List<DestinationIndexItem> findByDestinationName(String queryString, int numOfResults)
			throws ParseException, IOException {

		Query query = beachNameQueryParser.parse(queryString);

		ScoreDoc[] queryResults = searcher.search(query, numOfResults).scoreDocs;
		List<DestinationIndexItem> results = new ArrayList<DestinationIndexItem>();

		for (ScoreDoc scoreDoc : queryResults) {
			Document doc = searcher.doc(scoreDoc.doc);
			results.add(new DestinationIndexItem(Integer.parseInt(doc.get(DestinationIndexItem.ID)),
					doc.get(DestinationIndexItem.DESTINATIONFRENCHNAME)));
		}
		return results;
	}

	public void close() throws IOException {
		searcher.close();
	}
}