package biblioteca.controller.cliente;

import biblioteca.model.Cliente;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import biblioteca.negocio.ClienteNegocio;
import biblioteca.negocio.NegocioException;

public class AnchorPaneAtualizarClienteController implements Initializable {

    @FXML
    private TableView<Cliente> tableViewCliente;
    @FXML
    private TableColumn<Cliente, String> tableColumnMatriculaCliente;
    @FXML
    private TableColumn<Cliente, String> tableColumnNomeCliente;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteTelefone;
    @FXML
    private TextField textFieldNomeCliente;
    @FXML
    private TextField textFieldTelefone;
    @FXML
    private Button btnAtualizarCliente;
    @FXML
    private Cliente ClienteAtualizar;

    private List<Cliente> listaClientes;
    private ObservableList<Cliente> observableListClientes;
    ClienteNegocio cliente = new ClienteNegocio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewClientes();
        this.tableViewCliente.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionarItensTableViewClientes(newValue));
    }

    public void carregarTableViewClientes() {
        this.tableColumnMatriculaCliente.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        this.tableColumnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.tableColumnClienteTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        listaClientes = cliente.listar();

        observableListClientes = FXCollections.observableArrayList(listaClientes);
        this.tableViewCliente.setItems(observableListClientes);
    }

    public void selecionarItensTableViewClientes(Cliente cli) {
        if (cli != null) {
            this.textFieldNomeCliente.setText(cli.getNome());
            this.textFieldTelefone.setText(cli.getTelefone());
            this.setClienteSelecionado(cli);
        } else {
            this.textFieldNomeCliente.setText("");
            this.textFieldTelefone.setText("");
        }
    }

    public void setClienteSelecionado(Cliente cliSelecionado) {
        this.ClienteAtualizar = cliSelecionado;
    }

    @FXML
    public void HandleBtnAtualizarCliente(ActionEvent event) throws IOException {
        if (ClienteAtualizar != null) {
            if (!this.textFieldNomeCliente.getText().isEmpty() && !this.textFieldTelefone.getText().isEmpty()) {

                try {
                    if (!ClienteAtualizar.getNome().equalsIgnoreCase(textFieldNomeCliente.getText())) {
                        this.ClienteAtualizar.setNome(textFieldNomeCliente.getText());
                    }
                    if (!ClienteAtualizar.getTelefone().equalsIgnoreCase(textFieldTelefone.getText())) {
                        this.ClienteAtualizar.setTelefone(textFieldTelefone.getText());
                    }
                    cliente.atualizar(ClienteAtualizar);
                } catch (NegocioException ex) {
                    Alert alertClienteNegocio = new Alert(AlertType.ERROR);
                    alertClienteNegocio.setHeaderText("Falha na Atualização");
                    alertClienteNegocio.setContentText(ex.getMessage());
                }
                this.carregarTableViewClientes();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Atualização");
                alert.setHeaderText("Cliente Atualizado");
                alert.setContentText("O Cliente " + textFieldNomeCliente.getText() + " foi atualizado com sucesso!");
                alert.showAndWait();
                this.textFieldNomeCliente.setText("");
                this.textFieldTelefone.setText("");
                ClienteAtualizar = null;
            } else {
                Alert alertVazio = new Alert(AlertType.ERROR);
                alertVazio.setHeaderText("ERRO!");
                alertVazio.setContentText("Não pode haver campos em branco!");
                alertVazio.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Escolha um Cliente para atualizar!");
            alert.show();
        }
    }
}
