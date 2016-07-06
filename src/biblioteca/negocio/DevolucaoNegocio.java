/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.negocio;

import biblioteca.dao.impl_BD.DevolucaoDaoBd;
import java.util.List;
import biblioteca.model.Devolucao;
import biblioteca.model.Retirada;

/**
 *
 * @author saulovieira
 */
public class DevolucaoNegocio {
    
    private DevolucaoDaoBd devolucaoDao;
    
    public DevolucaoNegocio(){
        devolucaoDao = new DevolucaoDaoBd();
    }
    
    public void salvar(Retirada r) throws Exception{
        devolucaoDao.salvar(r);
    }
    
    public List<Devolucao> listar()
    {
        return devolucaoDao.listar();
    }
}
