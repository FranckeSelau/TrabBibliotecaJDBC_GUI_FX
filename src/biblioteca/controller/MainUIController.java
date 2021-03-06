package biblioteca.controller;

import biblioteca.controller.cliente.ClienteUIController;
import biblioteca.controller.devolucao.DevolucaoUIController;
import biblioteca.controller.livro.LivroUIController;
import biblioteca.controller.relatorio.RelatorioUIController;
import biblioteca.controller.retirada.RetiradaUIController;
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

public class MainUIController implements Initializable {

    @FXML
    private Button BtnMenuCliente;
    @FXML
    private Button BtnMenuLivro;
    @FXML
    private Button BtnMenuRetirada;
    @FXML
    private Button BtnMenuDevolucao;
    @FXML
    private Button BtnMenuRelatorio;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void HandleBtnMenuCliente(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/biblioteca/view/ClienteUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ClienteUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void HandleBtnMenuLivro(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/biblioteca/view/LivroUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LivroUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void HandleBtnMenuRetirada(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/biblioteca/view/RetiradaUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(RetiradaUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void HandleBtnMenuDevolucao(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/biblioteca/view/DevolucaoUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DevolucaoUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void HandleBtnMenuRelatorio(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/biblioteca/view/RelatorioUI.fxml"));
        } catch (IOException ex) {
            System.out.println("entrou");
            Logger.getLogger(RelatorioUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
