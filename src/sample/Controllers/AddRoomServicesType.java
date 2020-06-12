package sample.Controllers;

        import DatabaseConnection.dbConnection;
        import Helpers.Service_Type;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.stage.Modality;
        import javafx.stage.Stage;

        import javax.swing.*;
        import java.io.IOException;
        import java.net.URL;
        import java.sql.Connection;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ResourceBundle;

public class AddRoomServicesType{

        private Connection connection = dbConnection.getConnection();
    private final Stage stage;
    @FXML private TextField Price;
    @FXML private TextField serviceName;
    @FXML private TextField quantity;
    @FXML private Button addNewServiceType;
    @FXML private Button cancleButton;
        
    public AddServicesTybe() throws Exception {
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../sample.Views/AddServicesTybe.fxml"));
            loader.setController(this);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Service Type");
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
        
}
