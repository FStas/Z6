import java.io.IOException;
import java.util.Scanner;

class WrongStudentName extends Exception { }
class WrongAge extends Exception { }
class WrongDateOfBirth extends Exception { }
class WrongCase extends Exception { }

class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            try {
                int ex = menu();
                switch(ex) {
                    case 1: exercise1(); break;
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    default: return;
                }
            } catch(IOException e) {

            } catch(WrongStudentName e) {
                System.out.println("Błędne imię studenta!");
            } catch(WrongAge e) {
                System.out.println("Błędny wiek studenta!");
            } catch(WrongDateOfBirth e) {
                System.out.println("Błędna data urodzenia studenta!");
            } catch(WrongCase e) {
                System.out.println("Błędny wybór!");
            }
        }
    }

    public static int menu() throws WrongCase {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        return ReadCase();
    }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine();
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();
        if(name.contains(" "))
            throw new WrongStudentName();

        return name;
    }

    public static int ReadAge() throws WrongAge {
        scan.nextLine();
        System.out.println("Podaj wiek: ");
        int age = scan.nextInt();
        if(age < 1 || age > 99)
            throw new WrongAge();
        
        return age;
    }

    public static String ReadDate() throws WrongDateOfBirth {
        scan.nextLine();
        System.out.println("Podaj datę urodzenia DD-MM-YYYY: ");
        String date = scan.nextLine();
        String[] part = date.split("-");
        if(part.length != 3 || part[0].length() != 2 || part[1].length() != 2 || part[2].length() != 4)
            throw new WrongDateOfBirth();
        
        return date;
    }

    public static int ReadCase() throws WrongCase {
        if(!scan.hasNextInt()){
            scan.nextLine();
            throw new WrongCase();
        }
        int choice = scan.nextInt();
        if(choice < 0 || choice > 3)
            throw new WrongCase();
        return choice;
    }

    public static void exercise1() throws IOException, WrongStudentName, WrongAge, WrongDateOfBirth {
        var name = ReadName();
        var age = ReadAge();
        var date = ReadDate();
        (new Service()).addStudent(new Student(name, age, date));
    }

    public static void exercise2() throws IOException {
        var students = (new Service()).getStudents();
        for(Student current : students) {
            System.out.println(current.ToString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imię: ");
        var name = scan.nextLine();
        var wanted = (new Service()).findStudentByName(name);
        if(wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
}
