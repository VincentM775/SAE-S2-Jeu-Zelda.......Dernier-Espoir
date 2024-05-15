module com.example.dernierespoirsae {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.dernierespoirsae to javafx.fxml;
    exports com.example.dernierespoirsae;
    exports com.example.dernierespoirsae.controleur;
    opens com.example.dernierespoirsae.controleur to javafx.fxml;
}