package biblioteca.controller.cliente;

import biblioteca.model.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import biblioteca.negocio.ClienteNegocio;
import biblioteca.negocio.NegocioException;

public class AnchorPaneCadastrarClienteController implements Initializable {

    @FXML
    private TextField textFieldNomeCliente;
    @FXML
    private TextField textFieldTelefone;
    @FXML
    private Button btnCadastrarCliente;

    ClienteNegocio cliente = new ClienteNegocio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void HandleBtnCadastrarCliente(ActionEvent event) throws IOException, NegocioException {

        if (!textFieldNomeCliente.getText().isEmpty() && !textFieldTelefone.getText().isEmpty()) {
            if (!validaNome(textFieldNomeCliente.getText())) {
                Cliente novo = new Cliente(textFieldNomeCliente.getText(), textFieldTelefone.getText());
                try {
                    cliente.salvar(novo);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Confirmação de Cadastro");
                    alert.setHeaderText("CONFIRMAÇÃO");
                    alert.setContentText("O Cliente " + textFieldNomeCliente.getText() + " foi cadastrado com sucesso!");
                    alert.showAndWait();
                } catch (NegocioException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(ex.getMessage());
                    alert.show();
                }
            } else {
                Alert alertFilmeCadastrado = new Alert(Alert.AlertType.WARNING);
                alertFilmeCadastrado.setTitle("Confirmação de Cadastro");
                alertFilmeCadastrado.setHeaderText("ERRO!!!");
                alertFilmeCadastrado.setContentText("O Cliente " + textFieldNomeCliente.getText() + " já foi cadastrado, insira um novo Cliente!");
                alertFilmeCadastrado.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Campos de cadastramento não podem ficar em branco!");
            alert.show();
        }
    }

    private boolean validaNome(String nome) throws NegocioException {
        List<Cliente> listaNome = cliente.procurarNome(nome);
        if (!listaNome.isEmpty()) {
            for (Cliente c : listaNome) {
                if (c.getNome().equalsIgnoreCase(nome)) {
                    return true;
                }
            }
        }
        return false;
    }
}
