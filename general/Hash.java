package general;

import java.util.Arrays;

/*
 * http://blog.csdn.net/v_JULY_v/article/details/6256463#comments
 * DOS attack based on hash collision
 * http://blog.codinglabs.org/articles/hash-collisions-attack-on-php.html
 */
public class Hash {
    public static long hash(long value) {
    return (value * 2654435769L) >> 28;
    }

    public static long[] prepareCryptTable() {
    long[] cryptTable = new long[0x500];
    long seed = 0x00100001;
    int index1 = 0;
    int index2 = 0;
    int i;

    for (index1 = 0; index1 < 0x100; index1++) {
        for (index2 = index1, i = 0; i < 5; i++, index2 += 0x100) {
        long temp1, temp2;

        seed = (seed * 125 + 3) % 0x2AAAAB;
        temp1 = (seed & 0xFFFF) << 0x10;

        seed = (seed * 125 + 3) % 0x2AAAAB;
        temp2 = (seed & 0xFFFF);

        cryptTable[index2] = (temp1 | temp2);
        }
    }

    System.out.println(Arrays.toString(cryptTable));
    return cryptTable;
    }

    public static long HashString(char[] fileName, long dwHashType) {
    long[] cryptTable = prepareCryptTable();

    char[] key = fileName;
    long seed1 = 0x7FED7FED;
    long seed2 = 0xEEEEEEEE;
    int i = 0;

    while (key[i] != 0) {
        char ch = Character.toUpperCase(key[i]);
        int index = (int) ((dwHashType << 8) + ch);
        seed1 = cryptTable[index] ^ (seed1 + seed2);
        seed2 = ch + seed1 + seed2 + (seed2 << 5) + 3;
    }
    return seed1;
    }

    public static void main(String[] args) {
    System.out.println(hash(155));
    System.out.println(hash(896));

    prepareCryptTable();
    }
}