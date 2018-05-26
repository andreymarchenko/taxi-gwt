package com.taxi.client.commands;

import com.google.gwt.event.shared.GwtEvent;
import com.taxi.shared.dto.ClientDto;

public class ChangeDataCommand extends GwtEvent<ChangeDataCommandHandler> {

    ClientDto eventDto;

    public static Type<ChangeDataCommandHandler> TYPE = new Type<ChangeDataCommandHandler>();

    @Override
    public Type<ChangeDataCommandHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ChangeDataCommandHandler handler) {
        handler.onChangeData(this);
    }

    public static ChangeDataCommand create() {
        return new ChangeDataCommand();
    }
}
