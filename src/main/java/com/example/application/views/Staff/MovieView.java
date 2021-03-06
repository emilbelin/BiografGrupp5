package com.example.application.views.Staff;
import com.example.application.Backend.model.Movie;
import com.example.application.Backend.service.MovieService;
import com.example.application.forms.MovieForm;
import com.example.application.forms.formState;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;


import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;

import com.vaadin.flow.router.*;


@Route(value = "movie", layout = StaffLayout.class)
@PageTitle("Lägg till/ta bort filmer")

public class MovieView extends VerticalLayout implements BeforeEnterListener {



    protected MovieService movieService;
    protected MovieForm form;

    protected Grid<Movie> grid = new Grid<>(Movie.class);
    protected SingleSelect<Grid<Movie>, Movie> selection = grid.asSingleSelect();

    protected Button delete = new Button("Ta bort");
    protected Button add = new Button("Lägg till");

    public MovieView(MovieService movieService) {

        this.movieService = movieService;
        form = new MovieForm(movieService, this);
        form.setVisible(false);

        configureButtons();


        setSizeFull();

        HorizontalLayout buttonLayout = new HorizontalLayout();

        buttonLayout.add(add, delete);


        grid.setColumns("id", "titel", "sprak", "aldergrans", "genre", "langd");
        grid.getColumnByKey("titel").setHeader("Titel");
        grid.getColumnByKey("sprak").setHeader("Språk");
        grid.getColumnByKey("aldergrans").setHeader("Åldersgräns");
        grid.getColumnByKey("genre").setHeader("Genre");
        grid.getColumnByKey("langd").setHeader("Längd(Minuter)");

        grid.asSingleSelect().addValueChangeListener(event -> selectionHandler());
        grid.removeColumn(grid.getColumnByKey("id"));

        add(buttonLayout,grid, form);
       
        updateList();

    }


    /**
     * @return the Movie object that is currently selected in the grid
     */
    public Movie getSelection() {
        return selection.getValue();
    }


    /**
     * Fills the grid with data from the mySQL database.
     * MovieService.findAll() runs a query and returns a list of objects from the DB.
     */
    public void updateList() {
        grid.setItems(movieService.findAll());
    }

    /**
     * Adds configurations for both visuals and functionality to buttons
     */
    public void configureButtons() {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add.addClickListener(event -> formVisibility(true, formState.adding));
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        delete.addClickListener(event -> form.deleteAndUpdate());
    }

    /**
     * @param bool true to set form to visible, false to hide
     * @param state ENUMS for deciding which buttons the form should have ( Adding, Editing, None )
     *              Hides/Shows the form and configures the form functionality depending on action
     */
    public void formVisibility(Boolean bool, formState state) {
        form.setVisible(bool);
        form.configureForm(state, form);
    }


    /**
     * If there is nothing selected, hide the form
     * If a grid item is selected, show the form with the editing state.
     */
    public void editFormObject() {
        if (selection.getValue() == null) {
            form.setVisible(false);
        } else {
            formVisibility(true, formState.editing);
            form.editMovie();
        }

    }

    public void selectionHandler() {
        if (selection.isEmpty()) {
            formVisibility(false, formState.none);
        } else {
            formVisibility(true, formState.editing);
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("USER")) {
            setEnabled(true);
        }
    }

}