/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.container.repository;

import com.container.model.MovimentacaoModel;
import com.container.util.HibernateConector;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;
import com.container.model.PessoaModel;

/**
 *
 * @author Lucas Santos
 */
public class MovimentacaoRepository {
    private Session session;
    private Transaction transaction;

    public void salvar(MovimentacaoModel movimentacao){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        this.session.saveOrUpdate(movimentacao);
        
        this.transaction.commit();
        this.session.close();
    }
    
    public List<MovimentacaoModel> buscarTodos(){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        List<MovimentacaoModel> listaDeMovimentacao = this.session.createQuery("from movimentacaoModel").list();
        
        this.transaction.commit();
        this.session.close();
        return listaDeMovimentacao;
    }
    
    public List<MovimentacaoModel> buscarSumarioEx(){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        List<MovimentacaoModel> listaSumario = this.session.createQuery("SELECT count(containercategoria) as Total_Registros FROM containerModel WHERE containercategoria= 'Exportação'").list();
        
        this.transaction.commit();
        this.session.close();
        return listaSumario;
    }
    public List<MovimentacaoModel> buscarSumarioImport(){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        List<MovimentacaoModel> listaSumario = this.session.createQuery("SELECT count(containercategoria) as Total_Registros FROM containerModel WHERE containercategoria= 'Importação'").list();
        
        this.transaction.commit();
        this.session.close();
        return listaSumario;
    }
    
    
    
     public List<MovimentacaoModel> buscarOrdenarCliente(){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        MovimentacaoModel pessoa = new MovimentacaoModel();
        
        String nome = null;
        
        if(nome != null){
         Query query = session.createQuery("from movimentacaoModel Where pessoa= :pessoa");
         query.setParameter("pessoa", nome);
        List<MovimentacaoModel> listaDeMovimentacao = query.list();
        this.transaction.commit();
        this.session.close();
        return listaDeMovimentacao;
        
        }
        else{
         Query query = session.createQuery("from movimentacaoModel order by pessoa");
        List<MovimentacaoModel> listaDeMovimentacao = query.list();
        this.transaction.commit();
        this.session.close();
        return listaDeMovimentacao;
        
        }
        
    }
    
    public void remover(long idMovimentacao){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        MovimentacaoModel movimentacao = (MovimentacaoModel) this.session.get(MovimentacaoModel.class, idMovimentacao);
        this.session.delete(movimentacao);
        
        this.transaction.commit();
        this.session.close();
    }
    
    public MovimentacaoModel buscarPorId(long idMovimentacao){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        MovimentacaoModel movimentacao;
        movimentacao = (MovimentacaoModel) this.session.get(MovimentacaoModel.class, idMovimentacao);
        
        this.transaction.commit();
        this.session.close();
        return movimentacao;
    }
    
}

