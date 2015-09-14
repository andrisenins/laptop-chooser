package lv.andrise.laptopscrapper.controller;

import lv.andrise.laptopscrapper.model.Laptop;
import lv.andrise.laptopscrapper.model.PricesDAO;
import lv.andrise.laptopscrapper.model.repositories.LaptopRepository;
import lv.andrise.laptopscrapper.service.Scrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Andris on 15.23.8.
 */
@RestController
public class MainController {

    @Autowired
    private Scrap scrap;

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private PricesDAO pricesDAO;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Set<Laptop> doStuff() throws IOException {
        return scrap.getLaptops();
    }

    @RequestMapping(value = "/scrap/{laptop}", method = RequestMethod.GET)
    public Laptop scrapPageSinglePage(@PathVariable("laptop") String laptop) throws IOException {
        Laptop laptop1 = scrap.scrapPage("http://www.1a.lv/datortehnika/portativiedatori/" + laptop);
        laptopRepository.save(laptop1);
        return laptop1;
    }

    @RequestMapping(value = "/laptops/{razotajs}", method = RequestMethod.GET)
    public List<Laptop> findLaptopsByRazotajs(@PathVariable("razotajs") String razotajs) {
        return laptopRepository.findLaptopsByRazotajs(razotajs);
    }

    @RequestMapping(value = "/laptops/{cena}/{cena2}", method = RequestMethod.GET)
    public List<Laptop> findByaktualaCena(@PathVariable("cena") Double cena, @PathVariable("cena2") Double cena2) {
        return laptopRepository.findByaktualaCenaBetween(cena, cena2);
    }

//    @RequestMapping(value = "/laptops/{ekranaIzmers}/{maksIzskirtspeja}/{procesoraSaime}/{procesoraPaaudze}", method = RequestMethod.GET)
//    public List<Laptop> findByEkranaIzmersAndOrMaksIzskirtspejaAndOrProcesoraSaimeAndOrProcesoraPaaudze(
//            @PathVariable ("ekranaIzmers") String ekranaIzmers, @PathVariable ("maksIzskirtspeja") String maksIzskirtspeja,
//            @PathVariable ("procesoraSaime") String procesoraSaime, @PathVariable ("procesoraPaaudze") String procesoraPaaudze) {
//
//        return laptopRepository.findByekranaIzmersAndmaksIzskirtspejaAndprocesoraSaimeAndprocesoraPaaudze(ekranaIzmers, maksIzskirtspeja, procesoraSaime, procesoraPaaudze);
//    }

    @RequestMapping(value = "/laptopspricelist/{id}", method = RequestMethod.GET)
    public List<Double> findLaptopPricesById(@PathVariable("id") Long id) {
        return pricesDAO.allPricesByLaptopId(id);
    }
}
