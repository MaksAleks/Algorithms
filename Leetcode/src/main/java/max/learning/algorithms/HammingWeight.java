package max.learning.algorithms;

public class HammingWeight {
    public static void main(String[] args) {
        var s = new HammingWeight();
        s.hammingWeight(-3);
    }
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt += (n & 0x01);
            n = n >>> 1;
        }
        return cnt;
    }
}