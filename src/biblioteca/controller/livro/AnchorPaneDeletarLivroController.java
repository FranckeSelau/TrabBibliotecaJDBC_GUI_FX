package biblioteca.controller.livro;

import biblioteca.controller.livro.*;
import biblioteca.model.Livro;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import biblioteca.negocio.LivroNegocio;
import biblioteca.negocio.NegocioException;
import java.text.SimpleDateFormat;

public class AnchorPaneDeletarLivroController implements Initializable {

    @FXML
    private TableView<Livro> tableViewLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnLivroISBN;
    @FXML
    private TableColumn<Livro, String> tableColumnLivroTitulo;
    @FXML
    private Button BtnDeletaLivro;
    @FXML
    private Label LabelLivroISBN;
    @FXML
    private Label LabelLivroTitulo;
    @FXML
    private Label LabelLivroAutor;
    @FXML
    private Label LabelLivroEditora;
    @FXML
    private Label LabelLivroAno;

    private List<Livro> listaLivros;
    private ObservableList<Livro> observableListLivros;
    LivroNegocio livro = new LivroNegocio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewLivros();
        this.tableViewLivro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionarItensTableViewLivros(newValue));
    }

    public void carregarTableViewLivros() {
        this.tableColumnLivroISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        this.tableColumnLivroTitulo.setCellValueFactory(new PropertyValueFactory<>("nome"));
      
        listaLivros = livro.listar();

        observableListLivros = FXCollections.observableArrayList(listaLivros);
        this.tableViewLivro.setItems(observableListLivros);
    }

    public void selecionarItensTableViewLivros(Livro livroSelecionado) {
        //formatando data ANO
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        if (livroSelecionado != null) {
            LabelLivroISBN.setText(livroSelecionado.getIsbn());
            this.LabelLivroTitulo.setText(livroSelecionado.getNome());
            this.LabelLivroAutor.setText(livroSelecionado.getAutor());
            this.LabelLivroEditora.setText(livroSelecionado.getEditora());
            this.LabelLivroAno.setText(dateFormat.format(livroSelecionado.getAno()));

        } else {
            LabelLivroISBN.setText("");
            this.LabelLivroTitulo.setText("");
            this.LabelLivroAutor.setText("");
            this.LabelLivroEditora.setText("");
            this.LabelLivroAno.setText("");
        }
    }

    @FXML
    public void HandleAlertDelete(ActionEvent event) throws IOException, NegocioException {
        Livro LivSelecionado = this.tableViewLivro.getSelectionModel().getSelectedItem();
        if (LivSelecionado != null) {
            boolean buttonConfirmarClicked = showFXMLAlertDeleteLivro(LivSelecionado);
            if (buttonConfirmarClicked) {
                LivroNegocio livro = new LivroNegocio();
                livro.deletar(LivSelecionado);
                this.carregarTableViewLivros();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Livro Excluido");
                alert.setHeaderText("Exclusão de Livro");
                alert.setContentText("O Livro " + LivSelecionado.getNome() + " foi excluido com sucesso!");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Livro!");
            alert.show();
        }
    }

    public boolean showFXMLAlertDeleteLivro(Livro liv) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AlertDeleteLivroController.class.getResource("/biblioteca/view/livro/AlertDeleteLivro.fxml"));
        Parent page = (Parent) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Excluir Livro");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o livro no Controller.
        AlertDeleteLivroController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        
        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmationClicked();
    }

}
