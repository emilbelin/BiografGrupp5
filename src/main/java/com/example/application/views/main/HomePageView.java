package com.example.application.views.main;


import com.example.application.views.Personal.PersonalView;
import com.example.application.views.Kund.FilmView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.awt.*;

@Route(value = "")
@PageTitle("Biografen")
public class HomePageView extends VerticalLayout {


    private H1 viewTitle;

    Button button = new Button("Personal");
    Button button1 = new Button("Kund");

    public HomePageView(){

        viewTitle = new H1("Javagruppens Biograf");
        add(viewTitle);
        add(new Image("images/javabio.png", "My App logo"));


        button.addClickListener( e -> newOverlay());
        button1.addClickListener( e -> UI.getCurrent().navigate(FilmView.class));


        add(button);
        add(button1);


        }
        public void newOverlay()
        {
            LoginOverlay component = new LoginOverlay();
            component.addLoginListener(e -> component.close());
            LoginI18n i18n = LoginI18n.createDefault();
            LoginI18n.Header header = new LoginI18n.Header();
            header.setTitle("Employee Login");
            i18n.setHeader(header);
            component.setI18n(i18n);
            component.setOpened(true);
            component.addLoginListener(e -> {
                component.close();
                UI.getCurrent().navigate(PersonalView.class);
            });

            }
}


