package uno;

public class botPlayer extends Player {
    //Constructor
    public botPlayer (String name,Card[] cardh){
    	super(name,cardh);
    }

     //Overloading playcard to match the Bot_Player
    public Card playCard(Card topCard){
       int i=0;
       while (i<cardsNum() && !cardh[i].isPlayable(topCard)) {
      i++;}
      if(i<cardsNum()){    
      return super.playCard(i);
      }
      else{
          System.out.println("The bot can't play you should have checked with canPlay method!!");
          return null;}//i added it just in case someone forgot  
    }
    
  //choose random
    public String wildChooseColor(Card topCard){
      int r=0,y=0,g=0,b=0;
    if(cardsNum()!=0){
      for(int j=0 ;j<cardsNum();j++ ){
        switch (cardh[j].getColor()) {
          case "red": r++;
           break;
          case "blue": b++;
           break;
          case "yellow": y++;
           break;
          case "green": g++;
           break;
        }
      }   
      String maxcolor="red";
      int max=r;
      if(b>max){
        maxcolor="blue";
        b=max;
      }
      if(g>max){
        maxcolor="green";
        g=max;
      }  
      if(y>max){
        maxcolor="yellow";
        y=max;
      }  
      return maxcolor;
    } 
    else{return topCard.getColor();}   
    }

} 