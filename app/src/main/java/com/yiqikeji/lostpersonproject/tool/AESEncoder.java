package com.yiqikeji.lostpersonproject.tool;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 对称加密算法封装类（对称加密算法：AES > 3DES > DES）
 * <p/>
 * String content = "hello world！";
 * String pStr = encrypt(content);
 * System.out.println("加密前：" + content);
 * System.out.println("加密后:" + pStr);
 * String postStr = decrypt(pStr);
 * System.out.println("解密后：" + postStr );
 */
public class AESEncoder {
    private static AESEncoder instance = null;

    /**
     * 加密文本
     *
     * @param text 文本
     * @return 加密后的文本
     */
    public static String encryptText(String text) {
        if (instance == null) {
            instance = new AESEncoder();
            try {
                instance.init();
            } catch (Exception e) {
                instance = null;
                e.printStackTrace();
            }
            if (instance == null) {
                return null;
            }
        }
        return instance.encrypt(text);
    }

    /**
     * 解密文本
     *
     * @param text 文本
     * @return 解密后的文本
     */
    public static String decryptText(String text) {
        if (instance == null) {
            instance = new AESEncoder();
            try {
                instance.init();
            } catch (Exception e) {
                instance = null;
                e.printStackTrace();
            }
            if (instance == null) {
                return null;
            }
        }
        return instance.decrypt(text);
    }

    private final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private KeyGenerator keyGenerator;
    private Cipher cipher;
    private String keyPassword = "E2d0u1P6lHahtkjk"; // 注意: 这里的password(秘钥必须是16位的)
    private SecretKeySpec keySpec;

    public void init() throws Exception {
        /**为指定算法生成一个 KeyGenerator 对象。
         *此类提供（对称）密钥生成器的功能。
         *密钥生成器是使用此类的某个 getInstance 类方法构造的。
         *KeyGenerator 对象可重复使用，也就是说，在生成密钥后，
         *可以重复使用同一 KeyGenerator 对象来生成进一步的密钥。
         *生成密钥的方式有两种：与算法无关的方式，以及特定于算法的方式。
         *两者之间的惟一不同是对象的初始化：
         *与算法无关的初始化
         *所有密钥生成器都具有密钥长度 和随机源 的概念。
         *此 KeyGenerator 类中有一个 init 方法，它可采用这两个通用概念的参数。
         *还有一个只带 keysize 参数的 init 方法，
         *它使用具有最高优先级的提供程序的 SecureRandom 实现作为随机源
         *（如果安装的提供程序都不提供 SecureRandom 实现，则使用系统提供的随机源）。
         *此 KeyGenerator 类还提供一个只带随机源参数的 inti 方法。
         *因为调用上述与算法无关的 init 方法时未指定其他参数，
         *所以由提供程序决定如何处理将与每个密钥相关的特定于算法的参数（如果有）。
         *特定于算法的初始化
         *在已经存在特定于算法的参数集的情况下，
         *有两个具有 AlgorithmParameterSpec 参数的 init 方法。
         *其中一个方法还有一个 SecureRandom 参数，
         *而另一个方法将已安装的高优先级提供程序的 SecureRandom 实现用作随机源
         *（或者作为系统提供的随机源，如果安装的提供程序都不提供 SecureRandom 实现）。
         *如果客户端没有显式地初始化 KeyGenerator（通过调用 init 方法），
         *每个提供程序必须提供（和记录）默认初始化。
         */
        keyGenerator = KeyGenerator.getInstance("AES");
        // 初始化此密钥生成器，使其具有确定的密钥长度。
        keyGenerator.init(128); // 128位的AES加密
        //keyGenerator.generateKey().getEncoded();

        /**
         *类 SecretKeySpec
         *可以使用此类来根据一个字节数组构造一个 SecretKey，
         *而无须通过一个（基于 provider 的）SecretKeyFactory。
         *此类仅对能表示为一个字节数组并且没有任何与之相关联的钥参数的原始密钥有用
         *构造方法根据给定的字节数组构造一个密钥。
         *此构造方法不检查给定的字节数组是否指定了一个算法的密钥。
         */
        keySpec = new SecretKeySpec(keyPassword.getBytes(), "AES");

        // 生成一个实现指定转换的 Cipher 对象。
        cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec); // 算法只用于加密
    }

    public String encrypt(String input) {
        String result = null;
        try {
            byte[] byteInput = input.getBytes("UTF-8");
            //cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encodeInput = cipher.doFinal(byteInput);
            result = parseByte2HexStr(encodeInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String decrypt(String input) {
        String result = null;
        try {
            byte[] byteInput = parseHexStr2Byte(input);
            //cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] resultInput = cipher.doFinal(byteInput);
            result = new String(resultInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将字节数组转化为十六进制字符串
     *
     * @param buffer 字节数组
     * @return 转化后的十六进制字符串
     */
    public String parseByte2HexStr(byte buffer[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buffer.length; i++) {
            String hex = Integer.toHexString(buffer[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toLowerCase());
        }
        return sb.toString();
    }

    /**
     * 将十六进制字符串转化为字节数组
     *
     * @param hexStr 十六进制字符串
     * @return 转化后的字节数组
     */
    public byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}