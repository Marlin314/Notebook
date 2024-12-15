package text.view;

public class Line extends View{
  public int h, asc, w;
  
  public Line(){show();}

  View show() {
    Y+= (L==null ? 0 : L.h); // y increases by previous lines height
    X=C.x;         // x is set back to left edge of current col
    BASE=Y+ asc;   // baseline for text is down by ascent of current line
    L=this;        // and we make this the current line
    return next;
  }
}
