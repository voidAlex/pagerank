package com.pagerank.core;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王麟东 on 2017/7/8 0008.
 */
public class PageRank {
    private Map<String, TeamItem> teamMap;
    private List<MatchResult> matchResultList;
    private String teamInfopath;
    private String matchResultPath;
    private int max;

    public PageRank(String teamInfopath, String matchResultPath, int max){
        this.teamInfopath = teamInfopath;
        this.matchResultPath = matchResultPath;
        this.max = max;
        init();
    }

    /**
     * 初始化，将球队信息和比赛结果读取到内存
     */

    private void init(){
        try {
            BufferedReader teamReader = new BufferedReader(new FileReader(new File(this.teamInfopath)));
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(teamReader.readLine(), JsonArray.class);
            teamReader.close();

            this.teamMap = new HashMap<String, TeamItem>();
            for (JsonElement jsonElement : jsonArray){
                TeamItem teamItem = new TeamItem();
                teamItem.setName(jsonElement.getAsJsonObject().get("team").getAsString());
                this.teamMap.put(teamItem.getName(), teamItem);
            }

            BufferedReader matchReader = new BufferedReader(new FileReader(new File(this.matchResultPath)));
            String line = "";

            this.matchResultList = new ArrayList<MatchResult>();
            while ((line = matchReader.readLine()) != null){
                MatchResult matchResult = new MatchResult(line);
                this.matchResultList.add(matchResult);
            }
            matchReader.close();
        }catch (IOException e){
            System.out.println("读取失败");
            System.exit(1);
        }

    }

    public Map<String, TeamItem> getTeamMap() {
        return teamMap;
    }

    public int getMax() {
        return max;
    }

    /**
     * PageRank算法迭代器
     * @param teamMaps 球队信息
     * @param max 迭代次数
     */

    public void Iteration(Map<String, TeamItem> teamMaps, int max){
        Map<String, TeamItem> tmp = new HashMap<String, TeamItem>();

        for (TeamItem teamItem : teamMaps.values()){
            TeamItem tmpTeam = new TeamItem();
            tmpTeam.setName(teamItem.getName());
            double pagerank = 0;
            int count = 0;

            for (MatchResult matchResult : this.matchResultList){
                int weight = matchResult.getWeight(teamItem.getName());
                if (weight != -1){
                    double pr = teamMaps.get(matchResult.getOtherTeam(teamItem.getName())).getPagerank();
                    pagerank += (weight * pr);
                    count ++;
                }
            }

            tmpTeam.setPagerank(pagerank / count);
            tmp.put(tmpTeam.getName(), tmpTeam);
        }
        max --;
        this.teamMap = tmp;
        if (max > 0){
            Iteration(this.teamMap, max);
        }
    }

    /**
     * 将结果写入文件
     */

    public void wirteToFile(){
        List<TeamItem> list = new ArrayList<TeamItem>(this.teamMap.values());

        try {
            JsonArray jsonArray = new JsonArray();
            for (TeamItem teamItem : list){
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("team", teamItem.getName());
                jsonObject.addProperty("pagerank", teamItem.getPagerank());

                jsonArray.add(jsonObject);
            }

            FileOutputStream outputStream = new FileOutputStream(new File(this.teamInfopath));
            outputStream.write(jsonArray.toString().getBytes());
            outputStream.close();
        }catch (IOException e){
            System.out.println("写入失败");
            System.exit(1);
        }
    }

    /**
     * 输出信息
     */

    public void print(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.teamInfopath)));
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(reader.readLine(), JsonArray.class);
            reader.close();

            int i = 1;
            while (jsonArray != null){
                Double max = 0.0;
                JsonElement temp = null;
                for (JsonElement je : jsonArray){
                    if(je.getAsJsonObject().get("pagerank").getAsDouble() > max){
                        temp = je;
                        max = je.getAsJsonObject().get("pagerank").getAsDouble();
                    }
                }

                if(temp != null){
                    System.out.println(i + ":" + temp.getAsJsonObject().get("team").getAsString()
                            + " " + temp.getAsJsonObject().get("pagerank").getAsString());

                    jsonArray.remove(temp);
                    i ++;
                }else {
                    break;
                }

            }
        }catch (IOException e){
            System.out.println("读取失败");
            System.exit(1);
        }
    }
}
