import com.pagerank.core.PageRank;

/**
 * Created by 王麟东 on 2017/7/8 0008.
 */
public class Test {
    @org.junit.Test
    public void test(){
        String team = "nba_team_list.json";
        String match = "nba_result_list";
        int max = 20;

        PageRank pageRank = new PageRank(team, match, max);
        pageRank.Iteration(pageRank.getTeamMap(), pageRank.getMax());
        pageRank.wirteToFile();
        pageRank.print();
    }
}
