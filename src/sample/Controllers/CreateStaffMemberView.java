package sample.Controllers;

import DatabaseConnection.dbConnection;
import Helpers.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStaffMemberView {
    private Connection connection = dbConnection.getConnection();
    @FXML private TextField first_name;
    @FXML private TextField last_name;
    @FXML private TextField personal_number;
    @FXML private TextField phone_number;
    @FXML private DatePicker birthday;
    @FXML private ChoiceBox position;
    @FXML private Label salary;
    @FXML private PasswordField password;
    @FXML private Button createNewStaffMember;
    @FXML private Button cancleCreation;
    @FXML private RadioButton Male;
    @FXML private RadioButton Female;

    private final Stage stage;
}