package lv.andrise.laptopscrapper.service;

import lv.andrise.laptopscrapper.model.Laptop;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andris on 15.24.8.
 */
@SuppressWarnings("CanBeFinal")
@Service
public class Scrap {

    private final Logger log = LoggerFactory.getLogger(Scrap.class);

    private final String regex = "^/datortehnika/portativiedatori/.*";
    private final String regex2 = "(^/.*ladetaji.*|^/.*dzesetaji.*|^/.*garantija.*|^/.*sledzene.*|^/.*citi.*|^/.*somas.*|^/.*baterija.*)";
    private final String regex3 = "^/datortehnika/portativiedatori/\\d+";
    private final String regex4 = "<span>Portatīvie datori (\\d+).*";
//    String moneyRegex = "\\d+\\.\\d+";
private final String moneyRegex = "€";
    private final Pattern pattern = Pattern.compile(regex);
    private final Pattern unimportantPageRegex = Pattern.compile(regex2);
    private final Pattern pageNumberRegex = Pattern.compile(regex3);
    private final Pattern pcQ = Pattern.compile(regex4);
    private final Pattern priceRegex = Pattern.compile(moneyRegex);

    public Set<Laptop> getLaptops() {
        Set<Laptop> laptopList = new HashSet<>();
        Set<String> urls = scrapPageList();
        urls.parallelStream().forEach(s -> laptopList.add(scrapPage(s)));
        return laptopList;
    }

    private Laptop scrapPage(String url) {
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
            Elements select = body.select("div.discount-box, .discount-box-lat");
            if (!select.isEmpty()) {
                String[] newPrice = priceRegex.split(select.select("strong.new-price").text());
                if(newPrice.length > 0) {
                    newPriceE = Double.parseDouble(newPrice[0]);
                    laptop.setAktualaCena(newPriceE);
                }
                String[] oldPrice = priceRegex.split(select.select("strong.old-price").text());
                if(oldPrice.length > 0) {
                    oldPriceE = Double.parseDouble(oldPrice[0]);
                    laptop.setVecaCena(oldPriceE);
                }
            }
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
