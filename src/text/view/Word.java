package text.view;

import java.awt.*;

public class Word extends View{
  public String str = "";
  public int w;

  public Word(String s){setStr(s);}

  public void setStr(String s){
    str = s;
    FontMetrics fm = G.getFontMetrics();
    w = fm.stringWidth(s);
    L.h = Math.max(L.h, fm.getHeight()+fm.getLeading()); // update line height for this word
    L.asc = Math.max(L.asc, fm.getAscent());  // update line ascent for this word
    X+=w;
  }

  public View show() {
    if(TX >= 0){
      if(X<=TX && (X+w)>=TX && Y<=TY && (Y+L.h)>=TY){
        hitList.add(this);
        System.out.println("hit: "+str); // for debugging to show that this word was hit
      }
    }
    if(hitList.contains(this)){G.drawLine(X,Y,X+w,Y);} // more debug of hit
    G.drawString(str,X,BASE);
    X+=w;
    return next;
  }
}

