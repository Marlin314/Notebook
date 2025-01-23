package sandbox;

import java.util.LinkedList;
import java.util.List;

/* a BK Tree is a search tree algorithm that assumes that you have a distance function to see how close two
 * elements in space are. I.e. it assumes that the tree elements live in a metric space. Furthermore, it assumes
 * that the metric is discrete, returning integers not real numbers.
 *
 * The principle utility of this tree is that unlike binary search trees or hash tables you can do approximate
 * searches. This is a mechanism that you need if you are trying to build a spelling corrector. If the misspelled
 * word is NOT in the dictionary you can look for all words that ARE in the dictionary that are withing a tolerance
 * level (a distance) from the misspelled word.
 *
 * This particular class in its test code uses the Levenshtein metric, sometimes called "minimum edit distance"
 * between two words, because it measures the distance between two words as the number of letter changes, insertions,
 * or deletion that are required to convert one word into another. Distance of zero means the words are identical.
 *
 * It is here largely because I am experimenting with the notion of using something like this as a base class for
 * keeping dictionaries, both a large English language one that I can use for my paragraphs of text talking about
 * code and possibly smaller ones that are project specific, knowing only the class/function/variable names used
 * in a single project.
 */

// Class representing the BKTree
public class BKTree<T> {
  private final Node<T> root;

  // Interface for Metric to measure distance
  public interface Metric<T> {int dist(T a, T b);}
  private final Metric<T> metric;

  public BKTree(T value, Metric<T> metric) {
    this.root = new Node<>(value);
    this.metric = metric;
    treeSize = 1;
  }

  // Nested Node class
  static class Node<T> {
    T value;
    List<ChildNode<T>> children;

    Node(T value) {
      this.value = value;
      this.children = new LinkedList<>();
    }
  }

  // Nested ChildNode class to represent sparse child nodes
  static class ChildNode<T> {
    int dist;
    Node<T> node;

    ChildNode(int dist, Node<T> node) {
      this.dist = dist;
      this.node = node;
    }
  }

  // Add a new value to the tree, ensuring no duplicates
  public void add(T value) {
    add(root, value);
    treeSize++;
  }

  private void add(Node<T> node, T value) {
    int dist = metric.dist(node.value, value);
    for (ChildNode<T> child : node.children) {
      if (child.dist == dist) {
        // If we find a node with the same distance, we need to check if it's the same value
        if (metric.dist(child.node.value, value) == 0) {
          // Value already exists in the tree
          return;
        }
        add(child.node, value);
        return;
      }
    }
    node.children.add(new ChildNode<>(dist, new Node<>(value)));
  }

  public static int treeSize=0;
  public static int searchCost;
  // Search the tree for values within a certain distance
  public List<T> search(T value, int tolerance) {
    List<T> results = new LinkedList<>();
    searchCost=0;
    search(root, value, tolerance, results);
    return results;
  }

  private void search(Node<T> node, T value, int tolerance, List<T> results) {
    int dist = metric.dist(node.value, value);
    if (dist <= tolerance) {
      results.add(node.value);
    }
    for (ChildNode<T> child : node.children) {
      if (child.dist >= dist - tolerance && child.dist <= dist + tolerance) {
        search(child.node, value, tolerance, results);
      }
    }
  }

  public static void include(BKTree<String> st, String words){
    String[] strs = words.split(" ");
    for(int i=0;i<strs.length;i++){st.add(strs[i]);}
    System.out.println("treeSize: "+treeSize);
  }
  public static void main(String[] args) {
    // Example usage
    Metric<String> metric = (a, b) -> {return levenshteinDist(a, b);};

    BKTree<String> bkTree = new BKTree<>("hello", metric);
    bkTree.add("hell");
    bkTree.add("halo");
    bkTree.add("help");

    include(bkTree,"I want to make the tree significantly longer see how search times vary");
    include(bkTree,"now is time for all good men come aid party");
    include(bkTree,"ten hundred thousand million billion trillion one two three four five six seven eight nine");
    include(bkTree,"pine oak peach pear apple birch maple alder willow tree morphology");
    include(bkTree,"mathematics physics chemistry biology history english french german philosophy economics");


    System.out.println("Search results hello1: " + bkTree.search("hello", 1) + " cost:"+searchCost);
    System.out.println("Search results hell0: " + bkTree.search("hello", 0) + " cost:"+searchCost);
    System.out.println("Search results hell0: " + bkTree.search("hell", 2) + " cost:"+searchCost);
    System.out.println("Search results econ: " + bkTree.search("biology", 1) + " cost:"+searchCost);

  }

  // Helper function to compute the Levenshtein distance
  private static int levenshteinDist(String a, String b) {
    searchCost++; // instrumented search counts number of calls to this metric function
    int[][] dp = new int[a.length() + 1][b.length() + 1];
    for (int i = 0; i <= a.length(); i++) {
      for (int j = 0; j <= b.length(); j++) {
        if (i == 0) {
          dp[i][j] = j;
        } else if (j == 0) {
          dp[i][j] = i;
        } else {
          dp[i][j] = Math.min(dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1),
              Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
        }
      }
    }
    return dp[a.length()][b.length()];
  }




}

