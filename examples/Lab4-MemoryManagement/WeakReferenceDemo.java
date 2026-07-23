import java.lang.ref.WeakReference;

public class WeakReferenceDemo {

    public static void main(String[] args) {
        System.out.println("===== Weak Reference Demonstration =====");

        System.out.println("--- Strong Reference ---");
        Person strongPerson = new Person("Strong User", 40);
        System.out.println("Before GC : " + strongPerson);
        MemoryMonitor.triggerGarbageCollection();
        System.out.println("After GC  : " + strongPerson);
        System.out.println("Object remains because a strong reference still exists.");

        System.out.println();
        System.out.println("--- Weak Reference ---");
        // TODO: create Person weakTarget; wrap in WeakReference<Person>

        Person person = new Person("hello", 21);
        WeakReference<Person> weakTarget = new WeakReference<>(person);
        // TODO: null weakTarget; trigger GC; print WeakReference.get() result
        person = null;
        System.gc();
        System.out.println("After GC : " + weakTarget.get());
        // throw new UnsupportedOperationException("TODO");
    }
}
