public class MainClass {
    public static void main(String[] args) {
        System.out.println("Hello from NSUUnit!");
        for(int i=0; i < args.length; i++)
            System.out.println("args[" + i + "] : " + args[i]);
        QueueOfClassesToTest queue = new QueueOfClassesToTest(3);
    }
}
