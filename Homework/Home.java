package Homework;

public class Home {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("3 arguments please");
            System.exit(-1);
        }
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);

        StringBuilder result = new StringBuilder();
        for (int i = a; i <= b; i++) {
            if (isKReducible(i, k)) {
                result.append(i).append(" ");
            }
            System.out.println("K-reductible numbers: " + result.toString());
        }
    }

    private static boolean isKReducible(int number, int k) {
        while (number > 9) {
            int sum = 0;
            while (number != 0) {
                int digit = number % 10;
                sum += digit * digit;
                number /= 10;
            }
            number = sum;
        }
        return number == k;
    }
}

