package blee.health;

import blee.api.ScoreBoardService;
import com.codahale.metrics.health.HealthCheck;

public class MagicHealthCheck extends HealthCheck {
    private static final String HEALTHY = "Very Healthy";
    private static final String UNHEALTHY = "Unhealthy :(";

    private static final String MESSAGE_PLACEHOLDER = "{}";

    private final ScoreBoardService scoreBoardService;

    public MagicHealthCheck(ScoreBoardService scoreBoardService) {
        this.scoreBoardService = scoreBoardService;
    }

    @Override
    public Result check() throws Exception {
        String status = scoreBoardService.performHealthCheck();

        if(status == null){
            return Result.healthy(HEALTHY);
        }
        else {
            return Result.unhealthy(UNHEALTHY + MESSAGE_PLACEHOLDER, status);
        }
    }
}
