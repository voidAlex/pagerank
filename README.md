# 使用PageRank算法计算球队排名

详情见[这里](https://voidalex.github.io/2017/07/08/%E4%BD%BF%E7%94%A8PageRank%E7%AE%97%E6%B3%95%E8%AE%A1%E7%AE%97%E7%90%83%E9%98%9F%E6%8E%92%E5%90%8D/)

## 目录结构
```
PageRank
    |- src/                 代码目录
    |- Liga_result_list     西甲联赛比赛结果（2016-2017赛季）
    |- Liga_team_list.json  西甲联赛球队信息
    |- PL_result_list       英超联赛比赛结果（2016-2017赛季）
    |- PL_team_list.json    英超联赛球队信息
    |- nba_result_list      NBA比赛结果（2016-2017赛季常规赛）
    |- nba_team_list.json   NBA球队信息
    |- lpl_result_list      LPL联赛比赛结果（S7赛季春季赛）
    |- lpl_team_list.json   LPL联赛球队信息
    |- pom.xml              Maven配置文件
```

## 编译环境
Maven3+，JDK1.8+