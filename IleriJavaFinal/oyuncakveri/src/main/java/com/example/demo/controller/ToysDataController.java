package com.example.demo.controller;

import com.example.demo.entity.Oyuncak;
import com.example.demo.service.OyuncakService;
import com.example.demo.dto.oyuncakDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@Slf4j
@RestController
public class ToysDataController {
    @Autowired
    OyuncakService oyuncakService;
    LocalDateTime now = LocalDateTime.now();

    @PostMapping ("/ekle")
    public String ekle (@RequestBody oyuncakDto oyuncakDto){
        log.info("ekle servisi "+now +" tarihinde çağırıldı.");
        try {
            Oyuncak oyuncak = new Oyuncak();
            oyuncak.setAd(oyuncakDto.getAd());
            oyuncak.setDesi(oyuncakDto.getDesi());
            oyuncak.setAlisTarihi(oyuncakDto.getAlisTarihi());
            oyuncak.setAlisFiyati(oyuncakDto.getAlisFiyati());
            oyuncak.setAd(oyuncakDto.getAd());
            oyuncak.setTur(oyuncakDto.getTur());
            oyuncak.setNotlar(oyuncakDto.getNotlar());
            Oyuncak yenioyuncak = oyuncakService.ekle(oyuncak);
            return yenioyuncak.getAd() +" isimli oyuncak eklendi.";
        }
        catch (Exception e){
            log.error("ekle servisi çalışırken hata aldı. "
                    + "Parametreler:" + oyuncakDto
                    + "Hata:" + e.getMessage());
            String hata= String.valueOf(e);
            return hata;
        }
    }
    @GetMapping ("/getir")
    public oyuncakDto getir (Long id){
        log.info("getir servisi "+now +" tarihinde çağırıldı.");
        try {
            Oyuncak oyuncak= oyuncakService.getir(id);
            oyuncakDto oyuncakdto = new oyuncakDto();
            oyuncakdto.setAd(oyuncak.getAd());
            oyuncakdto.setAlisTarihi(oyuncak.getAlisTarihi());
            oyuncakdto.setDesi(oyuncak.getDesi());
            oyuncakdto.setTur(oyuncak.getTur());
            oyuncakdto.setNotlar(oyuncak.getNotlar());
            oyuncakdto.setAlisFiyati(oyuncak.getAlisFiyati());
            oyuncakdto.setID(oyuncak.getID());
            return oyuncakdto;
        }
        catch (Exception e){
            log.error("getir servisi çalışırken hata aldı. "
                    + "Hata:" + e.getMessage());
            return null;
        }

    }
    @PutMapping ("/guncelle/{id}")
    public String guncelle (@PathVariable Long id, @RequestBody oyuncakDto oyuncakDto){
        log.info("güncelle servisi " +now +" tarihinde çağırıldı.");
        try {
            Oyuncak oyuncak = oyuncakService.getir(id);
            oyuncak.setAlisTarihi(oyuncakDto.getAlisTarihi());
            oyuncak.setAlisFiyati(oyuncakDto.getAlisFiyati());
            oyuncak.setAd(oyuncakDto.getAd());
            oyuncak.setTur(oyuncakDto.getTur());
            oyuncak.setDesi(oyuncakDto.getDesi());
            oyuncak.setNotlar(oyuncakDto.getNotlar());
            oyuncakService.ekle(oyuncak);
            return id + "id'ye sahip oyuncak güncellendi";
        }
        catch (Exception e){
            log.error("guncelle servisi çalışırken hata aldı. "
                    + "Parametreler:" + oyuncakDto
                    + "Hata:" + e.getMessage());
            String hata= String.valueOf(e);
            return hata;
        }
    }
    @DeleteMapping("/sil")
    public String sil (Long id ){
        log.info("sil servisi "+now +" tarihinde çağırıldı.");
        try {
            oyuncakService.sil(id);
            return id +"id li oyuncak silindi.";
        }
        catch (Exception e){
            log.error("sil servisi çalışırken hata aldı. "
                    + "Hata:" + e.getMessage());
            String hata= String.valueOf(e);
            return hata;
        }
    }
    @GetMapping("/listele")
    public List<oyuncakDto> listele() {
        log.info("listele servisi "+now +" tarihinde çağırıldı.");
        try {
            List<Oyuncak> oyuncakList = oyuncakService.listele();
            List<oyuncakDto> allOyuncaklar = new ArrayList<>();
            for (Oyuncak oyuncak : oyuncakList) {
                oyuncakDto oyuncakDto = new oyuncakDto();
                oyuncakDto.setID(oyuncak.getID());
                oyuncakDto.setAlisTarihi(oyuncak.getAlisTarihi());
                oyuncakDto.setAlisFiyati(oyuncak.getAlisFiyati());
                oyuncakDto.setAd(oyuncak.getAd());
                oyuncakDto.setTur(oyuncak.getTur());
                oyuncakDto.setDesi(oyuncak.getDesi());
                oyuncakDto.setNotlar(oyuncak.getNotlar());
                allOyuncaklar.add(oyuncakDto);
            }
            return allOyuncaklar;
        }
        catch (Exception e){
            log.error("listele servisi çalışırken hata aldı. "
                    + "Hata:" + e.getMessage());
            return null;
        }
    }
}
