package catalogo.dao;

import catalogo.entity.Prodotto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ProdottoDAO {
    private EntityManager em;

    public ProdottoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Prodotto prodotto) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(prodotto);
        et.commit();
    }

    public Prodotto getByIsbn(String isbn) {
        return em.find(Prodotto.class, isbn);
    }

    public void update(Prodotto prodotto) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(prodotto);
        et.commit();
    }

    public List<Prodotto> findAll() {
        return em.createQuery("SELECT p FROM Prodotto p", Prodotto.class).getResultList();
    }

    public List<Prodotto> findByAnno(Integer anno) {
        return em.createQuery("SELECT p FROM Prodotto p WHERE anno_pubblicazione = :anno", Prodotto.class)
                .setParameter("anno", anno)
                .getResultList();
    }

    public List<Prodotto> findByAutore(String autore) {
        return em.createQuery("SELECT p FROM Prodotto p WHERE autore LIKE :autore", Prodotto.class)
                .setParameter("autore", "%"+autore+"%")
                .getResultList();
    }

    public List<Prodotto> findByTitolo(String titolo) {
        return em.createQuery("SELECT p FROM Prodotto p WHERE titolo LIKE :titolo", Prodotto.class)
                .setParameter("titolo", "%"+titolo+"%")
                .getResultList();
    }

    public void delete(Prodotto prodotto) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(prodotto);
        et.commit();
    }
}