package com.holySearch.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class DestinationIndexer {
	private IndexWriter writer;

	public DestinationIndexer(String indexDir) throws IOException {
		if (writer == null) {
			writer = new IndexWriter(FSDirectory.open(new File(indexDir)),
					new IndexWriterConfig(Version.LUCENE_36, new StandardAnalyzer(Version.LUCENE_36)));
		}
	}

	public void index(DestinationIndexItem indexItem) throws IOException {

		writer.deleteDocuments(new Term(DestinationIndexItem.DESTINATIONID, Integer.toString(indexItem.getDestinationId())));
		Document doc = new Document();
		doc.add(new Field(DestinationIndexItem.DESTINATIONID, Integer.toString(indexItem.getDestinationId()), Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(DestinationIndexItem.DESTINATIONJSON, indexItem.getDestinationJSON(),
				Field.Store.YES, Field.Index.ANALYZED));

		writer.addDocument(doc);
	}

	public void close() throws IOException {
		writer.close();
	}
}