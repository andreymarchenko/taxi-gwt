package com.taxi.client.commands;

import com.google.gwt.event.shared.EventHandler;
import com.taxi.shared.dto.ClientDto;

public interface RegistrationCommandHandler extends EventHandler {
    void onRegister(ClientDto clientDto, RegistrationCommand registrationCommand);
}
