package carmenromano;

import carmenromano.dao.CatalogoDAO;
import carmenromano.entities.Catalogo;
import carmenromano.entities.Riviste;
import carmenromano.enums.Periodicità;
import com.github.javafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Locale;
import java.util.Random;

public class Application {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");
        EntityManager em = emf.createEntityManager();
        Random random = new Random();

        System.out.println("Hello World!");
        Faker faker = new Faker(Locale.ITALY);
        CatalogoDAO catalogoDAO = new CatalogoDAO(em);
        Random rndm = new Random();

        /////SAVE
        Riviste rivista = new Riviste(35,2024,"Geopop",Periodicità.SETTIMANALE);
     ///   catalogoDAO.save(rivista);

        ////RICERCA PER ID
        Catalogo ricerca = catalogoDAO.searchById("76bb39f7-9e8c-43b7-bde2-5de1cb772ea8");
        System.out.println("Elemento trovato dal catalogo "+ ricerca);

        //DELETE
        catalogoDAO.delete("76bb39f7-9e8c-43b7-bde2-5de1cb772ea8");

    }
}
