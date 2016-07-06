package biblioteca.controller.livro;

import biblioteca.model.Livro;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AlertDeleteLivroController implements Initializable {

    @FXML
    private Button sim;
    @FXML
    private Button nao;

    private Stage dialogStage;
    private boolean buttonConfirmationClicked = false;
    private Livro livroSelecionado;

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

    public Livro getLivroSelecionado() {
        return livroSelecionado;
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
