import org.junit.*;
import static org.junit.Assert.*;

/** Testing skeleton for AlphaMon.*/
public class TestAlphamon {
  private Game game;
  
  @Before public void setup() {
    game = new GameImpl();
    game.newGame();
  } 
  @Test public void shouldHaveNoPlayerInTurnAfterNewGame() {
    assertEquals( Color.NONE, game.getPlayerInTurn() );
  }
  @Test public void shouldHaveBlackPlayerInTurnAfterNewGame() {
    game.nextTurn(); // will throw [1,2] and thus black starts
    assertEquals( Color.BLACK, game.getPlayerInTurn() );
  }
  @Test
  public void throwDices1TimeShouldBe1And2(){
	    game.nextTurn();
	    assertArrayEquals(new int[]{2,1}, game.diceThrown());
  }
  @Test
  public void throwDice2TimesShouldBe3And4(){
	    game.nextTurn();
	    game.nextTurn();
		assertArrayEquals(new int[]{4,3}, game.diceThrown());
  }
  @Test
  public void throwDice6times(){
	    game.nextTurn();
	    game.nextTurn();
	    game.nextTurn();
	    game.nextTurn();
	    game.nextTurn();
	    game.nextTurn();
		assertArrayEquals(new int[]{6,5}, game.diceThrown());
		assertEquals(Color.RED, game.winner());	
  }
  @Test(expected = RuntimeException.class)
  public void throwDice7TimesThrowsException() throws RuntimeException{
	    game.nextTurn();
	    game.nextTurn();
	    game.nextTurn();
	    game.nextTurn();
	    game.nextTurn();
	    game.nextTurn();
	    game.nextTurn();
  }
  @Test
  public void throwDice2TimesNoWinner(){
	    game.nextTurn();
	    game.nextTurn();
	    assertEquals(Color.NONE, game.winner());
  }
 @Test
 public void ValidateANewGameAccordingToTheRules()
 {
	 assertEquals(2, game.getCount(Location.R1));
	 assertEquals(Color.BLACK, game.getColor(Location.R1));
	 assertEquals(5, game.getCount(Location.R12));
	 assertEquals(Color.BLACK, game.getColor(Location.R12));
	 assertEquals(3, game.getCount(Location.B8));
	 assertEquals(Color.BLACK, game.getColor(Location.B8));
	 assertEquals(5, game.getCount(Location.B6));
	 assertEquals(Color.BLACK, game.getColor(Location.B6));
	//RED
	 assertEquals(2, game.getCount(Location.B1));
	 assertEquals(Color.RED, game.getColor(Location.B1));
	 assertEquals(5, game.getCount(Location.B12));
	 assertEquals(Color.RED, game.getColor(Location.B12));
	 assertEquals(3, game.getCount(Location.R8));
	 assertEquals(Color.RED, game.getColor(Location.R8));
	 assertEquals(5, game.getCount(Location.R6));
	 assertEquals(Color.RED, game.getColor(Location.R6));
	 //NONE
	 assertEquals(0, game.getCount(Location.B2));
	 assertEquals(Color.NONE, game.getColor(Location.B2));
	 assertEquals(0, game.getCount(Location.B3));
	 assertEquals(Color.NONE, game.getColor(Location.B3));
	 assertEquals(0, game.getCount(Location.B4));
	 assertEquals(Color.NONE, game.getColor(Location.B4));
	 assertEquals(0, game.getCount(Location.B5));
	 assertEquals(Color.NONE, game.getColor(Location.B5));
	 assertEquals(0, game.getCount(Location.B7));
	 assertEquals(Color.NONE, game.getColor(Location.B7));
	 assertEquals(0, game.getCount(Location.B9));
	 assertEquals(Color.NONE, game.getColor(Location.B9));
	 assertEquals(0, game.getCount(Location.B10));
	 assertEquals(Color.NONE, game.getColor(Location.B10));
	 assertEquals(0, game.getCount(Location.B11));
	 assertEquals(Color.NONE, game.getColor(Location.B11));
	
	 assertEquals(0, game.getCount(Location.R2));
	 assertEquals(Color.NONE, game.getColor(Location.R2));
	 assertEquals(0, game.getCount(Location.R3));
	 assertEquals(Color.NONE, game.getColor(Location.R3));
	 assertEquals(0, game.getCount(Location.R4));
	 assertEquals(Color.NONE, game.getColor(Location.R4));
	 assertEquals(0, game.getCount(Location.R5));
	 assertEquals(Color.NONE, game.getColor(Location.R5));
	 assertEquals(0, game.getCount(Location.R7));
	 assertEquals(Color.NONE, game.getColor(Location.R7));
	 assertEquals(0, game.getCount(Location.R9));
	 assertEquals(Color.NONE, game.getColor(Location.R9));
	 assertEquals(0, game.getCount(Location.R10));
	 assertEquals(Color.NONE, game.getColor(Location.R10));
	 assertEquals(0, game.getCount(Location.R11));
	 assertEquals(Color.NONE, game.getColor(Location.R11));
	
 }  
  @Test
  public void MoveFromR1ToR2()
  {
	  game.nextTurn();
	  assertTrue(game.move(Location.R1, Location.R2));
	  assertEquals(Color.BLACK, game.getColor(Location.R1));
	  assertEquals(Color.BLACK, game.getColor(Location.R2));
	  assertEquals(1, game.getCount(Location.R1));
	  assertEquals(1, game.getCount(Location.R2));
	  assertEquals(1, game.getNumberOfMovesLeft());	  
  }
  @Test
  public void MoveFromR1ToR2AndR1ToR3()
  {
	  game.nextTurn();
	  assertTrue(game.move(Location.R1, Location.R2));
	  assertEquals(Color.BLACK, game.getColor(Location.R1));
	  assertEquals(Color.BLACK, game.getColor(Location.R2));
	  assertEquals(1, game.getCount(Location.R1));
	  assertEquals(1, game.getCount(Location.R2));
	  assertEquals(1, game.getNumberOfMovesLeft());	  
	  assertTrue(game.move(Location.R1, Location.R3));
	  assertEquals(Color.BLACK, game.getColor(Location.R2));
	  assertEquals(Color.BLACK, game.getColor(Location.R3));
	  assertEquals(0, game.getCount(Location.R1));
	  assertEquals(1, game.getCount(Location.R2));
	  assertEquals(1, game.getCount(Location.R3));
	  assertEquals(0, game.getNumberOfMovesLeft());	  
  }
  @Test
  public void ShouldBeRedOnSecondTurn()
  {
	  game.nextTurn();
	  game.move(Location.R1, Location.R2);
	  game.move(Location.R1, Location.R3);
	  game.nextTurn();
	  assertEquals(Color.RED, game.getPlayerInTurn());
	  assertArrayEquals(new int[]{4,3}, game.diceThrown());
  }
  @Test
  public void After2ThrowTestDiceValuesLeft()
  {
	  game.nextTurn();
	  game.move(Location.R1, Location.R2);
	  game.move(Location.R1, Location.R3);
	  game.nextTurn();
	  assertArrayEquals(new int[]{4,3}, game.diceValuesLeft());
	  game.move(Location.B1, Location.B4);
	  assertArrayEquals(new int[]{4}, game.diceValuesLeft());
	  game.move(Location.B1, Location.B5);
	  assertArrayEquals(new int[]{}, game.diceValuesLeft());	  
  }
  @Test
  public void InvalidMoveWrongDistanceShouldStillBeTwoMovesLeft()
  {	  
	  game.nextTurn();
	  game.move(Location.R1, Location.R4);
	  assertEquals(2, game.getNumberOfMovesLeft());	  
  }
  @Test
  public void TryingToMoveToAOccupiedLocation()
  {
	  game.nextTurn();
	  game.move(Location.R12, Location.B12);
	  assertEquals(2, game.getNumberOfMovesLeft()); 
  }
  @Test
  public void TryingToMoveWrongWay()
  {
	  game.nextTurn();
	  game.move(Location.R1, Location.B1);
	  assertEquals(2, game.getNumberOfMovesLeft());	  
  }
  @Test
  public void MakeAMoveAndTestThatWeKnowTheValuesOfTheDiceRolled()
  {
	  game.nextTurn();
	  game.move(Location.R1, Location.R2);
	  game.move(Location.R1, Location.R3);
	  assertArrayEquals(new int[]{2,1}, game.diceThrown());
  }
  @Test
  public void PlayTheGame()
  {
	  game.nextTurn();
	  game.move(Location.R1, Location.R2);
	  game.move(Location.R1, Location.R3);
	  assertEquals(Color.BLACK, game.getPlayerInTurn());
	  System.out.println("Thrown: "+game.diceThrown()[0]+","+game.diceThrown()[1]);
	  game.nextTurn();
	  game.move(Location.B12, Location.R10);
	  game.move(Location.R8, Location.R4);
	  assertEquals(Color.RED, game.getPlayerInTurn()); 
	  System.out.println("Thrown: "+game.diceThrown()[0]+","+game.diceThrown()[1]);
		 
	  game.nextTurn();
	  game.move(Location.R3, Location.R9);
	  game.move(Location.R12, Location.B8);
	  assertEquals(Color.BLACK, game.getPlayerInTurn());
	  System.out.println("Thrown: "+game.diceThrown()[0]+","+game.diceThrown()[1]);
	  game.nextTurn();
	  game.move(Location.R10, Location.R8);
	  game.move(Location.R4, Location.R3);
	  assertEquals(Color.RED, game.getPlayerInTurn()); 
	  System.out.println("Thrown: "+game.diceThrown()[0]+","+game.diceThrown()[1]);
		 
	  game.nextTurn();
	  game.move(Location.R9, Location.R12);
	  game.move(Location.R12, Location.B9);
	  assertEquals(Color.BLACK, game.getPlayerInTurn());
	  System.out.println("Thrown: "+game.diceThrown()[0]+","+game.diceThrown()[1]);
	  game.nextTurn();
	  game.move(Location.B12, Location.R8);
	  game.move(Location.B12, Location.R7);
	  assertEquals(Color.RED, game.getPlayerInTurn()); 
	  System.out.println("Thrown: "+game.diceThrown()[0]+","+game.diceThrown()[1]);
	  assertEquals(Color.RED, game.winner());
	  System.out.println("Winner: "+ game.winner());
  }
}
