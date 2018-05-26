package com.taxi.client.commands;

import com.google.gwt.event.shared.EventHandler;
import com.taxi.shared.dto.LoginDto;

public interface LoginCommandHandler extends EventHandler {
    void onLogin(LoginDto loginDto, LoginCommand loginCommand);
}
