package com.bryndzak.bm.repository;

import com.bryndzak.bm.domain.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository//вказує на те що клас має доступ до БД
@Transactional//всі методи будуть виконуватися по трансакції
public class BookRepository {

    @Autowired//автоматично впроваджує залежності бінів в даний клас
    private SessionFactory sessionFactory;//клас для роботи з hibernate

    public void addBook(Book book){
        this.sessionFactory.getCurrentSession().save(book);
    }

    public List<Book> listAll(){
        return this.sessionFactory.getCurrentSession().createQuery("from Book").list();
    }

    public void removeBook(Integer iD){
        Book contact = (Book)this.sessionFactory.getCurrentSession().load(Book.class, iD);
        if (null!=contact){
            this.sessionFactory.getCurrentSession().delete(contact);
        }
    }
}