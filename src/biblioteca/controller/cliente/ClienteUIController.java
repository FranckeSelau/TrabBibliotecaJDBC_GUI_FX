package biblioteca.controller.cliente;

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
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ClienteUIController implements Initializable {

    @FXML
    private Button BtnVoltarPrincipal;
    @FXML
    private Button MenuItemCadastroCliente;
    @FXML
    private Button MenuItemDeletaCliente;
    @FXML
    private MenuItem MenuItemBuscaClienteNome;
    @FXML
    private MenuItem MenuItemBuscaClienteMatricula;
    @FXML
    private Button MenuItemAtualizaCliente;
    @FXML
    private Button MenuItemListaCliente;
    @FXML
    private AnchorPane AnchorPaneCliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/cliente/AnchorPaneFundoCliente.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ClienteUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AnchorPaneCliente.getChildren().setAll(root);
    }

    @FXML
    public void HandleBtnVoltar(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/biblioteca/view/MainUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ClienteUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void HandleMenuItemDeletar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/cliente/AnchorPaneDeletarCliente.fxml"));
        AnchorPaneCliente.getChildren().setAll(root);
    }

    public void HandleMenuItemVisualizar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/cliente/AnchorPaneVisualizarCliente.fxml"));
        AnchorPaneCliente.getChildren().setAll(root);
    }

    public void HandleMenuItemCadastrar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/cliente/AnchorPaneCadastrarCliente.fxml"));
        AnchorPaneCliente.getChildren().setAll(root);
    }

    public void HandleMenuItemAtualizar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/cliente/AnchorPaneAtualizarCliente.fxml"));
        AnchorPaneCliente.getChildren().setAll(root);
    }

    public void HandleMenuItemBuscaPorNome(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/cliente/AnchorPaneBuscaNome.fxml"));
        AnchorPaneCliente.getChildren().setAll(root);
    }
    
    public void HandleMenuItemBuscaPorMatricula(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/cliente/AnchorPaneBuscaMatricula.fxml"));
        AnchorPaneCliente.getChildren().setAll(root);
    }
}
