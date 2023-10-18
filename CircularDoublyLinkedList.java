/*******************************************************************************
 * Data Structures Post-AP
 *******************************************************************************/

import java.util.Iterator;
import java.util.ListIterator;

public class CircularDoublyLinkedList<E> implements MyList<E> 
{
   private Node<E> head;
   private int size = 0; // Number of elements in the list
   
   /** Create an empty list */
   public CircularDoublyLinkedList() {
   }

   /** Create a list from an array of objects */
   public CircularDoublyLinkedList(E[] objects) 
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
         return (head.previous).element;
      }
   }

/******************************************************
   
   /** Add an element to the beginning of the list */
   public void addFirst(E e) 
   {
     Node<E> node = new Node<>(e);
     
     if (head == null)
     {
        head = node;
        
        head.previous = head;
        head.next = head;
        
        size++;
     }
     else
     {
        node.next = head;
        head.previous = node;
        
        head = node;
        size++;
        
        syncEnds();
     }
   }

   /** Add an element to the end of the list */
   public void addLast(E e) 
   {
      Node<E> node = new Node<>(e);
      
      if (head == null)
      {
         head = node;
         
         head.previous = head;
         head.next = head;
      }
      else
      {
         (head.previous).next = node;
         node.previous = head.previous;
         
         head.previous = node;
         node.next = head;
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
      
      size--;
      
      if (head == null)
      {
         head.previous = null;
         head.next = null;
      }
      else
         syncEnds();  
      
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
      
      E deletedElement = (head.previous).element;
      
      Node<E> secondToLastElem = (head.previous).previous;
      head.previous = secondToLastElem;
      secondToLastElem.next = head;
      
      size--;
      
      return deletedElement;
      
   }
   
   /** Sync the next and previous fields of both ends of the list */
   public void syncEnds()
   {
      Node<E> node = head;
      for (int i = 0; i < size - 1; i++)
         node = node.next;
      
      head.previous = node;
      (head.previous).next = head;
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
         
         if (i != (size - 1))
            result.append(", "); // Separate two elements with a comma
         else 
            result.append("]"); // Insert the closing ] in the string
      }
   
      return result.toString();
   }
   
   public void walkBackward()
   { 
      Node<E> current = head.previous;
      for (int i = 0; i < size; i++)
      {
         System.out.print(current.element + " ");
         current = current.previous;
      }
      System.out.println();
   }
   
   public void walkForward()
   {
      Node<E> current = head;
      for (int i = 0; i < size; i++)
      {
         System.out.print(current.element + " ");
         current = current.next;
      }
      System.out.println();
   }
   
   public void addBefore(E item, E newItem)
   {
      for (int i = 0; i < size; i++)
      {
         if (get(i).equals(item))
         {
            add(i, newItem);
            break;
         }
      }
   }
   
   public void rotate()
   {
      E firstElem = head.element;
      removeFirst();
      addLast(firstElem);
   }

   @Override /** Clear the list */
   public void clear() 
   {
      size = 0;
      head = null;
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
      private boolean firstIteration = true;  
      
      public LinkedListIterator() {
      }
       
      public LinkedListIterator(int index) {
         for (int i = 0; i < index; i++)
            current = current.next;
      }
      
      public void setLast() {
         current = head.previous;
      }
   
      @Override
      public E next() {
         E e = current.element;
         current = current.next;
         firstIteration = false;
         return e;
      }
      
      @Override
      public E previous() {
         E e = current.element;
         current = current.previous;
         firstIteration = false;
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
         return (firstIteration == true || current != head);
      }
      
      @Override
      public boolean hasPrevious() {
         return (firstIteration == true || current != head.previous);
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