module com.example.customer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.customer to javafx.fxml;
    opens com.example.customer.models;
    exports com.example.customer;
}