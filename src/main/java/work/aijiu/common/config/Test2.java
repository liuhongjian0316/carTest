package work.aijiu.common.config;

public class Test2 {
    public static void main(String[] args) {
        String str = "2020-07-10 - 2020-08-08";

        String regbeg = str.substring(0, 10);
        String regend = str.substring(13, 23);

        String[] split = str.split(" - ");

        System.out.println(split[0]);
        System.out.println(split[1]);

        System.out.println(regbeg);
        System.out.println(regend);
    }
}
