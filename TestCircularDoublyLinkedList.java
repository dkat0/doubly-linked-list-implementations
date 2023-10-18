/*******************************************************************************
 * Data Structures Post-AP
 *******************************************************************************/

public class TestCircularDoublyLinkedList 
{
   /** Main method */
   public static void main(String[] args) 
   {
      System.out.println("CircularDoublyLinkedList Testing!\n");
      
      // Create a list for strings
      CircularDoublyLinkedList<String> list = new CircularDoublyLinkedList<>();
   
      // Add elements to the list
      list.add("America"); // Add America to the list
      System.out.println("(1) " + list);
   
      list.add(0, "Canada"); // Add Canada to the beginning of the list
      System.out.println("(2) " + list);
   
      list.add("Russia"); // Add Russia to the end of the list
      System.out.println("(3) " + list);
   
      list.addLast("France"); // Add France to the end of the list
      System.out.println("(4) " + list);
   
      list.add(2, "Germany"); // Add Germany to the list at index 2
      System.out.println("(5) " + list);
   
      list.add(5, "Norway"); // Add Norway to the list at index 5
      System.out.println("(6) " + list);
   
      list.add(0, "Poland"); // Same as list.addFirst("Poland")
      System.out.println("(7) " + list);
   
      // Remove elements from the list
      list.remove(0); // Same as list.remove("Poland") in this case
      System.out.println("(8) " + list);
   
      list.remove(2); // Remove the element at index 2
      System.out.println("(9) " + list);
      
      list.addFirst("Spain"); // Add France to the beginning of the list
      System.out.println("(10) " + list);
      
      list.removeFirst(); // Remove first element in list
      System.out.println("(11) " + list);  
      
      list.remove(list.size() - 1); // Remove the last element
      System.out.print("(12) " + list + "\n(13) ");
      
      for (String s : list)
         System.out.print(s.toUpperCase() + " ");
      
      list.clear();
      System.out.println("\nAfter clearing the list, the list size is " + list.size());
      
      /** Test cases for addAll, removeAll, retainAll, and containsAll methods. */
      CircularDoublyLinkedList<String> list1 = new CircularDoublyLinkedList<>(new String[]{"red", "green", "blue", "magenta", "cyan"});
      CircularDoublyLinkedList<String> list2 = new CircularDoublyLinkedList<>(new String[]{"green", "magenta", "yellow", "black", "white"});
      
      System.out.println("\n(14) list1: " + list1);
      System.out.println("(15) list2: " + list2);
      
      CircularDoublyLinkedList<String> listTemp = new CircularDoublyLinkedList<>(new String[]{"red", "green", "blue", "magenta", "cyan"});
      listTemp.addAll(list2);
      System.out.println("(16) list1.addAll(list2): " + listTemp);
      
      listTemp = new CircularDoublyLinkedList<>(new String[]{"red", "green", "blue", "magenta", "cyan"});
      listTemp.removeAll(list2);
      System.out.println("(17) list1.removeAll(list2): " + listTemp);
      
      listTemp = new CircularDoublyLinkedList<>(new String[]{"red", "green", "blue", "magenta", "cyan"});
      listTemp.retainAll(list2);
      System.out.println("(18) list1.retainAll(list2): " + listTemp);
      
      listTemp = new CircularDoublyLinkedList<>(new String[]{"red", "green", "blue", "magenta", "cyan"});
      System.out.println("(19) list1.containsAll(list2): " + listTemp.containsAll(list2));
      
      /** Test toArray method.*/
      System.out.print("(20) list1.toArray(): [");
      String[] array = list1.toArray(new String[list1.size()]);
      
      for (String s : array)
         System.out.print(s.toUpperCase() + " ");
      System.out.print("]\n");
      
      System.out.print("\n(21) list1.walkBackward(): ");
      list1.walkBackward();
      
      System.out.print("(22) list1.walkForward(): ");
      list1.walkForward();
      
      System.out.print("(23) list1.addBefore('blue', 'purple'): ");
      list1.addBefore("blue", "purple");
      System.out.println(list1);
      
      System.out.print("(23) list1.rotate(): ");
      list1.rotate();
      System.out.println(list1); 
   } 
}