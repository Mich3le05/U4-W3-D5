package catalogo.entity;

import catalogo.enums.Periodicita;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="riviste")
public class Rivista extends Prodotto implements Serializable {
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    public Rivista(String isbn, String titolo, Integer annoPubblicazione, Integer numPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numPagine);
        this.periodicita = periodicita;
    }

    public Rivista() {
        super();
    }

    public Periodicita getPeriodicita() {
        return this.periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }
}