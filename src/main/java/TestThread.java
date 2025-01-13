public class TestThread {
    int i;

    public void increment() {
        i += 1;
        System.out.println(getI());
    }

    public int getI() {
        return i;
    }
}