package cc.wangdl.mylucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Description: 索引创建器
 * Author: WangDL
 * Date: 2018-12-02 13:43
 * Copyright (c) 2018, ewell.com All Rights Reserved.
 */
public class MyIndexCreator {


    public void createIndex() throws IOException {
        Analyzer analyzer = new StandardAnalyzer();

        // To store an index on disk, use this instead:
        Path              path      = FileSystems.getDefault().getPath(Constants.IDEX_DIR);
        Directory         directory = new SimpleFSDirectory(path);
        IndexWriterConfig config    = new IndexWriterConfig(analyzer);
        IndexWriter       iwriter   = new IndexWriter(directory, config);
        Document          doc       = new Document();
        String            text      = "This is the text to be indexed.";
        doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
        iwriter.addDocument(doc);
        iwriter.close();
    }
}
