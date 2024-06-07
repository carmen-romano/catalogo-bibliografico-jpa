package carmenromano.entities;

import carmenromano.enums.Periodicità;

import javax.persistence.*;

@Entity
@Table(name = "riviste")
public class Riviste extends Catalogo{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Periodicità periodicità;

    public Riviste(int numeroPagine, int annoPubblicazione, String titolo, Periodicità periodicità) {
        super(numeroPagine, annoPubblicazione, titolo);
        this.periodicità = periodicità;
    }

    public Riviste(Periodicità periodicità) {
        this.periodicità = periodicità;
    }

    public Periodicità getPeriodicità() {
        return periodicità;
    }

    public void setPeriodicità(Periodicità periodicità) {
        this.periodicità = periodicità;
    }

    @Override
    public String toString() {
        return "Riviste{" +
                "periodicità=" + periodicità +
                ", titolo='" + titolo + '\'' +
                ", id=" + id +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
