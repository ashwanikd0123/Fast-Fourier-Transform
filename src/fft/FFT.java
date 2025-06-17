package fft;

import utils.ComplexNumber;

import static utils.ComplexNumber.*;

public class FFT {

    public static ComplexNumber[] fft(ComplexNumber[] signal) {
        int N = signal.length;
        ComplexNumber[] res = new ComplexNumber[N];
        for (int i = 0; i < N; i++) {
            ComplexNumber val = new ComplexNumber();
            for (int j = 0; j < N; j++) {
                ComplexNumber multiplier = new ComplexNumber(-2.0f * (float) Math.PI * j * i / (float) N);
                val = add(val, multiply(multiplier, signal[j]));
            }
            res[i] = val;
        }
        return res;
    }

    public static ComplexNumber[] ifft(ComplexNumber[] signal) {
        int N = signal.length;
        ComplexNumber[] res = new ComplexNumber[N];
        for (int i = 0; i < N; i++) {
            ComplexNumber val = new ComplexNumber();
            for (int j = 0; j < N; j++) {
                ComplexNumber multiplier = new ComplexNumber(2.0f * (float) Math.PI * j * i / (float) N);
                val = add(val, multiply(multiplier, signal[j]));
            }
            res[i] = divide(val, N);
        }
        return res;
    }
}
