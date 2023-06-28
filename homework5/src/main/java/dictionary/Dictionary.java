package dictionary;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;
public class Dictionary<K, V> {
    private Hashtable<K, V> glossary;
    public Dictionary() {
        glossary = new Hashtable<K, V>();
    }
    public int size() {
        return glossary.size();
    }
    public boolean isEmpty() {
        return glossary.isEmpty();
    }
    public boolean containsKey(K key) {
        return glossary.containsKey(key);
    }
    public boolean containsValue(V value) {
        return glossary.containsValue(value);
    }
    public V get(K key) {
        return glossary.get(key);
    }
    public boolean put(K key, V value) {
        glossary.put(key, value);
        return glossary.get(key) == value;
    }
    public boolean remove(K key) {
        if (glossary.containsKey(key)) {
            glossary.remove(key);
            return true;
        } else {
            return false;
        }
    }
    public boolean putAll(Dictionary<K, V> dictionary) {
        boolean bool = true;
        for (K key : dictionary.glossary.keySet()) {
            glossary.put(key, dictionary.glossary.get(key));
            if (glossary.get(key) != dictionary.glossary.get(key)) {
                bool = false;
            }
        }
        return bool;
    }
    public boolean clear() {
        if (glossary.isEmpty()) {
            return false;
        } else {
            glossary.clear();
            return true;
        }
    }
    public Set<K> keySet() {
        return glossary.keySet();
    }
    public Collection<V> values() {
        return glossary.values();
    }
    public void printDictionary (){
        for (K key : glossary.keySet()) {
            System.out.println(key + " - " + glossary.get(key));
        }
    }
    @Override
    public String toString() {
        return "Dictionary{" +
                "dict:" + glossary +
                '}';
    }
}