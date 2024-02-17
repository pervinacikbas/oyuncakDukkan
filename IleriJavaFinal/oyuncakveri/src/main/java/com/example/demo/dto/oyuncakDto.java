package com.example.demo.dto;
import lombok.Data;
import java.time.LocalDate;



@Data
public class oyuncakDto {
    private Long ID;
    private LocalDate alisTarihi;
    private Double alisFiyati;
    private String ad;
    private String tur;
    private Integer desi;
    private String notlar;


}
