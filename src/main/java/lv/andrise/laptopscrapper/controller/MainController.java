package lv.andrise.laptopscrapper.controller;

import lv.andrise.laptopscrapper.model.Laptop;
import lv.andrise.laptopscrapper.model.repositories.LaptopRepository;
import lv.andrise.laptopscrapper.service.Scrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Set;

/**
 * Created by andris on 15.23.8.
 */
@Controller
public class MainController {

    @Autowired
    private Scrap scrap;

    @Autowired
    private LaptopRepository laptopRepository;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Set<Laptop> doStuff() throws IOException {
        return scrap.getLaptops();
    }

    @RequestMapping(value = "/scrap/{laptop}", method = RequestMethod.GET)
    @ResponseBody
    public Laptop scrapPageSinglePage(@PathVariable("laptop") String laptop) throws IOException {
        Laptop laptop1 = scrap.scrapPage("http://www.1a.lv/datortehnika/portativiedatori/" + laptop);
        laptopRepository.save(laptop1);
        return laptop1;

    }
}
