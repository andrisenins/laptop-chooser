package lv.andrise.laptopscrapper.model;

import lv.andrise.laptopscrapper.model.repositories.LaptopPriceRepository;
import lv.andrise.laptopscrapper.model.repositories.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PricesDAO {

    @Autowired
    LaptopRepository laptopRepository;

    @Autowired
    LaptopPriceRepository laptopPriceRepository;

    public List<Double> allPricesByLaptopId(Long laptopId) {
        List<Double> laptopPrices = new ArrayList<>();
        laptopPrices.add(laptopRepository.findLaptopById(laptopId).getVecaCena());
        laptopPrices.add(laptopRepository.findLaptopById(laptopId).getAktualaCena());
        laptopPrices.addAll(laptopPriceRepository.findByLaptopId(laptopId).stream().map(LaptopPrice::getAktualaCena).collect(Collectors.toList()));

        return laptopPrices;
    }
}
