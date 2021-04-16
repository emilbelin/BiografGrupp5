package com.example.application.views.Homepage;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import static com.vaadin.flow.component.UI.getCurrent;

@Route("login")
@PageTitle("Personal - Javabiografen")
public class LoginView extends VerticalLayout {

    private LoginForm loginForm = new LoginForm();
    HorizontalLayout layout = new HorizontalLayout();
    Button button = new Button("Tillbaka");

    /**
     * If someone tries to access the secured content of the site, per example the movie view or the schedule view,
     * you are directed to this route which tells you that you've entered the wrong username or password.
     * A button was added to take you back to the landing page.
     */


    public LoginView() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(new H1("Fel användarnamn eller lösenord"));
        add(button);
        button.setHeight(2, Unit.CM);
        button.setWidth(6,Unit.CM);
        button.addClickListener( e -> getCurrent().navigate(HomePageView.class));
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
    }



}
