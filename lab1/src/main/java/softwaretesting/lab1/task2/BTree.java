package softwaretesting.lab1.task2;

public class BTree {
    private Node root;

    public BTree() {
        root = new Node();
        root.numberOfNodes = 0;
        // initial the key value in the root to -1 (null)
        root.key[0] = -1;
    }

    public void insert(int k) {
        Node r = root;
        // split the root node if it is full
        if (r.numberOfNodes == 3) {
            // create a new node s
            Node s = new Node();
            // the new node s will become a new root node after root node r gets spilt
            root = s;
            s.numberOfNodes = 0;
            // the new root node is no longer a isLeaf
            s.isLeaf = false;
            s.children[0] = r;

            // call the split method to split the node r because it is full
            splitChild(s, 1, r);
            // call the insert in none full node method to insert the key k into the new node s
            insertNonFull(s, k);
        } // If the root r node is not full, just insert the key k into it by calling the insert in none full node method
        else {
            insertNonFull(r, k);
        }
    }

    /**
     * The Insert in none full node Method:
     * This method insert a key k into a node that is already full.
     * The insert method takes two arguments: Node x, and integer k as a value
     * The run time of the Insert in None Full Node algorithm is: O(t log n).
     *
     * @param node that the value will be inserted in.
     * @param value the value to be inserted
     */
    private void insertNonFull(Node node, int value) {
        // Allocate the node
        int i = node.numberOfNodes;
        // if the node is a isLeaf, we insert the value into the node
        if (node.isLeaf) {
            while (i >= 1 && value < node.key[i - 1]) {
                node.key[i] = node.key[i - 1];
                i--;
            }
            node.key[i] = value;
            node.numberOfNodes++;
        } /*
         * if the node is not a isLeaf, the value will be inserted into the appropriate isLeaf node in the subtree.
         * Also, it will be checking if the node is full; if yes then split the node.
         */ else {
            while (i >= 1 && value < node.key[i - 1]) {
                i--;
            }
            i++;
            // if the node is full, split it.
            if (node.children[i - 1].numberOfNodes == 3) {
                // call the Split child method to split the node to two nodes
                splitChild(node, i, node.children[i - 1]);
                // Determine which of the two children is now the correct one to descend to.
                if (value > node.key[i - 1]) {
                    i++;
                }
            }
            // Recursive Call
            insertNonFull(node.children[i - 1], value);
        }
    }

    private void splitChild(Node parentNode, int childIndex, Node newChild) {
        // Create a new node z, z will be the new child of node x
        Node z = new Node();
        z.isLeaf = newChild.isLeaf;
        z.numberOfNodes = 1;   // fix it to t-1
        z.key[0] = newChild.key[2];

        // if node y is not a isLeaf, assign the largest t-1 keys and child to the node z.
        if (!newChild.isLeaf) {
            z.children[1] = newChild.children[3];
            z.children[0] = newChild.children[2];
        }
        newChild.numberOfNodes = 1; // fix it to t -1

        // Here we insert node z as a child of node x
        for (int j = parentNode.numberOfNodes + 1; j >= childIndex + 1; j--) {
            parentNode.children[j] = parentNode.children[j - 1];
            parentNode.key[j - 1] = parentNode.key[j - 2];
        }
        // assign the reference to the node z ( node z is a child of the node x)
        parentNode.children[childIndex] = z;
        parentNode.key[childIndex - 1] = newChild.key[1];
        // increment
        parentNode.numberOfNodes++;
    }

    private boolean contains(Node node, int value) {
        // initial i to be equal to 1
        int i = 1;
        // find the smallest index i such that k<= x.keyi, or else set i to x.n ++
        while (i <= node.numberOfNodes && value > node.key[i - 1]) {
            //increment i
            i++;
        }
        // check if the key k is found
        if (i <= node.numberOfNodes && value == node.key[i - 1]) {
            // return true if the key k is found in the B Tree
            return true;
        }
        /* if the x node is an internal node, terminate the search unsuccessful or
         * recurse to search the appropriate subtree of node x.
         */
        // if the x node is not a isLeaf
        if (!node.isLeaf) {
            // recursive call to search in the subtree of the node x
            return contains(node.children[i - 1], value);
        }
        // return false if the key k is not found in the B Tree
        return false;
    }

    public boolean contains(int k) {
        // assign the node x to be the root and start search from the root
        Node x = root;
        // send the root x and the key k as an arguments to the the method search above
        return contains(x, k);
    }

    public boolean delete(int k) {
        Node x = root; // getting the root node
        return delete(x, k); // calling to delete with the root node as starting point
    }

    private boolean delete(Node node, int value) {
        // look up for a key k that will be deleted
        int i = 1;

        // find the smallest index i such that k<= x.keyi, or else set i to x.n ++
        while (i <= node.numberOfNodes && value > node.key[i - 1]) {
            //increment i
            i++;
        }
        /* if the x node is an internal node, terminate the search unsuccessful or
         * recurse to search the appropriate subtree of node x.
         */
        // if the node is a Leaf
        if (node.isLeaf) {
            // recursive call to search in the subtree of the node x
            // check if the key k is found
            if (i <= node.numberOfNodes && value == node.key[i - 1]) {
                // return true if the key k is found in the B Tree
                node.key[i - 1] = 0;
                // Deleting and sifting
                for(int j = i - 1; j < node.numberOfNodes - 1; j++){
                    node.key[j] = node.key[j+1];
                    if(j+1 == node.numberOfNodes - 1)
                        node.numberOfNodes--;
                }
                return true;
            }
        } else {
            return delete(node.children[i - 1], value);
        }
        // return false if the key k is not found in the B Tree
        return false;
    }

    public void print() {
        printBtree(root, "");
    }

    private void printBtree(Node node, String indent) {
        if (node == null) {
            System.out.println(indent + "The B-Tree is Empty");
        } else {
            for (int i = 0; i < node.numberOfNodes; i++) {
                if (!node.isLeaf) {
                    printBtree(node.children[i], indent + "\t");
                }
                if(node.key[i] > 0)
                    System.out.println(indent + node.key[i]);
            }
            if (!node.isLeaf) {
                printBtree(node.children[node.numberOfNodes], indent + "\t");
            }
        }
    }
}
