package catalogo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "libri")
public class Libro extends Prodotto {
    private String autore;
    private String genere;

    public Libro(String isbn, String titolo, Integer annoPubblicazione, Integer numPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Libro() {
        super();
    }

    public String getAutore() {
        return this.autore;
    }

    public String getGenere() {
        return this.genere;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}