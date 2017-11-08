package utils.utilities;

import java.util.Date;
import java.util.Random;

public class GenerateKey {
    private static final char[] symbols = new char[36];

    static {
        for (int idx = 0; idx < 10; ++idx)
            symbols[idx] = (char) ('0' + idx);
        for (int idx = 10; idx < 36; ++idx)
            symbols[idx] = (char) ('a' + idx - 10);
    }

    private static final Random random = new Random();

    private static String nextString(int length){
        final char[] buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    public static String generateEntityId() {
        Long l = System.currentTimeMillis();
        Integer randomNumber = (int)(Math.random() * 1000);
        String entityId = nextString(4)+l.toString() + randomNumber;

        return entityId.toUpperCase();
    }

    public static String generateSecurityCode() {
        Long randomNumber = 0L;
        while (randomNumber < 10000) {
            randomNumber = (long)(Math.random() * 1000000);
        }
        return randomNumber.toString();
    }

    public static void main(String[] args) {
        for (int i=0; i< 100; i++)
            System.out.println(generateRef());
    }

    public static String generateToken() {

        Long now = new Date().getTime();
        Double rand = Math.random();
        System.out.println("Random value: " + rand);
        String token = rand.toString().substring(4, 7) + now.toString();
        System.out.println("Token: " + token);

        return token;
    }

    public static String generateReferenceNumber() {
        Double rand = Math.random();
        String number = rand.toString().substring(1, 10);
        return number;
    }

    public static String generateVoucher() {

        Long now = new Date().getTime();
        String token = now.toString();
        System.out.println("Token: " + token);

        return token;
    }

    public static String generateRef() {
        long dob = 1175584320000L; // 03 April 2007, 0912hrs.
        StringBuilder id = new StringBuilder();
        id.append(System.currentTimeMillis() - dob);
        return id.toString();
    }

}
