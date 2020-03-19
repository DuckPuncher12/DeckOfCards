package com.shanemartin.codetest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import javax.annotation.PostConstruct;

import com.shanemartin.codetest.model.Card;
import com.shanemartin.codetest.model.CardDeck;

import org.springframework.stereotype.Service;

@Service
public class PokerService {

    private CardDeck cardDeck;
    private Random random;

    @PostConstruct
    public void init() {
        cardDeck = new CardDeck();
        random = new Random();
    }

    public boolean shuffle() {
        List<Card> originalDeckList = cardDeck.getDeckOfCards();
        int originalSize = originalDeckList.size();
        List<Card> newDeckList = new ArrayList<Card>();

        for(final ListIterator<Card> i = originalDeckList.listIterator(); i.hasNext();){
            Card shuffleCard = originalDeckList.get(random.nextInt(originalDeckList.size()));
            newDeckList.add(shuffleCard);
            originalDeckList.remove(shuffleCard);
        }

        if(newDeckList.size() == originalSize){
            cardDeck.setDeckOfCards(newDeckList);
            return true;
        }

        return false;
    }

    public Card dealOneCard() {
        List<Card> cards = cardDeck.getDeckOfCards();

        if (cards.size() <= 0) {
            return null;
        }

        Card card = cards.get(random.nextInt(cards.size()));

        cardDeck.removeCard(card);
        return card;
    }

    public boolean reset() {
        cardDeck = new CardDeck();

        if (cardDeck != null) {
            return true;
        } else {
            return false;
        }
    }

}