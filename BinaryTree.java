class TreeNode {
    /* Integer variable, value for the Node */
    int val;

    /* Left Child of the Node */
    TreeNode left;

    /* Right Child of the Node */
    TreeNode right;

    /**
     * Default constructor of the TreeNode
     * that initialises the Node with a given
     * value, but sets the left and right
     * children as null by default.
     */
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    /* Root of the Binary Tree */
    public TreeNode root;

    /* Number of the elements in the Binary Tree */
    public int size;

    /**
     * Default constructor for binary tree
     * it initialises the root as null by
     * default
     */
    public BinaryTree() {

        this.root = null;
        this.size = 0;
    }

    /**
     * This method constructs a TreeNode with
     * the given integer value, it will then
     * check where to add the node, either
     * at the root, or a non-null left/right
     * child node, based on the integer value
     * being added to the Binary Tree. This
     * is done recursively.

     * @param val  value of the node to add.
     * @return The Root of the tree with the
     *         newly added Tree Node
     */
    public TreeNode insertRecursive(TreeNode node, int val) {
        /* Checking if node is null, then adding */
        if (node == null) {
            return new TreeNode(val);
        }

        if (val < node.val) {
            /* Insert into left subtree with recursion */
            node.left = insertRecursive(node.left, val);
        } else if (val > node.val) {
            /* Insert into right subtree with recursion */
            node.right = insertRecursive(node.right, val);
        }
        return node;
    }

    /**
     * This method calls upon the recursive
     * insert to update the value of the root
     * node with the return of the function

     * @param val  value of the node to add.
     */
    public void insert(int val) {
        this.root = insertRecursive(this.root, val);
    }

    /**
     * This method searches for a given Node
     * in the Binary Tree which matches the input,
     * and deletes it from the Binary Tree. This
     * is done recursively and returns the TreeNode
     * after the deletion.

     * @param val  value of the Node we wish to remove
     *             from the Binary Tree
     * @return The Root of the tree with the
     *         newly added Tree Node
     */
    private TreeNode deleteRecursive(TreeNode root, int val) {
        /* Root node is null, return null */
        if (root == null) {
            return null;
        }

        /*
            Search for the node to delete using
            recursion, values less than the root
            will call recursion on the left, else
            on the right child/ subtree.
        */
        if (val < root.val) {
            root.left = deleteRecursive(root.left, val);
        } else if (val > root.val) {
            root.right = deleteRecursive(root.right, val);
        }
        /* Node value is found, check the number of children */
        else {
            /* Case 1: One or No children */
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            /* Case 2: Two Children, get in-order successor */
            TreeNode minValueNode = root.right;

            while (minValueNode.left != null) {
                minValueNode = minValueNode.left;
            }

            /* Successor found, store it as root */
            root.val = minValueNode.val;

            /* Delete the in-order successor */
            root.right = deleteRecursive(root.right, minValueNode.val);
        }
        return root;
    }


    /**
     * This method calls upon the recursive
     * delete to update the value of the root
     * node with the return of the function.

     * @param val  value of the node to add.
     */
    public void delete(int val) {
        this.root = deleteRecursive(this.root, val);
    }

    /**
     * This method searches the binary tree
     * to find the value specified, and then
     * returns a boolean if it exists in the
     * Binary Tree or not.

     * @param val  value we are searching
     *             for in the Binary Tree
     * @return true if the value exists, else false
     */
    public boolean search(int val) {

        /* Return false on a null Root Node */
        if(this.root == null) {
            return false;
        }

        /* Initialise a checking node */
        TreeNode node = this.root;

        while(node != null) {
            /* Check if we have found our value */
            if (node.val == val) {
                return true;
            }

            /* Check left child, value is lower */
            if(val < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }

    /***
     * This method returns an ArrayList representation
     * of the Binary Tree.
     * @param node The node we are checking
     * @param arrayList The ArrayList we will update
     * @return An ArrayList of all values.
     */
    public ArrayList binaryTreeArray(TreeNode node, ArrayList arrayList) {
        /* Base Case: Node is null */
        if (node == null) {
            return arrayList;
        }

        /* Process the array in-order traversal */
        arrayList = binaryTreeArray(node.left, arrayList);
        arrayList.addItem(node.val);
        arrayList = binaryTreeArray(node.right, arrayList);


        return arrayList;
    }

    /***
     * This method prints the in-order traversal
     * of the Binary Tree to standard output.
     * @param node The node we are checking.
     */
    public void inorderTraversal(TreeNode node) {
        if(this.root == node) {
            System.out.print("\nIn-order traversal: ");
        }
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.print(node.val + " ");
        inorderTraversal(node.right);
    }

    /***
     * This method prints the pre-order traversal
     * of the Binary Tree to standard output.
     * @param node The node we are checking.
     */
    public void preorderTraversal(TreeNode node) {
        if(this.root == node) {
            System.out.print("\nPre-order traversal: ");
        }

        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    /***
     * This method prints the post-order traversal
     * of the Binary Tree to standard output.
     * @param node The node we are checking.
     */
    public void postorderTraversal(TreeNode node) {
        if(this.root == node) {
            System.out.print("\nPost-order traversal: ");
        }

        if (node == null) {
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.val + " ");
    }
}
