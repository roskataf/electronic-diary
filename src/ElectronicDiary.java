import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ElectronicDiary {

    public static void main(String[] args) {
        String inputFile = "students.txt";
        String outputFile = "grades.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            Random random = new Random();

            writer.write("Име,Фамилия,Клас,Оценки,Среден успех\n");

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");

                if (parts.length == 3) {
                    String firstName = parts[0];
                    String lastName = parts[1];
                    int age = Integer.parseInt(parts[2]);

                    String className;
                    String parallel;

                    if (age == 13 || age == 14) {
                        className = "8";
                    } else {
                        className = "9";
                    }

                    if ((firstName.length() + lastName.length()) < 15) {
                        parallel = "А";
                    } else {
                        parallel = "Б";
                    }

                    String studentClass = className + parallel;
                    List<Integer> grades = generateGrades(firstName);

                    double averageGrade = calculateAverageGrade(grades);

                    writer.write(firstName + "," + lastName + "," + studentClass + "," + gradesToString(grades) + "," + averageGrade + "\n");
                }
            }

            System.out.println("Успешно генериране. Файл: " + outputFile);
        } catch (IOException e) {
            System.out.println("Грешка при четене/писане на файлове: " + e.getMessage());
        }
    }

    public static List<Integer> generateGrades(String firstName) {
        List<Integer> grades = new ArrayList<>();
        Random random = new Random();
        boolean isFemale = isFemale(firstName);

        int numberOfGrades = random.nextInt(10) + 6;

        for (int i = 0; i < numberOfGrades; i++) {
            int grade;

            if (isFemale) {
                grade = random.nextInt(3) + 4;
            } else {
                grade = random.nextInt(5) + 2;
            }

            grades.add(grade);
        }

        return grades;
    }

    public static boolean isFemale(String firstName) {
        char lastChar = firstName.toLowerCase().charAt(firstName.length() - 1);
        return lastChar == 'a' || lastChar == 'е' || lastChar == 'и' || lastChar == 'о' || lastChar == 'у' || lastChar == 'ъ';
    }

    public static double calculateAverageGrade(List<Integer> grades) {
        int sum = 0;

        for (int grade : grades) {
            sum += grade;
        }

        return (double) sum / grades.size();
    }

    public static String gradesToString(List<Integer> grades) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < grades.size(); i++) {
            sb.append(grades.get(i));

            if (i < grades.size() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}