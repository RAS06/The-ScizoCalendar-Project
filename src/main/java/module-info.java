module ugds.theoriginalscizocalendar {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens ugds.theoriginalscizocalendar to javafx.fxml;
    exports ugds.theoriginalscizocalendar;
}