package blee.api;

import blee.db.ScoreBoardDao;
import blee.model.ScoreBoard;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.skife.jdbi.v2.exceptions.UnableToObtainConnectionException;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import java.util.List;

public abstract class ScoreBoardService {
    @CreateSqlObject
    abstract ScoreBoardDao scoreBoardDao();

    public List<ScoreBoard> getScoreBoards() {
        return scoreBoardDao().getScoreBoards();
    }

    public ScoreBoard getScoreBoard(String name) {
        return scoreBoardDao().getScoreBoards(name);
    }

    public ScoreBoard updateScoreBoard(ScoreBoard scoreBoard) {
        return scoreBoardDao().updateScoreBoard(scoreBoard);
    }

    public boolean deleteScoreBoard(String name) {
        scoreBoardDao().deleteScoreBoard(name);
        return true;
    }

    public void deleteAll() {
        scoreBoardDao().resetScoreBoard();
    }

    public String performHealthCheck() {
        try {
            scoreBoardDao().getScoreBoards();
        } catch (UnableToObtainConnectionException ex) {
            return "No connection: " + ex.getLocalizedMessage();
        } catch (UnableToExecuteStatementException ex) {
            return "No execution: " + ex.getLocalizedMessage();
        } catch (Exception ex) {
            return "DATABASE ERROR: " + ex.getLocalizedMessage();
        }
        return null;
    }
}
