public class ArrayDeque<T> implements Deque<T> {
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
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int index = getBehind(nextFirst);
        for (int i = 0; i < size; i++) {
            a[i] = items[index];
            index = getBehind(index);
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }    

    // first implement all the methods, and then consider resizing problem.

    // Adds an item of type T to the front of the deque.
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextFirst] = item;
        nextFirst = getPrev(nextFirst);
        size += 1;
    }

    // Adds an item of type T to the back of the deque.
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextLast] = item;
        nextLast = getBehind(nextLast);
        size += 1;
    }

    // Returns true if deque is empty, false otherwise.
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    // Returns the number of items in the deque.
    @Override
    public int size() {
        return size;
    }

    // Prints the items in the deque from first to last, separated by a space.
    @Override
    public void printDeque() {
        int counter = size;

        int index = getBehind(nextFirst);

        while (counter > 0) {
            System.out.print(items[index] + " ");
            counter -= 1;
            index = getBehind(index);
        }
        System.out.println();
    }

    // Removes and returns the item at the front of the deque.
    // If no such item exists, returns null.
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        int index = getBehind(nextFirst);
        T first = items[index];
        items[index] = null;
        nextFirst = index;

        if (items.length >= 16 && size < items.length * RATIO) {
            resize(items.length / 2);
        }
        return first;
    }

    // Removes and returns the item at the back of the deque.
    // If no such item exists, returns null.
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        int index = getPrev(nextLast);
        T last = items[index];
        items[index] = null;
        nextLast = index;

        if (items.length >= 16 && size < items.length * RATIO) {
            resize(items.length / 2);
        }
        return last;
    }

    // Gets the item at the given index, where 0 is the front,
    // 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    @Override
    public T get(int index) {
        int i = getBehind(nextFirst);
        while (index != 0) {
            i = getBehind(i);
            index--;
        }
        return items[i];
    }
}
