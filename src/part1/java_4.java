package part1;

public class java_4 {
    public static void main(String[] args) throws InterruptedException {
        System.out.print(12);
        double i = 0.0;
        while (true) {
            if (i == 3) return;
            System.out.println(i);
            i = i + 0.1;

            Thread.sleep(100);
        }
    }
}
