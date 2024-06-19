import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Novelds {
    private ArrayList<Integer> heap;
    private int numChildrenExp;
    private int numChildren;

    public Novelds(int numChildrenExp) {
        this.numChildrenExp = numChildrenExp;
        this.numChildren = (int) Math.pow(2, numChildrenExp);
        this.heap = new ArrayList<>();
    }

    private int parentIndex(int index) {
        return (index - 1) / numChildren;
    }

    private int childIndex(int parentIndex, int childNumber) {
        return numChildren * parentIndex + 1 + childNumber;
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int popMax() {
        if (heap.size() == 0) {
            throw new NoSuchElementException("Heap is empty.");
        }
        
        int maxValue = heap.get(0);
        int lastValue = heap.remove(heap.size() - 1);
        
        if (heap.size() > 0) {
            heap.set(0, lastValue);
            heapifyDown(0);
        }
        
        return maxValue;
    }

    private void heapifyUp(int index) {
        int parentIndex = parentIndex(index);
        if (index > 0 && heap.get(index) > heap.get(parentIndex)) {
            swap(index, parentIndex);
            heapifyUp(parentIndex);
        }
    }

    private void heapifyDown(int index) {
        int maxIndex = index;

        for (int i = 0; i < numChildren; i++) {
            int childIdx = childIndex(index, i);
            if (childIdx < heap.size() && heap.get(childIdx) > heap.get(maxIndex)) {
                maxIndex = childIdx;
            }
        }

        if (maxIndex != index) {
            swap(index, maxIndex);
            heapifyDown(maxIndex);
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void main(String[] args) {
        // Novelds heap = new Novelds(1); 
        // heap.insert(5);
        // heap.insert(4);
        // heap.insert(3);
        // heap.insert(2);
        // heap.insert(1);

        // System.out.println(heap.popMax()); 
        // System.out.println(heap.popMax()); 
    }
}
