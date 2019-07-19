package blee.mapper;

import blee.model.ScoreBoard;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreBoardMapper implements ResultSetMapper<ScoreBoard> {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SCORE = "score";

    @Override
    public ScoreBoard map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new ScoreBoard(resultSet.getInt(ID), resultSet.getInt(SCORE), resultSet.getString(NAME));
    }
}
