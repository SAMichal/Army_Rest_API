package pjatk.tpo.tpo6_sm_s30679.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;

@Entity
public class Druzyna
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nazwa;
    //przedstawia listę żołnierzy należących do drużyny
    private ArrayList<Zolnierz> zolnierze=new ArrayList<>();
}
