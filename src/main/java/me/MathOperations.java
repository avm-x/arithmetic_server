package me;

public class MathOperations {
    public static double sum(double a, double b) {
        return a + b;
    }

    public static int sum(int a, int b) {
        return a + b;
    }

    public static double minus(double a, double b) {
        return a - b;
    }

    public static int minus(int a, int b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        return a / b;
    }

    public static int divide(int a, int b) {
        return a / b;
    }

    public int processInput(String input) {
        String[] nums = input.split("[0-9]+");
        return Integer.valueOf(nums[0]);
    }

}
