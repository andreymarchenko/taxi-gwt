package com.taxi.client.commands;

import com.google.gwt.event.shared.GwtEvent;
import com.taxi.shared.dto.ClientDto;
import com.taxi.shared.dto.LoginDto;

public class LoginCommand extends GwtEvent<LoginCommandHandler> {

    private LoginDto loginDto;

    public static GwtEvent.Type<LoginCommandHandler> TYPE = new GwtEvent.Type<LoginCommandHandler>();

    public LoginCommand(LoginDto loginDto) {this.loginDto = loginDto;}

    @Override
    public Type<LoginCommandHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LoginCommandHandler handler) {
        handler.onLogin(loginDto, this);
    }

    public static LoginCommand create(LoginDto loginDto) {
        return new LoginCommand(loginDto);
    }
}
