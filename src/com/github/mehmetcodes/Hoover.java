package com.github.mehmetcodes;
import java.util.HashMap;

class Mypoint{
  public int x;
  public int y;

  public Mypoint(int ix,int iy){
    x = ix;
    y = iy;
  }
  public String toString(){
    return new String(x+","+y);
  }

  @Override
  public int hashCode(){
    int hash = 1;
    hash = hash * 17 + x;
    hash = hash * 31 + y;
    return hash;
  }

  @Override
  public boolean equals(Object that){
    return this.hashCode() == that.hashCode();
  }
}

public class Hoover{

  private Mypoint righttopcorner, currlocation;
  private HashMap<Mypoint, Integer> dirtpatches = new HashMap<Mypoint, Integer>();
  private int cleaned =0;
  /**
  * Resets the clean counter
  *
  * @return void
  */
  public void resetCleaned(){
    cleaned = 0;
  }
  /**
  * Checks if dirt is on the coordinates hoover is
  * currently occupying.  If it is, hoover marks it as cleaned
  * and proudly increments its clean count
  *
  * @return int value 1 if hoover cleaned, 0 if not
  */
  public int checkDirtCollision(){
    int result = 0;
    if(dirtpatches.get(currlocation)!=null && dirtpatches.get(currlocation)==1 ){
       result = dirtpatches.get(currlocation);
       cleaned++;
       dirtpatches.put(currlocation,0);
       //System.out.println("Found dirt at "+currlocation);
    }else{
      //System.out.println("No dirt at "+currlocation);
    }
    return result;
  }
  /**
  * Instructs hoover to move ascending along the y axis
  * If a move is valid, hoover will check for dirt
  * Hoover will not move if the y coordinate exceeds the boundary, dimensions
  * have not yet been set or the start coordinates have not been set.
  *
  * @return int value 1 if hoover moved, 0 if not
  * @see checkDirtCollision
  * @see setStart
  * @see setTopRightCorner
  */
  public int goNorth(){
    int moved = 0;
    if(currlocation != null && righttopcorner !=null){
      if(righttopcorner.y >= currlocation.y + 1)
      {
        currlocation.y++;
        checkDirtCollision();
        moved = 1;
      }
    }
    return moved;
  }
  /**
  * Instructs hoover to move descending along the y axis
  * If a move is valid, hoover will check for dirt
  * Hoover will not move if the y coordinate exceeds the boundary, dimensions
  * have not yet been set or the start coordinates have not been set.
  *
  * @return int value 1 if hoover moved, 0 if not
  * @see checkDirtCollision
  * @see setStart
  * @see setTopRightCorner
  */
  public int goSouth(){
    int moved = 0;
    if(currlocation != null && righttopcorner !=null){
      if( currlocation.y - 1 >= 0)
      {
        currlocation.y--;
        checkDirtCollision();
        moved = 1;
      }
    }
    return moved;
  }
  /**
  * Instructs hoover to move descending along the x axis
  * If a move is valid, hoover will check for dirt
  * Hoover will not move if the x coordinate exceeds the boundary, dimensions
  * have not yet been set or the start coordinates have not been set.
  * @return int value 1 if hoover moved, 0 if not
  * @see checkDirtCollision
  * @see setStart
  * @see setTopRightCorner
  */
  public int goWest(){
    int moved = 0;
    if(currlocation != null && righttopcorner !=null){
      if( currlocation.x - 1 >= 0)
      {
        currlocation.x--;
        checkDirtCollision();
        moved = 1;
      }
    }
    return moved;
  }
  /**
  * Instructs hoover to move ascending along the x axis
  * If a move is valid, hoover will check for dirt
  * Hoover will not move if the x coordinate exceeds the boundary, dimensions
  * have not yet been set or the start coordinates have not been set.
  * @return int value 1 if hoover moved, 0 if not
  * @see checkDirtCollision
  * @see setStart
  * @see setTopRightCorner
  */
  public int goEast(){
    int moved = 0;
    if(currlocation != null && righttopcorner !=null){
      if(righttopcorner.x >= currlocation.x + 1)
      {
        currlocation.x++;
        checkDirtCollision();
        moved = 1;
      }
    }
    return moved;
  }
 /**
 * Creates a dirtpatch att the coordinates, checks to determine if one was
 * contains logic to added at this point and if the hoover is sitting on this point
 *
 * @param  ix  a positive value representing the x coordinate
 * @param  iy a positive value representing the y coordinate
 * @return      void
 */
  public void addDirtpatch(int ix,int iy){
      //System.out.println("Adding dirt ("+ix+","+iy+")");
      if(ix >= 0 && iy >=0 ){
        Mypoint toInsert = new Mypoint(ix,iy);
        //System.out.println(toInsert.equals(currlocation) );
        if(dirtpatches.get(toInsert) != null){

          if(dirtpatches.get(currlocation) == 1 && currlocation.equals(toInsert)){
            dirtpatches.put(toInsert,0);
            cleaned++;
          }
        }else{
          dirtpatches.put(toInsert,1);
          if(dirtpatches.get(currlocation) != null && dirtpatches.get(currlocation) == 1 && currlocation.equals(toInsert)){
            dirtpatches.put(toInsert,0);
            cleaned++;
          }
        }
      }
  }
  /**
  * This helps hoover understand the room boudaries and sets the max
  * and x and y boundary values.  Throws a generic exception with a message
  * requiring positive dimensions if non-positive values are used.
  *
  * @param  ix  a positive value representing the x dimensional length
  * @param  iy a positive value representing the y dimensional length
  * @throws Exception
  * @return      void
  */
  public void setTopRightCorner(int ix,int iy) throws Exception{
    if(ix > 0 && iy > 0){
      righttopcorner = new Mypoint(ix-1,iy-1);
    }else{
      throw new Exception("Dimensions must be positive integers");
    }
  }

  /**
  * Empty constructor, nothing to see here
  *
  */
  public Hoover(){

  }

  /**
  * Sets the start coordinates on the XY plane and resets the cleaning counter
  *
  * @param  ix  a positive value representing the x coordinate
  * @param  iy a positive value representing the y coordinate
  * @throws Exception
  * @return      void
  */
  public void setStart(int ix,int iy) throws Exception{
    if(ix >= 0 && iy >= 0 && ix <= righttopcorner.x && iy <= righttopcorner.y){
      currlocation = new Mypoint(ix,iy);
      resetCleaned();
    }else{
      throw new Exception("Starting coordinates must be positive integers and confine the hoover");
    }
  }
  /**
  * Intended for verbose purposes, gives current location, patches cleaned
  * and the dirtpatches conveniently in a formatted String.
  *
  * @return      String
  */
  public String toString(){
    StringBuilder sb = new StringBuilder("");
    if(currlocation != null){
      sb.append("Current Location: "+currlocation.toString()+"\n");
    }else{
      sb.append("Current Location: N/A (Not set yet)\n");
    }
    sb.append("Patches cleaned: "+cleaned+"\n");
    if( dirtpatches.size() > 0 ){
      sb.append("Dirtpatches: ");
      for( Mypoint key : dirtpatches.keySet() ){
        sb.append( (key).toString() +" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }
  /**
  * Reports current coordinates and clean count in a
  * predetermined format as required into a string.
  *
  * @return      String
  */
  public String status(){
    StringBuilder sb = new StringBuilder("");
    if(currlocation != null){
      sb.append(currlocation.x+" "+currlocation.y+"\n");
    }
    sb.append(cleaned+"\n");
    return sb.toString();
  }

}
