package biblioteca.controller.retirada;

import biblioteca.model.Retirada;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import biblioteca.negocio.RetiradaNegocio;
import java.text.SimpleDateFormat;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.util.Callback;

public class AnchorPaneVisualizarRetiradaController implements Initializable {

    @FXML
    private TableView<Retirada> tableViewRetirada;
    @FXML
    private TableColumn<Retirada, String> tableColumnRetiradaTitulo;
    @FXML
    private TableColumn<Retirada, String> tableColumnRetiradaCliente;
    @FXML
    private Label LabelRetiradaID;
    @FXML
    private Label LabelRetiradaTitulo;
    @FXML
    private Label LabelRetiradaCliente;
    @FXML
    private Label LabelRetiradaRet;
    @FXML
    private Label LabelRetiradaEntrega;
    @FXML
    private Label LabelRetiradaDevolvido;

    private List<Retirada> listaRetiradas;
    private ObservableList<Retirada> observableListRetiradas;
    RetiradaNegocio retirada = new RetiradaNegocio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewRetiradas();
        this.tableViewRetirada.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionarItensTableViewRetiradas(newValue));
    }

    public void carregarTableViewRetiradas() {

        //call back para mostrar a coluna título do livro
        tableColumnRetiradaTitulo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Retirada, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Retirada, String> cell) {
                final Retirada liv = cell.getValue();
                final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty(liv.getLivro().getNome());
                return simpleObject;
            }

        });

        //call back para mostrar a coluna nome do cliente
        tableColumnRetiradaCliente.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Retirada, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Retirada, String> cell) {
                final Retirada cli = cell.getValue();
                final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty(cli.getCliente().getNome());
                return simpleObject;
            }

        });

        listaRetiradas = retirada.listar();

        observableListRetiradas = FXCollections.observableArrayList(listaRetiradas);
        this.tableViewRetirada.setItems(observableListRetiradas);
    }

    public void selecionarItensTableViewRetiradas(Retirada retiradaSelecionado) {
        //formatando data ANO
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (retiradaSelecionado != null) {
            LabelRetiradaID.setText(String.valueOf(retiradaSelecionado.getId()));
            this.LabelRetiradaTitulo.setText(retiradaSelecionado.getLivro().getNome());
            this.LabelRetiradaCliente.setText(retiradaSelecionado.getCliente().getNome());
            this.LabelRetiradaRet.setText(retiradaSelecionado.getRetiradaFormatada());
            this.LabelRetiradaEntrega.setText(retiradaSelecionado.getEntregaFormatada());
            this.LabelRetiradaDevolvido.setText(retiradaSelecionado.getDevolvidoFormatada());

        } else {
            LabelRetiradaID.setText("");
            this.LabelRetiradaTitulo.setText("");
            this.LabelRetiradaCliente.setText("");
            this.LabelRetiradaRet.setText("");
            this.LabelRetiradaEntrega.setText("");
            this.LabelRetiradaDevolvido.setText("");
        }
    }
}
