package blee.auth;

import io.dropwizard.auth.Authorizer;

public class MagicAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {
        return user.getRole().equals(role);
    }
}
