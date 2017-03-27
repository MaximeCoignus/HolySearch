package com.holySearch.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
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

	private static final Logger log = Logger.getLogger(DestinationSearcher.class);

	private IndexSearcher searcher;
	private QueryParser destinationNameQueryParser;

	public DestinationSearcher(String indexDir) throws IOException {
		log.info("IndexReader " + IndexReader.open(FSDirectory.open(new File(indexDir))).getRefCount());
		searcher = new IndexSearcher(IndexReader.open(FSDirectory.open(new File(indexDir))));
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		destinationNameQueryParser = new QueryParser(Version.LUCENE_36, DestinationIndexItem.DESTINATIONFRENCHNAME,
				analyzer);
	}

	public List<DestinationIndexItem> findByDestinationName(String queryString, int numOfResults)
			throws ParseException, IOException {

		log.info("queryString = " + queryString);
		Query query = destinationNameQueryParser.parse(queryString);

		log.info("query = " + query);
		log.info(searcher.search(query, numOfResults).totalHits);

		ScoreDoc[] queryResults = searcher.search(query, numOfResults).scoreDocs;
		List<DestinationIndexItem> results = new ArrayList<DestinationIndexItem>();

		log.info(queryResults.length+" résultats trouvés");
		for (ScoreDoc scoreDoc : queryResults) {
			log.info(scoreDoc+" scoreDoc");
			Document doc = searcher.doc(scoreDoc.doc);
			log.info(doc+" doc");
			if (doc != null) {
				int id = Integer.parseInt(doc.get(DestinationIndexItem.DESTINATIONID));
				String name = doc.get(DestinationIndexItem.DESTINATIONFRENCHNAME);
				results.add(new DestinationIndexItem(Integer.parseInt(doc.get(DestinationIndexItem.DESTINATIONID)),
						doc.get(DestinationIndexItem.DESTINATIONFRENCHNAME)));
			}
		}
		return results;
	}

	public void close() throws IOException {
		searcher.close();
	}
}