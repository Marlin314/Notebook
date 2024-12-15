package text.view;

public class Box extends View{
  public int x,y; // left and top of Box: absolute coordinates
  public int w,h; // adjusted as you add views to this box
  public Col c; // first col (possibly only col) for box
  
  public Box(int x, int y, int nCol){
    this.x=x; this.y=y;
    c = new Col(null); Col last = c;  for(int i = 1; i<nCol; i++){last = new Col(last);}
    show();
    L=new Line();
  }

  View show() {B=this; X= x; Y=y; C=c ; C.x=X; L=null; RT=0; RH=0; return next;}
}
