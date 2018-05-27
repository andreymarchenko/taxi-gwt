package com.taxi.client.commands;


import com.google.gwt.event.shared.EventHandler;

public interface ChangeDataCommandHandler extends EventHandler {
    void onChangeData(ChangeDataCommand changeDataCommand);
}
