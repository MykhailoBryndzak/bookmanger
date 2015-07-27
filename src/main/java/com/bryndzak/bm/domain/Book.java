package com.bryndzak.bm.domain;


import javax.persistence.*;

@Entity //вказує на те що клас незалежжний(сущность) ,який буде зберігатися в БД
@Table (name = "books")//назва таблиці яка буде зберігатися в mySQL DB
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue()//автогенерація значення ід
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "genre")
    private String genre;

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
