package com.beloventseva.Java_lab.services;

import com.beloventseva.Java_lab.models.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RozetkaRarser {
    private static final String ROZETKA_HEADER_URL = "https://rozetka.com.ua/";
    private static final String ROZETKA_CATALOG = "ua/mobile-phones/c80003/";
    private static final int ROZETKA_MAX_TIMEOUT = 30000;

    public List<Item> parseByQuery() {
        List<Item> items = new ArrayList<>();

        Document document;
        String fullUrl = "";
        Elements itemsName;
        Elements itemsPrice;

            try {
                fullUrl = ROZETKA_HEADER_URL + ROZETKA_CATALOG;
                document = Jsoup.connect(fullUrl).timeout(ROZETKA_MAX_TIMEOUT).get();
            } catch (IOException e) {
                System.out.println("Problems with connecting to " + fullUrl);
                throw new RuntimeException(e);
            }
            System.out.println(ROZETKA_HEADER_URL + ROZETKA_CATALOG);


            itemsName = document.select(".goods-tile__title");
            itemsPrice = document.select(".goods-tile__price-value");

        Element itemName;
        Element itemPrice;
        String name;
        String price;

        for (int i=0; i<30; i++){
            itemName = itemsName.get(i);
            name = itemName.text();

            itemPrice = itemsPrice.get(i);
            price = itemPrice.text();

            Item item = new Item(name, price);
            items.add(item);
        }
        return items;
    }

    public List<String> transformParseQuery(List<Item> items) {
        List<String> results = new ArrayList<>();

        for (int i = 0, itemsSize = items.size(); i < itemsSize; i++) {
            Item item = items.get(i);
            String itemName = item.getName();
            String itemPrice = item.getPrice();
            String result = "\n Name: " + itemName + " | Price: " + itemPrice;
            results.add(result);
        }
        return results;
    }

}
