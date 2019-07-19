package blee.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.util.Optional;

public class MagicAuthenticator implements Authenticator<String, User> {
    private final String token;
    private final String admin;

    public MagicAuthenticator(String token, String admin) {
        this.token = token;
        this.admin = admin;
    }

    @Override
    public Optional<User> authenticate(String token) throws AuthenticationException {

        if (this.token.equals(token)) {
            return Optional.of(new User("USER"));
        }

        if (this.admin.equals(token)) {
            return Optional.of(new User("ADMIN"));
        }

        return Optional.empty();
    }
}
