package carmenromano.dao;

import carmenromano.entities.Catalogo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
    public Catalogo searchById(String id) {
        return entityManager.find(Catalogo.class, UUID.fromString(id));
    }

}
