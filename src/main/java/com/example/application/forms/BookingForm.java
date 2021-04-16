package com.example.application.forms;

import com.example.application.Backend.model.*;
import com.example.application.Backend.service.BookingService;
import com.example.application.views.Customer.BookingView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.example.application.Backend.model.Customer;

import java.util.List;

public class BookingForm extends FormLayout {

    protected List<Customer> customers;

    protected BookingService bookingService;
    protected BookingView bookingView;

    protected TextField first_Name = new TextField("Namn");
    protected TextField last_Name = new TextField("Efternamn");
    protected TextField phone = new TextField("Telefonnummer");
    protected ComboBox<Row> rowPicker = new ComboBox<>("Rad");
    protected ComboBox<Chair> chairPicker = new ComboBox<>("Stol");
    protected ComboBox<PaymentMethod> paymentPicker = new ComboBox<>("Betalning");

    protected BookingObject bookingObject = new BookingObject(null, null, null, null);
    protected Customer customer = new Customer("","","");

    protected Binder<Customer> binderCustomer = new Binder<>(Customer.class);
    protected Binder<BookingObject> binder = new Binder<>(BookingObject.class);

    protected Button confirm = new Button("Boka");
    protected Button cancel = new Button("Avbryt");

    protected HorizontalLayout buttonLayout = new HorizontalLayout();

    /**
     * @param bookingService
     * @param bookingView
     * The form for the Booking view.
     */
    public BookingForm(BookingService bookingService, BookingView bookingView)
    {
        customers = bookingService.findCustomers();

        this.bookingView = bookingView;
        this.bookingService = bookingService;

        ConfigureBinder();
        ConfigureButtonTheme();
        ConfigureButtonHandler();
        ConfigureComboBoxes();

        buttonLayout.add(confirm, cancel);
    }

    private void closeForm()
    {
        this.setVisible(false);
    }

    /**
     * @return the int to determine which function should run
     *  First - check if the phone number is valid
     * 3 checks - * Does the field phone number match the current customer in the list? returnvalue = 1
     *            * If it does match, does the field names match the customer names? returnvalue =  2
     *            * If neither the number nor the names match, returnvalue = 3.
     */
    //Fixme dåligt kodat
    public int FrankensteinsMonster()
    {
        if(phone.getValue().length() == 10) {
            int returnValue = 0;
            for (Customer customer : customers) {

                if (phone.getValue().equals(customer.getTelefonnummer())) {
                    returnValue += 1;
                }
                if (first_Name.getValue().equals(customer.getFornamn()) && last_Name.getValue().equals(customer.getEfternamn())) {
                    returnValue += 1;
                }
            }
            //In case there are multiple customers with the same name
            if (returnValue > 2) {
                returnValue = 2;
            }
            return returnValue;
        }
        else
            {
                return 3;
            }

    }

    /**
     * Returns the result of the  FrankensteinsMonster function and calls a specific function depending on the result.
     */
    public void CheckForNameAndPhone()
    {
        switch (FrankensteinsMonster())
        {
            case(0):
                newCustomerTicket();
                break;
            case(1):
                customerAlreadyExistError();
                break;
            case(2):
                existingCustomerTicket();
                break;
            case(3):
                showPhoneError("Error: Telefonnummer måste vara ett tiosifrigt nummer");
                break;
            default:
                System.out.println("fel");
        }
    }


    /**
     * Adds a new booking in the database related to the customer by id
     * prints notification
     * refreshes the grid in the bookingview
     * hides the form
     */
    public void existingCustomerTicket()
    {
        int tempInt = bookingService.findWithPhoneNumber(phone.getValue());
        bookingService.addBooking(tempInt, paymentPicker.getValue().getId(), bookingView.getSelection().getId(), chairPicker.getValue().getId());
        addNotification("Biljett bokad " + "på telefonnummer " + phone.getValue());
        toggleForm(true);
        bookingView.populateGrid();
    }


    /**
     * Prints an error if the user tries to book a ticket with an already existing phonenumber, but not matching names.
     */
    public void customerAlreadyExistError()
    {
        addNotification("Var god använd ett annat telefonnummer");
        customers = bookingService.findCustomers();
        phone.setValue("");
    }

    /**
     * Creates a new customer in the DB
     * Creates a new booking related to the new customer by ID
     * prints notification
     * refreshes the grid in the booking view
     */
    public void newCustomerTicket()
    {
        bookingService.addCustomer(first_Name.getValue(), last_Name.getValue(), phone.getValue());
        int tempInt = bookingService.findWithPhoneNumber(phone.getValue());
        bookingService.addBooking(tempInt, paymentPicker.getValue().getId(), bookingView.getSelection().getId(), chairPicker.getValue().getId());
        addNotification("Biljett bokad " + "på telefonnummer " + phone.getValue());
        customers = bookingService.findCustomers();
        toggleForm(true);
        bookingView.populateGrid();
    }

    /**
     * @param message the message to print in the notification
     */
    public void addNotification(String message)
    {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Label label = new Label(message);
        label.setWidthFull();
        Notification notify = new Notification();
        notify.setPosition(Notification.Position.MIDDLE);
        Button notificationButton = new Button("Close");
        horizontalLayout.add(label, notificationButton);
        notificationButton.addClickListener(event -> notify.close());
        notify.add(horizontalLayout);
        notificationButton.setVisible(true);
        notify.open();
    }

    /**
     * @param message the message to print in the notification
     */
    public void showPhoneError(String message)
    {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            Label label = new Label(message);
            label.setWidthFull();
            Notification notify = new Notification();
            notify.setPosition(Notification.Position.MIDDLE);
            Button notificationButton = new Button("Close");
            horizontalLayout.add(label, notificationButton);
            notificationButton.addClickListener(event -> notify.close());
            notify.add(horizontalLayout);
            notificationButton.setVisible(true);
            notify.open();
    }

    private void clearForm()
    {
        customer = new Customer("","","");
        bookingObject = new BookingObject(null,null,null,null);
        binderCustomer.setBean(customer);
        binder.setBean(bookingObject);
    }

    private void fillForm()
    {
        customer = new Customer( first_Name.getValue(), last_Name.getValue(), phone.getValue());
        bookingObject = new BookingObject(customer, chairPicker.getValue(), rowPicker.getValue(), paymentPicker.getValue());
        binderCustomer.setBean(customer);
        binder.setBean(bookingObject);
    }

    public void toggleForm(boolean bool)
    {
        if(bool)
        {
            chairPicker.setEnabled(false);
            rowPicker.setEnabled(false);
            remove(first_Name, last_Name, phone, rowPicker, chairPicker, paymentPicker, buttonLayout);
            clearForm();
            fillForm();
            add(first_Name, last_Name, phone, rowPicker, chairPicker, paymentPicker, buttonLayout);
            this.setVisible(true);
        }
        else
        {
            this.setVisible(false);
            remove(first_Name, last_Name, phone, rowPicker, chairPicker, paymentPicker, buttonLayout);
        }
    }

    /**
     * Binds the firstname,lastname and phone fields to the Customer object
     * Binds the comboBox fields to the BookingObject object.
     *
     */
    public void ConfigureBinder()
    {
        binderCustomer.forField(first_Name).bind(Customer::getFornamn, Customer::setFornamn);
        binderCustomer.forField(last_Name).bind(Customer::getEfternamn, Customer::setEfternamn);
        binderCustomer.forField(phone).bind(Customer::getTelefonnummer, Customer::setTelefonnummer);

        binder.forField(rowPicker).bind(BookingObject::getRow, BookingObject::setRow);
        binder.forField(chairPicker).bind(BookingObject::getChair, BookingObject::setChair);
        binder.forField(paymentPicker).bind(BookingObject::getPaymentMethod, BookingObject::setPaymentMethod);

        binderCustomer.setBean(customer);
        binder.setBean(bookingObject);
    }

    public void ConfigureButtonTheme() {

        confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);

    }

    public void ConfigureButtonHandler()
    {
        confirm.addClickListener(event -> CheckForNameAndPhone());
        cancel.addClickListener(event -> toggleForm(false));
    }

    public void ConfigureComboBoxes()
    {
        rowPicker.setEnabled(false);
        chairPicker.setEnabled(false);

        paymentPicker.setItemLabelGenerator(PaymentMethod::getBetalningsmetod);
        paymentPicker.setItems(bookingService.findPaymentMethod());

    }

    public void ConfigureChairPicker()
    {
        if(rowPicker.getValue()!=null) {
            chairPicker.setItemLabelGenerator(Chair::convertNummer);
            chairPicker.setItems(bookingService.findChair(rowPicker.getValue().getId()));
            chairPicker.setEnabled(true);
        }
    }

    public void ConfigureRowPicker(boolean bool)
    {
        if(bool && !rowPicker.isEnabled()){
            rowPicker.setEnabled(true);
            rowPicker.setItemLabelGenerator(Row::convertNummer);
            rowPicker.setItems(bookingService.findRow(bookingView.getSelection().getSalong()));
            rowPicker.addValueChangeListener(event -> ConfigureChairPicker());
        }
    }
}
