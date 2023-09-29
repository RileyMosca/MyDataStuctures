import java.util.Timer;

public class Algorithms {

    /***
     * Binary Search Method: O(log(n)) algorithm
     * that finds the index of the target value
     * for a given sorted array
     * @param array A sorted input array
     * @param target A target value
     * @return The index of the target value that lies
     * in the sorted input array.
     */
    public int binarySearch(ArrayList<Integer> array, int target, boolean displayIteration) {

        /* Declaring default starting points for min/max */
        int min = 0;
        int max = array.count - 1;
        int iterCount = 0;

        /* Declaring an averaging factor */
        final double FACTOR = 0.5;

        /* Use Binary Search to find the index */
        while (min <= max) {
            iterCount++;

            /* Updating Guess */
            int guess = (int) Math.floor(FACTOR * (min + max));

        /*
            Return guess on success, else
            manipulate min/max values for
            new guess and try again.
        * */
            if (array.get(guess) == target) {
                if(displayIteration) {
                    System.out.println("Binary Iteration Count = " + iterCount);
                }
                return guess;
            } else if (array.get(guess) < target) {
                min = guess + 1; // Adjust the min index
            } else {
                max = guess - 1; // Adjust the max index
            }
        }
        /* Element was not found */
        return -1;
    }

    /***
     * This is the standard search method that checks
     * across every element in the array from the 0th
     * index to the Nth index (N being size - 1) for
     * a target value.
     * @param array An array (unsorted or sorted)
     * @param target Target value we are searching for
     * @return Index of the position the target lies.
     */
    public int linearSearch(ArrayList<Integer> array, int target, boolean displayIteration) {
        int iterCount = 0;
        /* Iterate across all indices */
        for(int idx  = 0; idx < array.count; idx++) {
            iterCount++;
            if(array.get(idx) == target) {
                if(displayIteration) {
                    System.out.println("Linear Iteration Count = " + iterCount);
                }
                return  idx;
            }
        }
        /* Element was not found */
        return -1;
    }

    /***
     *
     * @param array An array to search.
     * @param target A target to find.
     * @return
     */
    public int interpolationSearch(ArrayList<Integer> array, int target, boolean displayIteration) {

        /* Declaring left and right indices */
        int leftIdx = 0;
        int rightIdx  = array.count - 1;
        int iterCount = 0;

        while((leftIdx <= rightIdx)
                && (array.get(leftIdx) <= target)
                && (target <= array.get(rightIdx))) {
            iterCount++;
            if(leftIdx == rightIdx) {
                if(array.get(leftIdx) == target) {
                    if(displayIteration) {
                        System.out.println("Interpolation Iteration Count = " + iterCount );
                    }
                    return  leftIdx;
                } else {
                    return  -1;
                }
            }

            /* Calculations for interpolation of position */
            int rightLeftDiff_Val = array.get(rightIdx) - array.get(leftIdx);
            int rightLeftDiff_Idx = rightIdx - leftIdx;
            int targetDiff = target -  array.get(leftIdx);
            int position = leftIdx + ((targetDiff * rightLeftDiff_Idx) / (rightLeftDiff_Val));

            if(array.get(position) == target) {
                if(displayIteration) {
                    System.out.println("Interpolation Iteration Count = " + iterCount);
                }
                return position;
            } else if(array.get(position) < target) {
                leftIdx = position + 1;
            } else {
                rightIdx = position - 1;
            }
        }
        /* Element was not found */
        return -1;
    }

    public ArrayList<Integer> insertionSort(ArrayList<Integer> array) {
        return null;
    }

    public ArrayList<Integer> generateSortedArray(int elements) {

        /* Create a default list of size 100 first */
        ArrayList<Integer> list =  new ArrayList<>(100);

        /* Add all elements */
        for(int i  = 0; i < elements; i++) {
            list.addItem(i + 1);
        }

        return list;
    }

    public static void main(String[] args) {
        /* Create an Algorithms object */
        Algorithms algo = new Algorithms();

        /* Array size */
        int arr_size = 20000;

        /* Create a sorted array */
        ArrayList<Integer> sorted = algo.generateSortedArray(arr_size);

        /* Storing total runtimes for all searches */
        long lin_sor_tot = 0;
        long bin_sor_tot = 0;
        long inter_sor_tot = 0;
        int iterations = sorted.count;
        int searching_element = 13627;

        /* Display searching and array data */
        System.out.println("\nThis is an analysis of searching algorithms. Searching for\n"
                + searching_element + " in a Sorted array of "
                + arr_size + " elements, starting from 0.");

        /* Declare Start, end and total vars */
        long endT; long startT; long total;

        for (int i = 0; i < iterations; i++) {
            /* Test the Linear Method */
            startT = System.nanoTime();
            algo.linearSearch(sorted, searching_element, false);
            endT = System.nanoTime();
            total = (endT - (startT));
            lin_sor_tot += total;

            /* Test the Binary Search Method */
            startT = System.nanoTime();
            algo.binarySearch(sorted, searching_element, false);
            endT = System.nanoTime();
            total = (endT - (startT));
            bin_sor_tot += total;

            /* Test the Binary Search Method */
            startT = System.nanoTime();
            algo.interpolationSearch(sorted, searching_element, false);
            endT = System.nanoTime();
            total = (endT - (startT));
            inter_sor_tot += total;
        }

        System.out.println("\n----- Average Times From Search -----");
        System.out.println("Linear Search (sorted): " +
                (lin_sor_tot / iterations) + "ns");

        System.out.println("Binary Search (sorted): " +
                (bin_sor_tot / iterations) + "ns");

        System.out.println("Interpolation Search (sorted): " +
                (inter_sor_tot / iterations) + "ns");

        System.out.println("\n----- Iteration Count/ Index Value Found From Search -----");
        System.out.println("Linear val (Sorted): " + algo.linearSearch(sorted,
                searching_element, true) + "\n");

        System.out.println("Binary val (Sorted): " + algo.binarySearch(sorted,
                searching_element,true)+ "\n");

        System.out.println("Interpolation val (Sorted): " + algo.interpolationSearch(sorted,
                searching_element,true)+ "\n");

    }
}


