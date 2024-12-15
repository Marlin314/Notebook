package text.view;

public class Col {
  public int x, w = 0, maxW = 0; // left (maintained as global state), width, max for wordWrap
  public Col next=null; // node: this is NOT a view. It is a list of columns held by some Box
  
  public Col(Col last){if(last!=null){last.next = this;}} // append to end of list
}
