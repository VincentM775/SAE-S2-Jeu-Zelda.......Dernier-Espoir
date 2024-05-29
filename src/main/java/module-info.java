module com.example.dernierespoirsae {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    exports com.example.dernierespoirsae.modele;

    requires org.controlsfx.controls;
    requires org.json;

    opens com.example.dernierespoirsae to javafx.fxml;
    exports com.example.dernierespoirsae;
    exports com.example.dernierespoirsae.controleur;
    opens com.example.dernierespoirsae.controleur to javafx.fxml;
    exports com.example.dernierespoirsae.modele.Armes;
}