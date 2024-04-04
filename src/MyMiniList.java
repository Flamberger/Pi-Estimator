import java.util.Arrays;
import java.util.Objects;

public class MyMiniList <T> implements MiniList<T> {

    private T[] objectStore;
    private int listSize;

    public MyMiniList() {
        objectStore = (T[]) new Object[10];
        listSize = 0;
    }

    @Override
    public void add(T element) {
        if (listSize >= objectStore.length) {
            int newCapacity = objectStore.length + 10;
            T[] newArray = Arrays.copyOf(objectStore, newCapacity);
            objectStore = newArray;
        }
        objectStore[listSize] = element;
        listSize++;
    }

    @Override
    public T get(int index) {
        try {
            Objects.checkIndex(index, objectStore.length);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        return objectStore[index];
    }

    @Override
    public int getIndex(T element) {
        for (int i = 0; i <= listSize; i++) {
            if (objectStore[i] == element || objectStore[i].equals(element)) return i;
        }
        return -1;
    }

    @Override
    public void set(int index, T element) {
        try {
            Objects.checkIndex(index, objectStore.length);
            objectStore[index] = element;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds. Could not insert " + element + " at index " + index);
            System.out.println("list size is " + objectStore.length);
        }
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public T remove(int index) {
        try {
            Objects.checkIndex(index, objectStore.length);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        T obj = objectStore[index];
        for (int i = index; i <= listSize; i++) {
            if (i < listSize) {
                objectStore[i] = objectStore[i+1];
            } else {
                objectStore[i] = null;
            }
        }
        listSize--;
        return obj;
    }

    @Override
    public boolean remove(T element) {
        T obj = remove(getIndex(element));
        if (obj == null) {
            return false;
        } else { return true; }
    }

    @Override
    public void clear() {
        objectStore = (T[]) new Object[10];
        listSize = 0;
    }
}
