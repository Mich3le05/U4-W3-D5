package catalogo.dao;

import catalogo.entity.Prestito;
import catalogo.entity.Prodotto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;

public class PrestitoDAO {
    private EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito prestito) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(prestito);
        et.commit();
    }

    public Prestito getById(Integer id) {
        return em.find(Prestito.class, id);
    }

    public List<Prodotto> getProductFromId(Integer id) {
        return em.createQuery("SELECT pr FROM Prestito pr", Prestito.class)
                .getResultStream()
                .map(Prestito::getProdotto)
                .toList();
    }

    public List<Prestito> getPrestitoScadutoONonConsegnato() {
        LocalDate oggi = LocalDate.now();

        return em.createQuery("SELECT pr FROM Prestito pr WHERE pr.dataRestituzioneEffettiva IS NULL OR " +
                        "pr.dataRestituzionePrevista < :oggi", Prestito.class)
                .setParameter("oggi", oggi)
                .getResultList();
    }

    public void update(Prestito prestito) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(prestito);
        et.commit();
    }

    public List<Prestito> findAll() {
        return em.createQuery("SELECT pr FROM Prestito pr", Prestito.class).getResultList();
    }

    public void delete(Prestito prestito) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(prestito);
        et.commit();
    }
}