package interfaces;

public interface BrainAction {
    void reminded();

    default void go() {
        System.out.println("Иду");
    }
}
