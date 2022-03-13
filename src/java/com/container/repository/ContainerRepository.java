/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.container.repository;
import com.container.model.ContainerModel;
import com.container.util.HibernateConector;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ContainerRepository {
    private Session session;
    private Transaction transaction;

    public void salvar(ContainerModel container){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        this.session.saveOrUpdate(container);
        
        this.transaction.commit();
        this.session.close();
    }
    
    public List<ContainerModel> buscarTodos(){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        List<ContainerModel> listaDeContainer = this.session.createQuery("from containerModel").list();
        
        this.transaction.commit();
        this.session.close();
        return listaDeContainer;
    }
    
    public void remover(long idContainer){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        ContainerModel container = (ContainerModel) this.session.get(ContainerModel.class, idContainer);
        this.session.delete(container);
        
        this.transaction.commit();
        this.session.close();
    }
    
    public ContainerModel buscarPorId(long idContainer){
        this.session = HibernateConector.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
        
        ContainerModel container;
        container = (ContainerModel) this.session.get(ContainerModel.class, idContainer);
        
        this.transaction.commit();
        this.session.close();
        return container;
    }
}
