package com.example.demo.service;

import com.example.demo.entity.Oyuncak;
import com.example.demo.repo.OyuncakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OyuncakserviceImpl implements OyuncakService{

    @Autowired
    OyuncakRepository oyuncakRepo;

    @Override
    public Oyuncak ekle(Oyuncak oyuncak) {
        return oyuncakRepo.save(oyuncak);
    }

    @Override
    public Oyuncak getir(Long id) {
        return oyuncakRepo.getReferenceById(id);
    }

    @Override
    public void sil(Long id) {
        oyuncakRepo.deleteById(id);
    }
    /* güncelle fonsksiyonu ekle ile yapıldığı için gerek kalmadı.
    @Override
    public Oyuncak guncelle( Oyuncak oyuncak) {
        return oyuncakRepo.save(oyuncak);
    }*/

    @Override
    public List<Oyuncak> listele() {
        return oyuncakRepo.findAll();
    }
}
