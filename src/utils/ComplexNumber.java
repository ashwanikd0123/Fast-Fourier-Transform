package utils;

public class ComplexNumber {
    public Float real;
    public Float imaginary;

    public ComplexNumber() {
        real = imaginary = 0.0f;
    }

    public ComplexNumber(Float real, Float imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber(Float theta) {
        this.real = (float) Math.cos(theta);
        this.imaginary = (float) Math.sin(theta);
    }

    public Float getReal() {
        return real;
    }

    public Float getImaginary() {
        return imaginary;
    }

    public ComplexNumber inverse() {
        float den = real * real + imaginary * imaginary;
        return new ComplexNumber(real / den, -imaginary / den);
    }

    public ComplexNumber conjugate() {
        return new ComplexNumber(real, -imaginary);
    }

    public static ComplexNumber add(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.real + b.real, a.imaginary + b.imaginary);
    }

    public static ComplexNumber subtract(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.real - b.real, a.imaginary - b.imaginary);
    }

    public static ComplexNumber multiply(ComplexNumber a, float b) {
        return new ComplexNumber(a.real * b, a.imaginary * b);
    }

    public static ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.real * b.real - a.imaginary * b.imaginary, a.real * b.imaginary + a.imaginary * b.real);
    }

    public static ComplexNumber divide(ComplexNumber a, float b) {
        return new ComplexNumber(a.real / b, a.imaginary / b);
    }

    public static ComplexNumber divide(ComplexNumber a, ComplexNumber b) {
        return multiply(a, b.inverse());
    }

    @Override
    public String toString() {
        return String.format("%.4f + %.4fi", real, imaginary);
    }

    public ComplexNumber copy() {
        return new ComplexNumber(real, imaginary);
    }
}