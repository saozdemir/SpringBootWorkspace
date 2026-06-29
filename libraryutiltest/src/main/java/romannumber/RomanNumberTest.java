package romannumber;

import com.github.fracpete.romannumerals4j.RomanNumeralFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author saozdemir@gmail.com
 * @project RomanNumberConverter
 * @date 07/04/2024
 * @description: This class contains unit tests for the RomanNumber converter.
 */
public class RomanNumberTest {
    public static void main(String[] args) {
        RomanNumeralFormat format = new RomanNumeralFormat();
        System.out.println(format.format(1));
        System.out.println(format.format(2));
        System.out.println(format.format(3));
        System.out.println(format.format(4));
        System.out.println(format.format(5));

        System.out.println(toRoman(3));
        System.out.println(toInt("XC"));
    }

    public static String toRoman(int number) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                roman.append(symbols[i]);
                number -= values[i];
            }
        }
        return roman.toString();
    }

    public static int toInt(String roman) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int result = 0;
        int preValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int currentValue = map.get(roman.charAt(i));
            if (currentValue < preValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            preValue = currentValue;

        }
        return result;
    }
}
