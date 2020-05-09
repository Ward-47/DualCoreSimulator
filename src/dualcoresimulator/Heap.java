/*
* Creating a program that simulates a Dual Core Simulator
* @author Ward Leavines
* @see DualCoreSimulator, HeapException, HeapAPI, Heap, PCB
*/

package dualcoresimulator;

import java.util.*;

/**
 * This class models an array-based binary heap that implements the 
 * HeapAPI interface. The array holds objects that implement the 
 * parameterized Comparable interface.
 * @param <E> the heap element type
 */
public class Heap<E extends Comparable<E>> implements HeapAPI<E>
{    
   /**
    * A complete tree stored in an array list representing this 
    * binary heap
    */
   private ArrayList<E> tree;
    
   /**
    * Constructs an empty heap
   */
   public Heap()
   {
      tree=new ArrayList();
   }

   public boolean isEmpty()
   {
      return tree.isEmpty();
      
   }

   //Still needs work! 
   public void insert(E obj)
   {

    if (isEmpty())

    {

    tree.add(obj);

    }

    else

    {

    tree.add(obj);

    int place = tree.size()-1;

    int parent = (place -1)/2;

  

    while(parent >= 0 && (tree.get(place).compareTo(tree.get(parent))) > 0)

    {

     swap(place, parent);

     place=parent;

     parent = (place -1)/2;

    }

  }

}


   public E remove() throws HeapException
   {
      E root = tree.get(0);
      tree.set(0 , tree.get(tree.size()-1));
      reheapify(0);
      return root;
   }

   // still needs work
   public E peek() throws HeapException
   {
      if (size() == 0){
          throw new HeapException();
      }
      return tree.get(0);
   }      

   public int size()
   {
      return(tree.size()); // still needs work
   }
   
   /**
    * Swaps a parent and child elements of this heap at the specified indices
    * @param place an index of the child element on this heap
    * @param parent an index of the parent element on this heap
    */
   private void swap(int place, int parent)
   {
      E temp = tree.get(place);
      tree.set(place, tree.get(parent));
      tree.set(parent, temp);
   }

   /**
    * Rebuilds the heap to ensure that the heap property of the tree is preserved.
    * @param root the root index of the subtree to be rebuilt
    */
   private void reheapify(int root)
   {
       int child = (2 * root) + 1;
       if (child < (size() -1))
       {
            
            
           if (child +1 <= size())
           {
               
               if(tree.get(child + 1).compareTo(tree.get(child)) > 0)
                       {
                           child = child + 1;
                       }
        
           }
           if (tree.get(root).compareTo(tree.get(child)) < 0)
                   {
                       swap(root,child);
                       reheapify(child);
                   }
           
       }

    }
public String toString(){
       return tree.toString();}
}

  









