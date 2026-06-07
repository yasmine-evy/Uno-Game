package uno;

class Player{
   
    private String name ;//the player's name
    protected Card[] cardh ;//cardh: cards in the hand of the player درتهم جدول
    
           //Constructor
            public Player (String name, Card[] cardh){
            this.name=name;
            this.cardh=cardh;}
            
          //Method Number of Cards
           public int cardsNum(){
        	int i=0;
        	int N=0;
           	while(i<cardh.length && cardh[i] != null) {
           		i++;
          		N++;
            }
            return N;
           } 
           
           public boolean canPlay(Card topCard){
        	     if(cardsNum()==0){
        	    	 return false;
        	     }
        	     else{
        	          int i=0;
        	          while (i < cardsNum() && cardh[i].isPlayable(topCard) == false){
        	        	  i++;
        	        	  }
        	          if(i==cardsNum()){
        	              return false;}
        	          else{
        	              return true;}
        	      }  
        	     }
           
           /*method PlayCard should return the cardh[i](chosen)and remove it from the array (like pop)*/
           public Card playCard(int i){
            Card cardpop=cardh[i];
            for(int j=i ;j<cardh.length-1;j++ ){
            cardh[j]=cardh[j+1];}
            cardh[cardh.length - 1] = null;
            return cardpop;}
        
           /*Method add card (like push)*/
            public Card[] addCard (Card cardpush){
            int n=cardsNum();//n is the numbers of card the player has
            cardh[n]= cardpush;
            return cardh;
            }
            
    
           //Method display
            public void displayCardsInHand () {
                if(cardsNum() == 0){
                 System.out.println( name + ":has no Cards");  
                }
                else{ 
                	for(int i=0; i<cardsNum() ;i++ ){
                		System.out.println(i+": "+cardh[i].toString());
                   	}
                }	
            }
    
           //set and get name
            public String getName(){return name;}
            public void setName(String name){this.name = name;}
           //set and get cards
            public Card[] getCardh(){return cardh;}
            public void setCardh(Card[] cardh){this.cardh = cardh;}
         }       