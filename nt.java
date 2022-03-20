import java.util.Random;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Card
{
    static String[] ranks = new String[] {"6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    static String[] colors = new String[] {"D", "C", "H", "S"};

    String rank = "";
    String color = "";

    public Card(){}

    public Card(String r, String c)
    {
        rank = r;
        color = c;
    }
}

public class nt 
{
    static Card[] createDeck()
    {
        Card[] deck = new Card[36];

        int index = 0;
        for(int i = 0; i < Card.ranks.length; ++i)
        {
            for(int j = 0; j < Card.colors.length; ++j)
            {
                
                Card temp = new Card("", "");
                temp.color = Card.colors[j];
                temp.rank = Card.ranks[i];
                deck[index] = temp;
                ++index;
            }
        }

        return deck;
    }

    //check for Royal Flush
    //value - 10
    public static boolean checkForRoyalFlush(Card[] plCards)
    {
        String ace = "A";
        boolean flag = false;

        //check for rank
        for(int i = 0; i < plCards.length; ++i)
        {
            if(ace.equals(plCards[i].rank) == true)
            {
                ace = "K";
                for(int j = 0; j < plCards.length; ++j)
                {
                    if(ace.equals(plCards[j].rank) == true)
                    {
                        ace = "Q";
                        for(int k = 0; k < plCards.length; ++k)
                        {
                            if(ace.equals(plCards[k].rank) == true)
                            {
                                ace = "J";
                                for(int l = 0; l < plCards.length; ++l)
                                {
                                    if(ace.equals(plCards[l].rank) == true)
                                    {
                                        ace = "10";
                                        for(int h = 0; h < plCards.length; ++h)
                                        {
                                            if(ace.equals(plCards[h].rank) == true)
                                            {
                                                flag  = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if(flag == false) return flag;

        ace = plCards[0].color;

        for(int i = 1; i < plCards.length; ++i)
        {
            if(ace.equals(plCards[i].color) == false)
            {
                return false;
            }
        }

        return true;
    }

    //check for Straight Flush
    //value - 9
    public static String checkForStraightFlush(Card[] plCards)
    {
        String ch = plCards[0].color;
        String mainCard;
        boolean flag = false;

        for(int i = 1; i < plCards.length; ++i)
        {
            if(ch.equals(plCards[i].color) == true)
            {
                flag = true;
            }
            else flag = false;
        }

        if(flag == false) return "";

        int index = 0, tmpInd = 0;

        for(int i = 0; i < plCards.length; ++i)
        {
            ch = plCards[i].rank;
            for(int j = 0; j < Card.colors.length; ++j)
            {
                if(ch.equals(Card.colors[j]) == true)
                {
                    tmpInd = j;
                }
            }

            if(tmpInd > index)
            {
                index = tmpInd;
            }
        }
        

        mainCard = Card.ranks[index];

        if(index < 4) return "";

        for(int i = 0; i < plCards.length; ++i)
        {
            if(plCards[i].rank == Card.ranks[index - 1])
            {
                for(int j = 0; j < plCards.length; ++j)
                {
                    if(plCards[j].rank == Card.ranks[index - 2])
                    {
                        for(int k = 0; k < plCards.length; ++k)
                        {
                            if(plCards[k].rank == Card.ranks[index - 3])
                            {
                                for(int l = 0; l < plCards.length; ++l)
                                {
                                    if(plCards[l].rank == Card.ranks[index - 4])
                                    {
                                        return mainCard;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return "";
    }

    //check for a four of a kind
    //value - 8
    public static String checkForFourOfAKind(Card[] plCards)
    {
        String tmp = "";
        int counter = 0, difCardCount = 1;

        for(int i = 0; i < 2; ++i)
        {
            tmp = plCards[i].rank;
            for(int j = 1; j < plCards.length; ++j)
            {
                if(tmp.equals(plCards[j].rank) == true)
                {
                    ++counter;
                }
                else
                {
                    ++difCardCount;
                }
            }
            
            if(difCardCount > 2 || counter == 2 || counter == 3) return "";

            if(counter == 4) return tmp;
            counter = 0;
            difCardCount = 0;
        }

        return tmp;
    }

    //check for full house
    //value - 7
    public static String checkForFullHouse(Card[] plCards)
    {
        int fCount = 0, difCard = 1;
        String card = plCards[0].rank;

        for(int i = 0; i < 2; ++i)
        {
            card = plCards[i].rank;
            for(int j = 1; j < plCards.length; ++j)
            {
                if(card.equals(plCards[j].rank) == true)
                {
                    ++fCount;
                }
                else
                {
                    ++difCard;
                }
            }

            if(difCard > 2) return "";
            
            if(fCount == 3) return card;

            difCard = 0;
            fCount = 0;
        }
        
        return "";
    }

    //check for flush
    //value - 6
    public static ArrayList<Integer> checkForFlush(Card[] plCards)
    {
        String card = plCards[0].color;
        String ch = plCards[0].rank;
        ArrayList<Integer> indexes = new ArrayList<Integer>();


        for(int i = 0; i < plCards.length; ++i)
        {
            if(card.equals(plCards[i].color) == false) return indexes;
        }

        for(int i = 0; i < plCards.length; ++i)
        {
            ch = plCards[i].rank;
            for(int j = 0; j < Card.ranks.length; ++j)
            {
                if(ch.equals(Card.ranks[j]) == true)
                {
                    indexes.add(j);
                }
            }
        }

        Collections.sort(indexes);
        Collections.reverse(indexes);

        return indexes; 
    }

    //check for straight
    //value - 5
    public static ArrayList<Integer> checkForStraight(Card[] plCards)
    {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        String str = "";
        boolean flag = false;

        int index = 0, tmpInd = 0;
        for(int i = 0; i < plCards.length; ++i)
        {
            str = plCards[i].rank;
            for(int j = 0; j < Card.ranks.length; ++j)
            {
                if(str.equals(Card.ranks[j]) == true)
                {
                    tmpInd = j;
                    if(tmpInd > index) index = tmpInd;
                }
            }
        }

        if(index < 4) return indexes;

        for(int i = 0; i < plCards.length; ++i)
        {
            if(plCards[i].rank.equals(Card.ranks[index]) == true)
            {
                for(int j = 0; j < plCards.length; ++j)
                {
                    if(plCards[j].rank.equals(Card.ranks[index - 1]) == true)
                    {
                        for(int k = 0; k < plCards.length; ++k)
                        {
                            if(plCards[k].rank.equals(Card.ranks[index - 2]) == true)
                            {
                                for(int l = 0; l < plCards.length; ++l)
                                {
                                    if(plCards[l].rank.equals(Card.ranks[index - 3]) == true)
                                    {
                                        for(int h = 0; h < plCards.length; ++h)
                                        {
                                            if(plCards[h].rank.equals(Card.ranks[index - 4]) == true)
                                            {
                                                flag = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if(flag == false) return indexes;

        for(int i = 0; i < plCards.length; ++i)
        {
            str = plCards[i].rank;
            for(int j = 0; j < Card.ranks.length; ++j)
            {
                if(str.equals(Card.ranks[j]) == true)
                {
                    indexes.add(j);
                }
            }
        }

        Collections.sort(indexes);
        Collections.reverse(indexes);

        return indexes;
    }

    //check for three of a kind
    //value - 4
    public static String checkForThreeOfAKind(Card[] plCards)
    {
        String th = "";
        int uniqCount = 1, count = 0;

        for(int i = 0; i < 3; ++i)
        {
            th = plCards[i].rank;
            for(int j = 0; j < plCards.length; ++j)
            {
                if(th.equals(plCards[j].rank) == true)
                {
                    ++count;
                }
                else
                {
                    ++uniqCount;
                }
            }

            if(uniqCount > 3) return "";

            if(count == 3) return th;

            count = 0;
            uniqCount = 0;
        }

        return "";
    }

    //check for two-pair
    //value - 3
    public static ArrayList<Integer> checkForTwoPair(Card[] plCards)
    {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int uniqCount = 1, tw = 0;
        String str;

        one:
        {
            for(int i = 0; i < 4; ++i)
            {
                str = plCards[i].rank;
                uniqCount = 0;

                for(int j = i + 1; j < plCards.length; ++j)
                {
                    if(uniqCount == 4 && i == 0) return indexes;
                    if(str.equals(plCards[j].rank) == true)
                    {
                        ++tw;
                    }
                    else
                    {
                        ++uniqCount;
                    }
                }

                if(tw == 2) break one;
            }
        }

        if(tw != 2) return indexes;

        for(int i = 0; i < plCards.length; ++i)
        {
            for(int j = 0; j < Card.ranks.length; ++j)
            {
                if(plCards[i].rank == Card.ranks[j])
                {
                    indexes.add(j);
                }
            }
        }

        Collections.sort(indexes);
        Collections.reverse(indexes);

        return indexes;
    }

    //check for one-pair
    //value - 2
    public static ArrayList<Integer> checkForOnePair(Card[] plCards)
    {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        String str;
        int count = 0;
        int uniq = 1;

        one:
        {
            for(int i = 0; i < 4; ++i)
            {
                str = plCards[i].rank;
                count = 0;

                for(int j = i + 1; j < plCards.length; ++j)
                {
                    if(str.equals(plCards[j].rank) == true)
                    {
                        ++count;
                    }
                    else
                    {
                        ++uniq;
                    }
                }

                if(i == 0 && uniq == 4) break one;
                if(count == 1) break one;

            }
        }

        if(count == 0) return indexes;

        for(int i = 0; i < plCards.length; ++i)
        {
            for(int j = 0; j < Card.ranks.length; ++j)
            {
                if(plCards[i].rank == Card.ranks[j])
                {
                    indexes.add(j);
                }
            }
        }

        Collections.sort(indexes);
        Collections.reverse(indexes);

        return indexes;

    }
    
    //check for high card
    //value - 1
    public static ArrayList<Integer> checkForHighCard(Card[] plCards)
    {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int uniq = 1;
        String str = plCards[0].rank;

        for(int i = 1; i < plCards.length; ++i)
        {
            if(str.equals(plCards[i].rank) == false)
            {
               ++uniq;
            }
        }

        if(uniq != 5) return indexes;

        for(int i = 0; i < plCards.length; ++i)
        {
            for(int j = 0; j < Card.ranks.length; ++j)
            {
                if(plCards[i].rank == Card.ranks[j])
                {
                    indexes.add(j);
                }
            }
        }

        Collections.sort(indexes);
        Collections.reverse(indexes);

        return indexes;
    }
    
    //check value of combination
    public static int valueOfCommbination(Card[] plCards)
    {
        boolean flagRoyal = checkForRoyalFlush(plCards);
        if(flagRoyal == true) 
        {
            System.out.print("Royal Flush!\n");
            return 10;
        }

        String sFlush = checkForStraightFlush(plCards);
        if(sFlush.isEmpty() == false) 
        {
            System.out.print("Straight Flush!\n");
            return 9;
        }

        String sFOAK = checkForFourOfAKind(plCards);
        if(sFOAK.isEmpty() == false) 
        {
            System.out.print("Four of a Kind!\n");  
            return 8;
        }

        String fullHouse = checkForFullHouse(plCards);
        if(fullHouse.isEmpty() == false) 
        {
            System.out.print("Full House!\n");
            return 7;
        }

        ArrayList<Integer> flush = checkForFlush(plCards);
        if(flush.size() != 0) 
        {
            System.out.print("Flush!\n");
            return 6;
        }

        ArrayList<Integer> straight = checkForStraight(plCards);
        if(straight.size() != 0) 
        {
            System.out.print("Straight!\n");
            return 5;
        }

        String tOAK = checkForThreeOfAKind(plCards);
        if(tOAK.isEmpty() == false) 
        {
            System.out.print("Three of a kind!\n");
            return 4;
        }

        ArrayList<Integer> twoPair = checkForTwoPair(plCards);
        if(twoPair.size() != 0) 
        {
            System.out.print("Two-Pair!\n");
            return 3;
        }

        ArrayList<Integer> onePair = checkForOnePair(plCards);
        if(onePair.size() != 0) 
        {
            System.out.print("One-Pair!\n");       
            return 2;
        }

        ArrayList<Integer> high = checkForHighCard(plCards);
        if(high.size() != 0) 
        {
            System.out.print("High card!\n");
            return 1;
        }


        return 0;
    }
    
    //compare with each other
    //0 - draw
    //1 - win of th first player
    //2 - win of the second player
    public static int rating(Card[] plFir, Card[] plSec)
    {
        int resFir = valueOfCommbination(plFir);
        int resSec = valueOfCommbination(plSec);

        if(resFir == resSec)
        {
            //check compil problems
            switch(resFir)
            {
                case 10:
                {
                    return 0;
                }
                
                case 9:
                {
                    String first = checkForStraightFlush(plFir);
                    String second = checkForStraightFlush(plSec);

                    if(first.length() == 2)
                    {
                        if(second.length() == 2)
                        {
                            return 0;
                        }
                        else
                        {
                            if(second.charAt(0) == 'J' || second.charAt(0) == 'Q' || second.charAt(0) == 'K' || second.charAt(0) == 'A') return 2;
                            else return 1;
                        }
                    }

                    if(second.length() == 2)
                    {
                        if(first.charAt(0) == 'J' || first.charAt(0) == 'Q' || first.charAt(0) == 'K' || first.charAt(0) == 'A') return 1;
                        else return 2;
                    }

                    if(first.charAt(0) >= '0' && first.charAt(0) <= '9')
                    {
                        if(second.charAt(0) >= '0' && second.charAt(0) <= '9')
                        {
                            if(first.charAt(0) > second.charAt(0)) return 1;
                            else return 2;
                        }
                        else return 2;
                    }

                    switch(first.charAt(0))
                    {
                        case 'J':
                        {
                            if(second.charAt(0) == 'J') return 0;
                            else if(second.charAt(0) == 'Q' || second.charAt(0) == 'K' || second.charAt(0) == 'A') return 2;
                            else return 1;
                        }
                        

                        case 'Q':
                        {
                            if(second.charAt(0) == 'J') return 1;
                            else if(second.charAt(0) == 'Q') return 0;
                            else return 2;
                        }
                        

                        case 'K':
                        {
                            if(second.charAt(0) == 'K') return 0;
                            else if(second.charAt(0) == 'A') return 2;
                            else return 1;
                        }
                        

                        case 'A':
                        {
                            if(second.charAt(0) == 'A') return 0;
                            else return 1;
                        }
                        
                    }

                }
                break;

                case 8:
                {
                    String first = checkForFourOfAKind(plFir);
                    String second = checkForFourOfAKind(plSec);
                
                    if(first.length() == 2)
                    {
                        if(second.length() == 2)
                        {
                            return 0;
                        }
                        else
                        {
                            if(second.equals("Q") || second.equals("A") || second.equals("K") || second.equals("J")) return 2;
                            else return 1;
                        }
                    }
                    else if(first.equals(second)) return 0;

                    if(second.length() == 2)
                    {
                        if(first.equals("Q") || first.equals("A") || first.equals("K") || first.equals("J")) return 1;
                        else return 2;
                    }

                    if(first.charAt(0) >= '0' && first.charAt(0) <= '9')
                    {
                        if(second.charAt(0) >= '0' && second.charAt(0) <= '9')
                        {
                            if(first.charAt(0) > second.charAt(0)) return 1;
                            else return 2;
                        }
                        else return 2;
                    }

                    switch(first.charAt(0))
                    {
                        case 'J':
                        {
                            if(second.charAt(0) == 'J') return 0;
                            else if(second.charAt(0) == 'Q' || second.charAt(0) == 'K' || second.charAt(0) == 'A') return 2;
                            else return 1;
                        }
                        

                        case 'Q':
                        {
                            if(second.charAt(0) == 'J') return 1;
                            else if(second.charAt(0) == 'Q') return 0;
                            else return 2;
                        }
                        

                        case 'K':
                        {
                            if(second.charAt(0) == 'K') return 0;
                            else if(second.charAt(0) == 'A') return 2;
                            else return 1;
                        }
                        

                        case 'A':
                        {
                            if(second.charAt(0) == 'A') return 0;
                            else return 1;
                        }
                        
                    }
                }
                break;

                case 7:
                {
                    String first = checkForFullHouse(plFir);
                    String second = checkForFullHouse(plSec);

                    if(first.length() == 2)
                    {
                        if(second.length() == 2)
                        {
                            return 0;
                        }
                        else
                        {
                            if(second.charAt(0) == 'J' || second.charAt(0) == 'Q' || second.charAt(0) == 'K' || second.charAt(0) == 'A') return 2;
                            else return 1;
                        }
                    }

                    if(second.length() == 2)
                    {
                        if(first.charAt(0) == 'J' || first.charAt(0) == 'Q' || first.charAt(0) == 'K' || first.charAt(0) == 'A') return 1;
                        else return 2;
                    }

                    if(first.charAt(0) >= '0' && first.charAt(0) <= '9')
                    {
                        if(second.charAt(0) >= '0' && second.charAt(0) <= '9')
                        {
                            if(first.charAt(0) > second.charAt(0)) return 1;
                            else return 2;
                        }
                        else return 2;
                    }

                    switch(first.charAt(0))
                    {
                        case'J':
                        {
                            if(second.charAt(0) == 'J') return 0;
                            else if(second.charAt(0) == 'Q' || second.charAt(0) == 'K' || second.charAt(0) == 'A') return 2;
                            else return 1;
                        }
                        

                        case 'Q':
                        {
                            if(second.charAt(0) == 'J') return 1;
                            else if(second.charAt(0) == 'Q') return 0;
                            else return 2; //PROBABLY BUG
                        }
                        

                        case 'K':
                        {
                            if(second.charAt(0) == 'K') return 0;
                            else if(second.charAt(0) == 'A') return 2;
                            else return 1;
                        }
                        

                        case 'A':
                        {
                            if(second.charAt(0) == 'A') return 0;
                            else return 1;
                        }
                        
                    }
                }
                break;

                case 6:
                {
                    ArrayList<Integer> fir = checkForFlush(plFir);
                    ArrayList<Integer> sec = checkForFlush(plSec);

                    for(int i = 0; i < fir.size(); ++i)
                    {
                        if(fir.get(i) != sec.get(i))
                        {
                            if(fir.get(i) > sec.get(i)) return 1;
                            else return 2;
                        }
                    }

                    return 0;
                }

                case 5:
                {
                    ArrayList<Integer> fir = checkForStraight(plFir);
                    ArrayList<Integer> sec = checkForStraight(plSec);

                    for(int i = 0; i < fir.size(); ++i)
                    {
                        if(fir.get(i) != sec.get(i))
                        {
                            if(fir.get(i) > sec.get(i)) return 1;
                            else return 2;
                        }
                    }

                    return 0;
                }
                
                case 4:
                {
                    String fir = checkForThreeOfAKind(plFir);
                    String sec = checkForThreeOfAKind(plSec);

                    if(fir.length() == 2)
                    {
                        if(sec.charAt(0) == 'J' || sec.charAt(0) == 'Q' || sec.charAt(0) == 'K' || sec.charAt(0) == 'A') return 2;
                        else return 1;
                    }

                    if(sec.length() == 2)
                    {
                        if(fir.charAt(0) == 'J' || fir.charAt(0) == 'Q' || fir.charAt(0) == 'K' || fir.charAt(0) == 'A') return 1;
                        else return 2;
                    }

                    if(fir.charAt(0) >= '0' && fir.charAt(0) <= '9')
                    {
                        if(sec.charAt(0) >= '0' && sec.charAt(0) <= '9')
                        {
                            if(fir.charAt(0) > sec.charAt(0)) return 1;
                            else return 2;
                        }
                        else return 2;
                    }

                    switch(fir.charAt(0))
                    {
                        case 'J':
                        {
                            if(sec.charAt(0) == 'J') return 0;
                            else if(sec.charAt(0) == 'Q' || sec.charAt(0) == 'K' || sec.charAt(0) == 'A') return 2;
                            else return 1;
                        }
                        

                        case 'Q':
                        {
                            if(sec.charAt(0) == 'J') return 1;
                            else if(sec.charAt(0) == 'Q') return 0;
                            else return 2;
                        }
                        

                        case 'K':
                        {
                            if(sec.charAt(0) == 'K') return 0;
                            else if(sec.charAt(0) == 'A') return 2;
                            else return 1;
                        }
                        

                        case 'A':
                        {
                            if(sec.charAt(0) == 'A') return 0;
                            else return 1;
                        }
                        
                    }

                }
                break;

                case 3:
                {
                    ArrayList<Integer> fir = checkForTwoPair(plFir);
                    ArrayList<Integer> sec = checkForTwoPair(plSec);

                    int fPair = -1, sPair = -1;
                    int ffPair = -1, ssPair = -1;
                    int f = -1, s = -1;

                    //first
                    for(int i = 0; i < fir.size() - 1; ++i)
                    {
                        if(fir.get(i) == fir.get(i+1))
                        {
                            fPair = fir.get(i);
                        }
                    }

                    for(int i = 0; i < fir.size() - 1; ++i)
                    {
                        if(fir.get(i) == fir.get(i+1) && fir.get(i) != fPair)
                        {
                            ffPair = fir.get(i);
                        }
                    }

                    for(int i = 0; i < fir.size(); ++i)
                    {
                        if(fir.get(i) != fPair && fir.get(i) != ffPair)
                        {
                            f = fir.get(i);
                        }
                    }
                    //first

                    //second
                    for(int i = 0; i < sec.size() - 1; ++i)
                    {
                        if(sec.get(i) == sec.get(i+1))
                        {
                            sPair = sec.get(i);
                        }
                    }

                    for(int i = 0; i < sec.size() - 1; ++i)
                    {
                        if(sec.get(i) == sec.get(i+1) && sec.get(i) != sPair)
                        {
                            ssPair = sec.get(i);
                        }
                    }

                    for(int i = 0; i < sec.size(); ++i)
                    {
                        if(sec.get(i) != sPair && sec.get(i) != ssPair)
                        {
                            s = sec.get(i);
                        }
                    }
                    //second

                    if(fPair < ffPair)
                    {
                        int tmp = fPair;
                        fPair = ffPair;
                        fPair = tmp;
                    }

                    if(sPair < ssPair)
                    {
                        int tmp = sPair;
                        sPair = ssPair;
                        ssPair = tmp;
                    }

                    if(fPair > sPair) return 1;
                    else if(fPair < sPair) return 2;
                    else
                    {
                        if(ffPair > ssPair) return 1;
                        else if(ffPair < ssPair) return 2;
                        else
                        {
                            if(f > s) return 1;
                            else if(f < s) return 2;
                        }
                    }

                    return 0;
                }
                
                case 2:
                {
                    ArrayList<Integer> fir = checkForOnePair(plFir);
                    ArrayList<Integer> sec = checkForOnePair(plSec);

                    int fTw = -1, sTw = -1;
                    int tmp = -1;

                    for(int i = 0; i < fir.size(); ++i)
                    {
                        tmp = fir.get(i);
                        if(fir.get(i) == tmp)
                        {
                            fTw = tmp;
                            break;
                        }
                    }

                    for(int i = 0; i < sec.size(); ++i)
                    {
                        tmp = sec.get(i);
                        if(fir.get(i) == tmp)
                        {
                            sTw = tmp;
                            break;
                        }
                    }

                    if(fTw > sTw) return 1;
                    else if(sTw > fTw) return 2;

                    int[] f = new int[3];
                    int[] s = new int[3];

                    for(int i = 0, k = 0; i < f.length; ++i)
                    {
                        if(fir.get(i) != fTw)
                        {
                            f[k] = fir.get(i);
                            ++k;
                        }
                    }

                    for(int i = 0, k = 0; i < s.length; ++i)
                    {
                        if(sec.get(i) != sTw)
                        {
                            s[k] = sec.get(i);
                            ++k;
                        }
                    }

                    for(int i = 0; i < fir.size(); ++i)
                    {
                        if(fir.get(i) > sec.get(i))
                        {
                            return 1;
                        }
                        else if(fir.get(i) < sec.get(i))
                        {
                            return 2;
                        }
                    }



                    return 0;
                }
                            
                case 1:
                {
                    ArrayList<Integer> fir = checkForHighCard(plFir);
                    ArrayList<Integer> sec = checkForHighCard(plSec);

                    for(int i = 0; i < fir.size(); ++i)
                    {
                        if(fir.get(i) != sec.get(i))
                        {
                            if(fir.get(i) > sec.get(i)) return 1;
                            else return 2;
                        }
                    }

                    return 0;
                }
                
            }
        }

        if(resFir > resSec) return 1;
        else return 2;
    }
    
    public static void game(Card[] deck, HashSet<Integer> ind)
    {
        Card[] plFir = new Card[5];
        Card[] plSec = new Card[5];

        int checkInd;
        Random rd = new Random();
        boolean bl;

        int i = 0;
        while(i != 5)                       
        {
            checkInd = rd.nextInt(36);
            bl = ind.add(checkInd);
            if(bl == true)
            {
                plFir[i] = deck[checkInd];
                ++i;
            }
            else continue;
        }

        bl = false;
        i = 0;
        while(i != 5)
        {
            checkInd = rd.nextInt(36);
            bl = ind.add(checkInd);
            if(bl)
            {
                plSec[i] = deck[checkInd];
                ++i;
            }
            else continue;
        }

        System.out.print("Player #1 has ");
        for(int r = 0; r < plFir.length; ++r)
        {
            System.out.print(plFir[r].rank + plFir[r].color + " ");
        }
        System.out.println();

        System.out.print("Player #2 has ");
        for(int r = 0; r < plSec.length; ++r)
        {
            System.out.print(plSec[r].rank + plSec[r].color + " ");
        }
        System.out.println();

        int result = rating(plFir, plSec);

        if(result == 0) System.out.print("It's DRAW!\n");
        else System.out.printf("Player #%s has the strongest combination!\n", result);
        
    }
    
    public static void main(String[] args)
    {
        Card[] deck =  new Card[36];
        deck = createDeck();
        HashSet ind = new HashSet();
        boolean playAgain = true;
        Scanner sc = new Scanner(System.in);

        while(playAgain)
        {
            game(deck, ind);

            System.out.print("Would u like to play again?(y/n)");
            char ch = ' ';
            while(ch != 'y' && ch != 'Y' && ch != 'n' && ch != 'N')
            {
                ch = sc.next().charAt(0);
                sc = new Scanner(System.in);
            }

            if(ch == 'n' || ch == 'N')
            {
                playAgain = false;
                System.out.print("Bye.");
            }
            else
            {
                ind.clear();
            }

            
            System.out.println();
        }
    }
}
