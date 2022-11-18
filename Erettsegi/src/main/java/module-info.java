module erettsegi.erettsegi {
    requires javafx.controls;
    requires javafx.fxml;


    opens erettsegi.erettsegi to javafx.fxml;
    exports erettsegi.erettsegi;
}