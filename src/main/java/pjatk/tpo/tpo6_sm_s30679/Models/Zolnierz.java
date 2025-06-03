package pjatk.tpo.tpo6_sm_s30679.Models;

import jakarta.persistence.*;

@Entity
public class Zolnierz
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String imie;
    private String nazwisko;
    private String pesel;
    @Enumerated(EnumType.STRING)
    private Ranga ranga;
    //{aktywny, koszary, martwy, szpital polowy}
    @Enumerated(EnumType.STRING)
    private Status status;
    //tylko jeśli status = szpital polowy lub martwy
    @Column(nullable = true)
    private String obrazenia;

    public Zolnierz() {
    }

    public Zolnierz(int id, String imie, String nazwisko, String pesel, Ranga ranga, Status status, String obrazenia) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.ranga = ranga;
        this.status = status;
        this.obrazenia = obrazenia;
    }

    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public Ranga getRanga() {
        return ranga;
    }

    public Status getStatus() {
        return status;
    }

    public String getObrazenia() {
        return obrazenia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko; // Poprawiono: przypisanie wartości
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setRanga(Ranga ranga) {
        this.ranga = ranga;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setObrazenia(String obrażenia) {
        this.obrazenia = obrażenia;
    }
}
