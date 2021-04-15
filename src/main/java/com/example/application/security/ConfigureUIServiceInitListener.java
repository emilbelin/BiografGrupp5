package com.example.application.security;


import com.example.application.views.Login.HomePageView;
import com.example.application.views.Staff.MovieView;
import com.example.application.views.Staff.ScheduleView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

import org.springframework.stereotype.Component;

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {



    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    private void authenticateNavigation(BeforeEnterEvent event) {
        if (HomePageView.class.equals(event.getNavigationTarget())
            && SecurityUtils.isUserLoggedIn()) {
            event.rerouteTo("movie");
        }
    }
}