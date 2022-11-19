package com.erettsegi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.*;

public class MainController {
    @FXML public TableView tv1;
    @FXML public GridPane gr1;
    @FXML public TextField tf1;
    @FXML public ComboBox cb1;
    @FXML public CheckBox ch1;
    @FXML public CheckBox ch2;
    @FXML public CheckBox ch3;
    @FXML public CheckBox ch4;
    @FXML public RadioButton rb1;
    @FXML public RadioButton rb2;
    @FXML public RadioButton rb3;
    @FXML public RadioButton rb4;
    @FXML public Button btnSzuro;
    @FXML public TableColumn<Vizsgaadatok, Integer> vizsgazoAzonCol;
    @FXML public TableColumn<Vizsgaadatok, String> nevCol;
    @FXML public TableColumn<Vizsgaadatok, String> osztalyCol;
    @FXML public TableColumn<Vizsgaadatok, Integer> vizsgatargyAzonCol;
    @FXML public TableColumn<Vizsgaadatok, String> vizsgatargyNevCol;
    @FXML public TableColumn<Vizsgaadatok, Integer> vizsgatargySzoMaxCol;
    @FXML public TableColumn<Vizsgaadatok, Integer> vizsgatargyIrMaxCol;
    @FXML public TableColumn<Vizsgaadatok, Integer> vizsgaSzobeliCol;
    @FXML public TableColumn<Vizsgaadatok, Integer> vizsgaIrasbeliCol;
    SessionFactory factory;
    Statement statement;
    Connection connection;

    private void ElemekTörlése() {
        gr1.setVisible(false);
        gr1.setManaged(false);
        tv1.setVisible(false);
        tv1.setManaged(false);
    }
    @FXML void initialize() throws SQLException {
        cb1.getItems().addAll("Magyar nyelv és irodalom", "Történelem", "Matematika", "Informatika", "Fizika", "Kémia", "Angol", "Német", "Földrajz", "Biológia");
        cb1.getSelectionModel().select("Magyar nyelv és irodalom");

        ElemekTörlése();
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
        final String URL = "jdbc:mysql://localhost/erettsegi?user=root&characterEncoding=utf8";
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        connection = DriverManager.getConnection(URL);
        statement = connection.createStatement();
    }
    @FXML
    protected void menuReadClick() throws SQLException {
        ElemekTörlése();
        tv1.setVisible(true);
        tv1.setManaged(true);
        tv1.getColumns().removeAll(tv1.getColumns());
        vizsgazoAzonCol = new TableColumn("Vizsgázó azonosítója");
        nevCol = new TableColumn("Neve");
        osztalyCol = new TableColumn("Osztálya");
        vizsgatargyAzonCol = new TableColumn<>("Vizsgatárgy azonosítója");
        vizsgatargyNevCol = new TableColumn<>("Vizsgatárgy neve");
        vizsgatargySzoMaxCol = new TableColumn<>("Szóbelin elérhető max. pont");
        vizsgatargyIrMaxCol = new TableColumn<>("Írásbelin elérhető max. pont");
        vizsgaSzobeliCol = new TableColumn<>("Szóbelin szerzett pontszáma");
        vizsgaIrasbeliCol = new TableColumn<>("Írásbelin szerzett pontszáma");
        tv1.getColumns().addAll(vizsgazoAzonCol, nevCol, osztalyCol, vizsgatargyAzonCol, vizsgatargyNevCol, vizsgatargySzoMaxCol, vizsgatargyIrMaxCol, vizsgaSzobeliCol, vizsgaIrasbeliCol);
        vizsgazoAzonCol.setCellValueFactory(new PropertyValueFactory<>("azon"));
        nevCol.setCellValueFactory(new PropertyValueFactory<>("nev"));
        osztalyCol.setCellValueFactory(new PropertyValueFactory<>("osztaly"));
        vizsgatargyAzonCol.setCellValueFactory(new PropertyValueFactory<>("targyAzon"));
        vizsgatargyNevCol.setCellValueFactory(new PropertyValueFactory<>("targyNev"));
        vizsgatargySzoMaxCol.setCellValueFactory(new PropertyValueFactory<>("szomax"));
        vizsgatargyIrMaxCol.setCellValueFactory(new PropertyValueFactory<>("irmax"));
        vizsgaSzobeliCol.setCellValueFactory(new PropertyValueFactory<>("szobeli"));
        vizsgaIrasbeliCol.setCellValueFactory(new PropertyValueFactory<>("irasbeli"));
        ResultSet vizsgaadatokSorai = statement.executeQuery("SELECT va.azon, va.nev, va.osztaly, vt.azon as targyAzon, vt.nev as targyNev, vt.szomax, vt.irmax, v.szobeli, v.irasbeli " +
                "FROM Vizsgazo as va LEFT JOIN Vizsga as v ON va.azon = v.vizsgazoaz LEFT JOIN Vizsgatargy as vt ON v.vizsgatargyaz = vt.azon");
        Vizsgaadatok vizsgaadatok;
        while(vizsgaadatokSorai.next()) {
            vizsgaadatok = new Vizsgaadatok(vizsgaadatokSorai.getInt("azon"), vizsgaadatokSorai.getString("nev"), vizsgaadatokSorai.getString("osztaly"),
                    vizsgaadatokSorai.getInt("targyAzon"), vizsgaadatokSorai.getString("targyNev"), vizsgaadatokSorai.getInt("szomax"),
                    vizsgaadatokSorai.getInt("irmax"), vizsgaadatokSorai.getInt("szobeli"), vizsgaadatokSorai.getInt("irasbeli"));
            tv1.getItems().add(vizsgaadatok);
        }
    }
    @FXML
    protected void menuRead2Click() {
        ElemekTörlése();
        gr1.setVisible(true);
        gr1.setManaged(true);
        tv1.setVisible(true);
        tv1.setManaged(true);
        tv1.getColumns().removeAll(tv1.getColumns());
        vizsgazoAzonCol = new TableColumn("Vizsgázó azonosítója");
        nevCol = new TableColumn("Neve");
        osztalyCol = new TableColumn("Osztálya");
        vizsgatargyAzonCol = new TableColumn<>("Vizsgatárgy azonosítója");
        vizsgatargyNevCol = new TableColumn<>("Vizsgatárgy neve");
        vizsgatargySzoMaxCol = new TableColumn<>("Szóbelin elérhető max. pont");
        vizsgatargyIrMaxCol = new TableColumn<>("Írásbelin elérhető max. pont");
        vizsgaSzobeliCol = new TableColumn<>("Szóbelin szerzett pontszáma");
        vizsgaIrasbeliCol = new TableColumn<>("Írásbelin szerzett pontszáma");
        tv1.getColumns().addAll(vizsgazoAzonCol, nevCol, osztalyCol, vizsgatargyAzonCol, vizsgatargyNevCol, vizsgatargySzoMaxCol, vizsgatargyIrMaxCol, vizsgaSzobeliCol, vizsgaIrasbeliCol);
        vizsgazoAzonCol.setCellValueFactory(new PropertyValueFactory<>("azon"));
        nevCol.setCellValueFactory(new PropertyValueFactory<>("nev"));
        osztalyCol.setCellValueFactory(new PropertyValueFactory<>("osztaly"));
        vizsgatargyAzonCol.setCellValueFactory(new PropertyValueFactory<>("targyAzon"));
        vizsgatargyNevCol.setCellValueFactory(new PropertyValueFactory<>("targyNev"));
        vizsgatargySzoMaxCol.setCellValueFactory(new PropertyValueFactory<>("szomax"));
        vizsgatargyIrMaxCol.setCellValueFactory(new PropertyValueFactory<>("irmax"));
        vizsgaSzobeliCol.setCellValueFactory(new PropertyValueFactory<>("szobeli"));
        vizsgaIrasbeliCol.setCellValueFactory(new PropertyValueFactory<>("irasbeli"));

    }
    @FXML
    protected void menuWriteClick() {

    }
    @FXML
    protected void menuUpdateClick() {

    }
    @FXML
    protected void menuDeleteClick() {

    }

    @FXML
    protected void btnSzuro() {
        
    }
}
