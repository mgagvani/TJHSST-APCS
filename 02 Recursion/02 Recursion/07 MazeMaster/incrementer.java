public class incrementer {
    public static int myCount;
    public incrementer() {}

    public static void incr(int count) {
        myCount = count;
        if(count < 8) {
            incr(myCount + 1);
        }
        System.out.println("" + myCount);
    }

    public static void main(String[] args) {
        incrementer.incr(0);
    }
}