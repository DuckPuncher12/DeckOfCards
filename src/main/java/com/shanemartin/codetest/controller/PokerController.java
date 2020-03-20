package com.shanemartin.codetest.controller;

import com.shanemartin.codetest.model.Card;
import com.shanemartin.codetest.service.PokerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Decided the easiest way for a user to "experience" the application was going
 * to be through a REST service. That way, you can run as many times as you'd
 * like, and run from a browser if don't have Postman or equivalent.
 */
@Controller
public class PokerController {

    @Autowired
    PokerService pokerService;

    /**
     * If endpoint shuffle is hit, call the pokerService and run the shuffle method.
     * 
     * @return Happy message on success, sad message on failure.
     */
    @RequestMapping("/shuffle")
    @ResponseBody
    public String shuffleDeck() {
        boolean success = pokerService.shuffle();

        if (!success) {
            return "Ooof, Failed Shuffle!";
        }

        return "Shuffled!";
    }

    /**
     * If endpoint dealcard is hit, call the pokerService and run the dealOneCard
     * method.
     * 
     * @return A card represented as a string or notify user there are no more
     *         cards.
     */
    @RequestMapping("/dealcard")
    @ResponseBody
    public String dealOneCard() {
        Card card = pokerService.dealOneCard();

        if (card == null) {
            return "No more cards";
        }

        return card.toString();
    }

    @RequestMapping("/reset")
    @ResponseBody
    public String resetDeck() {
        boolean success = pokerService.reset();

        if (!success) {
            return "Aww, failed to reset";
        }

        return "Reset Deck!";
    }

}