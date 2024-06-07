package carmenromano;

import carmenromano.dao.CatalogoDAO;
import carmenromano.dao.PrestitoDAO;
import carmenromano.dao.UtenteDAO;
import carmenromano.entities.*;
import carmenromano.enums.Periodicità;
import com.github.javafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

public class Application {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");
        EntityManager em = emf.createEntityManager();
        Faker faker = new Faker(Locale.ITALY);
        CatalogoDAO catalogoDAO = new CatalogoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);
        /////SAVE
        for (int i = 0; i < 100; i++) {
        Libri libri = new Libri(faker.number().numberBetween(10,1000),faker.number().numberBetween(2000,2024), faker.book().title(),faker.book().author(), faker.book().genre());
        // catalogoDAO.save(libri);
        }
        for (int i = 0; i < 20; i++) {
            Riviste rivista = new Riviste(faker.number().numberBetween(10,100),faker.number().numberBetween(2000,2024),faker.book().title(),Periodicità.SETTIMANALE);
        //    catalogoDAO.save(rivista);
        }
        for (int i = 0; i < 50; i++) {
            Utente utente1 = new Utente(faker.name().firstName(),faker.name().lastName(), LocalDate.of(faker.number().numberBetween(1950,2000),
                    faker.number().numberBetween(1,12),faker.number().numberBetween(1,30)));
        //    utenteDAO.save(utente1);
        }

        ////RICERCA PER ID
         Catalogo ricerca = catalogoDAO.searchById("01fb2f58-8d4e-4260-99f9-b880f96b4b8d");
          System.out.println("Elemento trovato dal catalogo "+ ricerca);

        //DELETE
        // catalogoDAO.delete("00814dae-54f5-4a7b-9ea5-9b7aa2af3b5b");

        //RICERCA PER ANNO DI PUBBLICAZIONE
        System.out.println("Ricerca per anno di pubblicazione");
        catalogoDAO.searchByPublicationYear(2013).forEach(System.out::println);

        //RICERCA PER AUTORE DI PUBBLICAZIONE

          System.out.println("Ricerca per autore");
          catalogoDAO.searchByAuthor("Sig. Silverio Villa").forEach(System.out::println);

          System.out.println("Ricerca per titolo o parte di esso");
          catalogoDAO.searchByTitle("The Proper Study").forEach(System.out::println);

          //CREAZIONE PRESTITO
         Utente utente2 = utenteDAO.searchById("3976b5f5-55e6-4e6f-80d4-6afadbb081e4");
         Catalogo catalogo1 = catalogoDAO.searchById("03bf38b2-3052-4d10-be66-347cd9611307");
         Prestito prestito = new Prestito(utente2,catalogo1);
         //prestitoDAO.save(prestito);

          Prestito prestitotest = prestitoDAO.searchById("07bd0252-6ca3-4730-a76b-59d3703a7140");

          // prestitoDAO.save(prestito);
          System.out.println("Ricerca prestiti in corso per utente");
          prestitoDAO.prestitiInCorso(80).forEach(System.out::println);

          System.out.println(prestitotest.getRestituzioneEffettiva());
          System.out.println("Ricerca prestiti scaduti e non ancora restituiti");
          prestitoDAO.prestitiScadutiENonRestituiti().forEach(System.out::println);
    }
}
