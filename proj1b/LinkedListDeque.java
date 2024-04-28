public class LinkedListDeque<T> implements Deque<T> {
    private  class TNode {
        private T item;
        private TNode prev;
        private TNode next;

        TNode(TNode p, T i, TNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private TNode sentinel;
    private int size;

    // Creates an empty deque.
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    // Adds an item of type T to the front of the deque.
    @Override
    public void addFirst(T item) {
        TNode first = new TNode(sentinel, item, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }

    // Adds an item of type T to the back of the deque.
    @Override
    public void addLast(T item) {
        TNode last = new TNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }

    // Returns true if deque is empty, false otherwise.
    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
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
        TNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    // Removes and returns the item at the front of the deque.
    // If no such item exists, returns null.
    @Override
    public T removeFirst() {
        TNode p = sentinel.next;
        if (p == sentinel) {
            return null;
        }

        size -= 1;
        p.next.prev = sentinel;
        sentinel.next = p.next;
        p.prev = null;
        p.next = null;
        return p.item;
    }

    // Removes and returns the item at the back of the deque.
    // If no such item exists, returns null.
    @Override
    public T removeLast() {
        TNode p = sentinel.prev;
        if (p == sentinel) {
            return null;
        }

        size -= 1;
        sentinel.prev = p.prev;
        p.prev.next = sentinel;
        p.prev = null;
        p.next = null;
        return p.item;
    }

    // Gets the item at the given index, where 0 is the front,
    // 1 is the next item, and so forth.
    // If no such item exists, returns null.
    @Override
    public T get(int index) {
        TNode p = sentinel.next;
        while (index != 0) {
            if (p == sentinel) {
                return null;
            }
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    // Same as get, but uses recursion.
    public T getRecursive(int index) {
        TNode p = sentinel.next;
        return getRecHelper(p, index);
    }

    private T getRecHelper(TNode p, int index) {
        if (p == sentinel) {
            return null;
        }
        if (index == 0) {
            return p.item;
        }
        return getRecHelper(p.next, index - 1);
    }

}
