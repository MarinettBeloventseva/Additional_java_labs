package com.beloventseva.Java_lab.controllers;

import com.beloventseva.Java_lab.models.Item;
import com.beloventseva.Java_lab.services.RozetkaRarser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    RozetkaRarser rozetkaRarser;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(){return "welcome";}

    @GetMapping("/rozetkaItems")
    String rozetkaItems(Model model){

        List<Item> parseByQuery = rozetkaRarser.parseByQuery();
        List<String> items = rozetkaRarser.transformParseQuery(parseByQuery);

        model.addAttribute("items",items);
        return "rozetkaItems";
    }
}
