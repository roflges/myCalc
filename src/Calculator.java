import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Calculator {


    private static void checkNumbers(int num1, int num2) throws Exception {

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10)
            throw new Exception("����� ������ ���� ������ � ��������� �� 1 �� 10");
    }

    private static int calc(int num1, char operation, int num2) throws Exception {
        checkNumbers(num1, num2);
        return switch (operation) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> throw new Exception("���� ��������: " + operation + " �� ������������� �������. ���������: +, -, /, *");
        };
    }

    public static void main(String[] args) {
        System.out.println("������� 2 ����� ����� ������ � �������� �������� � ����(/,*,-,+)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int result;
        try {
            String input = reader.readLine();               //���������� ������ � �������
            String[] inputArray = input.split(" ");   //�������������� ������ � ������ �����, ����������� ������
            if (inputArray.length != 3)                     //�������� ������ ������� (��������� 3 ��������)
                throw new Exception("������ �������������� ��������: \"" +
                        input + "\" �� ������������� ������� - ��� �������� � ���� ��������");
            char operation = inputArray[1].charAt(0);
            int num1, num2;

            //���������� ���������� ��� �������� �����
            try {
                num1 = Integer.parseInt(inputArray[0]);
                num2 = Integer.parseInt(inputArray[2]);
                result = calc(num1, operation, num2);
                System.out.println(result);
            }
            //���������� ���������� ��� ������� �����
            catch (NumberFormatException e) {
                num1 = Converter.romanToArabic(inputArray[0]); //����������� � ��������
                num2 = Converter.romanToArabic(inputArray[2]);
                result = calc(num1, operation, num2);
                if (result < 1) throw new Exception("� ������� ������� ��� ���� � ������������� �����");
                System.out.println(Converter.arabicToRoman(result)); //����������� ���������� �� ��������� ����� � �������
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}