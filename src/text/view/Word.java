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
    if(TX >= 0){
      if(X<=TX && (X+width)>=TX && Y<=TY && (Y+L.h)>=TY){
        hitList.add(this);
        System.out.println("hit: "+str); // for debugging to show that this word was hit
      }
    } else {
      G.drawString(str,X,BASE);
      if(hitList.contains(this)){G.drawLine(X,Y,X+width,Y);} // more debug for hit
    }
    X+=width;
    return next;
  }
}
