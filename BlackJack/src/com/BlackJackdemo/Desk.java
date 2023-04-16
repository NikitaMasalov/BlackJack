package com.BlackJackdemo;

import java.util.ArrayList;
import java.util.Random;

public class Desk {
    //instance vars
    private ArrayList<Card> cards;

    //construct
    public Desk() {
        this.cards = new ArrayList<Card>();
    }

    public void createFullDesk(){
        //генерируем карты
        for (Suit cardSuit : Suit.values()){
            for(Value cardValue : Value.values()){
                //новые карты для перемешивания
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }
    }

    public void shuffle(){
        ArrayList<Card>tmpDesk = new ArrayList<Card>();
        //Перемешанная колода
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        for(int i = 0; i < originalSize; i++){
            //Случайный индекс
            randomCardIndex = random.nextInt((this.cards.size()-1 - 0)+1)+0;
            tmpDesk.add(this.cards.get(randomCardIndex));
            //перемещаем из оригинальной колоды
            this.cards.remove(randomCardIndex);
        }
        this.cards = tmpDesk;
    }

    public  String toString(){
        String cardListOutput = "";
        for (Card acard : this.cards){
            cardListOutput += "\n"+ acard.toString();
        }
        return cardListOutput;
    }

    public void removeCard(int i){
        this.cards.remove(i);
    }

    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public void addCard(Card addCard){
        this.cards.add(addCard);
    }

    public void  draw(Desk comingFrom){
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public int deckSize(){
        return this.cards.size();
    }

    public void moveAllToDesk(Desk moveTo){
        int thisDeskSize = this.cards.size();

        for (int i = 0; i < thisDeskSize; i++){
            moveTo.addCard(this.getCard(i));
        }
        for (int i = 0; i < thisDeskSize; i++){
            this.removeCard(0);
        }
    }

    public int cardsValue(){
        int totalValue = 0;
        int aces = 0;

        for(Card aCard : this.cards) {
            switch (aCard.getValue()){
                case TWO: totalValue += 2; break;
                case THERE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case ACE: aces += 1; break;

            }
        }

        for(int i =0; i < aces; i++){
            if(totalValue > 10){
                totalValue +=1;
            }else {
                totalValue += 11;
            }
        }
        return totalValue;
    }
}
