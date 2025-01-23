package sandbox;

/*  I want to be able to save ids as strings I thought of writing them in base 62 using the 10 digits
    and the 26 letters in both uppper and lower case. I decided to remove capital 'O' and lower case 'l'
    because they could be confused with 0 and 1. that left me with 60 digits, hence the name B60.

    I then decided that 'I' is also confusing, so I removed it as well. I did not change the name of the
    class to reflect that we are actually working in base 59 now. It is implemented in such a way that
    I can easily add other characters like @$%^ so can easily change the number of characters representing
    digits, thus the value of the BASE really doesn't much matter.
 */
public class B60 {

  // Characters used for Base60 encoding (0-9, A-Z excluding 'O', a-z excluding 'l')
  private static final String B60_CHARS = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
  private static final int BASE = B60_CHARS.length();

  // Convert an integer to a Base60 string
  public static String toB60(int num) {
    if (num == 0) return "0";
    StringBuilder sb = new StringBuilder();
    while (num > 0) {
      sb.append(B60_CHARS.charAt(num % BASE));
      num /= BASE;
    }
    return sb.reverse().toString();
  }

  // Convert a Base60 string to an integer
  public static int toInt(String str) {
    int num = 0;
    for (char ch : str.toCharArray()) {
      num = num * BASE + B60_CHARS.indexOf(ch);
    }
    return num;
  }

  public static void main(String[] args) {
    // Example usage
    int number = 123456;
    String b60String = toB60(number);
    System.out.println("Integer to Base60: " + b60String);

    int convertedNumber = toInt(b60String);
    System.out.println("Base60 to Integer: " + convertedNumber);

    for(int i=0;i<30;i++){System.out.print(' '+toB60(i*1000));}
  }
}

