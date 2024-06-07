package carmenromano.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prestito")
public class Prestito {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "elemento_prestato_id", nullable = false)
    private Catalogo elementoPrestato;

    @Column(nullable = false)
    private LocalDate inizioPrestito;

    @Column(nullable = false)
    private LocalDate restituzionePrevista;

    @Column
    private LocalDate restituzioneEffettiva;


    public Prestito() {}


    public Prestito(Utente utente, Catalogo elementoPrestato, LocalDate restituzioneEffettiva) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.restituzioneEffettiva = restituzioneEffettiva;
    }

    public UUID getId() {
        return id;
    }


    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Catalogo getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(Catalogo elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public LocalDate getInizioPrestito() {
        return inizioPrestito;
    }

    public void setInizioPrestito(LocalDate inizioPrestito) {
        this.inizioPrestito = inizioPrestito;
    }

    public LocalDate getRestituzionePrevista() {
        return restituzionePrevista;
    }

    public void setRestituzionePrevista(LocalDate restituzionePrevista) {
        this.restituzionePrevista = restituzionePrevista;
    }

    public LocalDate getRestituzioneEffettiva() {
        return restituzioneEffettiva;
    }

    public void setRestituzioneEffettiva(LocalDate restituzioneEffettiva) {
        this.restituzioneEffettiva = restituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", elementoPrestato=" + elementoPrestato +
                ", inizioPrestito=" + inizioPrestito +
                ", restituzionePrevista=" + restituzionePrevista +
                ", restituzioneEffettiva=" + restituzioneEffettiva +
                '}';
    }
}

