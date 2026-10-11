module com.example.s3z2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.s3z2 to javafx.fxml;
    exports com.example.s3z2;
}