package biblioteca.controller.devolucao;

import biblioteca.model.Livro;
import java.util.List;
import biblioteca.negocio.LivroNegocio;
import java.text.SimpleDateFormat;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
import biblioteca.model.Cliente;
import biblioteca.model.Retirada;
import java.io.IOException;
import java.net.URL;
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
import biblioteca.negocio.ClienteNegocio;
import biblioteca.negocio.NegocioException;
import biblioteca.negocio.RetiradaNegocio;
import java.util.ArrayList;
import java.util.Date;

public class AnchorPaneCadastrarDevolucaoController implements Initializable {

    @FXML
    private TextField textFieldBuscaMatricula;
    @FXML
    private Button btnBuscaCliente;
    @FXML
    private TableView<Cliente> tableViewCliente;
    @FXML
    private TableColumn<Cliente, String> tableColumnMatriculaCliente;
    @FXML
    private TableColumn<Cliente, String> tableColumnNomeCliente;
    @FXML
    private TableColumn<Cliente, String> tableColumnTelefoneCliente;
    @FXML
    private TextField textFieldBuscaISBN;
    @FXML
    private Button btnBuscaLivro;
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

    private ObservableList<Cliente> observableListClientes;
    private ClienteNegocio cliente = new ClienteNegocio();
    private ArrayList listaclientes;

    private RetiradaNegocio retirada = new RetiradaNegocio();

    public void carregarTableViewClientes(int matricula) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        livro = new LivroNegocio();
    }
//lista livros  

    public void carregarTableViewLivros(String isbn) {

    }
}
