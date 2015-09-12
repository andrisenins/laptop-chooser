package lv.andrise.laptopscrapper.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andris on 15.10.9.
 */

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "laptopprices")
public class LaptopPrice {
    @Id
//    @SequenceGenerator(name = "laptopprice_id_seq", sequenceName = "laptopprice_id_seq", initialValue = 1, allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "laptopprice_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long laptopId;
    private double aktualaCena;
    private Date ierakstaDatums;

    public LaptopPrice() {
    }

    public LaptopPrice(long laptopId, double aktualaCena, Date ierakstaDatums) {
        this.laptopId = laptopId;
        this.aktualaCena = aktualaCena;
        this.ierakstaDatums = ierakstaDatums;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(long laptopId) {
        this.laptopId = laptopId;
    }

    public double getAktualaCena() {
        return aktualaCena;
    }

    public void setAktualaCena(double aktualaCena) {
        this.aktualaCena = aktualaCena;
    }

    public Date getIerakstaDatums() {
        return ierakstaDatums;
    }

    public void setIerakstaDatums(Date ierakstaDatums) {
        this.ierakstaDatums = ierakstaDatums;
    }
}
