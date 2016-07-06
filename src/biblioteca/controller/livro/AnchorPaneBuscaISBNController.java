package biblioteca.controller.livro;

import biblioteca.model.Livro;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import biblioteca.negocio.LivroNegocio;
import biblioteca.negocio.NegocioException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;

public class AnchorPaneBuscaISBNController implements Initializable {

    @FXML
    private TextField textFieldBuscaISBN;
    @FXML
    private Button btnBusca;
    @FXML
    private TableView<Livro> tableViewLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnIsbnLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnTituloLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnAutorLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnEditoraLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnAnoLivro;

    private List<Livro> listaLivros;
    private ObservableList<Livro> observableListLivros;
    private LivroNegocio livro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        livro = new LivroNegocio();
    }

    public void carregarTableViewLivros(String isbn) {

        try {
            Livro liv = livro.procurarPorIsbn(isbn);
            if (liv != null) {
                ArrayList listalivroISBN = new ArrayList<Livro>();
                listalivroISBN.add(liv);
                this.listaLivros = listalivroISBN;
            }
            if (!listaLivros.isEmpty()) {
                this.tableColumnIsbnLivro.setCellValueFactory(new PropertyValueFactory<>("isbn"));
                this.tableColumnTituloLivro.setCellValueFactory(new PropertyValueFactory<>("nome"));
                this.tableColumnAutorLivro.setCellValueFactory(new PropertyValueFactory<>("autor"));
                this.tableColumnEditoraLivro.setCellValueFactory(new PropertyValueFactory<>("editora"));
                //Formato de data yyyy ou dd/MM/yyyy - precisa de um callback para converter o dado.                
                this.tableColumnAnoLivro.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Livro, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Livro, String> cell) {
                        final Livro livro = cell.getValue();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                        final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty(dateFormat.format(livro.getAno()));
                        return simpleObject;
                    }
                });

                observableListLivros = FXCollections.observableArrayList(listaLivros);
                tableViewLivro.setItems(observableListLivros);

            } else {
                tableViewLivro.setItems(null);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Resultado da Busca");
                alert.setHeaderText("");
                alert.setContentText("Nenhum Livro foi encontrado com o ISBN " + isbn + " !");
                alert.showAndWait();
            }
        } catch (NegocioException ex) {
            Alert alertFilmeNegocio = new Alert(Alert.AlertType.ERROR);
            alertFilmeNegocio.setHeaderText("Falha na Busca por ISBN");
            alertFilmeNegocio.setContentText(ex.getMessage());
            alertFilmeNegocio.show();
        }
    }

    @FXML
    public void HandlebtnPesquisar(ActionEvent event) throws IOException {
        if (!textFieldBuscaISBN.getText().isEmpty()) {
            this.carregarTableViewLivros(textFieldBuscaISBN.getText());
        } else {
            this.tableViewLivro.setItems(null);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Falha na Busca por ISBN");
            alert.setContentText("ERRO! Você precisa informar um ISBN para busca!");
            alert.show();
        }
    }
}
