package carmenromano;

import carmenromano.dao.CatalogoDAO;
import carmenromano.entities.Catalogo;
import carmenromano.entities.Libri;
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
     //   for (int i = 0; i < 100; i++) {
      //  Libri libri = new Libri(150,faker.number().numberBetween(1990,2024), faker.book().title(),faker.book().author(), faker.book().genre());
      //  catalogoDAO.save(libri);
     //     }
        Riviste rivista = new Riviste(25,2024,"Vanity Fair",Periodicità.SEMESTRALE);
        //catalogoDAO.save(rivista);

        ////RICERCA PER ID
     Catalogo ricerca = catalogoDAO.searchById("41a9e930-29b3-47f9-8f37-9b8ce32dd4c8");
      System.out.println("Elemento trovato dal catalogo "+ ricerca);

        //DELETE
       // catalogoDAO.delete("d97e3d78-a9d5-4878-9fef-3d51c749a017");

        //RICERCA PER ANNO DI PUBBLICAZIONE
        System.out.println("Ricerca per anno di pubblicazione");
          catalogoDAO.searchByPublicationYear(2024).forEach(System.out::println);
        //RICERCA PER AUTORE DI PUBBLICAZIONE

        System.out.println("Ricerca per autore");
        catalogoDAO.searchByAuthor("Jacopo Gatti").forEach(System.out::println);

    }
}
