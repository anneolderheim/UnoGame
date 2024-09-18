package prosjekt;

public class Card {

private String color;
private int value;


public Card(String color, int value) {
    if (color.equals("Blue") || color.equals("Green") ||color.equals("Red") ||color.equals("Yellow")) {
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

    else {
        throw new IllegalArgumentException("ugyldig verdi");
    }
}

public String getColor() {
    return color;
}

public int getValue() {
    return value;
}

public void setColor(String color) {
    this.color = color;
}


public String cardString() {
    if (value< 10) {
    return ""+value+"";
    }

    else if (value == 10) {
        return "Skip";
    }
    else if (value == 11) {
        return "Snu";
    }
    else if (value == 12) {
        return "+2";
    }
    else if (value == 13) {
        return "Bytt farge";
    }
    else if (value == 14) {
        return "Bytt farge +4";
    }

    return null;
    
}

public String toString() {
    if (value< 10) {
    return color+""+value+"";
    }

    else if (value == 10) {
        return color + "Skip";
    }
    else if (value == 11) {
        return color + "Snu";
    }
    else if (value == 12) {
        return color + "+2";
    }
    else if (value == 13) {
        return "Bytt farge";
    }
    else if (value == 14) {
        return "Bytt farge +4";
    }

    return null;
    
}


public static void main(String[] args) {
    Card kort = new Card("Red", 11);
    System.out.println(kort.toString());
    Card kort1 = new Card(13);
    System.out.println(kort1);
    System.out.println(kort1.getColor());
    Card card = new Card(13);
    System.out.println(card.getColor());
}


}