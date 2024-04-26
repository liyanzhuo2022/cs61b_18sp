public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double RATIO = 0.25;

    // Constructor.
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }

    // Helper methods, to get the next node
    private int getPrev(int x) {
        x -= 1;
        if (x < 0) {
            x += items.length;
        }
        return x;
    }

    private int getBehind(int x) {
        x += 1;
        if (x > items.length - 1) {
            x -= items.length;
        }
        return x;
    }

    // Resizes the list.
    private void resize(int capability) {
        T[] a = (T[]) new Object[capability];
        int index = getBehind(nextFirst);
        for (int i = 0; i < size; i++) {
            a[i] = items[index];
            index = getBehind(index);
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    // Helper method for resize ratio.
    private void resizeCheck() {
        double ratio = size / items.length;
        if (size >= 16 && ratio < RATIO) {
            resize(size * 2);
        }
    }

    // first implement all the methods, and then consider resizing problem.

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextFirst] = item;
        nextFirst -= getPrev(nextFirst);
        size += 1;
    }

    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast += getBehind(nextLast);
        size += 1;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    // Returns the number of items in the deque.
    public int size() {
        return size;
    }

    // Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        int counter = size;

        int index = getBehind(nextFirst);

        while (counter > 0) {
            System.out.print(items[index] + " ");
            counter -= 1;
            index = getBehind(index);
        }
    }

    // Removes and returns the item at the front of the deque.
    // If no such item exists, returns null.
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        int index = getBehind(nextFirst);
        T first = items[index];
        items[index] = null;
        nextFirst = index;
        resizeCheck();
        return first;
    }

    // Removes and returns the item at the back of the deque.
    // If no such item exists, returns null.
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        int index = getPrev(nextLast);
        T last = items[index];
        items[index] = null;
        nextLast = index;
        resizeCheck();
        return last;
    }

    // Gets the item at the given index, where 0 is the front,
    // 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        int i = getBehind(nextFirst);
        while (index != 0) {
            i = getBehind(i);
            index--;
        }
        return items[i];
    }
}
