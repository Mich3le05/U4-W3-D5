package catalogo.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "prodotti")
public abstract class Prodotto {
    @Id
    private String isbn;
    private String titolo;
    @Column(name = "anno_pubblicazione")
    private Integer annoPubblicazione;
    @Column(name = "num_pagine")
    private Integer numPagine;

    public Prodotto(String isbn, String titolo, Integer annoPubblicazione, Integer numPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numPagine = numPagine;
    }

    public Prodotto() {

    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnnoPubblicazione() {
        return this.annoPubblicazione;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "isbn='" + isbn + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numPagine=" + numPagine +
                '}';
    }

    public String getTitolo() {
        return this.titolo;
    }

    public Integer getNumPagine() {
        return this.numPagine;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAnnoPubblicazione(Integer annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public void setNumPagine(Integer numPagine) {
        this.numPagine = numPagine;
    }
}