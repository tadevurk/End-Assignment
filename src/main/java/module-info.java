module com.example.endassignment {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.endassignment to javafx.fxml;
    exports com.example.endassignment;
    exports Model;
    opens Model to javafx.fxml;
    exports Data;
    opens Data to javafx.fxml;
}