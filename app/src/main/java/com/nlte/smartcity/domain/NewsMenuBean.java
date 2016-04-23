package com.nlte.smartcity.domain;

import java.util.ArrayList;

/**网络分类信息的封装
 * 使用Gson解析Json注意事项：
 *  1. 逢{}创建对象，逢[]创建ArrayList<>
 *  2. 类中所有字段的命名要和网络返回的字段一致（名字和类型要一致）
 *  3. 不需要的字段可以不解析或者不声明
 * Created by Nlte
 * 2016/4/23 0023.
 */

public class NewsMenuBean {
    public int retcode;
    public ArrayList<String> extend;
    public ArrayList<NewsMenuData> data;

    @Override
    public String toString() {
        return "NewsMenuBean{" +
                "retcode=" + retcode +
                ", extend=" + extend +
                ", data=" + data +
                '}';
    }

    /*侧边栏对象*/
    public class  NewsMenuData{
        public String id;
        public String title;
        public int type;
        public ArrayList<NewsTabData> children;

        @Override
        public String toString() {
            return "NewsMenuData{" +
                    "title='" + title + '\'' +
                    ", children=" + children +
                    '}';
        }
    }
    /*Tab对象， 页签对象*/
    public class NewsTabData{
        public String id;
        public String title;
        public String url;
        public int type;

        @Override
        public String toString() {
            return "NewsTabData{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

}
