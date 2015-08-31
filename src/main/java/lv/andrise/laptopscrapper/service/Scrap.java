package lv.andrise.laptopscrapper.service;

import lv.andrise.laptopscrapper.model.Laptop;
import lv.andrise.laptopscrapper.model.repositories.LaptopRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andris on 15.24.8.
 */
@Service
public class Scrap {

    @Autowired
    private LaptopRepository laptopRepository;

    private final Logger log = LoggerFactory.getLogger(Scrap.class);

    private final String regex = "^/datortehnika/portativiedatori/.*";
    private final String regex2 = "(^/.*ladetaji.*|^/.*dzesetaji.*|^/.*garantija.*|^/.*sledzene.*|^/.*citi.*|^/.*somas.*|^/.*baterija.*)";
    private final String regex3 = "^/datortehnika/portativiedatori/\\d+";
    private final String regex4 = "<span>Portatīvie datori (\\d+).*";
    private final String moneyRegex = "€";  // Regex for removing euro sign from string
    private final Pattern pattern = Pattern.compile(regex);
    private final Pattern unimportantPageRegex = Pattern.compile(regex2);
    private final Pattern pageNumberRegex = Pattern.compile(regex3);
    private final Pattern pcQ = Pattern.compile(regex4);
    private final Pattern priceRegex = Pattern.compile(moneyRegex);

    public Set<Laptop> getLaptops() {
        Set<Laptop> laptopList = new HashSet<>();
        Set<String> urls = scrapPageList();
        urls.parallelStream().forEach(s -> laptopList.add(scrapPage(s)));
        laptopRepository.save(laptopList);
        return laptopList;
    }

    public Laptop scrapPage(String url) {
        Laptop laptop = new Laptop();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error("Scrapping {} failed!", url, e);
            scrapPage(url);
        }
        if (doc != null) {
            double newPriceE;
            double oldPriceE;
            Element body = doc.body();
            laptop.setNosaukums(body.select("div.product-title-container h2").text());
            laptop.setUrl(url);
            laptop.setIerakstaDatums(new Date());
            Elements select = body.select("div.discount-box, .discount-box-lat");
            if (!select.isEmpty()) {
                String[] newPrice = priceRegex.split(select.select("strong.new-price").text());
                if (newPrice.length > 0) {
                    newPriceE = Double.parseDouble(newPrice[0]);
                    laptop.setAktualaCena(newPriceE);
                }
                String[] oldPrice = priceRegex.split(select.select("strong.old-price").text());
                if (oldPrice.length > 0) {
                    oldPriceE = Double.parseDouble(oldPrice[0]);
                    laptop.setVecaCena(oldPriceE);
                }
                log.info("Discount laptop {}  added to the list with new price {} and old price {}", laptop.getNosaukums(), laptop.getAktualaCena(), laptop.getVecaCena());
            }
            if (select.isEmpty()) {
                Elements selectNoDiscount = body.select("div.price-box, .price-box-lat");
                if (!selectNoDiscount.isEmpty()) {
                    newPriceE = Double.parseDouble(selectNoDiscount.select("span").text());
                    laptop.setAktualaCena(newPriceE);
                }
                log.info("Laptop {}  added to the list with new price {} and old price {}", laptop.getNosaukums(), laptop.getAktualaCena(), laptop.getVecaCena());
            }
            Elements name = body.select("div#tab1, table.characteristics-table");

            Elements tableRows = name.get(1).select("tr");
//            tableRows.next().child(1).text();
            String[] rows = tableRows.toString().split("</tr>");
            int rowCounter = 1;
            for (String row : rows) {
                String[] splitColumns = row.split("</td>");
                if (splitColumns.length > 1) {
//                    System.out.println(splitColumns[1].substring(8));
                    switch (rowCounter) {
                        case 2:
                            laptop.setRazotajs(splitColumns[1].substring(8).trim());
                            break;
                        case 3:
                            laptop.setDatoraTips(splitColumns[1].substring(8).trim());
                            break;
                        case 5:
                            laptop.setEkranaIzmers(splitColumns[1].substring(8).trim().replace("\\", ""));
                            break;
                        case 6:
                            laptop.setEkranaTips(splitColumns[1].substring(8).trim());
                            break;
                        case 7:
                            laptop.setIPSEkrans(splitColumns[1].substring(8).trim());
                            break;
                        case 8:
                            laptop.setMaksIzskirtspeja(splitColumns[1].substring(8).trim());
                            break;
                        case 10:
                            laptop.setProcesoraSaime(splitColumns[1].substring(8).trim());
                            break;
                        case 12:
                            laptop.setProcesoraPaaudze(splitColumns[1].substring(8).trim());
                            break;
                        case 13:
                            laptop.setProcesoraKodoluSkaits(splitColumns[1].substring(8).trim());
                            break;
                        case 14:
                            laptop.setCache(splitColumns[1].substring(8).trim());
                            break;
                        case 15:
                            laptop.setChipset(splitColumns[1].substring(8).trim());
                            break;
                        case 17:
                            laptop.setUzstaditaisRam(splitColumns[1].substring(8).trim());
                            break;
                        case 18:
                            laptop.setMaxRam(splitColumns[1].substring(8).trim());
                            break;
                        case 19:
                            laptop.setRamType(splitColumns[1].substring(8).trim());
                            break;
                        case 20: if(splitColumns[1].substring(8).trim().equals("Nav") || splitColumns[1].substring(8).trim().equals("Nav informācijas"))
                            laptop.setRamSlots(0);
                            else
                            laptop.setRamSlots(Integer.parseInt(splitColumns[1].substring(8).trim()));
                            break;
                        case 22:
                            laptop.setHDDSize(splitColumns[1].substring(8).trim());
                            break;
                        case 23:
                            laptop.setHDDSpeed(splitColumns[1].substring(8).trim());
                            break;
                        case 24:
                            laptop.setSSDSize(splitColumns[1].substring(8).trim());
                            break;
                        case 26:
                            laptop.setVideocardType(splitColumns[1].substring(8).trim());
                            break;
                        case 27:
                            laptop.setVideocard(splitColumns[1].substring(8).trim());
                            break;
                        case 28:
                            laptop.setVideocardMemory(splitColumns[1].substring(8).trim());
                            break;
                        case 30:
                            laptop.setWifi(splitColumns[1].substring(8).trim());
                            break;
                        case 31:
                            if (splitColumns[1].substring(8).trim().equals("Jā"))
                                laptop.setG3(true);
                            break;
                        case 32:
                            if (splitColumns[1].substring(8).trim().equals("Jā"))
                                laptop.setG4(true);
                            break;
                        case 33:
                            if (splitColumns[1].substring(8).trim().equals("Jā (4.0)"))
                                laptop.setBluetooth(true);
                            break;
                        case 34:
                            if (splitColumns[1].substring(8).trim().equals("Jā"))
                                laptop.setNFC(true);
                            break;
                        case 35:
                            if (splitColumns[1].substring(8).trim().equals("Jā"))
                                laptop.setWiDi(true);
                            break;
                        case 37:
                            laptop.setUSBPorti(splitColumns[1].substring(8).trim().replace("<br>", ", "));
                            break;
                        case 38:
                            if (splitColumns[1].substring(8).trim().equals("Jā"))
                                laptop.setHDMI(true);
                            break;
                        case 39:
                            laptop.setInterfeiss(splitColumns[1].substring(8).trim().replace("<br>", ", "));
                            break;
                        case 41:
                            if (splitColumns[1].substring(8).trim().equals("Nav"))
                                laptop.setCDRom(false);
                            else {
                                laptop.setCDRom(true);
                            }
                            break;
                        case 42:
                            laptop.setAudio(splitColumns[1].substring(8).trim());
                            break;
                        case 43:
                            laptop.setTiklaKarte(splitColumns[1].substring(8).trim());
                            break;
                        case 44:
                            if (splitColumns[1].substring(8).trim().equals("Nav"))
                                laptop.setBuiltInWebcam(false);
                            else {
                                laptop.setBuiltInWebcam(true);
                            }
                            break;
                        case 45:
                            if (splitColumns[1].substring(8).trim().equals("Nav"))
                                laptop.setBuiltInMic(false);
                            else {
                                laptop.setBuiltInMic(true);
                            }
                            break;
                        case 46:
                            laptop.setBuiltInCardReader(splitColumns[1].substring(8).trim());
                            break;
                        case 48:
                            laptop.setKlaviaturasValodas(splitColumns[1].substring(8).trim());
                            break;
                        case 49:
                            if (splitColumns[1].substring(8).trim().equals("Jā"))
                                laptop.setKlaviaturasApgaismojums(true);
                            else {
                                laptop.setKlaviaturasApgaismojums(false);
                            }
                            break;
                        case 50:
                            if (splitColumns[1].substring(8).trim().equals("Nē"))
                                laptop.setMitrumdrosaKlaviatura(false);
                            else {
                                laptop.setMitrumdrosaKlaviatura(true);
                            }
                            break;
                        case 51:
                            laptop.setKorpusaMaterials(splitColumns[1].substring(8).trim().replace("<br>", ", "));
                            break;
                        case 52:
                            laptop.setKorpusaKrasa(splitColumns[1].substring(8).trim().replace("<br>", ", "));
                            break;
                        case 53:
                            laptop.setIzmeri(splitColumns[1].substring(8).trim().replace("<br>", ", "));
                            break;
                        case 54:
                            laptop.setSvars(splitColumns[1].substring(8).trim().replace("<br>", ", "));
                            break;
                        case 56:
                            laptop.setOperetajSistema(splitColumns[1].substring(8).trim().replace("<br>", ", "));
                            break;
                        case 58:
                            laptop.setStandartaBaterija(splitColumns[1].substring(8).trim().replace("<br>", ", "));
                            break;
                        case 59:
                            laptop.setBaterijasDarbibasLaiks(splitColumns[1].substring(8).trim().replace("<br>", ", "));
                            break;
                        case 60:
                            laptop.setDrosiba(splitColumns[1].substring(8).trim().replace("<br>", ", "));
                            break;
                        case 62:
                            laptop.setIpashasatzimes(splitColumns[1].substring(8).trim().replace("<br>", ", "));
                            break;
                    }
                }
                rowCounter++;
            }

//            System.out.println(url);
            // TODO: Add other field handling
        }
        return laptop;
    }

    private Set<String> scrapPageList() {
        log.debug("Starting scrapping!");
        String url = "http://www.1a.lv/datortehnika/portativiedatori/";
        Set<String> urls = new HashSet<>();

        return getUrls(url, urls);
    }

    private Set<String> getUrls(String url, Set<String> urls) {
        log.debug("getting URLS from {}", url);
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error("Connection to {} refused.", url);
            log.debug("Retrying connection to {}.", url);
            urls.addAll(getUrls(url, urls));
        }
        if (url.equals("http://www.1a.lv/datortehnika/portativiedatori/") && doc != null) {
            Elements pcQuantity = doc.select("div .paging-text span");
            if (!pcQuantity.isEmpty()) {
                Integer quantity = allProductsOfThisType(pcQuantity);
                Set<String> pages = pagesToScan(quantity);
                pages.parallelStream().forEach(s -> urls.addAll(getUrls(s, urls)));
            }
        }
        if (doc != null) {
            Elements pc = doc.select("a");
            if (!pc.isEmpty()) {
                for (Element element : pc) {
                    Matcher href = pattern.matcher(element.attr("href"));
                    Matcher doesntMatch = unimportantPageRegex.matcher(element.attr("href"));
                    Matcher href1 = pageNumberRegex.matcher(element.attr("href"));
                    if (href.matches() && !doesntMatch.matches() && !href1.matches()) {
                        urls.add("http://www.1a.lv" + element.attr("href"));
                    }
                }
            }
        }
        return urls;
    }

    private Set<String> pagesToScan(Integer quantity) {
        Set<String> urls = new HashSet<>();
        int pages = 0;
        if (quantity % 12 != 0) {
            pages = quantity / 12 + 1;
        }
        else {
            pages = quantity / 12 ;
        }
        for (int i = 1; i <= pages; i++) {
            urls.add("http://www.1a.lv/datortehnika/portativiedatori/" + (i + 1));
        }
        return urls;
    }

    private Integer allProductsOfThisType(Elements pcQuantity) {
        Set<Integer> splitMessage = new HashSet<>();
        Integer max = 0;
        for (Element element : pcQuantity) {
            Matcher matcher = pcQ.matcher(element.toString());
            if (!matcher.matches()) {
                String[] split = element.text().replaceAll("\\D+", " ").split(" ");
                for (String s : split) {
                    splitMessage.add(Integer.parseInt(s));
                }
                max = Collections.max(splitMessage);
            }
        }
        return max;
    }
}
