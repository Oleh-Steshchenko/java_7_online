package mathlist;
import java.util.Arrays;
public class MatList<E extends Number> {
    private E[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 100;
    public MatList() {
        elements = createArray();
        size = 0;
    }
    @SafeVarargs
    public MatList(E[]... numbers) {
        this();
        for (E[] arr : numbers) {
            add(arr);
        }
    }
    @SafeVarargs
    public MatList(MatList<E>... numbers) {
        this();
        for (MatList<E> list : numbers) {
            add((E[]) list.toArray());
        }
    }
    @SuppressWarnings("unchecked")
    private E[] createArray() {
        return (E[]) new Number[MatList.DEFAULT_CAPACITY];
    }
    public void add(E n) {
        if (size == elements.length) {
            resizeArray();
        }
        elements[size] = n;
        size++;
    }
    @SafeVarargs
    public final void add(E... n) {
        for (E num : n) {
            add(num);
        }
    }
    @SafeVarargs
    public final void join(MatList<E>... ml) {
        for (MatList<E> list : ml) {
            for (int i = 0; i < list.size; i++) {
                add(list.elements[i]);
            }
        }
    }
    @SafeVarargs
    public final void intersection(MatList<E>... ml) {
        boolean[] removeFlags = new boolean[size];
        for (MatList<E> list : ml) {
            for (int i = 0; i < size; i++) {
                if (!list.contains(elements[i])) {
                    removeFlags[i] = true;
                }
            }
        }
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            if (!removeFlags[i]) {
                elements[newSize] = elements[i];
                newSize++;
            }
        }
        size = newSize;
    }
    public void sortDesc() {
        sortDesc(0, size - 1);
    }
    public void sortDesc(int firstIndex, int lastIndex) {
        if (firstIndex < 0 || lastIndex >= size || firstIndex >= lastIndex) {
            throw new IndexOutOfBoundsException();
        }
        E[] sublist = Arrays.copyOfRange(elements, firstIndex, lastIndex + 1);
        Arrays.sort(sublist, (a, b) -> Double.compare(b.doubleValue(), a.doubleValue()));
        for (int i = firstIndex; i <= lastIndex; i++) {
            elements[i] = sublist[i - firstIndex];
        }
    }
    public void sortDesc(E value) {
        Arrays.sort(elements, (a, b) -> {
            if (a == null && b == null) {
                return 0;
            } else if (a == null) {
                return 1;
            } else if (b == null) {
                return -1;
            } else {
                boolean aEqualsValue = a.equals(value);
                boolean bEqualsValue = b.equals(value);
                if (aEqualsValue && !bEqualsValue) {
                    return -1;
                } else if (!aEqualsValue && bEqualsValue) {
                    return 1;
                } else {
                    return -Double.compare(a.doubleValue(), b.doubleValue());
                }
            }
        });
    }
    public void sortAsc() {
        sortAsc(0, size - 1);
    }
    public void sortAsc(int firstIndex, int lastIndex) {
        if (firstIndex < 0 || lastIndex >= size || firstIndex >= lastIndex) {
            throw new IndexOutOfBoundsException();
        }
        E[] sublist = Arrays.copyOfRange(elements, firstIndex, lastIndex + 1);
        Arrays.sort(sublist);
        for (int i = firstIndex; i <= lastIndex; i++) {
            elements[i] = sublist[i - firstIndex];
        }
    }
    public void sortAsc(E value) {
        Arrays.sort(elements, (a, b) -> {
            if (a == null && b == null) {
                return 0;
            } else if (a == null) {
                return 1;
            } else if (b == null) {
                return -1;
            } else if (a.equals(value) && !b.equals(value)) {
                return -1;
            } else if (!a.equals(value) && b.equals(value)) {
                return 1;
            } else {
                return Double.compare(a.doubleValue(), b.doubleValue());
            }
        });
    }
    public Number get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }
    public Number getMax() {
        if (size == 0) {
            return null;
        }
        Number max = elements[0];
        for (int i = 1; i < size; i++) {
            E element = elements[i];
            if (element.doubleValue() > max.doubleValue()) {
                max = element;
            }
        }
        return max;
    }
    public Number getMin() {
        if (size == 0) {
            return null;
        }
        Number min = elements[0];
        for (int i = 1; i < size; i++) {
            E element = elements[i];
            if (element.doubleValue() < min.doubleValue()) {
                min = element;
            }
        }
        return min;
    }
    public Number getAverage() {
        if (size == 0) {
            return null;
        }
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += elements[i].doubleValue();
        }
        return sum / size;
    }
    public Number getMedian() {
        if (size == 0) {
            return null;
        }
        E[] sortedArray = Arrays.copyOf(elements, size);
        Arrays.sort(sortedArray);
        int midIndex = size / 2;
        if (size % 2 == 0) {
            return (sortedArray[midIndex - 1].doubleValue() + sortedArray[midIndex].doubleValue()) / 2;
        } else {
            return sortedArray[midIndex].doubleValue();
        }
    }
    public Number[] toArray() {
        return Arrays.copyOf(elements, size);
    }
    public Number[] toArray(int firstIndex, int lastIndex) {
        if (firstIndex < 0 || lastIndex >= size || firstIndex >= lastIndex) {
            throw new IndexOutOfBoundsException();
        }
        return Arrays.copyOfRange(elements, firstIndex, lastIndex + 1);
    }
    public MatList<E> cut(int firstIndex, int lastIndex) {
        if (firstIndex < 0 || lastIndex >= size || firstIndex >= lastIndex) {
            throw new IndexOutOfBoundsException();
        }
        MatList<E> result = new MatList<>();
        result.elements = Arrays.copyOfRange(elements, firstIndex, lastIndex + 1);
        result.size = lastIndex - firstIndex + 1;
        return result;
    }
    public void clear() {
        size = 0;
    }
    public void clear(Number[] numbers) {
        for (int i = 0; i < size; i++) {
            boolean match = true;
            for (int j = 0; j < numbers.length; j++) {
                if (!elements[i + j].equals(numbers[j])) {
                    match = false;
                    break;
                }
            }
            if (match) {
                System.arraycopy(elements, i + numbers.length, elements, i, size - i - numbers.length);
                size -= numbers.length;
                break;
            }
        }
    }
    public boolean isEmpty() {
        return size == 0;
    }
    private void resizeArray() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }
}