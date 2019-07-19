package blee.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ScoreBoard {
    private int id;

    @NotEmpty
    private int score;

    @NotEmpty
    @Length(max = 50)
    private String name;

    public ScoreBoard() {

    }

    public ScoreBoard(int id, int score, String username) {
        this.id = id;
        this.score = score;
        this.name = username;
    }

    public ScoreBoard(int score, String username) {
        this.score = score;
        this.name = username;
    }

    @JsonProperty
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
