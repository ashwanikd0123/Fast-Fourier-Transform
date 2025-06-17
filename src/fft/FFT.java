package fft;

import utils.ComplexNumber;

import static utils.ComplexNumber.*;

public class FFT {

    public static ComplexNumber[] fft(ComplexNumber[] signal) {
        return recursiveFft(padNearestTwoPower(signal));
    }

    public static ComplexNumber[] ifft(ComplexNumber[] signal) {
        int N = signal.length;
        ComplexNumber[] res = conjugate(signal);
        res = fft(res);
        for (int i = 0; i < N; i++) {
            res[i] = divide(res[i].conjugate(), N);
        }
        return truncate(res, N);
    }

    public static ComplexNumber[] conjugate(ComplexNumber[] signal) {
        ComplexNumber[] res = new ComplexNumber[signal.length];
        for (int i = 0; i < signal.length; i++) {
            res[i] = signal[i].conjugate();
        }
        return res;
    }

    private static ComplexNumber[] linearFft(ComplexNumber[] signal) {
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

    private static ComplexNumber[] recursiveFft(ComplexNumber[] signal) {
        if (signal.length == 1) {
            return new ComplexNumber[]{ signal[0] };
        }

        int N = signal.length;
        ComplexNumber[] odd = new ComplexNumber[N / 2];
        ComplexNumber[] even = new ComplexNumber[N / 2];

        int k = 0;
        for (int i = 0; i < N; i += 2) {
            even[k++] = signal[i].copy();
        }

        k = 0;
        for (int i = 1; i < N; i += 2) {
            odd[k++] = signal[i].copy();
        }

        odd = recursiveFft(odd);
        even = recursiveFft(even);

        ComplexNumber[] res = new ComplexNumber[N];
        for (int i = 0; i < N / 2; i++) {
            ComplexNumber factor = new ComplexNumber(-2.0f * (float) Math.PI * i / (float) N);
            res[i] = add(even[i], multiply(factor, odd[i]));
            res[i + N / 2] = subtract(even[i], multiply(factor, odd[i]));
        }

        return res;
    }

    private static ComplexNumber[] padNearestTwoPower(ComplexNumber[] signal) {
        int len = nextPowerOfTwo(signal.length);
        ComplexNumber[] res = new ComplexNumber[len];
        for (int i = 0; i < len; i++) {
            res[i] = i < signal.length ? signal[i] : new ComplexNumber();
        }
        return res;
    }

    private static ComplexNumber[] truncate(ComplexNumber[] signal, int length) {
        ComplexNumber[] res = new ComplexNumber[length];
        for (int i = 0; i < length; i++) {
            res[i] = signal[i];
        }
        return res;
    }

    private static int nextPowerOfTwo(int n) {
        int power = 1;
        while (power < n) power *= 2;
        return power;
    }
}
