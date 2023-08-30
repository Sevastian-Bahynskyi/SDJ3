package peer;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class PeerList implements Serializable, Collection<PeerInterface>
{
    private ArrayList<PeerInterface> peerList;

    public PeerList()
    {
        peerList = new ArrayList<>();
    }


    public void trimToSize()
    {
        peerList.trimToSize();
    }

    public void ensureCapacity(int minCapacity)
    {
        peerList.ensureCapacity(minCapacity);
    }

    @Override
    public int size()
    {
        return peerList.size();
    }

    @Override
    public boolean isEmpty()
    {
        return peerList.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return peerList.contains(o);
    }

    public int indexOf(Object o)
    {
        return peerList.indexOf(o);
    }

    public int lastIndexOf(Object o)
    {
        return peerList.lastIndexOf(o);
    }

    @Override
    public Object clone()
    {
        return peerList.clone();
    }

    @Override
    public Object[] toArray()
    {
        return peerList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return peerList.toArray(a);
    }

    public PeerInterface get(int index)
    {
        return peerList.get(index);
    }

    public PeerInterface set(int index, PeerInterface element)
    {
        return peerList.set(index, element);
    }

    @Override
    public boolean add(PeerInterface peerInterface)
    {
        return peerList.add(peerInterface);
    }

    public void add(int index, PeerInterface element)
    {
        peerList.add(index, element);
    }

    public PeerInterface remove(int index)
    {
        return peerList.remove(index);
    }

    @Override
    public boolean equals(Object o)
    {
        return peerList.equals(o);
    }

    @Override
    public int hashCode()
    {
        return peerList.hashCode();
    }

    @Override
    public boolean remove(Object o)
    {
        return peerList.remove(o);
    }

    @Override
    public void clear()
    {
        peerList.clear();
    }

    @Override
    public boolean addAll(Collection<? extends PeerInterface> c)
    {
        return peerList.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends PeerInterface> c)
    {
        return peerList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return peerList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return peerList.retainAll(c);
    }

    public ListIterator<PeerInterface> listIterator(int index)
    {
        return peerList.listIterator(index);
    }

    public ListIterator<PeerInterface> listIterator()
    {
        return peerList.listIterator();
    }

    @Override
    public Iterator<PeerInterface> iterator()
    {
        return peerList.iterator();
    }

    public List<PeerInterface> subList(int fromIndex, int toIndex)
    {
        return peerList.subList(fromIndex, toIndex);
    }

    @Override
    public void forEach(Consumer<? super PeerInterface> action)
    {
        peerList.forEach(action);
    }

    @Override
    public Spliterator<PeerInterface> spliterator()
    {
        return peerList.spliterator();
    }

    @Override
    public boolean removeIf(Predicate<? super PeerInterface> filter)
    {
        return peerList.removeIf(filter);
    }

    public void replaceAll(UnaryOperator<PeerInterface> operator)
    {
        peerList.replaceAll(operator);
    }

    public void sort(Comparator<? super PeerInterface> c)
    {
        peerList.sort(c);
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return peerList.containsAll(c);
    }

    @Override
    public String toString()
    {
        return peerList.toString();
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator)
    {
        return peerList.toArray(generator);
    }

    @Override
    public Stream<PeerInterface> stream()
    {
        return peerList.stream();
    }

    @Override
    public Stream<PeerInterface> parallelStream()
    {
        return peerList.parallelStream();
    }

    public static <E> List<E> of()
    {
        return List.of();
    }

    public static <E> List<E> of(E e1)
    {
        return List.of(e1);
    }

    public static <E> List<E> of(E e1, E e2)
    {
        return List.of(e1, e2);
    }

    public static <E> List<E> of(E e1, E e2, E e3)
    {
        return List.of(e1, e2, e3);
    }

    public static <E> List<E> of(E e1, E e2, E e3, E e4)
    {
        return List.of(e1, e2, e3, e4);
    }

    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5)
    {
        return List.of(e1, e2, e3, e4, e5);
    }

    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6)
    {
        return List.of(e1, e2, e3, e4, e5, e6);
    }

    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7)
    {
        return List.of(e1, e2, e3, e4, e5, e6, e7);
    }

    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8)
    {
        return List.of(e1, e2, e3, e4, e5, e6, e7, e8);
    }

    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9)
    {
        return List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9);
    }

    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10)
    {
        return List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
    }

    @SafeVarargs
    public static <E> List<E> of(E... elements)
    {
        return List.of(elements);
    }

    public static <E> List<E> copyOf(Collection<? extends E> coll)
    {
        return List.copyOf(coll);
    }
}
