
package biblioteca.dao;

import java.util.List;
import biblioteca.model.Cliente;

//Além dos métodos do Crud padronizado na interface Dao, procurarPorNomeLista é obrigatório.
public interface ClienteDao extends Dao<Cliente>{
    public List<Cliente> procurarPorNomeLista(String nome); 
}
