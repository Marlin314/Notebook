package sandbox;

import java.util.Random;


public class SkipList<T> {
  public static class Node<T> {
    int key;
    T value;
    Node<T>[] forward;

    @SuppressWarnings("unchecked")
    public Node(int key, T value, int level) {
      this.key = key;
      this.value = value;
      this.forward = new Node[level + 1];
    }
  }

  private static final int MAX_LEVEL = 16;
  private final Node<T> head = new Node<>(Integer.MIN_VALUE, null, MAX_LEVEL);
  private int level = 0;
  private final Random random = new Random();

  private int randomLevel() {
    int lvl = 0;
    while (random.nextInt(2) == 1 && lvl < MAX_LEVEL) {
      lvl++;
    }
    return lvl;
  }

  public void insert(int key, T value) {
    Node<T>[] update = new Node[MAX_LEVEL + 1];
    Node<T> x = head;
    for (int i = level; i >= 0; i--) {
      while (x.forward[i] != null && x.forward[i].key < key) {
        x = x.forward[i];
      }
      update[i] = x;
    }
    x = x.forward[0];
    if (x == null || x.key != key) {
      int lvl = randomLevel();
      if (lvl > level) {
        for (int i = level + 1; i <= lvl; i++) {
          update[i] = head;
        }
        level = lvl;
      }
      x = new Node<>(key, value, lvl);
      for (int i = 0; i <= lvl; i++) {
        x.forward[i] = update[i].forward[i];
        update[i].forward[i] = x;
      }
    }
  }

  public T search(int key) {
    Node<T> x = head;
    for (int i = level; i >= 0; i--) {
      while (x.forward[i] != null && x.forward[i].key < key) {
        x = x.forward[i];
      }
    }
    x = x.forward[0];
    if (x != null && x.key == key) {
      return x.value;
    }
    return null;
  }

  public void delete(int key) {
    Node<T>[] update = new Node[MAX_LEVEL + 1];
    Node<T> x = head;
    for (int i = level; i >= 0; i--) {
      while (x.forward[i] != null && x.forward[i].key < key) {
        x = x.forward[i];
      }
      update[i] = x;
    }
    x = x.forward[0];
    if (x != null && x.key == key) {
      for (int i = 0; i <= level; i++) {
        if (update[i].forward[i] != x) break;
        update[i].forward[i] = x.forward[i];
      }
      while (level > 0 && head.forward[level] == null) {
        level--;
      }
    }
  }

  public static void main(String[] args) {
    SkipList<Integer> skipList = new SkipList<>();
    skipList.insert(3, 30);
    skipList.insert(6, 60);
    skipList.insert(7, 70);
    skipList.insert(9, 90);
    skipList.insert(12, 120);
    skipList.insert(19, 190);

    System.out.println("Search for key 6: " + skipList.search(6)); // Output: 60
    System.out.println("Search for key 15: " + skipList.search(15)); // Output: null

    skipList.delete(3);
    System.out.println("Search for key 3 after deletion: " + skipList.search(3)); // Output: null
  }
}

