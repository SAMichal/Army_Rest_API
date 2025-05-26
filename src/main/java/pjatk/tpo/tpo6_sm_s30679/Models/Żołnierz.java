package pjatk.tpo.tpo6_sm_s30679.Models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
public class Żołnierz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String imie;
    private String nazwisko;
    private String pesel;
    @Enumerated(EnumType.STRING)
    private Ranga ranga;
    //{aktywny, koszary, martwy, szpital polowy}
    @Enumerated(EnumType.STRING)
    private Status status;
    //tylko jeśli status = szpital polowy
    private String obrażenia;

    public Żołnierz() {
    }

    public Żołnierz(int id, String imie, String nazwisko, String pesel, Ranga ranga, Status status, String obrażenia) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.ranga = ranga;
        this.status = status;
        this.obrażenia = obrażenia;
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

    public String getObrażenia() {
        return obrażenia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
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

    public void setObrażenia(String obrażenia) {
        this.obrażenia = obrażenia;
    }
}
