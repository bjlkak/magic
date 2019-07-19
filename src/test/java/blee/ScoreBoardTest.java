package blee;

import blee.model.ScoreBoard;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class ScoreBoardTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final String JSON = "fixtures/scoreboard.json";

    @Test
    public void serializesToJSON() throws Exception {
        final ScoreBoard scoreBoard = new ScoreBoard(0, 1000, "test");

        final String expected =
                MAPPER.writeValueAsString(MAPPER.readValue(fixture(JSON), ScoreBoard.class));

        assertThat(MAPPER.writeValueAsString(scoreBoard)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final ScoreBoard scoreBoard = new ScoreBoard(0, 1000, "test");

        assertThat(MAPPER.readValue(fixture(JSON), ScoreBoard.class).getId()).isEqualTo(scoreBoard.getId());
        assertThat(MAPPER.readValue(fixture(JSON), ScoreBoard.class).getName())
                .isEqualTo(scoreBoard.getName());
        assertThat(MAPPER.readValue(fixture(JSON), ScoreBoard.class).getScore())
                .isEqualTo(scoreBoard.getScore());
    }
}