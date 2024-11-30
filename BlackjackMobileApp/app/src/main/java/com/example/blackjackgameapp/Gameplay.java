package com.example.blackjackgameapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Gameplay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gameplay);

        PlayerRoundInformation round1 = (PlayerRoundInformation) getIntent().getSerializableExtra("roundInfo");

        round1.setPlayerCount(0);
        round1.setDealerCount(0);
        round1.setDealerWin(false);
        round1.setPlayerWin(false);
        round1.setDraw(false);
        round1.setDealerFiveCard(false);
        round1.setHitCount(0);
        round1.initializePlayerCardID(5);
        round1.initializeDealerCardID(5);

        if (round1.getCardPool().size() < 10) {
            round1.setFirstDeckExists(false);
        }

        String[] cardTypes = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        if (!(round1.getFirstDeckExists())) {
            ArrayList<String> emptyDeck = new ArrayList<>();
            round1.setCardPool(emptyDeck);
            for (int i = 0; i < round1.getNoOfDecks(); i++) {
                for (String cardType : cardTypes) {
                    for (int j = 0; j < 4; j++) {
                        round1.getCardPool().add(cardType);
                    }
                }
            }
            round1.setFirstDeckExists(true);
        }

        ImageView imageCardP1 = findViewById(R.id.cardP1);
        ImageView imageCardP2 = findViewById(R.id.cardP2);
        ImageView imageCardP3 = findViewById(R.id.cardP3);
        ImageView imageCardP4 = findViewById(R.id.cardP4);
        ImageView imageCardP5 = findViewById(R.id.cardP5);
        ImageView imageCardD1 = findViewById(R.id.cardD1);
        ImageView imageCardD2 = findViewById(R.id.cardD2);
        ImageView imageCardD3 = findViewById(R.id.cardD3);
        ImageView imageCardD4 = findViewById(R.id.cardD4);
        ImageView imageCardD5 = findViewById(R.id.cardD5);

        TextView playerNameTextView = findViewById(R.id.playerName);
        playerNameTextView.setText(String.valueOf(round1.getName()));

        TextView betAmountTextView = findViewById(R.id.playerBet);
        betAmountTextView.setText(String.valueOf(round1.getBetAmount()));

        TextView cardsLeftTextView = findViewById(R.id.cardsLeft);
        cardsLeftTextView.setText(String.valueOf(round1.getCardPool().size()-1));

        TextView playerCountTextView = findViewById(R.id.playerCount);
        TextView dealerCountTextView = findViewById(R.id.dealerCount);

        Button btnHit = findViewById(R.id.btnHit);
        btnHit.setOnClickListener(v -> {
            if (round1.getPlayerCount() <= 21) {
                if (round1.getHitCount() == 0) {
                    drawCard(round1.getCardPool(), round1, cardsLeftTextView, imageCardP3, true, 2, playerCountTextView, dealerCountTextView);
                    round1.setHitCount(1);
                    if (round1.getPlayerCount()>21) {
                        round1.setDealerWin(true);
                        gameEnder(round1);
                    }
                    else if (round1.getPlayerCount()==21) {
                        round1.setPlayerWin(true);
                        gameEnder(round1);
                    }

                // 4th Card
                } else if (round1.getHitCount() == 1) {
                    drawCard(round1.getCardPool(), round1, cardsLeftTextView, imageCardP4, true, 3, playerCountTextView, dealerCountTextView);
                    round1.setHitCount(2);
                    if (round1.getPlayerCount()>21) {
                        round1.setDealerWin(true);
                        gameEnder(round1);
                    }
                    else if (round1.getPlayerCount()==21) {
                        round1.setPlayerWin(true);
                        gameEnder(round1);
                    }
                // 5th Card
                } else if (round1.getHitCount() == 2) {
                    drawCard(round1.getCardPool(), round1, cardsLeftTextView, imageCardP5, true, 4, playerCountTextView, dealerCountTextView);
                    round1.setHitCount(3);
                    if (round1.getPlayerCount()<=21) {
                        round1.setPlayerWin(true);
                        gameEnder(round1);
                    }
                    else{
                        round1.setDealerWin(true);
                        gameEnder(round1);
                    }
                }
            }
            else{
                round1.setPlayerWin(true);
                gameEnder(round1);
            }
        });

        // Stay Button
        Button btnStay = findViewById(R.id.btnStay);
        btnStay.setOnClickListener(v -> {
            // Dealer 1st card
            drawCard(round1.getCardPool(), round1, cardsLeftTextView, imageCardD1,false, 0, playerCountTextView, dealerCountTextView); // Cannot loop because imageview are unique
            if (round1.getDealerCount() < 17)
                // Dealer 3rd card
                drawCard(round1.getCardPool(), round1, cardsLeftTextView, imageCardD3,false, 2, playerCountTextView, dealerCountTextView);
            if (round1.getDealerCount() < 17)
                // Dealer 4rd card
                drawCard(round1.getCardPool(), round1, cardsLeftTextView, imageCardD4,false, 3, playerCountTextView, dealerCountTextView);
            if (round1.getDealerCount() < 17) {
                // Dealer 5rd card
                drawCard(round1.getCardPool(), round1, cardsLeftTextView, imageCardD5,false, 4, playerCountTextView, dealerCountTextView);
                gameEnder(round1);
                round1.setDealerFiveCard(true);
            }
            checkGameEnd(round1);
        });

        gameStarter(round1.getCardPool(), round1, cardsLeftTextView, imageCardP1, imageCardD2, imageCardP2, playerCountTextView, dealerCountTextView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnReplay), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public static void randomizeCard(PlayerRoundInformation round1, ArrayList<String> cardPool, TextView cardsLeft, boolean isPlayer, int cardNumber) {
        Random r = new Random();
        int randomIndex = r.nextInt(cardPool.size());
        String cardDrawn = cardPool.get(randomIndex);
        if (isPlayer) {
            round1.getPlayerCardID().set(cardNumber, cardDrawn);
        } else{
            round1.getDealerCardID().set(cardNumber, cardDrawn);
        }

        cardPool.remove(randomIndex);
        cardsLeft.setText(String.valueOf(cardPool.size()-1));
    }

    public static void displayCardImage(String cardLabel, ImageView card) {
        switch (cardLabel) {
            case "A":
                card.setImageResource(R.drawable.card_a);
                break;
            case "2":
                card.setImageResource(R.drawable.card_2);
                break;
            case "3":
                card.setImageResource(R.drawable.card_3);
                break;
            case "4":
                card.setImageResource(R.drawable.card_4);
                break;
            case "5":
                card.setImageResource(R.drawable.card_5);
                break;
            case "6":
                card.setImageResource(R.drawable.card_6);
                break;
            case "7":
                card.setImageResource(R.drawable.card_7);
                break;
            case "8":
                card.setImageResource(R.drawable.card_8);
                break;
            case "9":
                card.setImageResource(R.drawable.card_9);
                break;
            case "10":
                card.setImageResource(R.drawable.card_10);
                break;
            case "J":
                card.setImageResource(R.drawable.card_j);
                break;
            case "Q":
                card.setImageResource(R.drawable.card_q);
                break;
            case "K":
                card.setImageResource(R.drawable.card_k);
                break;
        }
    }

    public static int findCardCount(String cardLabel) {
        int count = 0;
        switch (cardLabel) {
            case "A":
                count = 1;
                break;
            case "2":
                count = 2;
                break;
            case "3":
                count = 3;
                break;
            case "4":
                count = 4;
                break;
            case "5":
                count = 5;
                break;
            case "6":
                count = 6;
                break;
            case "7":
                count = 7;
                break;
            case "8":
                count = 8;
                break;
            case "9":
                count = 9;
                break;
            case "10":
            case "J":
            case "Q":
            case "K":
                count = 10;
                break;
        }
        return count;
    }

    public void drawCard(ArrayList<String> cardPool, PlayerRoundInformation round1, TextView cardsLeft, ImageView cardImage, boolean isPlayer, int cardNumber, TextView player, TextView dealer) {
        MediaPlayer mpCardFlip;
        mpCardFlip = MediaPlayer.create(this, R.raw.cardflip);
        mpCardFlip.start();
        mpCardFlip.release();

        randomizeCard(round1, cardPool, cardsLeft, isPlayer, cardNumber);
        if (isPlayer) {
            displayCardImage(round1.getPlayerCardID().get(cardNumber), cardImage);

            int cardCount = findCardCount(round1.getPlayerCardID().get(cardNumber));
            round1.addPlayerCount(cardCount);
            player.setText(String.valueOf(round1.getPlayerCount()));
        } else {
            displayCardImage(round1.getDealerCardID().get(cardNumber), cardImage);

            int cardCount = findCardCount(round1.getDealerCardID().get(cardNumber));
            round1.addDealerCount(cardCount);
            dealer.setText(String.valueOf(round1.getDealerCount()));
        }
    }

    public void gameEnder(PlayerRoundInformation round1) {

        // 2s delay -> dealer score
        if (round1.getPlayerWin()) {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    int remainingAmount = round1.getRemainingAmount() + round1.getBetAmount();
                    round1.setRemainingAmount(remainingAmount);
                    Intent winScreen = new Intent(Gameplay.this, ResultScreen.class);
                    winScreen.putExtra("roundInfo", round1);
                    startActivity(winScreen);
                }
            };
            Timer waitingPeriod = new Timer();
            waitingPeriod.schedule(timerTask, 1000);

        } else if (round1.getDealerWin()) {
            TimerTask timerTask = new TimerTask() {
                public void run() {
                    int remainingAmount = round1.getRemainingAmount() - round1.getBetAmount();
                    round1.setRemainingAmount(remainingAmount);
                    Intent loseScreen = new Intent(Gameplay.this, ResultScreen.class);
                    loseScreen.putExtra("roundInfo", round1);
                    startActivity(loseScreen);
                }
            };
            Timer waitingPeriod = new Timer();
            waitingPeriod.schedule(timerTask, 1000);
        } else {
            TimerTask timerTask = new TimerTask() {
                public void run() {
                    Intent drawScreen = new Intent(Gameplay.this, ResultScreen.class);
                    drawScreen.putExtra("roundInfo", round1);
                    startActivity(drawScreen);
                }
            };
            Timer waitingPeriod = new Timer();
            waitingPeriod.schedule(timerTask, 1000);
        }
    }

    public void checkGameEnd(PlayerRoundInformation round1) {
        if (round1.getPlayerCount() > 21) { // Player Bust
            round1.setDealerWin(true);
            gameEnder(round1);
        } else if (round1.getPlayerCount() == 21) { // Player 21 Win
            round1.setPlayerWin(true);
            gameEnder(round1);
        } else if (round1.getDealerCount() > 21) {  // Dealer Bust
            round1.setPlayerWin(true);
            gameEnder(round1);
        } else if (round1.getDealerCount() == 21) { // Dealer 21 Win
            round1.setDealerWin(true);
            gameEnder(round1);
        } else if (round1.getHitCount()== 3 && round1.getPlayerCount() <= 21) { // Player 5 Hit Win
            round1.setPlayerWin(true);
            gameEnder(round1);
        } else if (round1.getDealerFiveCard()) { // Dealer 5 Hit Win
            round1.setDealerWin(true);
            gameEnder(round1);
        } else if (round1.getPlayerCount() > round1.getDealerCount()) { // Player Count > Dealer Count
            round1.setPlayerWin(true);
            gameEnder(round1);
        } else if (round1.getPlayerCount() < round1.getDealerCount()) { // Player Count < Dealer Count
            round1.setDealerWin(true);
            gameEnder(round1);
        } else if (round1.getPlayerCount() == round1.getDealerCount()) { // Player Count == Dealer Count
            round1.setDraw(true);
            gameEnder(round1);
        }
    }

    public void gameStarter(ArrayList<String> cardPool, PlayerRoundInformation round1, TextView cardsLeft, ImageView cardImageP1, ImageView cardImageD2, ImageView cardImageP2, TextView player, TextView dealer) {
        drawCard(round1.getCardPool(), round1, cardsLeft, cardImageP1, true, 0, player, dealer);
        drawCard(round1.getCardPool(), round1, cardsLeft, cardImageD2, false, 1, player, dealer);
        drawCard(round1.getCardPool(), round1, cardsLeft, cardImageP2, true, 1, player, dealer);

        if (round1.getPlayerCardID().size() >= 2 && ( // Instant Win, SC == 21
            (round1.getPlayerCardID().get(0).equals("K") && round1.getPlayerCardID().get(1).equals("A")) ||
            (round1.getPlayerCardID().get(0).equals("A") && round1.getPlayerCardID().get(1).equals("K")) ||
            (round1.getPlayerCardID().get(0).equals("A") && round1.getPlayerCardID().get(1).equals("A")) ||
            (round1.getPlayerCardID().get(0).equals("A") && round1.getPlayerCardID().get(1).equals("Q")) ||
            (round1.getPlayerCardID().get(0).equals("A") && round1.getPlayerCardID().get(1).equals("J")) ||
            (round1.getPlayerCardID().get(0).equals("A") && round1.getPlayerCardID().get(1).equals("10")) ||
            (round1.getPlayerCardID().get(0).equals("J") && round1.getPlayerCardID().get(1).equals("A")) ||
            (round1.getPlayerCardID().get(0).equals("Q") && round1.getPlayerCardID().get(1).equals("A")) ||
            (round1.getPlayerCardID().get(0).equals("10") && round1.getPlayerCardID().get(1).equals("A"))
        )) {
            round1.setPlayerCount(21);
            player.setText("21");
            round1.setPlayerWin(true);
            gameEnder(round1);
        }
    }
}
