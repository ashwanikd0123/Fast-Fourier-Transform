package testing;

import static org.junit.Assert.assertEquals;

import fft.FFT;
import org.junit.Test;
import utils.ComplexNumber;
import static utils.ComplexNumber.*;

public class FFTTest {

    private static final float EPSILON = 1e-3f;

    @Test
    public void testComplexAddition() {
        ComplexNumber a = new ComplexNumber(2.0f, 3.0f);
        ComplexNumber b = new ComplexNumber(1.0f, -1.0f);
        ComplexNumber result = add(a, b);
        assertEquals(3.0f, result.real, EPSILON);
        assertEquals(2.0f, result.imaginary, EPSILON);
    }

    @Test
    public void testComplexMultiplication() {
        ComplexNumber a = new ComplexNumber(1.0f, 1.0f);
        ComplexNumber b = new ComplexNumber(1.0f, -1.0f);
        ComplexNumber result = multiply(a, b);
        assertEquals(2.0f, result.real, EPSILON);
        assertEquals(0.0f, result.imaginary, EPSILON);
    }

    @Test
    public void testFFTAndIFFTRoundTrip() {
        ComplexNumber[] signal = {
                new ComplexNumber(1.0f, 0.0f),
                new ComplexNumber(2.0f, 1.0f),
                new ComplexNumber(3.0f, 0.0f),
                new ComplexNumber(4.0f, 1.0f),
        };

        ComplexNumber[] freqDomain = FFT.fft(signal);
        ComplexNumber[] timeDomain = FFT.ifft(freqDomain);

        for (int i = 0; i < signal.length; i++) {
            assertEquals(signal[i].real, timeDomain[i].real, EPSILON);
            assertEquals(signal[i].imaginary, timeDomain[i].imaginary, EPSILON);
        }
    }

    @Test
    public void testZeroSignalFFT() {
        ComplexNumber[] signal = new ComplexNumber[4];
        for (int i = 0; i < 4; i++) {
            signal[i] = new ComplexNumber(0.0f, 0.0f);
        }

        ComplexNumber[] result = FFT.fft(signal);
        for (ComplexNumber c : result) {
            assertEquals(0.0f, c.real, EPSILON);
            assertEquals(0.0f, c.imaginary, EPSILON);
        }
    }

    @Test
    public void testConstantSignalFFT() {
        ComplexNumber[] signal = new ComplexNumber[4];
        for (int i = 0; i < 4; i++) {
            signal[i] = new ComplexNumber(5.0f, 0.0f);
        }

        ComplexNumber[] result = FFT.fft(signal);
        // First (DC component) should be sum of all = 20
        assertEquals(20.0f, result[0].real, EPSILON);

        // All other frequencies should be ~0
        for (int i = 1; i < 4; i++) {
            assertEquals(0.0f, result[i].real, EPSILON);
            assertEquals(0.0f, result[i].imaginary, EPSILON);
        }
    }
}
