# Fast Fourier Transform (FFT) in Java

This repository provides a simple implementation of the **Fast Fourier Transform (FFT)** and its inverse (**IFFT**) in Java, using a custom `ComplexNumber` class.

---

## 📘 What is FFT?

The **Fast Fourier Transform (FFT)** is an efficient algorithm to compute the **Discrete Fourier Transform (DFT)** and its inverse. The DFT transforms a sequence of complex numbers from the **time domain** into the **frequency domain**.

It’s widely used in:
- Signal processing
- Audio and image compression
- Spectral analysis
- Polynomial multiplication

While a naive DFT requires **O(N²)** operations, FFT reduces this to **O(N log N)** using a divide-and-conquer approach.

---

## 🔧 Project Structure

### Classes:

- `FFT.java` — Main class that contains:
    - `fft()` – Fast Fourier Transform
    - `ifft()` – Inverse FFT
    - `recursiveFft()` – Core recursive FFT function
    - `linearFft()` – (Unused) Naive O(N²) implementation for reference
    - Utility functions like `conjugate`, `padNearestTwoPower`, `truncate`, etc.

- `ComplexNumber.java` (in `utils` package) — Helper class to represent and operate on complex numbers.
    - Should contain methods like: `add`, `subtract`, `multiply`, `conjugate`, `copy`, and division support.

---

## 🚀 How It Works

### FFT Flow:
1. **Padding** – The input signal is padded to the nearest power of 2 (if needed).
2. **Divide and Conquer** – Input array is split into even and odd indexed elements.
3. **Recursive Calls** – FFT is recursively applied to both parts.
4. **Merge Results** – Combine using the complex roots of unity (`e^(-2πik/N)`).

### IFFT Flow:
1. Take **complex conjugate** of the input signal.
2. Apply **FFT**.
3. Take **conjugate again** and divide each value by the length to normalize.
4. Truncate to the original signal size.

---

## 🧪 Example Usage

```java
ComplexNumber[] input = {
    new ComplexNumber(1, 0),
    new ComplexNumber(2, 0),
    new ComplexNumber(3, 0),
    new ComplexNumber(4, 0)
};

ComplexNumber[] output = FFT.fft(input);
ComplexNumber[] restored = FFT.ifft(output);