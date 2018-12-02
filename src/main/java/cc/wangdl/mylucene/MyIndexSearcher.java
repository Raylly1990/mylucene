package cc.wangdl.mylucene;

import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Description: 索引查询器
 * Author: WangDL
 * Date: 2018-12-02 13:43
 * Copyright (c) 2018, ewell.com All Rights Reserved.
 */
public class MyIndexSearcher {

    public void searchIndex() throws IOException, ParseException {
        Analyzer analyzer = new StandardAnalyzer();

        // Now search the index:
        Path      path      = FileSystems.getDefault().getPath(Constants.IDEX_DIR);
        Directory directory = new SimpleFSDirectory(path);

        DirectoryReader ireader   = DirectoryReader.open(directory);
        IndexSearcher   isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("fieldname", analyzer);
        Query       query  = parser.parse("text");
        ScoreDoc[]  hits   = isearcher.search(query, null, 1000).scoreDocs;

        // Iterate through the results:
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            System.out.println("第" + i + "次->获取到的文档内容:" + hitDoc.get("fieldname"));
        }
        ireader.close();
        directory.close();
    }

    private void assertEquals(Object target, Object source) {
        boolean equal = true;
        if (target instanceof String) {
            equal = target.equals(source);
        }
        if (target instanceof Integer) {
            equal = target.equals(source);
        }

        if (!equal) {
            throw new RuntimeException("和预期不符:target->" + target + "   source=>" + source);
        }
    }


}




