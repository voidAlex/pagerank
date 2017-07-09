package com.pagerank.core;

/**
 * 球队类
 * Created by 王麟东 on 2017/7/8 0008.
 */
public class TeamItem {
    private String name;
    private double pagerank;

    public TeamItem(){
        this.pagerank = 1;
    }


    public String getName() {
        return name;
    }


    public double getPagerank() {
        return pagerank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPagerank(double pagerank) {
        this.pagerank = pagerank;
    }
}
