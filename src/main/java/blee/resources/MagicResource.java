package blee.resources;

import blee.api.ScoreBoardService;
import blee.model.ScoreBoard;
import com.codahale.metrics.annotation.Timed;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/magic")
@Produces(MediaType.APPLICATION_JSON)
public class MagicResource {
    private final ScoreBoardService scoreBoardService;

    public MagicResource(ScoreBoardService scoreBoardService) {
        this.scoreBoardService = scoreBoardService;
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GET
    @Timed
    public List<ScoreBoard> getScoreBoards() {
        return scoreBoardService.getScoreBoards();
    }

    @RolesAllowed({"ADMIN", "USER"})
    @POST
    @Timed
    public ScoreBoard updateScoreBoard(@QueryParam("name") @NotEmpty @Length(max=50) String name, @QueryParam("score") int score) {
        return scoreBoardService.updateScoreBoard(new ScoreBoard(score, name));
    }

    @RolesAllowed("ADMIN")
    @DELETE
    @Timed
    public void deleteScoreBoard(@QueryParam("name") String name) {
        if(name == null) {
            scoreBoardService.deleteAll();
        }
        else {
            scoreBoardService.deleteScoreBoard(name);
        }
    }
}
