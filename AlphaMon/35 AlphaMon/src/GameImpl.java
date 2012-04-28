import java.util.HashMap;
import java.util.Map;

/** Skeleton implementation of HotGammon.*/
public class GameImpl implements Game {
	private Color color;
	private Map<Location,Integer> locations = new HashMap<Location, Integer>();
	private int count, movesLeft;
	private int[] diceValues;
	private int[] diceThrown;
	public void newGame() {
    	color = Color.NONE;
    	for( Location l : Location.values() ) {
    		locations.put(l, 0);
       }
		locations.put(Location.R1, 2);
		locations.put(Location.R12,5);
		locations.put(Location.B6, 5);
		locations.put(Location.B8, 3);
		locations.put(Location.B1,-2);
		locations.put(Location.B12,-5);
		locations.put(Location.R8,-3);
		locations.put(Location.R6,-5);

		diceThrown = new int[] {1,1};
		 diceValues=diceThrown;
		count = 0;
	}
	public void nextTurn(){
		if(color == Color.NONE || color == Color.RED)
			color = Color.BLACK;
		else 
			color = Color.RED;
		
		movesLeft = 2;
	  if(count==6)throw new RuntimeException("Only 6 dice-throws are legal");
	  count++;  	  
	  switch (count) {
		case 1:
		case 4: diceThrown= new int[] {2,1};	
			break;
		case 2:
		case 5: diceThrown= new int[] {4,3};	
			break;
		case 3:
		case 6: diceThrown= new int[] {6,5};	
			break;
	  }
	  diceValues=diceThrown;
	}
  public boolean move(Location from, Location to) {
	  boolean res; 
	  int numberOfEyesDice1,numberOfEyesDice2;
	  if(movesLeft>0 && (getPlayerInTurn()==getColor(to)||Color.NONE==getColor(to)))
	  {
		  if(movesLeft==2)
		  {
			  numberOfEyesDice1 = diceValuesLeft()[0];
			  numberOfEyesDice2 = diceValuesLeft()[1];
		  
			  Location  _location1 = Location.findLocation(getPlayerInTurn(), from, numberOfEyesDice1);
			  Location  _location2 = Location.findLocation(getPlayerInTurn(), from, numberOfEyesDice2);
			  if(_location1.equals(to) )
			  {		  
				  locations.put(from, locations.get(from)-1);
				  locations.put(to, locations.get(to)+1);
				  movesLeft--;
				  diceValues = new int[]{numberOfEyesDice2};
				  res=true;
			  }
			  else if(_location2.equals(to))
				  {		  
					  locations.put(from, locations.get(from)-1);
					  locations.put(to, locations.get(to)+1);
					  movesLeft--;
					  diceValues = new int[]{numberOfEyesDice1};
					  res=true;
				  }
			  else
				  res = false;
		  }
		  else if(movesLeft==1)
		  {
			  numberOfEyesDice1 = diceValuesLeft()[0];
			  Location  _location = Location.findLocation(getPlayerInTurn(), from, numberOfEyesDice1);
			  if(_location.equals(to))
			  {		  
				  locations.put(from, locations.get(from)-1);
				  locations.put(to, locations.get(to)+1);
				  movesLeft--;
				  diceValues = new int[]{};
				  res=true;
			  }
			  else
				  res = false;
		  }
		  else 
			  res = false;
	  }
	  else  
		  res = false;
	  return res; 
  }

  public Color getPlayerInTurn() { 
	  return color; }
  public int getNumberOfMovesLeft() { 
	  return movesLeft; 
  }
  public int[] diceThrown() {
	  return diceThrown;
  }
  public int[] diceValuesLeft() { 
	  return diceValues;
  }
  public Color winner() { 
	  Color res;
	  if(count== 6) res = Color.RED;
	  else res = Color.NONE;
	  return res; 
  }
  public Color getColor(Location location) {
	  Color res = Color.getColorFromNumerical(locations.get(location));
	  return res ; 
  }
  public int getCount(Location location) { 	  
	  return  Math.abs(locations.get(location)); 
  }
}
