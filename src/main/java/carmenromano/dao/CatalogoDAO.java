package carmenromano.dao;

import carmenromano.entities.Catalogo;
import carmenromano.entities.Libri;
import carmenromano.entities.Riviste;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CatalogoDAO {
    private EntityManager entityManager;

    public CatalogoDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public void save(Catalogo catalogo) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            entityManager.persist(catalogo);
            trans.commit();
            System.out.println("Elemento catalogo: - " + catalogo.getTitolo() + " creato con successo!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Catalogo searchById(String id) {
        return entityManager.find(Catalogo.class, UUID.fromString(id));
    }

    public void delete(String id) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            Catalogo found = searchById(id);
            if (found != null) {
                trans.begin();
                entityManager.remove(found);
                trans.commit();
                System.out.println("Elemento con ID "+ id + " eliminato dal catalogo");
            } else System.out.println("Elemento non trovato");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public List<Catalogo> searchByPublicationYear(int annoPubblicazione) {
        TypedQuery<Catalogo> query = entityManager.createQuery("SELECT c FROM Catalogo c WHERE c.annoPubblicazione = :annoPubblicazione", Catalogo.class);
        query.setParameter("annoPubblicazione", annoPubblicazione);
        return query.getResultList();
    }

    public List<Libri> searchByAuthor(String autore) {
        TypedQuery<Libri> query = entityManager.createQuery("SELECT c FROM Libri c WHERE c.autore = :autore", Libri.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    // public List<Catalogo> searchByTitle(String titolo) {
    //     TypedQuery<Catalogo> query = entityManager.createQuery("SELECT a FROM Catalogo a WHERE LOWER(a.titolo) LIKE LOWER(:titolo)", Catalogo.class);
    //    query.setParameter("titolo", "%" + titolo.toLowerCase() + "%");
    //   return query.getResultList();
    //ERROR Cannot instantiate abstract class or interface:  : carmenromano.entities.Catalogo


    public List<Catalogo> searchByTitle(String titolo) {
        TypedQuery<Libri> queryLibri = entityManager.createQuery("SELECT l FROM Libri l WHERE LOWER(l.titolo) LIKE LOWER(:titolo)", Libri.class);
        queryLibri.setParameter("titolo", "%" + titolo.toLowerCase() + "%");
        TypedQuery<Riviste> queryRiviste = entityManager.createQuery("SELECT r FROM Riviste r WHERE LOWER(r.titolo) LIKE LOWER(:titolo)", Riviste.class);
        queryRiviste.setParameter("titolo", "%" + titolo.toLowerCase() + "%");
        List<Catalogo> result = new ArrayList<>();
        result.addAll(queryLibri.getResultList());
        result.addAll(queryRiviste.getResultList());

        return result;
    }



}
