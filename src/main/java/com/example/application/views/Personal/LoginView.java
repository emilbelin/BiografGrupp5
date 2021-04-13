package com.example.application.views.Personal;

import com.example.application.views.Kund.FilmView;
import com.example.application.views.main.HomePageView;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import static com.vaadin.flow.component.UI.getCurrent;

@Route("login")
@PageTitle("Personal - Javabiografen")


public class LoginView extends VerticalLayout {


    Button button = new Button("Gå tillbaka");

    public LoginView() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        HorizontalLayout layout = new HorizontalLayout();
        VerticalLayout vlay = new VerticalLayout();
        button.addClickListener( e -> getCurrent().navigate(HomePageView.class));
        layout.setVerticalComponentAlignment(Alignment.CENTER, button);
        vlay.setHorizontalComponentAlignment(Alignment.CENTER,button);
        button.setHeight(2, Unit.CM);
        button.setWidth(6,Unit.CM);

        layout.setJustifyContentMode(JustifyContentMode.CENTER);

        add(new H1("FEL ANVÄNDARNAMN ELLER LÖSENORD :)"));
        add(button);




    }


}
