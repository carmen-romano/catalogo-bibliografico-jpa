package carmenromano.entities;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "libri")
@DiscriminatorValue("libro")
public class Libri extends Catalogo{
    @Column(nullable = false)
    private String autore;

    @Column(nullable = false)
    private String genere;

    public Libri() {

    }
    public Libri(int numeroPagine, int annoPubblicazione, String titolo, String autore, String genere) {
        super(numeroPagine, annoPubblicazione, titolo);
        this.autore = autore;
        this.genere = genere;
    }


    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libri{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", titolo='" + titolo + '\'' +
                ", id=" + id +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
