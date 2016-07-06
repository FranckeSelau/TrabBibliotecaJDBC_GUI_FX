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
import biblioteca.negocio.ClienteNegocio;
import biblioteca.negocio.NegocioException;

public class AnchorPaneDeletarClienteController implements Initializable {

    @FXML
    private TableView<Cliente> tableViewCliente;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteMatricula;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteNome;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteTelefone;
    @FXML
    private Button BtnDeletaCliente;
    @FXML
    private Label LabelClienteMatricula;
    @FXML
    private Label LabelClienteNome;
    @FXML
    private Label LabelClienteTelefone;

    private List<Cliente> listaClientes;
    private ObservableList<Cliente> observableListClientes;
    ClienteNegocio cliente = new ClienteNegocio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewClientes();
        this.tableViewCliente.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionarItensTableViewClientes(newValue));
    }

    public void carregarTableViewClientes() {
        this.tableColumnClienteMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        this.tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.tableColumnClienteTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        listaClientes = cliente.listar();

        observableListClientes = FXCollections.observableArrayList(listaClientes);
        this.tableViewCliente.setItems(observableListClientes);
    }

    public void selecionarItensTableViewClientes(Cliente clienteSelecionado) {
        if (clienteSelecionado != null) {
            LabelClienteMatricula.setText(String.valueOf(clienteSelecionado.getMatricula()));
            this.LabelClienteNome.setText(clienteSelecionado.getNome());
            this.LabelClienteTelefone.setText(clienteSelecionado.getTelefone());

        } else {
            LabelClienteMatricula.setText("");
            this.LabelClienteNome.setText("");
            this.LabelClienteTelefone.setText("");
        }
    }

    @FXML
    public void HandleAlertDelete(ActionEvent event) throws IOException, NegocioException {
        Cliente cliSelecionado = this.tableViewCliente.getSelectionModel().getSelectedItem();
        if (cliSelecionado != null) {
            boolean buttonConfirmarClicked = showFXMLAlertDeleteCliente(cliSelecionado);
            if (buttonConfirmarClicked) {
                ClienteNegocio cliente = new ClienteNegocio();
                cliente.deletar(cliSelecionado);
                this.carregarTableViewClientes();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Cliente Excluido");
                alert.setHeaderText("Exclusão de Cliente");
                alert.setContentText("O Cliente " + cliSelecionado.getNome() + " foi excluido com sucesso!");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Cliente!");
            alert.show();
        }
    }

    public boolean showFXMLAlertDeleteCliente(Cliente cli) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AlertDeleteClienteController.class.getResource("/biblioteca/view/cliente/AlertDeleteCliente.fxml"));
        Parent page = (Parent) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Excluir Cliente");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        AlertDeleteClienteController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        
        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmationClicked();
    }

}
