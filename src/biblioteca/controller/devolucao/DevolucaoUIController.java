package biblioteca.controller.devolucao;

import biblioteca.Biblioteca;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DevolucaoUIController implements Initializable {

    @FXML
    private Button BtnVoltarPrincipal;
    @FXML
    private Button MenuItemCadastroDevolucao;
    @FXML
    private Button MenuItemListaDevolucao;
    @FXML
    private AnchorPane AnchorPaneDevolucao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/devolucao/AnchorPaneFundoDevolucao.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DevolucaoUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AnchorPaneDevolucao.getChildren().setAll(root);
    }

    @FXML
    public void HandleBtnVoltar(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/biblioteca/view/MainUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DevolucaoUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void HandleMenuItemVisualizar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/devolucao/AnchorPaneVisualizarDevolucao.fxml"));
        AnchorPaneDevolucao.getChildren().setAll(root);
    }

    public void HandleMenuItemCadastrar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/retirada/AnchorPaneCadastrarRetirada.fxml"));
        AnchorPaneDevolucao.getChildren().setAll(root);
    }

}
