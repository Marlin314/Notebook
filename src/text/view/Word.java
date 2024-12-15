package text.view;

import java.awt.*;

public class Word extends View{
  public String str = "";
  public int width;

  public Word(String s){setStr(s);}

  public void setStr(String s){
    str = s;
    FontMetrics fm = G.getFontMetrics();
    width = fm.stringWidth(s);
    L.h = Math.max(L.h, fm.getHeight()+fm.getLeading()); // update line height for this word
    L.asc = Math.max(L.asc, fm.getAscent());  // update line ascent for this word
    L.w = X-C.x;
    C.w = Math.max(C.w, L.w);
    X+=width;
  }

  View show() {
    G.drawString(str,X,BASE);
    X+=width; //
    return next;
  }
}
