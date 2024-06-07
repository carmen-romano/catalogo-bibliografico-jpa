package carmenromano.dao;

import carmenromano.entities.Catalogo;
import carmenromano.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class UtenteDAO {
    private EntityManager entityManager;


    public UtenteDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }
    public void save(Utente utente) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            entityManager.persist(utente);
            trans.commit();
            System.out.println("Utente: - " + utente.getNome() + " creato con successo!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Utente searchById(String id) {
        return entityManager.find(Utente.class, UUID.fromString(id));
    }

    public void delete(String id) {
        try {
            EntityTransaction trans = entityManager.getTransaction();
            Utente found = searchById(id);
            if (found != null) {
                trans.begin();
                entityManager.remove(found);
                trans.commit();
                System.out.println("Elemento con ID "+ id + " eliminato dagli utenti");
            } else System.out.println("Elemento non trovato");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
}
}
