package by.epam.training.library.interfaces;

public interface Entity<K, V> {
    K getKey();

    V getValue();
}
