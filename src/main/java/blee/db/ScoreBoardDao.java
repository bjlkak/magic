package blee.db;

import blee.mapper.ScoreBoardMapper;
import blee.model.ScoreBoard;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(ScoreBoardMapper.class)
public interface ScoreBoardDao {

    @SqlQuery("select * from scoreboard")
    public List<ScoreBoard> getScoreBoards();

    @SqlQuery("select * from scoreboard where name = :name")
    public ScoreBoard getScoreBoards(@Bind("name") final String name);

    @SqlQuery("insert into scoreboard(name, score) values (:name, :score) returning *")
    public ScoreBoard updateScoreBoard(@BindBean ScoreBoard scoreBoard);

    // admin only?
    @SqlUpdate("delete from scoreboard where name = :name")
    public void deleteScoreBoard(@Bind("name") final String name);

    @SqlUpdate("delete from scoreboard")
    public void resetScoreBoard();
}
