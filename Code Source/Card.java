package uno;

public class Card {

    private String color;
    private int value;
    private String type;


    //Constructor
    
    public Card(String color, int value, String type){
        
        this.type = type;

        if( this.type.equalsIgnoreCase("wild") || this.type.equalsIgnoreCase("wild draw four")){
            this.color = "any";

        } else {
            this.color = color;
        }
        if (this.type.equalsIgnoreCase("number")) {
            this.value = value; // Set value for number cards 
            
        }else if( this.type.equalsIgnoreCase("skip")){
            this.value = -5;

        } else if( this.type.equalsIgnoreCase("reverse")){
            this.value = -1;

        } else 
        	if( this.type.equalsIgnoreCase("draw two")){
        	this.value = -2;

        } else if( this.type.equalsIgnoreCase("wild")){
        	this.value = -3;

        }else {
            this.value = -4;
        }
    }
          


    // getters

    public String getColor(){
        return color;
    }

    public int getValue(){
        return value;
    }

    public String getType(){
        return type;
    }


    //setters

    public void setColor(String col){
        color = col;
    }

    public void setValue(int val){
        value = val;
    }

    public void setType(String t){
        type = t;
    }


    // check if the card can be played with the topCard or not
    public boolean isPlayable(Card topCard){
        if(type.equalsIgnoreCase(topCard.type) && topCard.color.equalsIgnoreCase(color)) {
            return true;
       } else {
        if ((topCard.value == value && topCard.value >= 0) || type.equalsIgnoreCase("wild") || type.equalsIgnoreCase("Wild draw four") || topCard.color.equalsIgnoreCase(color)) {
            return true;
        } else {
            return false ;
        }
        }
    }



    @Override
    public String toString() {
        if (type.equalsIgnoreCase("number")) {
            return "["+color + " (" + value + ")]"; // For number cards, display the color and value
        } else if (type.equalsIgnoreCase("wild") || type.equalsIgnoreCase("wild draw four")) {
            if(color.equalsIgnoreCase("any"))
            	return "["+type+"]"; // Wild cards don't need a color
            else
            	return "["+color+" "+type+"]";
        } else {
            return "["+color + " " + type+"]"; // For action cards, display the color and type
        }
    }
}


