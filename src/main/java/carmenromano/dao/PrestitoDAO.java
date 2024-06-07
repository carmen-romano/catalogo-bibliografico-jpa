package carmenromano.dao;


import carmenromano.entities.Libri;
import carmenromano.entities.Prestito;
import carmenromano.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PrestitoDAO {
    private EntityManager entityManager;


    public PrestitoDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public void save(Prestito prestito) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            entityManager.persist(prestito);
            trans.commit();
            System.out.println("Il prestito per l'utente: - " + prestito.getUtente().getNome() + " creato con successo!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Prestito searchById(String id) {
        return entityManager.find(Prestito.class, UUID.fromString(id));
    }

    public void delete(String id) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            Prestito found = searchById(id);
            if (found != null) {
                trans.begin();
                entityManager.remove(found);
                trans.commit();
                System.out.println("Prestito con ID " + id + " eliminato");
            } else System.out.println("Elemento non trovato");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Prestito> prestitiInCorso(int numeroTesseraUtente) {
        TypedQuery<Prestito> query = entityManager.createQuery(
                "SELECT p FROM Prestito p " + "WHERE p.utente.numeroTessera = :numeroTesseraUtente AND p.restituzioneEffettiva" +
                        " IS NULL", Prestito.class
        );
        query.setParameter("numeroTesseraUtente", numeroTesseraUtente);
        return query.getResultList();
    }

    public List<Prestito> prestitiScadutiENonRestituiti() {
        TypedQuery<Prestito> query = entityManager.createQuery(
                "SELECT p FROM Prestito p WHERE p.restituzionePrevista < CURRENT_DATE()" +
                        " AND p.restituzioneEffettiva IS NULL", Prestito.class
        );
        return query.getResultList();
    }

}

