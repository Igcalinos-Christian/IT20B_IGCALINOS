
package ig20b;

import java.util.LinkedList;

public class Ig20B {

    public static void main(String[] args) {
         LinkedList<String> animals = new LinkedList<>();

// insertion
        animals.add("Dog");
        animals.addFirst("Cat");
        animals.addLast("Elephant");
        animals.add(1, "Lion");

        System.out.println("Linked List Original: " + animals);
        System.out.println(animals.size());

//  Seraching  
        if (animals.contains("Lion")) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }

        boolean containslion = animals.contains("Lion");
        System.out.println(containslion);

//        Deletion
//        animals.remove();
//        animals.removeFirst();
//        animals.removeLast();
//        animals.remove(2);
//        System.out.println("LinkedList After Deletion: " + animals);
//        Insertion:    add(); addFirst();  addLast();  add(index, data);
//         Deletion:    remove();   removeFirst();  removeLast(); remove(index);
//          Searching operation:        contains(element); size();
    }
    
}
