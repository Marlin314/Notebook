package text.view; import java.awt.*;
import java.util.ArrayList;

public abstract class View {
  // all Views live in a single list. Which we can run calling show
  public static View head, tail; // points to fist View and last View on list.

  public View next; // this is normally the next View on the list, but can be overridden by show()

  abstract View show();   // show returns a next view. Allows a View to decide what should actually come next

  // shared static members for all views to use and to update
  public static Graphics G; // need this to hold current font, color and graphics context
  public static int X,Y,BASE;  // info needed to write a Word
  public static int INDENT;
  public static Box B;         // current Box
  public static Line L;        // current Line
  public static int TX=-1, TY; // target X,Y - if TX >= 0 view.show should add view to hitList if it was a hit.
  public static ArrayList<View> hitList = new ArrayList<>();

  public static void hit(int x, int y){TX = x; TY = y; hitList.clear(); showAll(); TX=0;}

  public View(){  // threads each new view onto growing list
    if(tail != null){tail.next = this;} else {head = this;} // properly initialize empty list
    tail = this; // tail is always last added View
  }

  public static void showAll(){View v = head; while(v!=null){v=v.show();}} // show the list


  //public static void hit(int x, int y){TX = x; TY = y; hitList.clear(); showAll(); TX=-1;}


}
