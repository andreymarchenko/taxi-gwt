package com.taxi.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.taxi.client.controller.LifeCycle;
import com.taxi.client.modules.Injector;
import org.fusesource.restygwt.client.Defaults;

public class taxi implements EntryPoint {

    public void onModuleLoad() {
        Defaults.setServiceRoot(GWT.getHostPageBaseURL());
        Injector injector = GWT.create(Injector.class);
        LifeCycle lifeCycle = injector.getLifeCycle();
        lifeCycle.start();
    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
