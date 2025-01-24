package catalogo;

import catalogo.dao.*;
import catalogo.entity.*;
import catalogo.enums.Periodicita;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4-W3-D5");
        EntityManager em = emf.createEntityManager();

        ProdottoDAO prodottoDAO = new ProdottoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);

        Libro l1 = new Libro();
        l1.setIsbn("9780439784542");
        l1.setTitolo("Harry Potter e il Principe Mezzosangue");
        l1.setAutore("J. K. Rowling");
        l1.setGenere("Romanzo");
        l1.setNumPagine(652);
        l1.setAnnoPubblicazione(2005);
        try {
            prodottoDAO.save(l1);
            System.out.println("Prodotto salvato con successo");
        } catch (Exception e) {
            System.err.println("Prodotto duplicato");
        }
        Libro l2 = new Libro();
        l2.setIsbn("9788869186127");
        l2.setTitolo("Harry Potter e il prigioniero di Azkaban");
        l2.setAutore("J. K. Rowling");
        l2.setGenere("Romanzo");
        l2.setNumPagine(441);
        l2.setAnnoPubblicazione(1999);
        try {
            prodottoDAO.save(l2);
            System.out.println("Prodotto salvato con successo");
        } catch (Exception e) {
            System.err.println("Prodotto duplicato");
        }
        Rivista r1 = new Rivista();
        r1.setIsbn("9788837092405");
        r1.setTitolo("TV Sorrisi e Canzoni");
        r1.setNumPagine(100);
        r1.setPeriodicita(Periodicita.SETTIMANALE);
        r1.setAnnoPubblicazione(2024);
        try {
            prodottoDAO.save(r1);
            System.out.println("Prodotto salvato con successo");
        } catch (Exception e) {
            System.err.println("Prodotto duplicato");
        }

        System.out.println("---------------------");

        try {
            Prodotto prodotto = prodottoDAO.getByIsbn("125");
            if (prodotto != null) {
                prodottoDAO.delete(prodotto);
                System.out.println("Prodotto eliminato con successo");
            } else {
                throw new RuntimeException("Prodotto non esistente");
            }
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        }

        System.out.println("---------------------");

        try {
            Prodotto prodotto = prodottoDAO.getByIsbn("9788869186127");
            if (prodotto != null) {
                prodottoDAO.delete(prodotto);
                System.out.println("Prodotto eliminato con successo");
            } else {
                throw new RuntimeException("Prodotto non esistente");
            }
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        }

        System.out.println("---------------------");

        Prodotto findProduct = prodottoDAO.getByIsbn("9788837092405");
        if (findProduct != null) {
            System.out.println(findProduct);
        } else {
            System.err.println("Prodotto non esistente");
        }

        System.out.println("--------------------");
        System.out.println("Prodotti Rowling");
        List<Prodotto> findProductsFromAutore = prodottoDAO.findByAutore("Rowling");
        if (findProductsFromAutore.isEmpty()) {
            System.err.println("Nessun risultato trovato");
        } else {
            findProductsFromAutore.forEach(System.out::println);
        }

        System.out.println("---------------------");

        System.out.println("Prodotti anno 2005");
        List<Prodotto> productsByAnno = prodottoDAO.findByAnno(2005);
        if (productsByAnno.isEmpty()) {
            System.err.println("Nessun prodotto trovato per quest'anno");
        } else {
            productsByAnno.forEach(System.out::println);
        }

        System.out.println("---------------------");

        System.out.println("Prodotti con titolo 'Principe'");
        List<Prodotto> productsByTitolo = prodottoDAO.findByTitolo("Principe");
        if (productsByTitolo.isEmpty()) {
            System.err.println("Nessun prodotto trovato per questo titolo");
        } else {
            productsByTitolo.forEach(System.out::println);
        }

        System.out.println("---------------------");

        Utente u1 = new Utente();
        u1.setNome("Fabio");
        u1.setCognome("Brogi");
        u1.setDataDiNascita(LocalDate.of(2000, 2, 7));
        try {
            utenteDAO.save(u1);
            System.out.println("Utente salvato correttamente");
        } catch (Exception e) {
            System.err.println("Tessera utente esistente");
        }
        Utente u2 = new Utente();
        u2.setNome("Vito");
        u2.setCognome("Dagnello");
        u2.setDataDiNascita(LocalDate.of(1996, 11, 9));
        try {
            utenteDAO.save(u2);
            System.out.println("Utente salvato correttamente");
        } catch (Exception e) {
            System.err.println("Tessera utente esistente");
        }
        Prestito pr1 = new Prestito();
        pr1.setUtente(utenteDAO.getById(1));
        pr1.setDataInizioPrestito(LocalDate.now());
        pr1.setProdotto(prodottoDAO.getByIsbn("9788837092405"));
        try {
            prestitoDAO.save(pr1);
            System.out.println("Prestito creato con successo");
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        }
        Prestito pr2 = new Prestito();
        pr2.setUtente(utenteDAO.getById(2));
        pr2.setDataInizioPrestito(LocalDate.now());
        pr2.setProdotto(prodottoDAO.getByIsbn("9780439784542"));
        try {
            prestitoDAO.save(pr2);
            System.out.println("Prestito creato con successo");
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        }
        System.out.println("--------------------------");
        System.out.println("Prodotti in prestito da Tessera 2");
        List<Prodotto> prodottiPrestito = prestitoDAO.getProductFromId(2);
        prodottiPrestito.forEach(System.out::println);
        System.out.println("--------------------------");
        System.out.println("Prodotti ancora non restituiti");
        List<Prestito> prestitiNonRestituiti = prestitoDAO.getPrestitoScadutoONonConsegnato();
        prestitiNonRestituiti.forEach(System.out::println);
    }
}