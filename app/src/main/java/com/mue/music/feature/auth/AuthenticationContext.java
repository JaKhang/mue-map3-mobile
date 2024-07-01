package com.mue.music.feature.auth;

import com.mue.music.model.Principal;

public interface AuthenticationContext {
    boolean isAuthenticated();

    void setToken(String token);

    String getToken();

    Principal getPrincipal();

    void setPrincipal(Principal data);
}
