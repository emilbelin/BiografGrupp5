package com.example.application.views.Staff;

import com.example.application.Backend.model.ShowViewModel;
import com.example.application.Backend.service.MovieService;
import com.example.application.Backend.service.ShowService;
import com.example.application.forms.ShowForm;
import com.example.application.forms.formState;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.Route;

@Route(value = "shows", layout = StaffLayout.class)


public class ShowsView extends VerticalLayout {

    protected Grid<ShowViewModel> grid = new Grid<>(ShowViewModel.class);
    protected SingleSelect<Grid<ShowViewModel>, ShowViewModel> select = grid.asSingleSelect();

    protected ShowService showService;
    protected ShowForm form;
    protected MovieService movieService;

    protected Button add = new Button("Lägg till");
    protected Button delete = new Button("Ta bort");


    /**
     * @param showService fetches data from the database
     * @param movieService -||-
     * The view where staff can add and remove shows.
     */
    public ShowsView(ShowService showService, MovieService movieService)
    {
        this.showService = showService;
        this.movieService = movieService;
        this.form = new ShowForm(showService, movieService, this);

        form.setVisible(false);
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(add,delete);
        configureButtons();

        grid.setColumns("film", "biograf", "salong", "platser_kvar", "tid", "datum", "id");
        grid.removeColumn(grid.getColumnByKey("id"));
        grid.getColumnByKey("platser_kvar").setHeader("Platser Kvar");
        populateGrid();

        add(buttonLayout, grid, form);
    }


    /**
     * Fills the grid in the view with Shows from the mySQL database
     */
    public void populateGrid()
    {
        grid.setItems(showService.findForestallningView());
    }

    public void configureButtons()
    {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        add.addClickListener(event -> formVisibility(true, formState.adding));
        delete.addClickListener(event -> form.deleteShow());
    }


    /**
     * @param bool true to set form to visible, false to hide
     * @param state ENUMS for deciding which buttons the form should have ( Adding, Editing, None )
     *              Hides/Shows the form and configures the form functionality depending on action
     */
    public void formVisibility(Boolean bool, formState state)
    {
        form.setVisible(bool);
        form.configureForm(state, form);
    }

    /**
     * @return the selected grid item
     */
    public ShowViewModel getSelection()
    {
        return select.getValue();
    }
}
