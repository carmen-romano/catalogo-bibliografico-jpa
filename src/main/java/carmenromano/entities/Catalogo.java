package carmenromano.entities;

import com.github.javafaker.Cat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "catalogo")
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Catalogo {
    @Id
    @GeneratedValue
    protected UUID id;
    @Column(nullable = false)
    protected String titolo;
    @Column(nullable = false)
    protected int annoPubblicazione;
    @Column(nullable = false)
    protected int numeroPagine;

    public Catalogo(){};

    public Catalogo(int numeroPagine, int annoPubblicazione, String titolo) {
        this.numeroPagine = numeroPagine;
        this.annoPubblicazione = annoPubblicazione;
        this.titolo = titolo;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public UUID getId() {
        return id;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
