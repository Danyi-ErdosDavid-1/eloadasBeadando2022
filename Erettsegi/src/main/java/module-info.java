module com.erettsegi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires spring.data.commons;
    requires mysql.connector.java;
    opens com.erettsegi to javafx.fxml;
    exports com.erettsegi;
}