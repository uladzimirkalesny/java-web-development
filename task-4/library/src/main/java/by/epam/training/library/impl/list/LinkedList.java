package by.epam.training.library.impl.list;

import by.epam.training.library.interfaces.Collection;
import by.epam.training.library.interfaces.Iterator;
import by.epam.training.library.interfaces.List;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {
    private int size = 0;
    private Node<E> first;
    private Node<E> last;
    private int maxSize = Integer.MAX_VALUE - 8;
    private Comparator<E> comparator;

    public LinkedList() {
    }

    public LinkedList(Comparator<E> comparator) {
        this();
        this.comparator = comparator;
    }

    @Override
    public Iterator<E> getIterator() {
        return new LinkedListIterator(0);
    }

    public Iterator<E> getIterator(int index) {
        return new LinkedListIterator(index);
    }

    private E unlink(Node<E> x) {
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    private Node<E> node(int index) {

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    @Override
    public int add(E element) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, element, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;

        if (this.comparator != null) {
            sort(comparator);
        } else {
            defaultSort();
        }

        return find(element);
    }

    public int addWithoutSort(E element) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, element, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;

        return find(element);
    }

    @Override
    public void addAll(Collection<? extends E> collectiom) {
        addAll(collectiom.toArray());
    }

    @Override
    public void addAll(E[] array) {
        if ((array.length + size) > maxSize) {
            throw new ArrayIndexOutOfBoundsException("Maximum array size exceeded!");
        }

        for (int i = 0; i < array.length; i++) {
            this.add(array[i]);
        }

        if (this.comparator != null) {
            sort(comparator);
        } else {
            defaultSort();
        }
    }

    public void addAllWithoutSort(E[] array) {
        if ((array.length + size) > maxSize) {
            throw new ArrayIndexOutOfBoundsException("Maximum array size exceeded!");
        }

        for (int i = 0; i < array.length; i++) {
            this.addWithoutSort(array[i]);
        }
    }

    public void addAllWithoutSort(Collection<? extends E> collectiom) {
        addAllWithoutSort(collectiom.toArray());
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);

        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;

        if (this.comparator != null) {
            sort(comparator);
        } else {
            defaultSort();
        }

        return oldVal;
    }

    public E setWithoutSort(int index, E element) {
        checkElementIndex(index);

        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;

        return oldVal;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);

        return unlink(node(index));
    }

    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }

        first = last = null;
        size = 0;
    }

    @Override
    public int find(E element) {
        int index = 0;

        if (element == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (element.equals(x.item))
                    return index;
                index++;
            }
        }

        return -1;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        E[] elementData = this.toArray();

        Object[] arr = new Object[array.length + elementData.length];

        System.arraycopy(elementData, 0, arr, 0, size);
        System.arraycopy(array, 0, arr, size, array.length);

        LinkedList<T> list = new LinkedList<>();
        list.addAll((T[]) arr);
        list.trim();
        return list.toArray();
    }

    @Override
    public E[] toArray() {
        Object[] elementData = new Object[size];
        int i = 0;

        for (Node<E> x = first; x != null; x = x.next) {
            elementData[i++] = x.item;
        }
        return (E[]) elementData;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void trim() {
        Iterator<E> iterator = getIterator();

        while (iterator.hasNext()) {
            if (iterator.getNext() == null) {
                iterator.remove();
            }
        }
    }

    @Override
    public void sort(Comparator<E> comparator) {
        LinkedListIterator iterator = new LinkedListIterator(0);

        int n = size;
        E[] elementData = this.toArray();
        Object temp;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (comparator.compare(elementData[j - 1], elementData[j]) > 0) {
                    temp = elementData[j - 1];
                    elementData[j - 1] = elementData[j];
                    elementData[j] = (E) temp;
                }
            }
        }

        for (Object element : elementData) {
            iterator.getNext();
            iterator.set((E) element);
        }


    }

    private void defaultSort() {
        LinkedListIterator iterator = new LinkedListIterator(0);

        int n = size;
        E[] elementData = this.toArray();
        Object temp;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (compare(elementData[j - 1], elementData[j]) > 0) {
                    temp = elementData[j - 1];
                    elementData[j - 1] = elementData[j];
                    elementData[j] = (E) temp;
                }
            }
        }

        for (Object element : elementData) {
            iterator.getNext();
            iterator.set((E) element);
        }
    }

    private int compare(Object o1, Object o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        }
        if (o2 == null) {
            return -1;
        }
        return o1.hashCode() - o2.hashCode();
    }

    @Override
    public int getMaxSize() {
        return this.maxSize;
    }

    @Override
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;

        if (maxSize < size) {
            LinkedListIterator iterator = new LinkedListIterator(maxSize);
            while (iterator.hasNext()) {
                iterator.getNext();
                iterator.remove();
            }
        }

        size = this.maxSize;
    }

    private void checkElementIndex(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void filterMatches(Collection<E> collectiom) {
        filterMatches(collectiom.toArray());
    }

    @Override
    public void filterMatches(E[] array) {
        LinkedList<E> linkedList = new LinkedList<>();
        Object[] elementData = this.toArray();

        for (Object element : array) {
            for (Object element2 : elementData) {
                if (element.equals(element2)) {
                    linkedList.add((E) element);
                    break;
                }
            }
        }

        this.clear();
        this.addAll(linkedList);
    }

    @Override
    public void filterDifference(Collection<E> collectiom) {
        filterDifference(collectiom.toArray());
    }

    @Override
    public void filterDifference(E[] array) {
        LinkedList<E> linkedList = new LinkedList<>();
        Object[] elementData = this.toArray();

        for (int i = 0; i < size; ++i) {
            boolean found = false;

            for (int j = 0; j < array.length; ++j) {
                if (array[j].equals(elementData[i])) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                linkedList.add((E) elementData[i]);
            }
        }

        linkedList.trim();

        this.clear();
        this.addAll(linkedList);
    }

    private class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node<E> next;
        private Node<E> prev;
        private int nextIndex;

        LinkedListIterator(int index) {
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return (nextIndex < size && nextIndex >= 0);
        }

        public E getNext() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            prev = next;
            next = next.next;
            nextIndex++;

            return prev.item;
        }

        public void remove() {
            if (prev == null) {
                throw new IllegalStateException();
            }

            Node<E> lastNext = prev.next;
            unlink(prev);

            if (next == prev) {
                next = lastNext;
            } else {
                nextIndex--;
            }

            prev = null;
        }

        public void set(E element) {
            if (prev == null)
                throw new IllegalStateException();
            prev.item = element;
        }

        @Override
        public void addBefore(E element) {
            if (prev.prev != null) {
                Node<E> node = new Node<>(prev.prev, element, prev);
                prev.prev = node;
                node.prev.next = node;
            } else {
                prev.prev = new Node<>(prev, element, null);
            }

            size++;
            nextIndex++;
        }

        @Override
        public void addAfter(E element) {
            if (prev.next != null) {
                Node<E> node = new Node<>(prev.next, element, prev);
                prev.next = node;
                node.next.prev = node;
            } else {
                prev.prev = new Node<>(null, element, prev);
            }

            size++;
        }
    }
}
