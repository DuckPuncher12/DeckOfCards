package com.shanemartin.codetest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.shanemartin.codetest.model.Card;
import com.shanemartin.codetest.model.Suit;
import com.shanemartin.codetest.service.PokerService;

@ExtendWith(SpringExtension.class)
public class PokerServiceTest {

    @TestConfiguration
    static class PokerServiceTestContextConfiguration {

        @Bean
        public PokerService employeeService() {
            return new PokerService();
        }
    }

    @Autowired
    private PokerService pokerService;

    @BeforeEach
    public void init() {
        pokerService.reset(); // Cleanup card deck before each test.
    }

    /*****************************************************************
     * Shuffle Tests
     *****************************************************************/
    @Test
    void pokerServiceShuffleSuccess() {
        assertTrue(pokerService.shuffle());
    }

    @Test
    void pokerServiceShuffleSuccessfullyShuffled() {
        List<Card> cardList = new ArrayList<Card>(pokerService.cardDeck.getDeckOfCards()); // Clone list so shuffle
                                                                                           // doesn't affect.
        pokerService.shuffle();
        List<Card> shuffledCardList = new ArrayList<Card>(pokerService.cardDeck.getDeckOfCards());

        assertFalse(cardList.equals(shuffledCardList)); // validate that lists aren't the same order.
        assertTrue(cardList.size() == shuffledCardList.size()); // But have the same size.
    }

    @Test
    void pokerServiceShuffleFail() {
        pokerService.cardDeck.getDeckOfCards().clear();
        assertFalse(pokerService.shuffle());
    }

    @Test
    void pokerServiceShuffleFailwithNull() {
        pokerService.cardDeck.setDeckOfCards(null);
        assertFalse(pokerService.shuffle());
    }

    /*****************************************************************
     * DealOneCard Tests
     *****************************************************************/

    @Test
    void pokerServiceDealOneCardSuccess() {
        Card card = pokerService.dealOneCard();
        assertThat(card, instanceOf(Card.class));
        assertThat(card.getSuit(), instanceOf(Suit.class));
    }

    @Test
    void pokerServiceDealOneCardFail() {
        pokerService.cardDeck.getDeckOfCards().clear();
        assertNull(pokerService.dealOneCard());
    }

    @Test
    void pokerServiceDealOneCardDeckReducesSize() {
        List<Card> cardList = new ArrayList<Card>(pokerService.cardDeck.getDeckOfCards()); // Clone list so dealOneCard
                                                                                           // doesn't affect.
        pokerService.dealOneCard();
        List<Card> newCardList = new ArrayList<Card>(pokerService.cardDeck.getDeckOfCards());

        assertFalse(cardList.size() == newCardList.size()); // Should not be same number
        assertTrue(cardList.size() == (newCardList.size() + 1)); //Should have only reduced size by 1.
    }

    @Test
    void pokerServiceDealOneCardDealDeck() {
        List<Card> cardList = new ArrayList<Card>(pokerService.cardDeck.getDeckOfCards());
        int counter = 0;
        while (counter < cardList.size()) {
            pokerService.dealOneCard();
            counter++;
        }
        assertTrue(counter == cardList.size()); //validate dealt entire deck.
        assertNull(pokerService.dealOneCard()); //Call deal one more time and should get Null;
    }

}