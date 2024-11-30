package com.example.blackjackgameapp;

import androidx.appcompat.app.AppCompatActivity;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayerRoundInformation extends AppCompatActivity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int noOfDecks = 0, betAmount = 0, remainingAmount = 1000, hitCount = 0;
    private int dealerCount = 0, playerCount = 0;
    private boolean dealerWin = false, playerWin = false, draw = false, dealer5card = false, firstDeckExists = false;
    private String name = "";
    private ArrayList<String> playerCardID = new ArrayList<>();
    private ArrayList<String> dealerCardID = new ArrayList<>();

    private ArrayList<String> cardPool = new ArrayList<>();


    public void setNoOfDecks(int x) {
        noOfDecks = x;
    }
    public int getNoOfDecks() {
        return noOfDecks;
    }

    public void setName(String x) {
        name = x;
    }
    public String getName() {
        return name;
    }

    public void setBetAmount(int x) {
        betAmount = x;
    }
    public int getBetAmount() {
        return betAmount;
    }

    public void setRemainingAmount(int x) {
        remainingAmount = x;
    }
    public int getRemainingAmount() {
        return remainingAmount;
    }

    public int getDealerCount() {
        return dealerCount;
    }
    public void addDealerCount(int x) {
        dealerCount += x;
    }
    public void setDealerCount(int x) {
        dealerCount = x;
    }
    public int getPlayerCount() {
        return playerCount;
    }
    public void addPlayerCount(int x) {
        playerCount += x;
    }

    public void setPlayerCount(int x) {
        playerCount = x;
    }

    public void setDealerWin(boolean x) {
        dealerWin = x;
    }
    public boolean getDealerWin() {
        return dealerWin;
    }

    public void setPlayerWin(boolean x) {
        playerWin = x;
    }
    public boolean getPlayerWin() {
        return playerWin;
    }

    public void setDealerFiveCard(boolean x) {
        dealer5card = x;
    }
    public boolean getDealerFiveCard() {
        return dealer5card;
    }

    public void setDraw(boolean x) {
        draw = x;
    }
    public boolean getDraw() {
        return draw;
    }

    public void setFirstDeckExists(boolean x) {
        firstDeckExists = x;
    }
    public boolean getFirstDeckExists() {
        return firstDeckExists;
    }

    public ArrayList<String> getCardPool() {
        return cardPool;
    }
    public void setCardPool(ArrayList<String> cardPool) {
        this.cardPool = cardPool;
    }

    public int getHitCount() {
        return hitCount;
    }
    public void setHitCount(int x) {
        hitCount = x;
    }

    public ArrayList<String> getPlayerCardID() {
        return playerCardID;
    }

    public void setPlayerCardID(ArrayList<String> playerCardID) {
        this.playerCardID = playerCardID;
    }

    public ArrayList<String> getDealerCardID() {
        return dealerCardID;
    }

    public void setDealerCardID(ArrayList<String> dealerCardID) {
        this.dealerCardID = dealerCardID;
    }

    public void initializePlayerCardID(int size) {
        playerCardID = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            playerCardID.add(null);
        }
    }

    public void initializeDealerCardID(int size) {
        dealerCardID = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            dealerCardID.add(null);
        }
    }
}
