package dc;

public class ModularExponentiation {

    public static long modExp(long A, long k, long M) {
        if (k == 0) return 1;
        long half = modExp(A, k / 2, M);
        long result = (half * half) % M;
        if (k % 2 != 0) {
            result = (result * A) % M;
        }
        return result;
    }

    public static void main(String[] args) {
        long A = 7, k = 128, M = 13;
        System.out.println(A + "^" + k + " mod " + M + " = " + modExp(A, k, M));
    }
}
