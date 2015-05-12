package com.shuiwen.campusys.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class ServerSecurity {

    private static int[] key = new int[] { 0x25D0C42F, 0xF848D250, 0x1F5EA5CB, 0x1EA6524F };

    private ServerSecurity() {
        // do nothing
    }

    public static String custom_encrypt(String Data) {
        byte[] out = Data.getBytes();

        // Log.d("Encrypt", "Yangzc input length " + out.length);

        int length = (out.length + 3) & ~3;

        byte[] out2 = new byte[length];

        for (int i = 0; i < out2.length; i++) {
            out2[i] = 0;
        }

        System.arraycopy(out, 0, out2, 0, out.length);

        byte[] binout = xxTea_encrypt(out2, toByteArray(key, false));

        String outString = base64encode(binout);

        return outString;
    }

    public static String custom_decrypt(String Data) {

        byte[] binout = base64decode(Data);

        byte[] clearout = xxTea_decrypt(binout, toByteArray(key, false));

        int j = 0;
        for (int i = 0; i < clearout.length; i++) {
            if (clearout[i] != 0) {
                j++;
            }
        }

        byte[] clearout2 = new byte[j];

        for (int i = 0; i < j; i++) {
            clearout2[i] = clearout[i];
        }

        String outString;
        try {
            outString = new String(clearout2, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

        return outString;
    }

    /**
     * Encrypt data with key.
     * 
     * @param data
     * @param key
     * @return
     */
    public static byte[] xxTea_encrypt(byte[] data, byte[] key) {
        if (data.length == 0) {
            return data;
        }
        return toByteArray(encrypt(toIntArray(data, false), toIntArray(key, false)), false);
    }

    /**
     * Decrypt data with key.
     * 
     * @param data
     * @param key
     * @return
     */
    public static byte[] xxTea_decrypt(byte[] data, byte[] key) {
        if (data.length == 0) {
            return data;
        }
        return toByteArray(decrypt(toIntArray(data, false), toIntArray(key, false)), false);
    }

    /**
     * Encrypt data with key.
     * 
     * @param v
     * @param k
     * @return
     */
    public static int[] encrypt(int[] v, int[] k) {
        int n = v.length - 1;

        if (n < 1) {
            return v;
        }
        if (k.length < 4) {
            int[] key = new int[4];

            System.arraycopy(k, 0, key, 0, k.length);
            k = key;
        }
        int z = v[n], y = v[0], delta = 0x9E3779B9, sum = 0, e;
        int p, q = 6 + 52 / (n + 1);

        while (q-- > 0) {
            sum = sum + delta;
            e = sum >>> 2 & 3;
            for (p = 0; p < n; p++) {
                y = v[p + 1];
                z = v[p] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
            }
            y = v[0];
            z = v[n] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
        }
        return v;
    }

    /**
     * Decrypt data with key.
     * 
     * @param v
     * @param k
     * @return
     */
    public static int[] decrypt(int[] v, int[] k) {
        int n = v.length - 1;

        if (n < 1) {
            return v;
        }
        if (k.length < 4) {
            int[] key = new int[4];

            System.arraycopy(k, 0, key, 0, k.length);
            k = key;
        }
        int z = v[n], y = v[0], delta = 0x9E3779B9, sum, e;
        int p, q = 6 + 52 / (n + 1);

        sum = q * delta;
        while (sum != 0) {
            e = sum >>> 2 & 3;
            for (p = n; p > 0; p--) {
                z = v[p - 1];
                y = v[p] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
            }
            z = v[n];
            y = v[0] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
            sum = sum - delta;
        }
        return v;
    }

    /**
     * Convert byte array to int array.
     * 
     * @param data
     * @param includeLength
     * @return
     */
    private static int[] toIntArray(byte[] data, boolean includeLength) {
        int n = (((data.length & 3) == 0) ? (data.length >>> 2) : ((data.length >>> 2) + 1));
        int[] result;

        if (includeLength) {
            result = new int[n + 1];
            result[n] = data.length;
        } else {
            result = new int[n];
        }
        n = data.length;
        for (int i = 0; i < n; i++) {
            result[i >>> 2] |= (0x000000ff & data[i]) << ((i & 3) << 3);
        }
        return result;
    }

    /**
     * Convert int array to byte array.
     * 
     * @param data
     * @param includeLength
     * @return
     */
    private static byte[] toByteArray(int[] data, boolean includeLength) {
        int n = data.length << 2;

        if (includeLength) {
            int m = data[data.length - 1];

            if (m > n) {
                return null;
            } else {
                n = m;
            }
        }

        byte[] result = new byte[n];

        for (int i = 0; i < n; i++) {
            result[i] = (byte) ((data[i >>> 2] >>> ((i & 3) << 3)) & 0xff);
        }
        return result;
    }

    // base64 encode, decode
    private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', '+', '/' };

    private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44,
            45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };

    public static String base64encode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;

        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }

    public static byte[] base64decode(String str) {
        byte[] data = str.getBytes();
        int len = data.length;
        ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
        int i = 0;
        int b1, b2, b3, b4;

        while (i < len) {

            /* b1 */
            do {
                b1 = base64DecodeChars[data[i++]];
            } while (i < len && b1 == -1);
            if (b1 == -1) {
                break;
            }

            /* b2 */
            do {
                b2 = base64DecodeChars[data[i++]];
            } while (i < len && b2 == -1);
            if (b2 == -1) {
                break;
            }
            buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

            /* b3 */
            do {
                b3 = data[i++];
                if (b3 == 61) {
                    return buf.toByteArray();
                }
                b3 = base64DecodeChars[b3];
            } while (i < len && b3 == -1);
            if (b3 == -1) {
                break;
            }
            buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

            /* b4 */
            do {
                b4 = data[i++];
                if (b4 == 61) {
                    return buf.toByteArray();
                }
                b4 = base64DecodeChars[b4];
            } while (i < len && b4 == -1);
            if (b4 == -1) {
                break;
            }
            buf.write((int) (((b3 & 0x03) << 6) | b4));
        }
        return buf.toByteArray();
    }
    
    
    public static void main(String[] args) throws Exception {
//        String str = "x/5rflLI1CiqRsYIB7jIG5JD0/TwObm77dZHkJ9A7ZO2gKV2U30uQjbFuMw29qEWywgTLPWCE6Qiw0Ip+dBEtb6Qi3o9dvxdK9IqpBQ8jOlNW83TBq8KzI3vylMhYTIPKq3SAZDnV+hCsVNFTVL+kxTU78epWjIsp6UCJ6mWWOxsJtAf4mQxV4M7R/oQPz7YazImJyJLalo7dnKZNP/3mFE91GtCgvHbTQJBP/NI0+Jw8OnYF5bfqxkQWjIkH+IAuvDSrzMKPqLe1tXMI0834Yz9nJ/QDawhKO+7fX+htmuY617kU1Xduvges7zJ3gZJ2jWFqlDJ2J9SuY9fXr2UzGkraPzz39XDEXstgyQSW8UiYzhxCS8HEDSsMVI+thkKM7HTrT837QKecyJ5AuAkxpbW/Gb/7D5Sb09zKUI1U3xstzQ/FJ+a1TkMkShwHpjQVJxShuN9G0rPiUuUDdybFHBDjYoyYleH4MC35t/8w4qL3EH+x+0JyG+mjIodnGGGJlsR8FSvVJxBmmgt6PVLqEZ4t3+qpS82MJMY483GQxuxYJH5mLjnRSTLb95UHiKjPT1zvNZ7DbKJsqlrCQoVsdkiJlbzSSvkGrALcAH5V+8uyFWnhzcOeNuEAHpOBVwo1+ml4r2AlAxer2vp2Jk5/bthlBW0lPVl1HwThthtgxSWk57K5P8hHKesfJH60N3FDN2iM/0/gtXcwizI0RcClA==";
//        System.out.println(ServerSecurity.custom_decrypt(str));
//        System.out.println(ServerSecurity.custom_decrypt("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"));
//        System.out.println(ServerSecurity.custom_decrypt("qabGCrJsZwjger0jOe+T515WvFtO0YvEIa1G7fkgBb8ov25qzams0xXceiqDitrgNw8Ivtcc0u4MZIWF9lgL3syaU/7UPo/NZd54rVqPxVVQfstfmuDooW+rucFGll4PZomheE5G3dkB6nA5Lm5AfxM805nA3RB14O4vpXcxNccDAXEXb/vwYFH4hpSNJJrTUgZ74mZ5z3dbgLKB1++v5lSso21j6s9uQXtz6q6VyE+/nJP+pOnj96Jn5edTQpoeXQiMHtkACct2/BXEWQmmYwFlou+85+VxnAI+LhiSKK1bpBSlfnQleFFME6AUKWhSNQDdJy0ff1fPskfvhZSlnej+pUlcrQmi0kVcVhm0jpzsLKE+HjrJ55lOChpdnS/F5zcYWsiAXeNnx46o17cSUGOObQZWCIMKJ0faJtSsDaawZeERZ8NrOkbwIVrGH9of2L9ZaoZIjfy5VINjYDy7X+TlsMbn9qlrGb8dcPOSKThDtP5BO2FsT3KlJq8bxMj6KPEWIvteI3cqIWUj9fv+Gbj533LgEMn4EobAoV3p5W5kp/cqqbtGZOyPHQRBUmL1qfYyqRbeNEvxAwaK468tTTWiwyiVQlgscweG7plle1zC2/Y7uByJ7PahIP713dTC1j2Ip+xp2xSFIVcRnv1nE8yMX9aZ2moRegmfx9NQCqY4WFzWaKa+WeV0SIHZtA7YnofZmEdtdiu2zVYOi6TdmVpk8z2j7eGD7FcMmEHpAfOME7RsWUotRXqtLt9vna3i8Xf6OF2L/l/0UAgBdk9KqRkmmCznejRpT0rSE/cicR65AJ/jSNKkawEKnomTVnGtZlWqY86OxIADNcrdksSY9dzQQ8r+iAoehWZqqOMWOv4kCXIGaM7QwRJi7aqv6Riv284aKoIw/InhnPovNwe5LDvm2XPUsU2Z4uoV4y8yUSgvQn0zHks995xJknnddhRJCOzr2UgpwljIuhAOBujQlHlISuS1o6FrOzinGX/YwdkVzWVPJKViO1LTyar7B1N2HltaPzHSnHYJnw/rHcLdxHqlthLvDpXfnq2VsAmVSd2/rcl/FYcp52mt05X4LdeTTsfzVXxUN8Nsi4FZFLObfHQGga1e76P221hG9f1bp15jXlEhcpSmzu+gxUeAEMy9ATAME1xZdFCGDFC12YevVCDf3ue3dZxPkW/gp87kDU++X5HexcgxkO6QqiOEylYm4ajdN7nN+L9M4rRdt3cbldCQ2s4VBharyKDBqWfYshBDC7Y7A7A01QlR6mLh98GxyI+U1sqRgsNRqIk8q6swdCbuK0/nZC0gzW012QZM9ntlP8ve6ClVDQ=="));

//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new SimpleDateFormat("yyyyMMdd").parse("20140601"));
//        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//        int maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//        
//        calendar.add(Calendar.MONTH, -1);
//        if (maxDayOfMonth == dayOfMonth && calendar.getActualMaximum(Calendar.DAY_OF_MONTH) <= maxDayOfMonth) {
//            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//        }
////        calendar.add(Calendar.DAY_OF_MONTH, -1);
//        System.out.println(calendar.getTime());
        System.out.println(new Date(1405353600000L));
    }
}