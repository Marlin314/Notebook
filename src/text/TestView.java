package text;
import graphics.WinApp;import text.view.*;import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TestView extends WinApp {
    public TestView(){super("Test View",1000,500); restart();}

    public void paintComponent(Graphics g){
      g.setColor(Color.WHITE); g.fillRect(0,0,5000,5000); g.setColor(Color.BLACK);
      debugView(g);
      View.G =g;
      View.showAll();

    }

    public static void restart(){
        View.tail = null; View.head = null; new Box(100,100, 0);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        char ch = ke.getKeyChar();
        switch(ch){
            case '@':  restart(); break;
            case '~':  setSty(true); break;
            case '`':  setSty(false); break;
            case '\n': new Line(); break;
            case ' ':  new Word(""); break;
            default: appendChar(ch);
        }
        repaint();
    }

    public static void setSty(boolean setFont){
        if(!(View.tail instanceof Sty)){new Sty(null,null);}
        Sty sty = (Sty) View.tail;
        if(setFont){sty.rndFont();} else {sty.rndColor();}
    }

    public static void appendChar(char ch){
        if(!(View.tail instanceof Word)){return;}
        Word word = (Word) View.tail;
        if(ch=='\b'){
            int n = word.str.length();
            if(n==0){return;}
            word.str = word.str.substring(0,n-1);
        } else {
            word.str += ch;
        }
        System.out.println("append:"+ch);
        word.width = View.G.getFontMetrics().stringWidth(word.str+" ");
    }

    public boolean dragging = false;
    public void mousePressed(MouseEvent me){
        int x=me.getX(), y=me.getY();
        Box B = View.B; if(B==null){return;}
        if(x<B.x && y <B.y && Math.abs(x-B.x)<10 && Math.abs(y-B.y)<10){dragging = true;}
        // hit detection:
        View.hit(x,y);  // this will set TX,TY and run the View list
        repaint();
    }
    public void mouseDragged(MouseEvent me){
        if(!dragging){return;}
        Box B = View.B;
        B.x = me.getX(); B.y = me.getY();
        repaint();
    }
    public void mouseReleased(MouseEvent me){dragging = false;}

    public static void main(String[] args){PANEL=new TestView(); launch();}

    public static void debugView(Graphics g){
        g.setColor(Color.BLUE);
        Box B = View.B; Col C = View.C; Line L = View.L;
        int X=View.X, Y=View.Y, RT=View.RT, RH=View.RH;

        if(View.B != null){
            wBar(g, "B.x", "", B.x, B.w, B.y-70, 20);
            hBar(g, "B.y","", 0,20, B.y, B.h);
        }
        if(View.C != null){
            g.setColor(Color.LIGHT_GRAY);
            if(C.maxW != 0){wBar(g, "C.x", "m", C.x, C.maxW, B.y-45, 20);}
            g.setColor(Color.RED);
            wBar(g, "C.x", "", C.x, C.w, B.y-45, 20);
            hBar(g, "RT","RH", 30,20, RT, RT+RH);
        }
        g.setColor(Color.BLACK);
        wBar(g,"X","", X,0, B.y-20,15);
        hBar(g, "Y","", 80,15, Y, 0);

    }

    public static void wBar(Graphics g, String s1, String s2, int x, int w, int y1, int h){
        FontMetrics fm = g.getFontMetrics();
        int s1w = fm.stringWidth(s1) + 5;
        g.drawString(s1, x-s1w, y1+h); // left label
        g.drawLine(x,y1,x,y1+h); // vertical x1 mark
        g.drawLine(x,y1+h/2,x+w,y1+h/2); // h line
        g.drawLine(x+w,y1,x+w,y1+h); // vertical x2 mark
        g.drawString(s2, x+w+5, y1+h); // right label
    }

    public static void hBar(Graphics g, String s1, String s2, int x, int w, int y, int h){
        g.drawString(s1, x, y); // up label
        g.drawLine(x,y,x+w,y); // top y mark
        g.drawLine(x+w/2,y,x+w/2,y+h); // v line
        g.drawLine(x,y+h,x+w,y+h); // bot mark
        g.drawString(s2, x, y+h+20); // right label
    }
}