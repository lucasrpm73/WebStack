/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.container.repository;
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
public class TipoMoviRepository {
    private Session session;
    private Transaction transaction;

    public List<TipoMovi> buscar(){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        List<TipoMovi> listaDeTiposMovi = this.session.createQuery("from tipoMovi").list();
        
        this.transaction.commit();
        this.session.close();
        return listaDeTiposMovi;
    }

    public void salvar(TipoMovi tipoMovi) {
         this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        this.session.saveOrUpdate(tipoMovi);
        
        this.transaction.commit();
        this.session.close();
    }

    public void remover(long idTipoMovi) {
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        TipoMovi tipoMovi = (TipoMovi) this.session.get(TipoMovi.class, idTipoMovi);
        this.session.delete(tipoMovi);
        
        this.transaction.commit();
        this.session.close();
    }
    
    
     public List<TipoMovi> buscarTodos(){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        List<TipoMovi> listaDeArea = this.session.createQuery("from tipoMovi").list();
        
        this.transaction.commit();
        this.session.close();
        return listaDeArea;
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
