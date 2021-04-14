package com.example.application.views.Staff;

import com.example.application.Backend.model.ScheduleViewModel;
import com.example.application.Backend.service.StaffScheduleService;
import com.example.application.Backend.service.StaffService;
import com.example.application.forms.ScheduleForm;
import com.example.application.forms.formState;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.Route;

@Route(value = "ScheduleViewModel", layout = StaffLayout.class)
public class ScheduleView extends VerticalLayout {

    protected Grid<ScheduleViewModel> grid = new Grid<>(ScheduleViewModel.class);
    protected SingleSelect<Grid<ScheduleViewModel>, ScheduleViewModel> selection = grid.asSingleSelect();


    protected Button add = new Button("Lägg till");
    protected Button delete = new Button("Ta bort");

    protected StaffScheduleService staffScheduleService;
    protected ScheduleForm form;
    protected StaffService staffService;
    public ScheduleView(StaffService staffService, StaffScheduleService staffScheduleService)
    {
        this.staffScheduleService = staffScheduleService;
        this.staffService = staffService;
        form = new ScheduleForm(staffService,this, staffScheduleService);
        form.setVisible(false);

        configureButtons();

        setSizeFull();

        HorizontalLayout buttonLayout = new HorizontalLayout();

        buttonLayout.add(add, delete);

        grid.setColumns("fornamn", "efternamn", "station", "datum", "skiftstart", "skiftslut", "schema_id");

        grid.getColumnByKey("fornamn").setHeader("Förnamn");
        grid.getColumnByKey("efternamn").setHeader("Efternamn");
        grid.getColumnByKey("station").setHeader("Station");
        grid.getColumnByKey("datum").setHeader("Datum");
        grid.getColumnByKey("skiftstart").setHeader("Skift Start");
        grid.getColumnByKey("skiftslut").setHeader("Skift Slut");
        grid.removeColumn(grid.getColumnByKey("schema_id"));


        add(buttonLayout,grid,form);
        populateGrid();
    }

    public void populateGrid()
    {
        grid.setItems(staffScheduleService.findScheduleList());
    }
    public ScheduleViewModel getSelection()
    {
        return selection.getValue();
    }
    public void configureButtons()
    {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        delete.addClickListener(event ->form.deleteAndUpdate());
        add.addClickListener(event -> formVisibility(true, formState.adding));
    }

    public void formVisibility(Boolean bool, formState state)
    {
        form.setVisible(bool);
        form.configureForm(state, form);
    }

}
