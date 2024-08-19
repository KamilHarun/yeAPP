import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        CustomerManager c1 = new Customer_1();
        c1.setName("Kamil");
        c1.setSurname("Harun");
        c1.setAge(27);
        c1.setBalance(2500);
        c1.setId("Ab1905");
        c1.setEmail("Harunov21@gmail.com");
        c1.setJob("Teacher");
        c1.setPassword(1995);


        CustomerManager c2 = new Customer_2();
        c2.setName("Umid");
        c2.setSurname("Harun");
        c2.setAge(31);
        c2.setBalance(4500);
        c2.setId("Aa1905");
        c2.setEmail("UmidHarun@gmail.com");
        c2.setJob("Doctor");
        c2.setPassword(1991);

        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("ABC Banka xos gelmisiniz! ");
        System.out.println("Sifrenizi yazin!");
        scan.nextInt();

        while (true) {

            if (c1.getPassword() == 1995) {
                System.out.println("");
                System.out.println(c1.getName() + " " + c1.getSurname());
                System.out.println("1- Balans");
                System.out.println("2- Karta Medaxil");
                System.out.println("3- Kartdan Mexaric");
                int secim = scan.nextInt();

                switch (secim) {
                    case 1:
                        System.out.println("");
                        System.out.println("Balansiniz : " + c1.getBalance() + " " + "Manat");
                        break;
                    case 2:
                        System.out.println("");
                        System.out.println("Daxil etmek istediyiniz meblegi yazin!");
                        int daxilOlan = scan.nextInt();
                        if (daxilOlan > 0) {
                            c1.setBalance(c1.getBalance() + daxilOlan);
                            System.out.println("Balansiniz! : " + c1.getBalance() + " " + "Manat");
                            break;
                        }
                    case 3:
                        System.out.println("");
                        System.out.println("Cekmek istediyiniz meblegi yazin");
                        int xaricOlan = scan.nextInt();
                        if (xaricOlan > 0 && xaricOlan < c1.getBalance()) {
                            c1.setBalance(c1.getBalance() - xaricOlan);
                            System.out.println("Balansiniz! : " + c1.getBalance() + " " + "Manat");
                        } else {
                            System.out.println("Balansinizda kifayet qeder vesait yoxdur");
                        }
                }

            }
            else {
                System.out.println("Daxil etdiyiniz sifre yalnisdir");
                break;
            }

            if (c2.getPassword() == 1991) {

                System.out.println("");
                System.out.println(c2.getName() + " " + c2.getSurname());
                System.out.println("1- Balans");
                System.out.println("2- Karta Medaxil");
                System.out.println("3- Kartdan Mexaric");
                System.out.println("4- Cixis");
                int secim = scan.nextInt();

                switch (secim) {
                    case 1:
                        System.out.println("");
                        System.out.println("Balansiniz : " + c2.getBalance() + " " + "Manat");
                        break;
                    case 2:
                        System.out.println("");
                        System.out.println("Daxil etmek istediyiniz meblegi yazin!");
                        int daxilOlan = scan.nextInt();
                        if (daxilOlan > 0) {
                            c2.setBalance(c2.getBalance() + daxilOlan);
                            System.out.println("Balansiniz! : " + c2.getBalance() + " " + "Manat");
                            break;
                        }
                    case 3:
                        System.out.println("");
                        System.out.println("Cekmek istediyiniz meblegi yazin");
                        int xaricOlan = scan.nextInt();
                        if (xaricOlan > 0 && xaricOlan < c2.getBalance()) {
                            c2.setBalance(c2.getBalance() - xaricOlan);
                            System.out.println("Balansiniz! : " + c2.getBalance() + " " + "Manat");
                        } else {
                            System.out.println("Balansinizda kifayet qeder vesait yoxdur");
                            break;
                        }
                    case 4:
                        System.out.println(" ");
                        System.out.println("Cixis olunur. Sag olun !");
                        break;
                }
            }
        }
    }
}








