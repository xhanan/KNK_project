package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import Helpers.Service_Type;
import sample.Repositories.PaymentRepository;

import Helpers.Rooms;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import Helpers.Payment;
import DatabaseConnection.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class PaymentView implements Initializable {

    @FXML private Button pay;
    @FXML private Label totali;
    @FXML private Label errorHandle;
    @FXML private Label emriMbiemri;
    @FXML private Label personalNr;
    @FXML private TableView<Rooms> tableView;
    @FXML private TableColumn<Rooms, Integer> firstColumn;
    @FXML private TableColumn<Rooms, String> secondColumn;
    @FXML private TableColumn<Rooms, Double> thirdColumn;
    @FXML private TableView<Service_Type> tableView1;
    @FXML private TableColumn<Service_Type, String> fourthColumn;
    @FXML private TableColumn<Service_Type, String> fifthColumn;
    @FXML private Connection connection;
    @FXML private RadioButton cash;
    @FXML private RadioButton creditCard;
    @FXML private RadioButton gift;
    private ToggleGroup toggle;
    private int total;

    //klienti qe po paguan
    private int user;
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    ObservableList<Rooms> oblist = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            connection = dbConnection.getConnection();

            //Rezervimet qe i ka bere klienti ne fjale
            ResultSet tabela = connection.createStatement().executeQuery("select dh.room_number, dh.room_type, dh.price from rooms dh \n" +
                    "inner join reservations r on r.room_id=dh.room_number " +
                    "inner join payments p on p.id = r.payment_id "+
                    "where r.guest_id = "+user +" and p.is_payed = 0;");

//            while(tabela.next()){
//                oblist.add(new Rooms(tabela.getInt("room_number"),
//                        tabela.getString("room_type"), tabela.getDouble("price")));
//                total += tabela.getDouble("price");
//            }

            //Emri i atij qe po paguan
            ResultSet nameLastname = connection.createStatement().executeQuery("select first_name, last_name from guests\n" +
                    " where id="+user);
            while(nameLastname.next()){
                String rezultati = nameLastname.getString("first_name") + " " + nameLastname.getString("last_name");
                emriMbiemri.setText(rezultati);
            }
            totali.setText(total + "€");
        } catch (Exception ex) {
            Logger.getLogger(PaymentView.class.getName()).log(Level.SEVERE, null, ex);
        }
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.setItems(oblist);
        
        toggle = new ToggleGroup();
        this.cash.setToggleGroup(toggle);
        this.creditCard.setToggleGroup(toggle);
        this.gift.setToggleGroup(toggle);
    }

    public void paguaj(ActionEvent actionEvent) throws Exception {
        RadioButton selectedMethod = (RadioButton) toggle.getSelectedToggle();
        String metodaEzgjedhur = selectedMethod.getText();
        String PaymentQuery = "update payments p \n" +
                "inner join reservations r on r.payment_id = p.id \n" +
                "set p.price = "+total+", p.payment_method = '"+metodaEzgjedhur+"', p.is_payed = 1, p.pay_date = now() \n" +
                "where p.guest_id="+user;
        Statement statement = connection.createStatement();
        statement.executeUpdate(PaymentQuery);
        
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/paymentConfirmed.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Payment confirmed");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
