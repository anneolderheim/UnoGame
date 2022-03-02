package prosjekt;

public class Card {

private char color;
private int value;


public Card(char color, int value) {
    if (color == 'B' || color == 'G' ||color == 'R' ||color == 'Y') {
        this.color = color;
        if (value >= 0 && value <= 12) {
            this.value = value;
        }
        else {
            throw new IllegalArgumentException("ugyldig verdi");
        }

    }

    else {
        throw new IllegalArgumentException("ugyldig farge");
    }

}

public Card(int value) {
    if (value == 13 || value == 14) {
        this.value = value;
    }
}


public char getColor() {
    return color;
}

public int getValue() {
    return value;
}


@Override
public String toString() {
    if (value< 10) {
    return color+""+value+"";
    }

    else if (value == 10) {
        return color + " Skip";
    }
    else if (value == 11) {
        return color + " Snu";
    }
    else if (value == 12) {
        return color + "Trekk to";
    }
    else if (value == 13) {
        return "Bytt farge";
    }
    else if (value == 14) {
        return "Bytt farge og trekk fire";
    }
   
    throw new IllegalArgumentException();
    
}


public static void main(String[] args) {
    Card kort = new Card('R', 11);
    System.out.println(kort.toString());
}


}