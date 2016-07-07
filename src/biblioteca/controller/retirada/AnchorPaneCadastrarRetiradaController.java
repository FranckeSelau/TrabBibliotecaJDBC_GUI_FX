package biblioteca.controller.retirada;

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

public class AnchorPaneCadastrarRetiradaController implements Initializable {

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

        try {
            Cliente cli = cliente.procurarMatricula(matricula);
            if (cli != null) {
                ArrayList listaClientesMatricula = new ArrayList<Cliente>();
                listaClientesMatricula.add(cli);
                this.listaclientes = listaClientesMatricula;
            }
            if (!listaclientes.isEmpty()) {
                this.tableColumnMatriculaCliente.setCellValueFactory(new PropertyValueFactory<>("matricula"));
                this.tableColumnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
                this.tableColumnTelefoneCliente.setCellValueFactory(new PropertyValueFactory<>("telefone"));

                observableListClientes = FXCollections.observableArrayList(listaclientes);
                this.tableViewCliente.setItems(observableListClientes);
            } else {
                this.tableViewCliente.setItems(null);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Resultado da Busca");
                alert.setHeaderText("");
                alert.setContentText("Nenhum cliente encontrado com a matrícula " + matricula + " !");
                alert.showAndWait();
            }
        } catch (NegocioException ex) {
            Alert alertNegocio = new Alert(Alert.AlertType.ERROR);
            alertNegocio.setHeaderText("Falha na Busca!");
            alertNegocio.setContentText(ex.getMessage());
            alertNegocio.show();
        }
    }

    @FXML
    public void HandlebtnPesquisarMatricula(ActionEvent event) throws IOException {
        if (!textFieldBuscaMatricula.getText().isEmpty()) {
            int codigo;
            try {
                codigo = Integer.parseInt(textFieldBuscaMatricula.getText());
                this.carregarTableViewClientes(codigo);
            } catch (NumberFormatException ex) {
                this.textFieldBuscaMatricula.setText("");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("ERRO! Formato inválido!");
                alert.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        livro = new LivroNegocio();
    }
//lista livros  

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
    public void HandlebtnPesquisarISBN(ActionEvent event) throws IOException {
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

    @FXML
    public void HandleBtnCadastrarRetirada(ActionEvent event) throws IOException, NegocioException {
        long DAY_IN_MS = 1000 * 60 * 60 * 24; // formatar data entrega
        if (!textFieldBuscaMatricula.getText().isEmpty() && !textFieldBuscaISBN.getText().isEmpty()) {
            Retirada novo = new Retirada(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + (7 * DAY_IN_MS)), new Date(System.currentTimeMillis() + (7 * DAY_IN_MS)), cliente.procurarMatricula(Integer.parseInt(textFieldBuscaMatricula.getText())), livro.procurarPorIsbn(textFieldBuscaISBN.getText()), Boolean.FALSE);
            try {
                retirada.salvar(novo);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmação de Cadastro");
                alert.setHeaderText("CONFIRMAÇÃO");
                alert.setContentText("Retirada cadastrada com sucesso!");
                alert.showAndWait();
            } catch (NegocioException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ex.getMessage());
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Campos de cadastramento não podem ficar em branco!");
            alert.show();
        }
    }
}
