package com.pagerank.core;

/**
 * 比赛结果类
 * Created by 王麟东 on 2017/7/8 0008.
 */
public class MatchResult {
    private String teamA;
    private String teamB;
    private int scoreA;
    private int scoreB;

    public MatchResult(String line) {
        String temp[] = line.split("-");

        this.teamA = temp[0].split(" ")[0];
        this.scoreA = Integer.parseInt(temp[0].split(" ")[1]);

        this.teamB = temp[1].split(" ")[1];
        this.scoreB = Integer.parseInt(temp[1].split(" ")[0]);
    }

    /**
     * 获得权重（比赛结果）
     * @param team 球队名
     * @return 该球队在本条比赛记录中的比赛结果，胜3平1负0，若本条比赛记录中没有这个球队，返回-1
     */

    public int getWeight(String team){
        int weight = -1;
        if (team.equals(this.teamA)){
            if (this.scoreA == this.scoreB){
                weight = 1;
            }else if (this.scoreA > this.scoreB){
                weight = 3;
            }else if (this.scoreA < this.scoreB){
                weight = 0;
            }
        }else if (team.equals(this.teamB)){
            if (this.scoreB == this.scoreA){
                weight = 1;
            }else if (this.scoreB > this.scoreA){
                weight = 3;
            }else if (this.scoreB < this.scoreA){
                weight = 0;
            }
        }

        return weight;
    }

    /**
     * 在获得比赛权重不为-1的情况下，获得对手球队名
     * @param team 球队名
     * @return 本条比赛记录中的对手球队名
     */

    public String getOtherTeam(String team){
        String otherTeam = this.teamA;

        if (team.equals(this.teamA)){
            otherTeam = this.teamB;
        }

        return otherTeam;
    }
}
