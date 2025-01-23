package text;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

/*  when I look at this file in IDEA they appear to use the following false color splits
    keywords(including public,for):Orange
    members:Pink
    String literals(and char):Green
    integer literals:Teal
    comments:Gray
    Annotations(like @Override):Yellow
    functionNames in Definition:Blue
    functionNames in calls:White (Black bkg)
    Types in Declarations:White
    operators:White

    what I have done in my BCS online books is a bit different
    I use blue for all function names both in definition and in call (and oddly blue for parameter names)
    I use green for members
    numeric literals:Black
    TypeNames in declarations(and primitive types):Rust(red/brown)
    I had several groups of keywords(
      new:pink
      return for if else:teal
      final:green
      public private protected void:Gray
    )

    I think I might do things differently.
    I like the way that pink for new emphasizes all use of constructors, perhaps constructor names should be pink too.
    I am inclined to use yellow for abstract & Override AND for function names that they modify
    I should Consider using a different color/font for private and public and possibly use that for fNames as well
    I should consider using a single font/Color for static AND static memberNames and static FunctionNames

 */

public class Vocab {
  private static final ArrayList<Word> ALL = new ArrayList<>();
  private static final Vocab ALL_WORDS = new Vocab();
  public static final Vocab JAVA_KEYWORDS = new Vocab();
  static {
    JAVA_KEYWORDS.addWords("package import class interface enum extends implements throws".split(" "));
    JAVA_KEYWORDS.addWords("int byte short long double float boolean char void".split(" "));
    JAVA_KEYWORDS.addWords("abstract static final native strictfp synchronized transient volatile".split(" "));
    JAVA_KEYWORDS.addWords("if else switch case default for do while break continue return try catch finally throw".split(" "));
    JAVA_KEYWORDS.addWords("goto const assert new this super instanceof".split(" "));
  }
  protected final TreeMap<String,Integer> map = new TreeMap<>();

  public Vocab(){}
  public Vocab(String[] a){addWords(a);}

  public boolean contains(String s){return map.containsKey(s);}

  public void addWord(String str){ // adding to vocab does not increment word nUse count
    if(!contains(str)){
      if(!ALL_WORDS.contains(str)){
        map.put(str,Word.newWord(str));
      }
    }
  }
  public void addWords(String[] a){for(int i=0;i<a.length;i++){addWord(a[i]);}}

  public String toString(){
    String res = "";
    Set<String> ks = map.keySet();
    for(String s : ks){res += " "+s+"("+ALL.get(map.get(s)).nUse+")";}
    return res;
  }

  public static class Word{
    private String str;
    private int nUse=1;

    private Word(String s){str=s; ALL.add(this); ALL_WORDS.map.put(s,ALL.size()-1);}

    public static Integer newWord(String s){ // factory method returns location of word
      Integer res = ALL_WORDS.map.get(s);
      if(res!=null){ALL.get(res).nUse++; return res;}
      // if s contains a space, it is a phrase. split into words and add those as well
      int k = s.indexOf(' ');
      if(k>-1){String[] a = s.split(" "); for(int i=0;i<a.length;i++){newWord(a[i]);}}

      res = ALL.size();
      new Word(s);
      return res;
    }
  }


  public static void main(String[] args){
    Vocab key = new Vocab("public protected private".split(" "));
    Vocab cont = new Vocab("it's we'll can't won't isn't don't we're I'll you'll".split(" "));
    key.addWords("class enum interface".split(" "));
    String[] para = "this is a paragraph with the same words over and over and this is another paragraph".split(" ");
    for(int i=0; i<para.length; i++){Word.newWord(para[i]);}
    Word.newWord("and now");
    System.out.println(key);
    System.out.println(ALL_WORDS);
  }
}
