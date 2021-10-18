/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Cards.Card;
import Model.BasicStrategy;
import Model.BlackjackPlayer;
import Model.Model;
import Model.Payout;
import static Model.Payout.BLACKJACK;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jason Christian - 21136899
 */
public class ModelTest {

    private ModelTest modelTest;
    private BlackjackPlayer player;

    public ModelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        modelTest = new ModelTest();
        player = new BlackjackPlayer("personTest");

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addBet method, of class BLackjackPlayer.
     */
    @Test
    public void testaddBet() {
        System.out.println("bet");
        double amount = 20.0;
        player.setBet(100.0);
        player.addBet(amount);
        // TODO review the generated test code and remove the default call to fail.
        double result = player.getBet();
        double expResult = 120.0;
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of betIsEmpty method, of class Model.
     */
    @Test
    public void testBetIsEmpty() {
        System.out.println("betIsEmpty");
        Model instance = new Model();
        player.setBet(0);
        // TODO review the generated test code and remove the default call to fail.
        boolean expResult = true;
        boolean result = instance.betIsEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of betIsSufficient method, of class Model.
     */
    @Test
    public void testBetIsSufficient() {
        System.out.println("betIsSufficient");
        Model instance = new Model();
        String name = "testPerson";
        int deckAmount = 30;
        int minimumBet = 60;
        int dealerBehaviour = 1;
        Object[] settings = {name,
            deckAmount, minimumBet, dealerBehaviour};
        instance.loadSettings(settings);
        player.setBet(50);
        // TODO review the generated test code and remove the default call to fail.
        boolean expResult = false;
        boolean result = instance.betIsSufficient();
        assertEquals(expResult, result);
    }

    /**
     * Test of canDoubleDown method, of class Model.
     */
    @Test
    public void testCanDoubleDown() {
        System.out.println("canDoubleDown");
        Model instance = new Model();
        String name = "testPerson";
        int deckAmount = 30;
        int minimumBet = 60;
        int dealerBehaviour = 1;
        Object[] settings = {name,
            deckAmount, minimumBet, dealerBehaviour};
        instance.loadSettings(settings);
        player.setBankroll(100);
        player.setBet(50);
        // TODO review the generated test code and remove the default call to fail.
        boolean expResult = true;
        boolean result = instance.canDoubleDown();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateRunningCount method, of class Model.
     */
    @Test
    public void testUpdateRunningCount() {
        System.out.println("updateRunningCount");
        int rank = 10;
        Model instance = new Model();
        instance.resetRunningCount();
        instance.updateRunningCount(rank);
        // TODO review the generated test code and remove the default call to fail.
        int expResult = -1;
        int result = instance.getRunningCount();
        System.out.println("------running count--------");
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of outOfChips method, of class Model.
     */
    @Test
    public void testOutOfChips() {
        System.out.println("outOfChips");
        Model instance = new Model();
        String name = "testPerson";
        int deckAmount = 30;
        int minimumBet = 60;
        int dealerBehaviour = 1;
        Object[] settings = {name,
            deckAmount, minimumBet, dealerBehaviour};
        instance.loadSettings(settings);
        player.setBankroll(100);
        player.setBet(50);
        // TODO review the generated test code and remove the default call to fail.
        boolean expResult = false;
        boolean result = instance.outOfChips();
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of playerHasBlackjack method, of class Model.
//     */
//    @Test
//    public void testPlayerHasBlackjack() {
//        System.out.println("playerHasBlackjack");
//        Model instance = new Model();
//        boolean expResult = false;
//        boolean result = instance.playerHasBlackjack();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of playerHit method, of class Model.
//     */
//    @Test
//    public void testPlayerHit() {
//        System.out.println("playerHit");
//        Card card = null;
//        Model instance = new Model();
//        instance.playerHit(card);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of resetBet method, of class Model.
//     */
//    @Test
//    public void testResetBet() {
//        System.out.println("resetBet");
//        Model instance = new Model();
//        instance.resetBet();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of resetHand method, of class Model.
//     */
//    @Test
//    public void testResetHand() {
//        System.out.println("resetHand");
//        Model instance = new Model();
//        instance.resetHand();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of restartGame method, of class Model.
//     */
//    @Test
//    public void testRestartGame() {
//        System.out.println("restartGame");
//        Model instance = new Model();
//        instance.restartGame();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of returnBet method, of class Model.
//     */
//    @Test
//    public void testReturnBet() {
//        System.out.println("returnBet");
//        Model instance = new Model();
//        instance.returnBet();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of shuffleDeck method, of class Model.
//     */
//    @Test
//    public void testShuffleDeck() {
//        System.out.println("shuffleDeck");
//        Model instance = new Model();
//        instance.shuffleDeck();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of wentOver method, of class Model.
//     */
//    @Test
//    public void testWentOver() {
//        System.out.println("wentOver");
//        Model instance = new Model();
//        boolean expResult = false;
//        boolean result = instance.wentOver();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of playerWon method, of class Model.
//     */
//    @Test
//    public void testPlayerWon() {
//        System.out.println("playerWon");
//        Model instance = new Model();
//        boolean expResult = false;
//        boolean result = instance.playerWon();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of playerBet method, of class Model.
//     */
//    @Test
//    public void testPlayerBet() {
//        System.out.println("playerBet");
//        Model instance = new Model();
//        double expResult = 0.0;
//        double result = instance.playerBet();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of playerChips method, of class Model.
//     */
//    @Test
//    public void testPlayerChips() {
//        System.out.println("playerChips");
//        Model instance = new Model();
//        double expResult = 0.0;
//        double result = instance.playerChips();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of dealerCardNames method, of class Model.
//     */
//    @Test
//    public void testDealerCardNames() {
//        System.out.println("dealerCardNames");
//        Model instance = new Model();
//        String[] expResult = null;
//        String[] result = instance.dealerCardNames();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of dealerFrontCard method, of class Model.
//     */
//    @Test
//    public void testDealerFrontCard() {
//        System.out.println("dealerFrontCard");
//        Model instance = new Model();
//        int expResult = 0;
//        int result = instance.dealerFrontCard();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of dealerHandValue method, of class Model.
//     */
//    @Test
//    public void testDealerHandValue() {
//        System.out.println("dealerHandValue");
//        Model instance = new Model();
//        int expResult = 0;
//        int result = instance.dealerHandValue();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of dealerHasSoftHand method, of class Model.
//     */
//    @Test
//    public void testDealerHasSoftHand() {
//        System.out.println("dealerHasSoftHand");
//        Model instance = new Model();
//        boolean expResult = false;
//        boolean result = instance.dealerHasSoftHand();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of holeCard method, of class Model.
//     */
//    @Test
//    public void testHoleCard() {
//        System.out.println("holeCard");
//        Model instance = new Model();
//        Card expResult = null;
//        Card result = instance.holeCard();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of playerCardNames method, of class Model.
//     */
//    @Test
//    public void testPlayerCardNames() {
//        System.out.println("playerCardNames");
//        Model instance = new Model();
//        String[] expResult = null;
//        String[] result = instance.playerCardNames();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of initialCards method, of class Model.
//     */
//    @Test
//    public void testInitialCards() {
//        System.out.println("initialCards");
//        Model instance = new Model();
//        String[] expResult = null;
//        String[] result = instance.initialCards();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of playerHandValue method, of class Model.
//     */
//    @Test
//    public void testPlayerHandValue() {
//        System.out.println("playerHandValue");
//        Model instance = new Model();
//        int expResult = 0;
//        int result = instance.playerHandValue();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of playerName method, of class Model.
//     */
//    @Test
//    public void testPlayerName() {
//        System.out.println("playerName");
//        Model instance = new Model();
//        String expResult = "";
//        String result = instance.playerName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
