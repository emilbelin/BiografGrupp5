package com.example.application.views.Staff;

import com.example.application.Backend.model.ScheduleObject;
import com.example.application.Backend.service.ScheduleService;
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

import java.awt.*;

@Route(value = "Schedule", layout = StaffLayout.class)
public class ScheduleView extends VerticalLayout {

    protected Grid<ScheduleObject> grid = new Grid<>(ScheduleObject.class);
    protected SingleSelect<Grid<ScheduleObject>, ScheduleObject> selection = grid.asSingleSelect();


    protected Button add = new Button("Lägg till");
    protected Button delete = new Button("Ta bort");

    protected ScheduleService scheduleService;
    protected ScheduleForm form;
    protected StaffService staffService;
    public ScheduleView(ScheduleService scheduleService, StaffService staffService)
    {
        this.staffService = staffService;
        this.scheduleService = scheduleService;
        form = new ScheduleForm(staffService, this);
        form.setVisible(false);

        configureButtons();

        setSizeFull();

        HorizontalLayout buttonLayout = new HorizontalLayout();

        buttonLayout.add(add, delete);

        grid.setColumns("staff", "date", "skift", "station");

        grid.getColumnByKey("staff").setHeader("Person");
        grid.getColumnByKey("date").setHeader("Datum");
        grid.getColumnByKey("skift").setHeader("Tid");
        grid.getColumnByKey("station").setHeader("Station");

        add(buttonLayout,grid,form);
    }

    public void populateGrid()
    {

    }
    public ScheduleObject getSelection()
    {
        return selection.getValue();
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
