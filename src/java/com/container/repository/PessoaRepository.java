/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.container.repository;

import com.container.model.PessoaModel;
import com.container.model.TipoMovi;
import com.container.util.HibernateConector;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lucas Santos
 */
public class PessoaRepository {
     private Session session;
    private Transaction transaction;

    public List<PessoaModel> buscar(){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        List<PessoaModel> listaDePessoas = this.session.createQuery("from PessoaModel").list();
        
        this.transaction.commit();
        this.session.close();
        return listaDePessoas;
    }

    public void salvar(PessoaModel pessoa) {
         this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        this.session.saveOrUpdate(pessoa);
        
        this.transaction.commit();
        this.session.close();
    }

    public void remover(long idPessoa) {
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        PessoaModel pessoa = (PessoaModel) this.session.get(PessoaModel.class, idPessoa);
        this.session.delete(pessoa);
        
        this.transaction.commit();
        this.session.close();
    }
    
    
     public List<PessoaModel> buscarTodos(){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        List<PessoaModel> listaDePessoas = this.session.createQuery("from pessoa").list();
        
        this.transaction.commit();
        this.session.close();
        return listaDePessoas;
    }
    

    public TipoMovi buscarPorId(long idTipoMovi) {
          this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        TipoMovi movimentacao = (TipoMovi) this.session.get(TipoMovi.class, idTipoMovi);
        Hibernate.initialize(movimentacao.getIdTipoMovi());
        
        this.transaction.commit();
        this.session.close();
        return movimentacao;
    }

    
}
