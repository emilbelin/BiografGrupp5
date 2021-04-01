package com.example.application.views.Personal;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import org.springframework.stereotype.Controller;

@Route(value = "personal", layout = MainView.class)
@PageTitle("Admintools")
@CssImport("./views/about/about-view.css")
@Controller

public class PersonalView extends Div {
    private LoginForm loginForm = new LoginForm();

    public PersonalView() {
        addClassName("personal-view");
        add(new Text("HÄR SKA ALLT ADMINRELATERAT SKE"));


    }


}

