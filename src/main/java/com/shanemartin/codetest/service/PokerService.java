package com.shanemartin.codetest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import javax.annotation.PostConstruct;

import com.shanemartin.codetest.model.Card;
import com.shanemartin.codetest.model.CardDeck;

import org.springframework.stereotype.Service;

/**
 * Main Service that contains the Business Logic for each requirement (shuffle()
 * and dealOneCard()). Initialized on start of the spring application, then
 * creates a Deck of Cards.
 */
@Service
public class PokerService {

    public CardDeck cardDeck;
    private Random random;

    @PostConstruct
    public void init() {
        cardDeck = new CardDeck(); // Create deck of cards.
        random = new Random(); // Create random generator once.
    }

    /**
     * Shuffle method based on the requirements
     * 
     * Requirements: shuffle() Shuffle returns no value, but results in the cards in
     * the deck being randomly permuted. Please do not use library-provided
     * “shuffle” operations to implement this function. You may use library provided
     * random number generators in your solution if needed.
     * 
     * @return success or failure
     */
    public boolean shuffle() {
        List<Card> originalDeckList = cardDeck.getDeckOfCards();
        if(originalDeckList == null) {//Deck Gave nothing back, failed shuffle rather than NPE
            return false;
        }

        int originalSize = originalDeckList.size();
        if(originalSize <= 0){//Nothing to shuffle, don't waste the time.
            return false;
        }

        List<Card> newDeckList = new ArrayList<Card>();

        // Create Iterator so we always loop through the list based on when shuffle was
        // called.
        for (final ListIterator<Card> i = originalDeckList.listIterator(); i.hasNext();) {
            Card shuffleCard = originalDeckList.get(random.nextInt(originalDeckList.size()));
            newDeckList.add(shuffleCard);
            originalDeckList.remove(shuffleCard); // Remove so we don't get multiples in new list
        }

        cardDeck.setDeckOfCards(newDeckList); // Set new deck to be used in DealOneCard.
        return true;
    }

    /**
     * DealOneCard method based on the requirements
     * 
     * dealOneCard() This function should return one card from the deck to the
     * caller. Specifically, a call to shuffle followed by 52 calls to dealOneCard()
     * should result in the caller being provided all 52 cards of the deck in a
     * random order. If the caller then makes a 53rd call dealOneCard(), no card is
     * dealt.
     * 
     * @return random card from the deck. Null if empty deck
     */
    public Card dealOneCard() {
        List<Card> cards = cardDeck.getDeckOfCards();

        if (cards.size() <= 0) { // Once deck gets to 0, return null
            return null;
        }

        Card card = cards.get(random.nextInt(cards.size())); // Random card from the deck

        cardDeck.removeCard(card);
        return card;
    }

    /**
     * Simple reset method to create a new deck. Purely for running and rerunning
     * the shuffle and dealOneCard methods without having to restart app.
     * 
     * @return success of reset
     */
    public boolean reset() {
        cardDeck = new CardDeck();

        if (cardDeck != null) {
            return true;
        } else {
            return false;
        }
    }

}