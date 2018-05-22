package com.taxi.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface taxiServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
