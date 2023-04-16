package com.BlackJackdemo;

import java.awt.desktop.AboutEvent;
import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args){
        //приветствие
        System.out.println("Добро пожаловать в BlackJack!");

        //новая игровая колода
        Desk platyingDesk = new Desk();
        platyingDesk.createFullDesk();
        platyingDesk.shuffle();
        //колода игрока
        Desk playerDesk = new Desk();

        Desk dealerDesk = new Desk();

        double playerMoney = 100.00;

        Scanner userInput = new Scanner(System.in);

        //игровой цикл
        while (playerMoney > 0){
            //ставка
            System.out.println("у тебя на счету $" + playerMoney +"Сколько вы хотите поставить?");
            double playerBet = userInput.nextDouble();
            if(playerBet > playerMoney){
                System.out.println("У вас столько нет!");
                break;
            }
            boolean endRound = false;
            if(playerBet < 10){
                System.out.println("Маленькая сумма! с вами не захотели играть :(");
                break;
            }
            playerDesk.draw(platyingDesk);
            playerDesk.draw(platyingDesk);

            dealerDesk.draw(platyingDesk);
            dealerDesk.draw(platyingDesk);

            while (true){
                System.out.println("Ваша рука");
                System.out.print(playerDesk.toString());
                System.out.println(" Ценность вашей карты: " + playerDesk.cardsValue());

                // рука диллера
                System.out.println("Рука диллера: " + dealerDesk.getCard(0).toString() + " и [Скрыто]");

                //берет еще или нет
                System.out.println("Возьмете карточку? (1)Да (2)Нет");
                int response = userInput.nextInt();

                if (response == 1){
                    playerDesk.draw(platyingDesk);
                    System.out.println("Вы взяли: " + playerDesk.getCard(playerDesk.deckSize()-1).toString());
                    // если больше 21
                    if(playerDesk.cardsValue() > 21){
                        System.out.println("К сожелению. Вы набрали::" +platyingDesk.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                    }
                }
                if(response == 2){
                    break;
                }
            }

            System.out.println("Карты диллера: " + dealerDesk.toString());
            if((dealerDesk.cardsValue() > playerDesk.cardsValue()) &&endRound == false){
                System.out.println("Диллер выйграл!");
                playerMoney -= playerBet;
                endRound = true;
            }
            while ((dealerDesk.cardsValue() < 17) && endRound == false){
                dealerDesk.draw(platyingDesk);
                System.out.println("Диллер взял: " + dealerDesk.getCard(dealerDesk.deckSize()-1).toString());
            }
            System.out.println("Ценность руки диллера: " + dealerDesk.cardsValue());
            if ((dealerDesk.cardsValue() > 21)&& endRound == false){
                System.out.println("Диллер переоценен! Вы выйграли!!!");
                playerMoney += playerBet;
                endRound = true;
            }

            if ((playerDesk.cardsValue() == dealerDesk.cardsValue()) && endRound == false){
                System.out.println("Ничья");
                endRound = true;
            }


            if ((playerDesk.cardsValue() > dealerDesk.cardsValue()) && endRound == false){
                System.out.println("Вы выйграли!!!");
                playerMoney += playerBet;
                endRound = true;
            }else if (endRound == false) {
                System.out.println("Вы проиграли :(");
                playerMoney -= playerBet;
                endRound = true;
            }

            playerDesk.moveAllToDesk(platyingDesk);
            dealerDesk.moveAllToDesk(platyingDesk);
            System.out.println("Конец раунда");

        }
        System.out.println("Вы проиграли! у вас закончились деньги :(");


        System.out.println(platyingDesk);
    }
}
