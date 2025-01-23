package text.view;
import java.awt.*;
import java.util.Stack;

public class Nest extends View{
  public static String[] pairs = "{ } ( ) [ ] < > \" \" ' ' /* */ // \n".split(" ");

  private int ndx;  // if even is open, if odd is close
  private int w;
  private boolean hid = false;
  private Nest mate; // the opener or closer to this

  private static Stack<Nest> openers = new Stack<>();

  private Nest(int ndx){
    this.ndx = ndx;
    FontMetrics fm = G.getFontMetrics();
    w = fm.stringWidth(pairs[ndx]);
    L.h = Math.max(L.h, fm.getHeight()+fm.getLeading());
    L.asc = Math.max(L.asc, fm.getAscent());
    show();
  }

  private static Nest newOpen(int i){Nest n = new Nest(i); openers.push(n); return n;}

  public static void openC(){newOpen(0);} // Curly Brace
  public static void openP(){newOpen(2);} // Parens
  public static void openK(){newOpen(4);} // bracKet
  public static void openA(){newOpen(6);} // Angle bracket
  public static void openQ(){newOpen(8);} // Quotes
  public static void openT(){newOpen(10);} // Tic
  public static void openB(){newOpen(12);} // Block comment
  public static void openE(){newOpen(14);} // EOL comment

  public static void close(){if(openers.empty()){return;} openers.pop().setMate();}
  private void setMate(){Nest c = new Nest(ndx + 1); mate = c; c.mate = this; }

  public boolean isOpen(){return (ndx&1) == 0;}
  public void togHid(){if(isOpen()){hid = !hid;} else {mate.hid = !mate.hid;}}

  public void adjustIndent(){
    int KK = 25;
    if(ndx == 0){INDENT += KK;}
    if(ndx == 1 && INDENT >=KK){INDENT -= KK; if(L.w==0){L.indent=INDENT;}}
  }

  public View show() {
    G.drawString(pairs[ndx],X,BASE);
    adjustIndent();
    // if Nest is hit, it toggles hid on the opener
    if (TX >= 0 && TX >= X && TX <= X + w && TY >= Y && TY <= Y + L.h) {hitList.add(this); togHid();}
    X+=w;
    if(isOpen() && hid && mate != null){return mate;}
    return next;
  }
}
