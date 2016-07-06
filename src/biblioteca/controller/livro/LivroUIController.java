package biblioteca.controller.livro;

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

public class LivroUIController implements Initializable {

    @FXML
    private Button BtnVoltarPrincipal;
    @FXML
    private Button MenuItemCadastroLivro;
    @FXML
    private Button MenuItemDeletaLivro;
    @FXML
    private MenuItem MenuItemBuscaLivroNome;
    @FXML
    private MenuItem MenuItemBuscaLivroISBN;
    @FXML
    private Button MenuItemAtualizaLivro;
    @FXML
    private Button MenuItemListaLivro;
    @FXML
    private AnchorPane AnchorPaneLivro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/livro/AnchorPaneFundoLivro.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LivroUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AnchorPaneLivro.getChildren().setAll(root);
    }

    @FXML
    public void HandleBtnVoltar(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/biblioteca/view/MainUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LivroUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void HandleMenuItemDeletar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/livro/AnchorPaneDeletarLivro.fxml"));
        AnchorPaneLivro.getChildren().setAll(root);
    }

    public void HandleMenuItemVisualizar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/livro/AnchorPaneVisualizarLivro.fxml"));
        AnchorPaneLivro.getChildren().setAll(root);
    }

    public void HandleMenuItemCadastrar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/livro/AnchorPaneCadastrarLivro.fxml"));
        AnchorPaneLivro.getChildren().setAll(root);
    }

    public void HandleMenuItemAtualizar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/livro/AnchorPaneAtualizarLivro.fxml"));
        AnchorPaneLivro.getChildren().setAll(root);
    }

    public void HandleMenuItemBuscaPorNome(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/livro/AnchorPaneBuscaNome.fxml"));
        AnchorPaneLivro.getChildren().setAll(root);
    }
    
    public void HandleMenuItemBuscaPorISBN(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/livro/AnchorPaneBuscaISBN.fxml"));
        AnchorPaneLivro.getChildren().setAll(root);
    }
}
