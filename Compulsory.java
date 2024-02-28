package compulsory;

/*
@author Varzar Alina-Miruna
*/

public class Compulsory {
    public static void main(String[] args){
        System.out.println("Hello World!"); //ex1
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"}; //ex2, def an array
        //System.out.println(languages);
        //random integer ex 3
        int n =(int) (Math.random()*1_000_000); System.out.println(n);
        //ex 4
        n=n*3; //System.out.println(n); //multiply n by 3
        //n=n+0b10101; System.out.println(n);
        n=n+Integer.parseInt("10101", 2); System.out.println(n); //add the binary number 10101
        n=n+Integer.parseInt("FF", 16); System.out.println(n); // hexadecimal number FF
        n=n*6; System.out.println(n);

        //ex 5
        while(n>9){ //has more than one digit
            int sum=0;
            while(n>0){
                sum= sum+n%10;
                n=n/10;
            }
            n=sum;
        }
        System.out.println(n);
        int result=n;
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);

    }
}

