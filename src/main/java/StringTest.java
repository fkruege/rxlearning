public class StringTest {


    public static void main(String[] args) throws InterruptedException {
       String s = "hello" ;
       modify(s);
        System.out.println(s);
    }

    private static void modify(String s){
        System.out.println(s);
        s = "world";
        System.out.println(s);
    }
}
