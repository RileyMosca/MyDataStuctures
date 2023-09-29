import java.util.ArrayList;
/* List Node Class */
class ListNode {
    /* Node value */
    public int value;

    /* Reference to next node */
    public ListNode next;

    /* Constructor for ListNode */
    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }
}


class LinkedList {

    /* Create head and tail nodes */
    public ListNode headNode;
    public ListNode tailNode;

    public LinkedList() {

        /*
            Initialise a head node
            with a null value and
            a null ptr to a next node
        */
        this.headNode = new ListNode(-1, null);

        /*
            let the tail be
            the head node
        */
        this.tailNode = this.headNode;
    }

    public int get(int index) {

        /* Set start node for iteration */
        ListNode startNode = this.headNode;

        /* Set a index tracker */
        int tracker = 0;

        /* Store result */
        int result = -1;

        while(startNode != null) {

            /* If index matches, store value */
            if(tracker == index) {
                result = startNode.value;
                break;
            }

            /* Go to next node */
            startNode = startNode.next;

            /* Increment tracker */
            tracker++;
        }
        return result;
    }

    public void insertHead(int val) {
        /* Create a new node, with null next ptr */
        ListNode node = new ListNode(val, null);

        /* Set the next node to be the old head node */
        node.next = this.headNode;

        /* Set the head node to be the new node */
        this.headNode =  node;

        /* Handle when list was empty before insertion */
        if (this.tailNode == null) {
            this.tailNode = node;
        }
    }

    public void insertTail(int val) {

        /* Create a new node to be the tail */
        ListNode node  = new ListNode(val, null);

        /* Set the tail's next ptr to the new node */
        this.tailNode.next = node;

        /* Update tail */
        this.tailNode = node;
    }

    public boolean remove(int index) {

        /* We want to remove first element */
        if(index  == 0) {
            ListNode node = this.headNode.next;
            this.headNode.next = null;
            this.headNode = node;
            return true;
        }

        ListNode startNode = this.headNode;

        /* Track across the indices */
        int tracker = 0;

        while(startNode != null) {
            /* If we match the index, we remove */
            if(tracker == index - 1) {
                startNode.next = startNode.next.next;
                return true;
            }

            /* Go to next node */
            startNode = startNode.next;

            /* Iterate tracker */
            tracker++;
        }
        return false;
    }

    public ArrayList<Integer> getValues() {

        /* initialise an array list */
        ArrayList<Integer> values = new ArrayList<>();

        ListNode startNode = this.headNode;

        while(startNode != null) {
            /* add values to node */
            values.add(startNode.value);

            /* Go to next node */
            startNode = startNode.next;
        }

        return values;
    }

    public static void main(String[] args) {
        LinkedList sll = new LinkedList();
        sll.insertHead(1);
        System.out.println(sll.getValues());
    }
}

