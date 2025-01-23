package text.view;

public class Line extends View{
  public int h, asc, w, indent;

  public Line(){indent = INDENT; show();}

  View show() {
    if(L!=null){ // there was a previous line
      Y+=L.h;  // so increase by previous lines height
      L.w = X - B.x - indent; // compute previous line's width.
      B.w = Math.max(B.w, L.w + indent);
    }else{
      Y=B.y;
    }
    L=this;        // Make this the current line
    X=B.x + indent;   // x is set back to left edge of the box AND the indent
    BASE=Y + asc;  // baseline for text is down by ascent of current line
    return next;
  }
}
