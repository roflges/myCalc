import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Calculator {


    private static void checkNumbers(int num1, int num2) throws Exception {

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10)
            throw new Exception("Числа должны быть строго в диапазоне от 1 до 10");
    }

    private static int calc(int num1, char operation, int num2) throws Exception {
        checkNumbers(num1, num2);
        return switch (operation) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> throw new Exception("Знак операции: " + operation + " не удовлетворяет заданию. Ожидается: +, -, /, *");
        };
    }

    public static void main(String[] args) {
        System.out.println("Введите 2 числа через пробел и выберите операцию с ними(/,*,-,+)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int result;
        try {
            String input = reader.readLine();               //Считывание строки с консоли
            String[] inputArray = input.split(" ");   //Преобразование строки в массив строк, разделитель пробел
            if (inputArray.length != 3)                     //Проверка длинны массива (ожидается 3 элемента)
                throw new Exception("Формат математической операции: \"" +
                        input + "\" не удовлетворяет заданию - два операнда и один оператор");
            char operation = inputArray[1].charAt(0);
            int num1, num2;

            //Вычисление результата для арабских чисел
            try {
                num1 = Integer.parseInt(inputArray[0]);
                num2 = Integer.parseInt(inputArray[2]);
                result = calc(num1, operation, num2);
                System.out.println(result);
            }
            //Вычисление результата для римских чисел
            catch (NumberFormatException e) {
                num1 = Converter.romanToArabic(inputArray[0]); //конвертация в арабские
                num2 = Converter.romanToArabic(inputArray[2]);
                result = calc(num1, operation, num2);
                if (result < 1) throw new Exception("В римской системе нет нуля и отрицательных чисел");
                System.out.println(Converter.arabicToRoman(result)); //конвертация результата из арабского числа в римское
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}