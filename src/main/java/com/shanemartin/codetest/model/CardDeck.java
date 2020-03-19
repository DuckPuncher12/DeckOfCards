package com.shanemartin.codetest.model;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private List<Card> deckOfCards = new ArrayList<Card>();

    public CardDeck() {
        // Populate full card deck (should be 52 from default but leaves the ability to
        // generate a full deck of whatever values are in the Enum classes)
        for (Suit s : Suit.values()) {
            for (CardValue cv : CardValue.values()) {
                deckOfCards.add(new Card(s, cv));
            }
        }
    }

    public List<Card> getDeckOfCards() {
        return deckOfCards;
    }

    public void setDeckOfCards(List<Card> deck) {
        this.deckOfCards = deck;
    }

    public List<Card> removeCard(Card card) {
        if (deckOfCards.contains(card)) {
            deckOfCards.remove(card);
        } else {
            return null; //will handle elsewhere but either deck is empty or card doesn't exist
        }

        return deckOfCards;
    }

}