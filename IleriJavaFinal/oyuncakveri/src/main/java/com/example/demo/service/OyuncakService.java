package com.example.demo.service;

import com.example.demo.entity.Oyuncak;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OyuncakService {
    Oyuncak ekle (Oyuncak oyuncak);
    Oyuncak getir (Long id);
    void sil(Long id);
    // güncelle fonksiyonu ekle ile yapıldığı için gerek kalmadı.
    //Oyuncak guncelle ( Oyuncak oyuncak);
    List<Oyuncak> listele ();


}
