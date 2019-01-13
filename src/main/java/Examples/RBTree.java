package Examples;

/***
 * What is RedBlack Tree?
 * Red black tree is een zelf balancerende binary search tree.
 *
 * Hoe werkt het?
 * De red black tree voldoet aan de volgende regels:
 * 1) Every node has a color either red or black.
 * 2) Root of tree is always black.
 * 3) There are no two adjacent red nodes (A red node cannot have a red parent or red child).
 * 4) Every path from a node (including root) to any of its descendant NULL node has the same number of black nodes.
 *
 * Er wordt een node geinsert in de tree als de waarde van de node kleiner is dan de root dan komt de node
 * aan de linker kant en anders aan de rechter kant
 * Per keer wordt er gekeken naar de "oom" van de node
 * Als de oom de kleur rood heeft:
 * Verander de kleur van ouder en oom naar zwart
 * Verander de kleur van de opa naar rood
 *
 * Als de oom de kleur zwart heeft:
 * als de ouder de linker kind is van opa en x is de linker kind van ouder. roteer links linker
 * als de ouder de linker kind is van opa en x is de rechter kind van ouder roteer linker rechts
 * als de ouder de rechter kind is van opa en x is de rechter kind van ouder roteer rechts rechts
 * als de ouder de linker kind is van opa en x is de linker kind van ouder roteer rechts links
 *
 * als de linkerkant niet in balans is met de rechterkant of andersom dan wordt er geroteerd en alle nodes worden
 * opnieuw gekleurd.
 *
 * Doordat de node
 */
public class RBTree {
    private RedBlackNode current;
    private RedBlackNode parent;
    private RedBlackNode grand;
    private RedBlackNode great;
    private RedBlackNode header;
    private static RedBlackNode nullNode;

    /* static initializer for nullNode */
    static {
        nullNode = new RedBlackNode(0);
        nullNode.left = nullNode;
        nullNode.right = nullNode;
    }

    /* Black - 1  RED - 0 */
    static final int BLACK = 1;
    static final int RED = 0;

    /* Constructor */
    public RBTree(int negInf) {
        header = new RedBlackNode(negInf);
        header.left = nullNode;
        header.right = nullNode;
    }

    /* Function to check if tree is empty */
    public boolean isEmpty() {
        return header.right == nullNode;
    }

    /* Make the tree logically empty */
    public void makeEmpty() {
        header.right = nullNode;
    }

    /* Function to insert item */
    public void insert(int item) {
        current = parent = grand = header;
        nullNode.element = item;
        while (current.element != item) {
            great = grand;
            grand = parent;
            parent = current;
            current = item < current.element ? current.left : current.right;
            // Check if two red children and fix if so
            if (current.left.color == RED && current.right.color == RED)
                recolor(item);
        }
        // Insertion fails if already present
        if (current != nullNode)
            return;
        current = new RedBlackNode(item, nullNode, nullNode);
        // Attach to parent
        if (item < parent.element)
            parent.left = current;
        else
            parent.right = current;
        recolor(item);
    }

    private void recolor(int item) {
        // Do the color flip
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED) {
            // Have to rotate
            grand.color = RED;
            if (item < grand.element != item < parent.element)
                parent = rotate(item, grand);  // Start dbl rotate
            current = rotate(item, great);
            current.color = BLACK;
        }
        // Make root black
        header.right.color = BLACK;
    }

    private RedBlackNode rotate(int item, RedBlackNode parent) {
        if (item < parent.element) {
            if (item < parent.left.element) {
                return rotateWithLeftChild(parent.left);
            } else {
                return rotateWithRightChild(parent.left);
            }
        } else {
            if (item < parent.right.element) {
                return rotateWithLeftChild(parent.right);
            } else {
                return rotateWithRightChild(parent.right);
            }
        }
    }

    private RedBlackNode rotateWithLeftChild(RedBlackNode leftChild) {
        RedBlackNode switchChild = leftChild.left;
        leftChild.left = switchChild.right;
        switchChild.right = leftChild;
        return switchChild;
    }

    private RedBlackNode rotateWithRightChild(RedBlackNode rightChild) {
        RedBlackNode switchChild = rightChild.right;
        rightChild.right = switchChild.left;
        switchChild.left = rightChild;
        return switchChild;
    }

    public boolean search(int value) {
        return search(header.right, value);
    }

    private boolean search(RedBlackNode root, int value) {
        boolean found = false;
        while ((root != nullNode) && !found) {
            int rval = root.element;
            if (value < rval)
                root = root.left;
            else if (value > rval)
                root = root.right;
            else {
                found = true;
                break;
            }
            found = search(root, value);
        }
        return found;
    }

    /* Function for inorder traversal */
    public void inorder() {
        inorder(header.right);
    }

    private void inorder(RedBlackNode root) {
        if (root != nullNode) {
            inorder(root.left);
            String c = "Black";
            if (root.color == RED)
                c = "Red";
            System.out.print(root.element + "" + c + " ");
            inorder(root.right);
        }
    }

    public void preorder() {
        preorder(header.right);
    }

    private void preorder(RedBlackNode root) {
        if (root != nullNode) {
            String c = "Black";
            if (root.color == RED)
                c = "Red";
            System.out.print(root.element + "" + c + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    /* Function for postorder traversal */
    public void postorder() {
        postorder(header.right);
    }

    private void postorder(RedBlackNode root) {
        if (root != nullNode) {
            postorder(root.left);
            postorder(root.right);
            String c = "Black";
            if (root.color == RED)
                c = "Red";
            System.out.print(root.element + "" + c + " ");
        }
    }
}