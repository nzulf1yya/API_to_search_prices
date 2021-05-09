package edu.ada.micronaut.security;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.ArrayList;

    @Singleton
    public class AuthenticationProviderUserPassword implements AuthenticationProvider  {
        @Override
        public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
            return Flowable.create(emitter -> {
                if ( authenticationRequest.getIdentity().equals("username") &&
                        authenticationRequest.getSecret().equals("password") ) {
                    emitter.onNext(new UserDetails((String) authenticationRequest.getIdentity(), new ArrayList<>()));
                    emitter.onComplete();
                } else {
                    emitter.onError(new AuthenticationException(new AuthenticationFailed()));
                }
            }, BackpressureStrategy.ERROR);
        }
    }

