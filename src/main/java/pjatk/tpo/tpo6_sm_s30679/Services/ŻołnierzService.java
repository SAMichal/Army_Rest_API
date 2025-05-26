package pjatk.tpo.tpo6_sm_s30679.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.tpo.tpo6_sm_s30679.Models.Żołnierz;
import pjatk.tpo.tpo6_sm_s30679.Repositories.ŻołnierzRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ŻołnierzService {
    @Autowired
    private ŻołnierzRepository repository;
    public List<Żołnierz> wszyscy()
    {
        return repository.findAll();
    }

}
