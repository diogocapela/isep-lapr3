package lapr.project.tree;

import java.util.List;

public class TreeUtils {

    public static <E extends Comparable<E>> Iterable<E> sortByBST(List<E> listUnsorted) {
        BST<E> tree = new BST<>();
        for (E e : listUnsorted) {
            tree.insert(e);
        }
        return tree.inOrder();
    }
}
