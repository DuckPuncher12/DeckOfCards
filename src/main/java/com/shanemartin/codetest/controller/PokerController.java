package com.shanemartin.codetest.controller;

import com.shanemartin.codetest.model.Card;
import com.shanemartin.codetest.service.PokerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PokerController {

    @Autowired
    PokerService pokerService;	

    @RequestMapping("/shuffle")
    @ResponseBody
    public String shuffleDeck() {
        boolean success = pokerService.shuffle();
        
        if(!success) {
            return "Ooof, Failed Shuffle!";
        }

    	return "Shuffled!"; 
    }
    
    @RequestMapping("/dealcard")
    @ResponseBody
    public String dealOneCard() {
        Card card = pokerService.dealOneCard();
        
        if(card == null){
            return "No more cards";
        }

    	return card.toString(); 
    }

    @RequestMapping("/reset")
    @ResponseBody
    public String resetDeck() {
        boolean success = pokerService.reset();
        
        if(!success) {
            return "Aww, failed to reset";
        }

    	return "Reset Deck!"; 
    }

}