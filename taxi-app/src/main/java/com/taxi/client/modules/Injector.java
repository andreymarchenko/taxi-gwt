package com.taxi.client.modules;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.taxi.client.controller.LifeCycle;

@GinModules(Module.class)
public interface Injector extends Ginjector {
    LifeCycle getLifeCycle();
}
