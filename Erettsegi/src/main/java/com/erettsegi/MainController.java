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
    @FXML public GridPane gr2;
    @FXML public GridPane gr3;
    @FXML public GridPane gr4;
    @FXML public TextField tf1;
    @FXML public TextField tf2;
    @FXML public TextField tf3;
    @FXML public TextField tf4;
    @FXML public TextField tf5;
    @FXML public ComboBox cb1;
    @FXML public ComboBox cb2;
    @FXML public ComboBox cb3;
    @FXML public ComboBox cb4;
    @FXML public CheckBox ch1;
    @FXML public CheckBox ch2;
    @FXML public CheckBox ch3;
    @FXML public CheckBox ch4;
    @FXML public RadioButton rb1;
    @FXML public RadioButton rb2;
    @FXML public RadioButton rb3;
    @FXML public RadioButton rb4;
    @FXML public ToggleGroup group;
    @FXML public Button btnSzuro;
    @FXML public Label errorForSzures;
    @FXML public Label errorForVizsgazoHozzaadas;
    @FXML public Label msgForVizsgazoHozzaadas;
    @FXML public Label errorForVizsgazoModositas;
    @FXML public Label msgForVizsgazoModositas;
    @FXML public Label msgForVizsgaTorles;
    @FXML public Label errorForVizsgaTorles;
    @FXML public Label errorForVizsgaTorles2;
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
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;

    protected void ElemekTörlése() {
        gr1.setVisible(false);
        gr1.setManaged(false);
        gr2.setVisible(false);
        gr2.setManaged(false);
        gr3.setVisible(false);
        gr3.setManaged(false);
        gr4.setVisible(false);
        gr4.setManaged(false);
        tv1.setVisible(false);
        tv1.setManaged(false);
        errorForSzures.setVisible(false);
        errorForSzures.setManaged(false);
        errorForVizsgazoHozzaadas.setVisible(false);
        errorForVizsgazoHozzaadas.setManaged(false);
        msgForVizsgazoHozzaadas.setVisible(false);
        msgForVizsgazoHozzaadas.setManaged(false);
        errorForVizsgazoModositas.setVisible(false);
        errorForVizsgazoModositas.setManaged(false);
        msgForVizsgazoModositas.setVisible(false);
        msgForVizsgazoModositas.setManaged(false);
        msgForVizsgaTorles.setVisible(false);
        msgForVizsgaTorles.setManaged(false);
        errorForVizsgaTorles.setVisible(false);
        errorForVizsgaTorles.setManaged(false);
        errorForVizsgaTorles2.setVisible(false);
        errorForVizsgaTorles2.setManaged(false);
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
    protected void initMenu() {
        ElemekTörlése();
        tv1.setVisible(true);
        tv1.setManaged(true);
        tv1.getColumns().removeAll(tv1.getColumns());
        tv1.getItems().removeAll(tv1.getItems());
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
        vizsgazoAzonCol.prefWidthProperty().bind(tv1.widthProperty().multiply(0.1));
        nevCol.prefWidthProperty().bind(tv1.widthProperty().multiply(0.1));
        vizsgatargyAzonCol.prefWidthProperty().bind(tv1.widthProperty().multiply(0.12));
        vizsgatargyNevCol.prefWidthProperty().bind(tv1.widthProperty().multiply(0.1));
        vizsgatargySzoMaxCol.prefWidthProperty().bind(tv1.widthProperty().multiply(0.13));
        vizsgatargyIrMaxCol.prefWidthProperty().bind(tv1.widthProperty().multiply(0.13));
        vizsgaSzobeliCol.prefWidthProperty().bind(tv1.widthProperty().multiply(0.13));
        vizsgaIrasbeliCol.prefWidthProperty().bind(tv1.widthProperty().multiply(0.13));
    }
    @FXML
    protected void menuReadClick() throws SQLException {
        initMenu();
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
        initMenu();
        tv1.getItems().removeAll(tv1.getItems());
        gr1.setVisible(true);
        gr1.setManaged(true);
        rb1.setSelected(true);
        ch1.setSelected(false);
        ch2.setSelected(false);
        ch3.setSelected(false);
        ch4.setSelected(false);
        tf1.setText("");
    }
    @FXML
    protected void menuWriteClick() {
        ElemekTörlése();
        gr2.setVisible(true);
        gr2.setManaged(true);

    }
    @FXML
    protected void menuUpdateClick() throws SQLException {
        ElemekTörlése();
        gr3.setVisible(true);
        gr3.setManaged(true);
        cb2.getItems().removeAll(cb2.getItems());
        ResultSet vizsgazoAzonositok = statement.executeQuery("SELECT azon FROM vizsgazo");
        while(vizsgazoAzonositok.next()) {
            cb2.getItems().add(vizsgazoAzonositok.getInt("azon"));
        }
        cb2.getSelectionModel().select("1");
    }
    @FXML
    protected void menuDeleteClick() throws SQLException {
        ElemekTörlése();
        gr4.setVisible(true);
        gr4.setManaged(true);
        cb3.getItems().removeAll(cb3.getItems());
        cb4.getItems().removeAll(cb4.getItems());
        ResultSet vizsgazoAzonositok = statement.executeQuery("SELECT DISTINCT vizsgazoaz FROM vizsga");
        int va = 0;
        while(vizsgazoAzonositok.next()) {
            cb3.getItems().add(vizsgazoAzonositok.getInt("vizsgazoaz"));
            int firstNum = vizsgazoAzonositok.getInt("vizsgazoaz");
            if(va == 0) cb3.getSelectionModel().select(firstNum);
            va++;
        }
        ResultSet vizsgatargyAzonositok = statement.executeQuery("SELECT DISTINCT vizsgatargyaz FROM vizsga");
        int vt = 0;
        while(vizsgatargyAzonositok.next()) {
            cb4.getItems().add(vizsgatargyAzonositok.getInt("vizsgatargyaz"));
            int firstNum = vizsgatargyAzonositok.getInt("vizsgatargyaz");
            if(vt == 0) cb4.getSelectionModel().select(firstNum);
            vt++;
        }
    }

    @FXML
    protected void btnSzuro() throws SQLException {
        if((ch1.isSelected() && ch3.isSelected()) || (ch2.isSelected() && ch4.isSelected())) {
            errorForSzures.setVisible(true);
            errorForSzures.setManaged(true);
            tv1.setVisible(false);
            tv1.setManaged(false);
            errorForSzures.setText("Egymásnak ellentmondó CheckBox -okat jelölt be! Kérem korrigálja!");
        } else {
            tv1.setVisible(true);
            tv1.setManaged(true);
            errorForSzures.setVisible(false);
            errorForSzures.setManaged(false);
            tv1.getItems().removeAll(tv1.getItems());
            String preparedQuery = "SELECT va.azon, va.nev, va.osztaly, vt.azon as targyAzon, vt.nev as targyNev, vt.szomax, vt.irmax, v.szobeli, v.irasbeli " +
                    "FROM Vizsgazo as va LEFT JOIN Vizsga as v ON va.azon = v.vizsgazoaz LEFT JOIN Vizsgatargy as vt ON v.vizsgatargyaz = vt.azon " +
                    "WHERE va.nev LIKE ? AND vt.nev = ? AND va.osztaly LIKE ? AND vt.szomax BETWEEN ? AND ? AND vt.irmax BETWEEN ? AND ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            if(tf1.getText().length() == 0) {
                preparedStatement.setString(1, "%");
            } else {
                preparedStatement.setString(1, tf1.getText());
            }
            preparedStatement.setString(2, cb1.getValue() + "");
            RadioButton rb = (RadioButton)group.getSelectedToggle();
            preparedStatement.setString(3, rb.getText() + "%");
            if (ch1.isSelected() == true) {
                preparedStatement.setInt(4, 0);
                preparedStatement.setInt(5, 50);
            } else if(ch3.isSelected() == true) {
                preparedStatement.setInt(4, 51);
                preparedStatement.setInt(5, 60);
            } else {
                preparedStatement.setInt(4, 0);
                preparedStatement.setInt(5, 60);
            }
            if (ch2.isSelected() == true) {
                preparedStatement.setInt(6, 0);
                preparedStatement.setInt(7, 90);
            } else if(ch4.isSelected() == true) {
                preparedStatement.setInt(6, 91);
                preparedStatement.setInt(7, 120);
            } else {
                preparedStatement.setInt(6, 0);
                preparedStatement.setInt(7, 120);
            }
            ResultSet vizsgaadatokSorai = preparedStatement.executeQuery();
            while(vizsgaadatokSorai.next()) {
                Vizsgaadatok vizsgaadatok = new Vizsgaadatok(vizsgaadatokSorai.getInt("azon"), vizsgaadatokSorai.getString("nev"), vizsgaadatokSorai.getString("osztaly"),
                        vizsgaadatokSorai.getInt("targyAzon"), vizsgaadatokSorai.getString("targyNev"), vizsgaadatokSorai.getInt("szomax"),
                        vizsgaadatokSorai.getInt("irmax"), vizsgaadatokSorai.getInt("szobeli"), vizsgaadatokSorai.getInt("irasbeli"));
                tv1.getItems().add(vizsgaadatok);
            }
        }
    }

    @FXML
    protected void btnVizsgazoHozzaad() throws SQLException {
        if(tf2.getText().length() == 0 || tf3.getText().length() == 0) {
            errorForVizsgazoHozzaadas.setVisible(true);
            errorForVizsgazoHozzaadas.setManaged(true);
            msgForVizsgazoHozzaadas.setVisible(false);
            msgForVizsgazoHozzaadas.setManaged(false);
        } else {
            errorForVizsgazoHozzaadas.setVisible(false);
            errorForVizsgazoHozzaadas.setManaged(false);
            preparedStatement = connection.prepareStatement("INSERT INTO vizsgazo (nev, osztaly) VALUES (?, ?)");
            preparedStatement.setString(1,tf2.getText() + "");
            preparedStatement.setString(2,tf3.getText() + "");
            preparedStatement.executeUpdate();
            msgForVizsgazoHozzaadas.setVisible(true);
            msgForVizsgazoHozzaadas.setManaged(true);
            tf2.setText("");
            tf3.setText("");
        }
    }
    @FXML
    protected void btnVizsgazoModosit() throws SQLException {
        if(tf4.getText().length() == 0 && tf5.getText().length() == 0) {
            errorForVizsgazoModositas.setVisible(true);
            errorForVizsgazoModositas.setManaged(true);
            msgForVizsgazoModositas.setVisible(false);
            msgForVizsgazoModositas.setManaged(false);
        } else {
            errorForVizsgazoModositas.setVisible(false);
            errorForVizsgazoModositas.setManaged(false);
            String vizsgazoAzonosito = cb2.getValue() + "";
            if(tf4.getText().length() != 0) {
                preparedStatement = connection.prepareStatement("UPDATE vizsgazo SET nev = ? WHERE azon = ?");
                preparedStatement.setString(1, tf4.getText());
                preparedStatement.setString(2, vizsgazoAzonosito);
                preparedStatement.executeUpdate();
            }
            if(tf5.getText().length() != 0) {
                preparedStatement = connection.prepareStatement("UPDATE vizsgazo SET osztaly = ? WHERE azon = ?");
                preparedStatement.setString(1, tf5.getText());
                preparedStatement.setString(2, vizsgazoAzonosito);
                preparedStatement.executeUpdate();
            }
            msgForVizsgazoModositas.setVisible(true);
            msgForVizsgazoModositas.setManaged(true);
            tf4.setText("");
            tf5.setText("");
        }
    }
    @FXML
    protected void btnVizsgaTorol() throws SQLException {
        if(cb3.getValue() == null || cb4.getValue() == null) {
            msgForVizsgaTorles.setVisible(false);
            msgForVizsgaTorles.setManaged(false);
            errorForVizsgaTorles2.setVisible(false);
            errorForVizsgaTorles2.setManaged(false);
            errorForVizsgaTorles.setVisible(true);
            errorForVizsgaTorles.setManaged(true);
        } else {
            preparedStatement = connection.prepareStatement("DELETE FROM vizsga WHERE vizsgazoaz = ? AND vizsgatargyaz = ?");
            preparedStatement.setString(1, cb3.getValue() + "");
            preparedStatement.setString(2, cb4.getValue() + "");
            System.out.println(preparedStatement.toString());
            errorForVizsgaTorles.setVisible(false);
            errorForVizsgaTorles.setManaged(false);
            errorForVizsgaTorles2.setVisible(false);
            errorForVizsgaTorles2.setManaged(false);
            if(preparedStatement.executeUpdate() == 0) {
                errorForVizsgaTorles2.setVisible(true);
                errorForVizsgaTorles2.setManaged(true);
            } else {
                msgForVizsgaTorles.setVisible(true);
                msgForVizsgaTorles.setManaged(true);
            };
        }
        cb3.getItems().removeAll(cb3.getItems());
        cb4.getItems().removeAll(cb4.getItems());
        ResultSet vizsgazoAzonositok = statement.executeQuery("SELECT DISTINCT vizsgazoaz FROM vizsga");
        int va = 0;
        while(vizsgazoAzonositok.next()) {
            cb3.getItems().add(vizsgazoAzonositok.getInt("vizsgazoaz"));
            int firstNum = vizsgazoAzonositok.getInt("vizsgazoaz");
            if(va == 0) cb3.getSelectionModel().select(firstNum);
            va++;
        }
        ResultSet vizsgatargyAzonositok = statement.executeQuery("SELECT DISTINCT vizsgatargyaz FROM vizsga");
        int vt = 0;
        while(vizsgatargyAzonositok.next()) {
            cb4.getItems().add(vizsgatargyAzonositok.getInt("vizsgatargyaz"));
            int firstNum = vizsgatargyAzonositok.getInt("vizsgatargyaz");
            if(vt == 0) cb4.getSelectionModel().select(firstNum);
            vt++;
        }
    }
}
