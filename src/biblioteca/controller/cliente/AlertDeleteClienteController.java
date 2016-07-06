package biblioteca.controller.cliente;

import biblioteca.model.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AlertDeleteClienteController implements Initializable {

    @FXML
    private Button sim;
    @FXML
    private Button nao;

    private Stage dialogStage;
    private boolean buttonConfirmationClicked = false;
    private Cliente clienteSelecionado;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmationClicked() {
        return buttonConfirmationClicked;
    }

    public void setButtonConfirmationClicked(boolean buttonConfirmationClicked) {
        this.buttonConfirmationClicked = buttonConfirmationClicked;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    public void HandleSimDeletar(ActionEvent event) throws IOException {
        this.buttonConfirmationClicked = true;
        this.dialogStage.close();
    }

    public void HandleNaoDeletar(ActionEvent event) throws IOException {
        dialogStage.close();
    }
}
