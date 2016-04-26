package com.nlte.smartcity.domain;

import java.util.ArrayList;

/**
 * 功能描述：新闻详情数据
 *
 * @author NLTE
 * @time 2016/4/25 0025 16:50
 */
public class NewsBean {
    public int retcode;
    public NewsData data;

    public class NewsData {
        public String more;
        public String title;
        public ArrayList<TopNews> topnews;
        public ArrayList<News> news;

        @Override
        public String toString() {
            return "NewsData{" +
                    "title='" + title + '\'' +
                    ", more='" + more + '\'' +
                    ", topnews=" + topnews +
                    ", news=" + news +
                    '}';
        }
    }

    //头条新闻
    public class TopNews {
        public String id;
        public String pubdate;
        public String title;
        public String topimage;
        public String url;

        @Override
        public String toString() {
            return "TopNews{" +
                    "id='" + id + '\'' +
                    ", pubdate='" + pubdate + '\'' +
                    ", title='" + title + '\'' +
                    ", topimage='" + topimage + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    //普通新闻
    public class News {
        public String id;
        public String pubdate;
        public String title;
        public String listimage;
        public String url;

        @Override
        public String toString() {
            return "News{" +
                    "id='" + id + '\'' +
                    ", pubdate='" + pubdate + '\'' +
                    ", title='" + title + '\'' +
                    ", listimage='" + listimage + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "retcode=" + retcode +
                ", data=" + data +
                '}';
    }
}

