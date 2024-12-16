package text.view; import java.awt.*;
import java.util.ArrayList;

public abstract class View {
  abstract View show();
  
  // shared static members for all views to use and to update
  public static Graphics G; // need this to hold current font, color and graphics context
  public static int X,Y,BASE;
  public static Box B;
  public static Col C;
  public static int RT,RH; // row top - to reset Y for new col, RH - to update for new row
  public static Line L;
  public static int TX=-1, TY; // target X,Y - if TX >= 0 view.show should add view to hitList if it was a hit.
  public static ArrayList<View> hitList = new ArrayList<View>();
  
  // all Views live in a single list that we can run through calling show
  public static View head, tail; // points to fist View and last View on list.
  
  public View next; // this is normally the next View on the list, but can be overridden by show()
  
  public View(){  // threads new view onto growing list
    if(tail != null){tail.next = this;} else {head = this;} // properly initialize empty list
    tail = this; // tail is always last added View
  }

  public static void showAll(){View v = head; while(v!=null){v=v.show();}} // show the list

  public static void hit(int x, int y){TX = x; TY = y; hitList.clear(); showAll(); TX=-1;}

}
