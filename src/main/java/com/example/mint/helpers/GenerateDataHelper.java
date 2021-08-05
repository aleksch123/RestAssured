package com.example.mint.helpers;


import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import com.example.mint.models.enums.PhoneCode;

public class GenerateDataHelper {

    public static String getRandomEmail(int size) {
        return getRandomString(size) + "@forbet.net";
    }

    public static String getRandomEmailWithSpecSymbol(int size) {
        return getStringWithSpecSimbols(size) + "@forbet.net";
    }

    public static String getRandomString(int size) {
        Random random = new Random();
        final int initialCapacity = 62;
        final List<Character> chars = new Vector<>(initialCapacity);

        for (char c = '0'; c <= '9'; c++) {
            chars.add(c);
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            chars.add(c);
        }

        for (char c = 'a'; c <= 'z'; c++) {
            chars.add(c);
        }

        final char[] charArray = new char[size - 1];

        for (int i = 0; i < size - 1; i++) {
            charArray[i] = chars.get(random.nextInt(initialCapacity));
        }

        return new String(charArray) + random.nextInt(9);
    }

    public static String getRandomPhone(PhoneCode code) {
        String first = "";

        if (code.equals(PhoneCode.CODE_7)) {
            first = "99980";
        } else if (code.equals(PhoneCode.CODE_37)) {
            first = "16511";
        } else if (code.equals(PhoneCode.CODE_38)) {
            first = "50202";
        }

        String phone = first
                + getRandomNumber(0, 10) + getRandomNumber(10, 100)
                + getRandomNumber(10, 100);

        return phone;
    }

    public String getRandomIp() {
        return getRandomNumber(100, 255) + "." + getRandomNumber(10, 99) + "." + getRandomNumber(10, 99) + "." + getRandomNumber(10, 99);
    }

    public static String getRandomNumber(int min, int max) {
        Random random = new Random();
        if (max == min)
            return String.valueOf(min);
        else
            return String.valueOf(random.nextInt(max - min) + min);
    }

    public static String getStringWithSpecSimbols(int size) {
        int second = (Calendar.getInstance().getTime().getSeconds());
        String simbol = getRandomSpecSymbol();
        String result = null;

        if (second % 3 == 2) {
            result = getRandomString(size) + simbol;
        }
        if (second % 3 == 1) {
            if (size % 2 == 1) {
                result = getRandomString(size / 2) + simbol
                        + getRandomString((size / 2) + 1);
            } else
                result = getRandomString(size / 2) + simbol
                        + getRandomString(size / 2);

        }

        if (second % 3 == 0) {
            result = getRandomString(size) + simbol;
        }

        return result;
    }

    public static String getRandomSpecSymbol() {
        String[] spec = new String[]{")", "#", "$", "%", "^", "&", "*",
                "?", "!", "№", ";", "%", ":", ",", "(", "\\", "/"};
        int time = (Calendar.getInstance().getTime().getSeconds());
        if (time > 9) {
            Random rand = new Random();
            return spec[rand.nextInt(spec.length)];
        } else {
            return spec[time];
        }
    }
}
