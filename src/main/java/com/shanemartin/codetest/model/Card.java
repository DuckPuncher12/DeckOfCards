package com.shanemartin.codetest.model;

import java.util.Random;

public class Card {

    private Suit suit;

    private CardValue value;

    /**
     * If creating a non specific card, create a random card. This would only be
     * used if adding more functionality beyond scope of this project...but I built
     * misreading dealOneCard logic and felt like it made sense logic wise as
     * something the default constructor might have if you wanted to create a card
     * but not sure what kind.
     */
    public Card() {
        this.value = RandomCardValues();
        this.suit = RandomSuit();
    }

    /**
     * Create a specific card with a Suit and card value
     * 
     * @param suitType  of the 4 Suit Types (HEART, DIAMOND, SPADE, CLUB)
     * @param cardValue card number -- between 2 and 11
     */
    public Card(Suit suitType, CardValue cardValue) {
        this.suit = suitType;
        this.value = cardValue;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public CardValue getValue() {
        return value;
    }

    public void setValue(CardValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Card: [Suit = " + suit + ", Value = " + value + "]";
    }

    private CardValue RandomCardValues() {
        Random random = new Random();
        return CardValue.values()[random.nextInt(CardValue.values().length)];
    }

    private Suit RandomSuit() {
        Random random = new Random();
        return Suit.values()[random.nextInt(Suit.values().length)];
    }
}
