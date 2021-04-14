package com.example.application.views.Staff;

import com.example.application.Backend.model.ShowViewModel;
import com.example.application.Backend.service.ShowService;
import com.example.application.forms.ShowForm;
import com.example.application.forms.formState;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "shows", layout = StaffLayout.class)


public class ShowsView extends VerticalLayout {

    protected Grid<ShowViewModel> grid = new Grid<>(ShowViewModel.class);
    protected ShowService showService;
    protected ShowForm form;

    protected Button add = new Button("Lägg till");
    protected Button delete = new Button("Ta bort");
    public ShowsView(ShowService showService)
    {
        this.showService = showService;
        this.form = new ShowForm(showService);
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(add,delete);
        grid.setColumns("film", "biograf", "salong", "platser_kvar", "tid", "datum");
        populateGrid();
        add(buttonLayout, grid, form);
    }




    public void populateGrid()
    {
        grid.setItems(showService.findForestallningView());
    }
    public void configureButtons()
    {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        add.addClickListener(event -> formVisibility(true, formState.adding));
    }
    public void formVisibility(Boolean bool, formState state)
    {
        form.setVisible(bool);
        form.configureForm(state, form);
    }
}
