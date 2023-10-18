/*******************************************************************************
 * Data Structures Post-AP
 *******************************************************************************/

import java.util.Iterator;
import java.util.ListIterator;

public class DoublyLinkedList<E> implements MyList<E> 
{
   private Node<E> head;
   private Node<E> tail;
   private int size = 0; // Number of elements in the list
   
   /** Create an empty list */
   public DoublyLinkedList() {
   }

   /** Create a list from an array of objects */
   public DoublyLinkedList(E[] objects) 
   {
      for (int i = 0; i < objects.length; i++)
         add(objects[i]); 
   }

   @Override /** Return the number of elements in this list */
   public int size() 
   {
      return size;
   }

   /** Return the head element in the list */
   public E getFirst() 
   {
      if (size == 0)
      {
         return null;
      }
      else 
      {
         return head.element;
      }
   }

   /** Return the last element in the list */
   public E getLast() 
   {
      if (size == 0)
      {
         return null;
      }
      else 
      {
         return tail.element;
      }
   }

   /** Add an element to the beginning of the list */
   public void addFirst(E e) 
   {
     Node<E> node = new Node<>(e);
     
     node.next = head;     
     head = node;
     if (node.next != null)
         (node.next).previous = node;
     
     size++;
     
     if (tail == null)
         tail = head;
   }

   /** Add an element to the end of the list */
   public void addLast(E e) 
   {
      Node<E> node = new Node<>(e);
      
      if (tail == null)
      {
         head = node;
         tail = node;
      }
      else
      {
         tail.next = node;
         node.previous = tail;
         tail = node;
      }
      
      size++;
   }

   @Override /** Add a new element at the specified index 
    * in this list. The index of the head element is 0 */
   public void add(int index, E e) 
   {
      if (index == 0)
      {
         addFirst(e);
      }
      else if (index == size)
      {
         addLast(e);
      }
      else
      {
         Node<E> node = head;
         for (int i = 1; i < index; i++)
         {
            node = node.next;
         }
         
         Node<E> tempNode = node.next;
         
         Node<E> newNode = new Node<>(e);
         node.next = newNode;
         newNode.previous = node;
         
         newNode.next = tempNode;
         tempNode.previous = newNode;
         
         size++;
      }
   }

   /** Remove the head node and
    *  return the object that is contained in the removed node. */
   public E removeFirst() 
   {
      if (size == 0)
         return null;
      
      E deletedElement = head.element;
      head = head.next;
      head.previous = null;
      
      if (head == null)
         tail = null;
         
      size--;
      
      return deletedElement;
   }

   /** Remove the last node and
    * return the object that is contained in the removed node. */
   public E removeLast() 
   {
      if (size == 0 || size == 1)
      {
         E element = removeFirst();
         return element;
      }
      
      // More efficient deletion using Doubly implementation:
      
      E deletedElement = tail.element;
      tail = tail.previous;
      tail.next = null;
      
      size--;
      
      return deletedElement;
      
   }

   @Override /** Remove the element at the specified position in this 
    *  list. Return the element that was removed from the list. */
   public E remove(int index) 
   {   
      if (index < 0 || index >= size)
      {
         return null;
      }
      else if (index == 0)
      {
         return removeFirst();
      }
      else if (index == size - 1)
      {
         return removeLast();
      }
      else
      {
         Node<E> node = head;
         
         for (int i = 1; i < index; i++)
         {
            node = node.next;
         }
         
         Node<E> deletedNode = node.next;
         node.next = deletedNode.next;
         (node.next).previous = node;
         
         size--;

         return deletedNode.element;
      }
   }

   /** Removes first node containing element passed as parameter
     * Return the element that was removed from the list. */
   public E delete(E e) 
   {
      for (int i = 0; i < size; i++)
      {
         if (get(i) == e)
         {
            E deletedElement = remove(i);
            return deletedElement;
         }
      }
      return null;
   }

/******************************************************/

   @Override /** Override toString() to return elements in the list */
   public String toString() 
   {
      StringBuilder result = new StringBuilder("[");
   
      Node<E> current = head;
      for (int i = 0; i < size; i++) 
      {
         result.append(current.element);
         current = current.next;
         if (current != null) 
         {
            result.append(", "); // Separate two elements with a comma
         }
         else 
         {
            result.append("]"); // Insert the closing ] in the string
         }
      }
   
      return result.toString();
   }
   
   public void printBackwards()
   {
      String elements = "";
      
      Node<E> current = tail;
      for (int i = 0; i < size; i++)
      {
         System.out.print(current.element + " ");
         current = current.previous;
      }
      System.out.println();
   }

   @Override /** Clear the list */
   public void clear() 
   {
      size = 0;
      head = tail = null;
   }

   @Override /** Return true if this list contains the element e */
   public boolean contains(Object e) 
   {
      E elem = (E) e;
      Iterator<E> iterator = iterator();
      while (iterator.hasNext())
      {
         if (iterator.next() == elem)
            return true;
      }
      return false;
   }

   @Override /** Return the element at the specified index */
   public E get(int index) 
   {
      if (index < 0 || index > size)
         throw new IndexOutOfBoundsException();
      
      Node<E> node = head;
      for (int i = 0; i < index; i++)
      {
         node = node.next;
      }
      return node.element;
   }

   @Override /** Return the index of the first matching element in 
    *  this list. Return -1 if no match. */
   public int indexOf(Object e) 
   {
      E elem = (E) e;
      
      for (int i = 0; i < size; i++)
      {
         if (get(i) == elem) 
            return i;
      }
   
      return -1;
   }

   @Override /** Return the index of the last matching element in 
    *  this list. Return -1 if no match. */
   public int lastIndexOf(E e) 
   {
      E elem = (E) e;
      
      for (int i = size - 1; i >= 0; i--)
      {
         if (get(i) == elem) 
            return i;
      }
      
      return -1;
   }

   @Override /** Replace the element at the specified position 
    *  in this list with the specified element. */
   public E set(int index, E e) 
   {
      if (index < 0 || index > size)
         throw new IndexOutOfBoundsException();
         
      Node<E> node = head;
      for (int i = 1; i < index; i++)
      {
         node = node.next;
      }
      
      Node<E> replacedNode = node.next;
      node.next = new Node<>(e);
      
      (node.next).previous = node;
      (node.next).next = replacedNode.next;
      (replacedNode.next).previous = node.next;
      
      return replacedNode.element;
   }

   public ListIterator<E> listIterator() {
      return new LinkedListIterator(); 
   }
  
   public ListIterator<E> listIterator(int index) {
      return new LinkedListIterator(index); 
   }

   @Override /** Override iterator() defined in Iterable */
   public Iterator<E> iterator() 
   {
      return new LinkedListIterator();
   }
   
   private class LinkedListIterator implements java.util.ListIterator<E>
   {
      private Node<E> current = head; // Current index     
      
      public LinkedListIterator() {
      }
       
      public LinkedListIterator(int index) {
         for (int i = 0; i < index; i++)
            current = current.next;
      }
      
      public void setLast() {
         current = tail;
      }
   
      @Override
      public E next() {
         E e = current.element;
         current = current.next;
         return e;
      }
      
      @Override
      public E previous() {
         E e = current.element;
         current = current.previous;
         return e;
      }
      
      @Override
      public void add(E e) {
         Node node = new Node<>(e);
         Node tempNode = current.next;
         
         current.next = node;
         node.previous = current;
         node.next = tempNode;
         tempNode.previous = node;
         
         current = current.next;
      }
      
      @Override
      public void set(E e) {
         current.element = e;
      }
      
      @Override
      public void remove() {
         (current.previous).next = current.next;
         (current.next).previous = current.previous;
      }
      
      @Override
      public int nextIndex() {
         if (hasNext())
            return indexOf(next());
         return 0;
      }
      
      @Override
      public int previousIndex() {
         if (hasPrevious())
            return indexOf(previous());
         return 0;
      }
      
      @Override
      public boolean hasNext() {
         return (current != null);
      }
      
      @Override
      public boolean hasPrevious() {
         return (current != null);
      }
   }
   
   private static class Node<E> 
   {
      E element;
      Node<E> next;
      Node<E> previous;
   
      public Node(E element) 
      {
         this.element = element;
      }
   }
}