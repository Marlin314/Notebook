package text.view;

import java.awt.*;
import java.util.Random;

public class Sty extends View{
  public Font font;
  public Color color;

  public Sty(Font font, Color color){this.font = font; this.color=color; show();}
  @Override
  View show() {
    if(font != null){G.setFont(font);}
    if(color != null){G.setColor(color);}
    if(next == null){G.drawString(""+font, X, BASE);} // for
    return next;
  }

  public void rndColor(){color = new Color(rnd(256),rnd(256),rnd(256));}
  public void rndFont(){font = new Font(rndName(), rnd(4), 12+rnd(30));}

  public static Random RND = new Random();
  public static int rnd(int max){return RND.nextInt(max);}

  public static String[] fontNames = {"Ariel", "Comic Sans", "Times"};
  public static String rndName(){return fontNames[rnd(fontNames.length)];}
}
