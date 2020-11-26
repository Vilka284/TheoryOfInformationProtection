package com.andrii.RC5;

import com.andrii.MD5.MD5;

import java.util.ArrayList;
import java.util.List;

public class RC5 {

    private final MD5 md5;
    private final RC5Utility rc5Utility;

    public RC5() {
        this.md5 = new MD5();
        this.rc5Utility = new RC5Utility();
    }

    public byte[] encrypt(byte[] data, String rawKeyword) {
        String keyword = md5.getDigest(rawKeyword.getBytes());
        data = makeAddition(data, keyword);
        return rc5Utility.encrypt(data, new RC5Key(keyword.getBytes(), RC5Utility.ROUNDS));
    }

    public byte[] decrypt(byte[] data, String rawKeyword) {
        String keyword = md5.getDigest(rawKeyword.getBytes());
        //data = makeAddition(data, keyword);
        byte[] decryptedData = rc5Utility.decrypt(data, new RC5Key(keyword.getBytes(), RC5Utility.ROUNDS));
        return removeAddition(decryptedData, keyword);
    }

    public byte[] makeAddition(byte[] data, String hashedKeyword) {
        // if data not multiple 8 make addition of first bytes of hashed keywords
        if (data.length % 8 != 0) {
            int preferredLength = data.length < 8 ? 8 : (int) (8 * (Math.floor(data.length / 8) + 1));
            byte[] temp = new byte[preferredLength];
            System.arraycopy(data, 0, temp, 0, data.length);

            //byte[] addition = new byte[preferredLength - data.length];
            //Arrays.fill(addition, (byte) -128);
            byte[] addition = hashedKeyword.getBytes();
            System.arraycopy(addition, 0, temp, data.length, preferredLength - data.length);
            data = temp;
        }
        return data;
    }

    public byte[] removeAddition(byte[] data, String hashedKeyword) {
        int actualTextLength = data.length - 1;
        List<Byte> keywordBytes = new ArrayList<>();

        // create bytes list of hashed keyword
        for (byte b :
                hashedKeyword.getBytes()) {
            keywordBytes.add(b);
        }

        // remove addition
        for (int i = data.length - 1; i >= 0; i--) {
            if (keywordBytes.contains(data[i])) {
                keywordBytes.remove((Byte) data[i]);
                actualTextLength--;
            } else {
                break;
            }
        }

        byte[] temp = new byte[data.length];

        // get slice of actual byte array
        for (int i = 0; i <= actualTextLength; i++) {
            temp[i] = data[i];
        }
        return temp;
    }
}
