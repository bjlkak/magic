package blee;

import blee.api.ScoreBoardService;
import blee.auth.MagicAuthenticator;
import blee.auth.MagicAuthorizer;
import blee.auth.User;
import blee.health.MagicHealthCheck;
import blee.model.ScoreBoard;
import blee.resources.MagicResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;
import javax.ws.rs.Produces;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MagicApplication extends Application<MagicConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MagicApplication().run(args);
    }

    @Override
    public String getName() {
        return "Magic";
    }

    @Override
    public void initialize(final Bootstrap<MagicConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(new MigrationsBundle<MagicConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(MagicConfiguration configuration) {
                return configuration.getDatabaseSourceFactory();
            }
        });
    }

    @Override
    public void run(final MagicConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        final DataSource dataSource =
                configuration.getDatabaseSourceFactory().build(environment.metrics(), "sql");

        DBI dbi = new DBI(dataSource);

        MagicHealthCheck healthCheck = new MagicHealthCheck(dbi.onDemand(ScoreBoardService.class));

        environment.healthChecks().register("Magic App", healthCheck);

        environment.jersey().register(new MagicResource(dbi.onDemand(ScoreBoardService.class)));

        Properties prop = this.getProperties();
        String token = prop.getProperty("token");
        String admin = prop.getProperty("admin");
        environment.jersey().register(new AuthDynamicFeature(new OAuthCredentialAuthFilter.Builder<User>()
        .setAuthenticator(new MagicAuthenticator(token, admin))
        .setAuthorizer(new MagicAuthorizer())
        .setPrefix("Bearer").buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
    }

    private Properties getProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
