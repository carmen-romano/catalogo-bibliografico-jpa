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
        Random random = new Random();

        System.out.println("Hello World!");
        Faker faker = new Faker(Locale.ITALY);
        CatalogoDAO catalogoDAO = new CatalogoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);
        /////SAVE
     //   for (int i = 0; i < 100; i++) {
      //  Libri libri = new Libri(150,faker.number().numberBetween(1990,2024), faker.book().title(),faker.book().author(), faker.book().genre());
      //  catalogoDAO.save(libri);
     //     }
        Riviste rivista = new Riviste(25,2024,"Vanity Fair",Periodicità.SEMESTRALE);
        //catalogoDAO.save(rivista);
        Utente utente1 = new Utente(faker.name().firstName(),faker.name().lastName(), LocalDate.of(1978,02,01));


        ////RICERCA PER ID
     Catalogo ricerca = catalogoDAO.searchById("10e8f2b1-5355-4cb5-8f58-efbbe7b49328");
      System.out.println("Elemento trovato dal catalogo "+ ricerca);

        //DELETE
       // catalogoDAO.delete("d97e3d78-a9d5-4878-9fef-3d51c749a017");

        //RICERCA PER ANNO DI PUBBLICAZIONE
        System.out.println("Ricerca per anno di pubblicazione");
          catalogoDAO.searchByPublicationYear(2024).forEach(System.out::println);
        //RICERCA PER AUTORE DI PUBBLICAZIONE

        System.out.println("Ricerca per autore");
        catalogoDAO.searchByAuthor("Jacopo Gatti").forEach(System.out::println);

        System.out.println("Ricerca per titolo o parte di esso");
        catalogoDAO.searchByTitle("The Monkey's Raincoat").forEach(System.out::println);

        //CREAZIONE PRESTITO
        Utente utente2 = utenteDAO.searchById("b4f7b19b-800e-4c61-92b0-6d724c26e41c");

        Prestito prestitotest = prestitoDAO.searchById("02bf32c3-5550-467f-91cf-73ca245bd7a2");
      //  prestito.setRestituzioneEffettiva(LocalDate.now());

       // prestitoDAO.save(prestito);
        System.out.println("Ricerca prestiti in corso per utente");
        prestitoDAO.prestitiInCorso(3).forEach(System.out::println);

        prestitotest.setRestituzionePrevista(LocalDate.now());
        System.out.println(prestitotest.getRestituzioneEffettiva());
        System.out.println("Ricerca prestiti scaduti e non ancora restituiti");
        prestitoDAO.prestitiScadutiENonRestituiti().forEach(System.out::println);
    }
}
