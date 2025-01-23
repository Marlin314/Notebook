package text.view;

public class Box extends View{
  public int x,y;  // left and top of Box: absolute coordinates
  public int w,h;  // adjusted as you add views to this box
  public int maxW; // if==0 then NO word wrap, if >0 it is width limit (almost!)

  public Box(int x, int y){
    this.x=x; this.y=y;
    show();
    L=new Line();
  }

  public void wordWrap(String str){
    if(maxW > 0 && X - B.x + G.getFontMetrics().stringWidth(str) > maxW ){new Line();}
    new Word(str);
  }

  View show() {B=this; X= x; Y=y; L=null; INDENT=0; return next;}
}
