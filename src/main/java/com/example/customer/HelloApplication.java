package com.example.customer;

import com.example.customer.models.Address;
import com.example.customer.models.City;
import com.example.customer.models.Country;
import com.example.customer.models.Customer;
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class HelloApplication extends Application {
    private TableView<Customer> tblCustomer;
    private List<Customer> customerList = new ArrayList<>();
    private List<Address> addressList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();
    private List<Country> countryList = new ArrayList<>();
    private ComboBox<Country> comboCountry = new ComboBox();
    private Button btnReset = new Button("Reset");//Btn to reset ComboBox

    Label lblTitle = new Label("Customers Application");
    Label lblFilter = new Label("Filter");


    @Override
    public void start(Stage stage) throws IOException {


        Scene scene = new Scene(buildGUI(), 500, 340);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private VBox buildGUI(){
        VBox vbox = new VBox(15);
        vbox.setAlignment(Pos.CENTER);

        initCountriesAndCities();
        initCustomers();

        vbox.getChildren().add(lblTitle);

        comboCountry.setItems(FXCollections.observableArrayList(countryList));

        //Applying filter
        comboCountry.valueProperty().addListener(new ChangeListener<Country>() {
            @Override
            public void changed(ObservableValue<? extends Country> observable, Country country, Country selectedCountry) {
                //sendMessage("Country select",selectedCountry.getName());
                if(!comboCountry.getSelectionModel().isEmpty()){
                    filerCustomers(selectedCountry.getId());
                }
            }
        });

        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(lblFilter, comboCountry, btnReset);
        vbox.getChildren().addAll(hbox);

        btnReset.setOnAction(handlerReset1);

        tblCustomer = new TableView<>();
        TableColumn <Customer, Integer> c1 = new TableColumn("ID");
        TableColumn <Customer, String> c2 = new TableColumn("FULLNAME");
        TableColumn <Customer, String> c3 = new TableColumn("EMAIL");
        TableColumn <Customer, String> c4 = new TableColumn("ADDRESS");
        TableColumn <Customer, String> c5 = new TableColumn("CITY");
        TableColumn <Customer, String> c6 = new TableColumn("COUNTRY");

        c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        //c2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        c2.setCellValueFactory((customerData) -> {
            return new SimpleStringProperty(
                    customerData.getValue().getFirstName() + " " +
                            customerData.getValue().getLastName());
        });
        //c3.setCellValueFactory(new PropertyValueFactory<>("email"));
        c3.setCellValueFactory((customerData) -> {
            return new SimpleStringProperty(
                    customerData.getValue().getEmail()
            );
        });
        c4.setCellValueFactory((customerData) -> {
            return new SimpleStringProperty(
                    customerData.getValue().getAddress().toString()
            );
        });
        c5.setCellValueFactory((customerData) -> {
            return new SimpleStringProperty(
                    customerData.getValue().getAddress().getCity().toString()
            );
        });
        c6.setCellValueFactory((customerData) -> {
            return new SimpleStringProperty(
                    customerData.getValue().getAddress().getCity().getCountry().toString()
            );
        });

        tblCustomer.getColumns().addAll(c1, c2, c3, c4, c5, c6);

        ObservableList<Customer> obsList = FXCollections.observableArrayList(customerList);
        tblCustomer.setItems(obsList);

        vbox.getChildren().add(tblCustomer);

        initStyles();//Callback init Label Styles

        return vbox;
    }

    private void initCountriesAndCities(){
        Country mx = new Country(1, "MEXICO");
        Country us = new Country(2, "USA");
        Country cnd = new Country(3, "CANADA");
        Country arg = new Country(4, "ARG");
        Country eng = new Country(5, "ENGLAND");

        countryList.add(mx);
        countryList.add(us);
        countryList.add(cnd);
        countryList.add(arg);
        countryList.add(eng);

        City celaya = new City(1, "CELAYA", "Guanajuato", mx);
        City salamanca = new City(2, "SALAMANCA", "Guanajuato", mx);
        City irapuato = new City(3, "IRAPUATO", "Guanajuato", mx);

        City chicago = new City(4, "CHICAGO", "Illinois", us);
        City la = new City(5, "Los Angeles", "California", us);
        City houston = new City(6, "HOUSTON", "Texas", us);

        City toronto = new City(7, "TORONTO", "Quebec", cnd);
        City otawa = new City(8, "OTAWA", "Quebec", cnd);
        City montreal = new City(9, "MONTREAL", "Quebec", cnd);

        City ba = new City(10, "BUENOS AIRES", "Mar de Plata", arg);
        City rosario = new City(11, "ROSARIO", "Mar de Plata", arg);
        City velez = new City(12, "VELEZ", "Mar de Plata", arg);

        City london = new City(13, "LONDON", "London", eng);
        City brentford = new City(14, "BRENTFORD", "London", eng);
        City chelsea = new City(15, "CHELSEA", "London", eng);

        cityList.add(celaya);
        cityList.add(salamanca);
        cityList.add(irapuato);
        cityList.add(la);
        cityList.add(chicago);
        cityList.add(houston);
        cityList.add(toronto);
        cityList.add(otawa);
        cityList.add(montreal);
        cityList.add(ba);
        cityList.add(rosario);
        cityList.add(velez);
        cityList.add(london);
        cityList.add(brentford);
        cityList.add(chelsea);
    }

    private void initCustomers(){
        Address ad1 = new Address(1, "AV. 1 #1", "2394829", cityList.get(0));
        Address ad2 = new Address(2, "AVENUE. 1 #1", "2392133", cityList.get(3));
        Address ad3 = new Address(3, "RUE. 1 #1", "2321333", cityList.get(6));
        Address ad4 = new Address(4, "AVENIDA CHE. 1 #1", "4324434", cityList.get(9));
        Address ad5 = new Address(5, "AVENUE MATE. 1 #1", "4324324", cityList.get(12));

        for (int i = 1; i <= 5; i++) {
            customerList.add(new Customer(i, "Customer " + i, "Lastname", "correo@gmail.com", true, ad1));
        }
        for (int i = 6; i <= 10; i++) {
            customerList.add(new Customer(i, "Customer " + i, "Lastname", "mail@gmail.com", true, ad2));
        }
        for (int i = 11; i <= 15; i++) {
            customerList.add(new Customer(i, "Customer " + i, "Lastname", "mailbou@gmail.com", true, ad3));
        }
        for (int i = 16; i <= 20; i++) {
            customerList.add(new Customer(i, "Customer " + i, "Lastname", "correochee@gmail.com", true, ad4));
        }
        for (int i = 21; i <= 25; i++) {
            customerList.add(new Customer(i, "Customer " + i, "Lastname", "mailmate@gmail.com", true, ad5));
        }

    }


    private void sendMessage(String title, String msg){
        Alert message = new Alert(Alert.AlertType.CONFIRMATION);
        message.setTitle(title);
        message.setContentText(msg);
        message.show();
    }

    //Filter by ID
    private void filerCustomers(int countryId){

        List<Customer> fiteredList = new ArrayList<>();

        for (Customer customer:customerList){
            if(customer.getAddress().getCity().getCountry().getId() == countryId){
                fiteredList.add(customer);
            }
            tblCustomer.getItems().clear();
            tblCustomer.setItems(FXCollections.observableArrayList(fiteredList));
        }
    }

    //Otra forma de reprensetar un handler

    /*EventHandler<ActionEvent> handlerReset = new EventHandler<>() {
        public void handle(ActionEvent actionEvent){

        }
    };*/

    //Cleaning comboBox selection
    EventHandler<ActionEvent> handlerReset1 = (actionEvent)->{
        tblCustomer.getItems().clear();
        tblCustomer.setItems(FXCollections.observableArrayList(customerList));
        comboCountry.getSelectionModel().clearSelection();
    };

    //Init Styles
    private  void initStyles(){
        lblTitle.setStyle("-fx-font-size: 20pt; -fx-text-fill:  rgba(190,0,255,0.65); -fx-font-weight: bold");
        lblFilter.setStyle("-fx-font-size: 20pt; -fx-text-fill:  rgba(231,17,70,0.65); -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS'");

        btnReset.setStyle("-fx-font-family: Arial; -fx-text-fill: #000; -fx-font-weight: bold; -fx-background-color: #d97bf8; -fx-border-color: #ffd124; -fx-border-width: 5pt; -fx-background-radius: 10pt; -fx-border-radius: 7pt");

        comboCountry.setStyle("-fx-background-color: #d97bf8");

        tblCustomer.setStyle("-fx-font-weight: bold");


    }

    public static void main(String[] args) {
        launch();
    }
}