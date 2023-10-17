package lab09.ex1;

import java.util.*;

public class iterator {

    public static void main (String args[]){
        VectorGeneric<Integer> vp = new VectorGeneric<>();
        for (int i = 0; i < 50; i++)
            vp.addElem(i);

        // ITERATOR
        Iterator<Integer> it= vp.iterator();
        System.out.println("Percorrer o iterator");
        System.out.println("\n");
        while(it.hasNext()) 
            System.out.print(it.next()+" ");


        // LIST ITERATOR DEFULT
        System.out.println("\n\nList Iterator");
        ListIterator<Integer> vecListIterator = vp.listIterator();
        System.out.println("\nNEXT");
        while (vecListIterator.hasNext())
            System.out.printf("Index %d: %d\n",vecListIterator.nextIndex(),vecListIterator.next());
        
        System.out.println("\nPREVIOUS");
        while (vecListIterator.hasPrevious())
            System.out.printf("Index %d: %d\n",vecListIterator.previousIndex(),vecListIterator.previous());

    
        // LIST ITERATOR WITH INDEX
        System.out.println("\n\nList Iterator with Index");
        ListIterator<Integer> vecListIteratorWithIndex = vp.listIterator(5);
        System.out.println("\nNEXT");
        while (vecListIteratorWithIndex.hasNext())
            System.out.printf("Index %d: %d\n",vecListIteratorWithIndex.nextIndex(),vecListIteratorWithIndex.next());
        
        System.out.println("\nPREVIOUS");
        while (vecListIteratorWithIndex.hasPrevious())
            System.out.printf("Index %d: %d\n",vecListIteratorWithIndex.previousIndex(),vecListIteratorWithIndex.previous());
    }
    
}
