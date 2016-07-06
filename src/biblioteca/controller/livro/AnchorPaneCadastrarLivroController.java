package biblioteca.controller.livro;

import biblioteca.model.Livro;
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
import biblioteca.negocio.LivroNegocio;
import biblioteca.negocio.NegocioException;
import biblioteca.util.DateUtil;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnchorPaneCadastrarLivroController implements Initializable {

    @FXML
    private TextField textFieldIsbnLivro;
    @FXML
    private TextField textFieldNomeLivro;
    @FXML
    private TextField textFieldAutorLivro;
    @FXML
    private TextField textFieldEditoraLivro;
    @FXML
    private TextField textFieldAnoLivro;
    @FXML
    private Button btnCadastrarLivro;

    LivroNegocio livro = new LivroNegocio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void HandleBtnCadastrarLivro(ActionEvent event) throws IOException, NegocioException {

        if (!textFieldNomeLivro.getText().isEmpty() && !textFieldIsbnLivro.getText().isEmpty() && !textFieldAutorLivro.getText().isEmpty() && !textFieldEditoraLivro.getText().isEmpty() && !textFieldAnoLivro.getText().isEmpty()) {
            if (!validaNome(textFieldNomeLivro.getText())) {
                Livro novo = null;
                try {
                    novo = new Livro(textFieldIsbnLivro.getText(), textFieldNomeLivro.getText(), textFieldAutorLivro.getText(), textFieldEditoraLivro.getText(), DateUtil.stringToYear(textFieldAnoLivro.getText()));
                } catch (ParseException ex) {
                    Logger.getLogger(AnchorPaneCadastrarLivroController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    livro.salvar(novo);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Confirmação de Cadastro");
                    alert.setHeaderText("CONFIRMAÇÃO");
                    alert.setContentText("O Livro " + textFieldNomeLivro.getText() + " foi cadastrado com sucesso!");
                    alert.showAndWait();
                } catch (NegocioException ex) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("ERRO! Formato ano inválido! Insira um ano válido! ");
                    alert.showAndWait();
                }
            } else {
                Alert alertFilmeCadastrado = new Alert(Alert.AlertType.WARNING);
                alertFilmeCadastrado.setTitle("Confirmação de Cadastro");
                alertFilmeCadastrado.setHeaderText("ERRO!!!");
                alertFilmeCadastrado.setContentText("O Livro " + textFieldNomeLivro.getText() + " já foi cadastrado, insira um novo Livro!");
                alertFilmeCadastrado.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Campos de cadastramento não podem ficar em branco!");
            alert.show();
        }
    }

    private boolean validaNome(String nome) throws NegocioException {
        List<Livro> listaNome = livro.procurarNome(nome);
        if (!listaNome.isEmpty()) {
            for (Livro c : listaNome) {
                if (c.getNome().equalsIgnoreCase(nome)) {
                    return true;
                }
            }
        }
        return false;
    }
}
