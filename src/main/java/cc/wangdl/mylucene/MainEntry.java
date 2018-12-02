package cc.wangdl.mylucene;

import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

/**
 * Description: //TODO(简略描述这个类的作用)
 * Author: WangDL
 * Date: 2018-12-02 13:04
 * Copyright (c) 2018, ewell.com All Rights Reserved.
 */
public class MainEntry {

    /*
    1.Create Documents by adding Fields;
    2.Create an IndexWriter and add documents to it with addDocument();
    3.Call QueryParser.parse() to build a query from a string; and
    4.Create an IndexSearcher and pass the query to its search() method.
     */
    public static void main(String[] args) {
        MyIndexCreator  creator  = new MyIndexCreator();
        MyIndexSearcher searcher = new MyIndexSearcher();

        try {
            creator.createIndex();
            searcher.searchIndex();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("==============lucene===============");
    }

}
