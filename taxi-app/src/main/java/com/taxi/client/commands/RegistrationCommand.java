package com.taxi.client.commands;

import com.google.gwt.event.shared.GwtEvent;
import com.taxi.shared.dto.ClientDto;

public class RegistrationCommand extends GwtEvent<RegistrationCommandHandler>{
    private ClientDto clientDto;

    public static GwtEvent.Type<RegistrationCommandHandler> TYPE = new GwtEvent.Type<RegistrationCommandHandler>();

    public RegistrationCommand(ClientDto clientDto) {this.clientDto = clientDto;}

    @Override
    public GwtEvent.Type<RegistrationCommandHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RegistrationCommandHandler handler) {
        handler.onRegister(clientDto, this);
    }

    public static RegistrationCommand create(ClientDto clientDto) {
        return new RegistrationCommand(clientDto);
    }
}
