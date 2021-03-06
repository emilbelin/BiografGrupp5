package com.example.application.forms;

import com.example.application.Backend.model.*;
import com.example.application.Backend.service.StaffScheduleService;
import com.example.application.Backend.service.StaffService;
import com.example.application.views.Staff.ScheduleView;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import java.awt.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class ScheduleForm extends FormLayout {
    protected List<Skift> skiftList = new ArrayList<>();
    protected List<Station> stationList = new ArrayList<>();
    protected StaffService staffService;
    protected StaffScheduleService staffScheduleService;
    protected ScheduleView scheduleView;

    protected Binder<ScheduleObject> binder = new Binder<>(ScheduleObject.class);

    protected Button add = new Button("Lägg till");
    protected Button save = new Button("Spara");
    protected Button cancel = new Button("Avbryt");
    protected Button clear = new Button("Rensa fält");
    protected Staff staff;
    protected ScheduleObject schedule = new ScheduleObject(null,null,null, null);

    protected List<ScheduleObject> scheduleObjectList = new ArrayList<>();
    protected ComboBox<Staff> staffPicker = new ComboBox<>("Personal");
    protected ComboBox<Skift> skiftPicker = new ComboBox<>("Skift");
    protected ComboBox<Station> stationPicker = new ComboBox<>("Station");
    protected DatePicker datePicker = new DatePicker("Datum");
    protected HorizontalLayout buttonLayout = new HorizontalLayout();

    /**
     * @param staffService fetches data from DB
     * @param scheduleView view with schedules
     * @param staffScheduleService fetches data from DB
     * Sets the form in the scheduleview so the user can add new schedules
     */
    public ScheduleForm(StaffService staffService, ScheduleView scheduleView, StaffScheduleService staffScheduleService)
    {
        this.staffScheduleService = staffScheduleService;
        this.staffService = staffService;
        this.scheduleView = scheduleView;
        configureBinder();
        configureButtons();
        configureButtonListener();
        configureComboBoxes();

        add(staffPicker, skiftPicker, stationPicker, datePicker, buttonLayout);
    }

    /**
     * Fills the form fields with data from the DB
     */
    public void configureBinder()
    {
        binder.forField(staffPicker).bind(ScheduleObject::getStaff, ScheduleObject::setStaff);
        binder.forField(datePicker).bind(ScheduleObject::getDate, ScheduleObject::setDate);
        binder.forField(skiftPicker).bind(ScheduleObject::getSkift, ScheduleObject::setSkift);
        binder.forField(stationPicker).bind(ScheduleObject::getStation, ScheduleObject::setStation);
        binder.setBean(schedule);
    }

    /**
     * @param state
     * @param form
     * Depending on the ENUM state value(adding, editing, none) ->
     * Configure the form to allow either adding, editing shows
     * or hide the form if state.none.
     */
    public void configureForm(formState state, ScheduleForm form)
    {
        form.remove(staffPicker, skiftPicker, stationPicker, datePicker, buttonLayout);
        if(state == formState.editing)
        {
            buttonLayout = buttonsEditing();
           // schedule = scheduleView.getSelection();
            binder.setBean(schedule);
            form.add(
                    staffPicker, skiftPicker, stationPicker, datePicker, buttonLayout
            );
        }
        else if(state == formState.adding)
        {
            clearSchedule();
            buttonLayout = buttonsAdding();
            form.add(
                    staffPicker, skiftPicker, stationPicker, datePicker, buttonLayout
            );
        }
        else
        {
            form.setVisible(false);
        }
    }

    private HorizontalLayout buttonsAdding()
    {
        return new HorizontalLayout(add, clear, cancel);
    }
    private HorizontalLayout buttonsEditing()
    {
        return new HorizontalLayout(save, clear);
    }

    /**
     * Configure button visuals
     */
    private void configureButtons()
    {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
        clear.addThemeVariants(ButtonVariant.LUMO_ERROR);
    }


    public List<ScheduleObject> getList()
    {
        return scheduleObjectList;
    }

    public void clearSchedule()
    {
        schedule = new ScheduleObject(null, null,null, null);
        binder.setBean(schedule);
    }

    public void deleteAndUpdate()
    {
        staffScheduleService.deleteSchedule(scheduleView.getSelection().getSchema_id());
        scheduleView.populateGrid();
    }

    private void addSchedule()
    {
        staffScheduleService.addToScheme(
                datePicker.getValue(),
                staffPicker.getValue().getId(),
                stationPicker.getValue().getId(),
                skiftPicker.getValue().getSkift_id());

        scheduleView.populateGrid();
    }

    private void closeForm()
    {
        this.setVisible(false);
    }

    /**
     * Configure button functionality
     */
    public void configureButtonListener()
    {
        add.addClickListener(event -> addSchedule());
        clear.addClickListener(event -> clearSchedule());
        cancel.addClickListener(event -> closeForm());
    }

    /**
     * Fill comboboxes with data from DB and configures what is displayed to the user
     */
    private void configureComboBoxes()
    {
        staffPicker.setItemLabelGenerator(Staff::getFullName);
        staffPicker.setItems(staffService.findAll());
        staffPicker.addValueChangeListener(event ->
        {
            if (event.getValue() == null)
            {

            }
            else
            {
                //Selected Object
            }
        });

        skiftPicker.setItemLabelGenerator(Skift::getValue);
        skiftPicker.setItems(staffScheduleService.findSkift());

        stationPicker.setItemLabelGenerator(Station::getNamn);
        stationPicker.setItems(staffScheduleService.findStation());
    }

}
