package RSA;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SecurityKeyPairGenerator {

    public   void taohaikhoa(){
        try {
            SecureRandom sr = new SecureRandom();
            // Thuật toán phát sinh khóa - RSA
            // Độ dài khóa 1024(bits), độ dài khóa này quyết định đến độ an toàn của khóa, càng lớn thì càng an toàn
            // Demo chỉ sử dụng 1024 bit. Nhưng theo khuyến cáo thì độ dài khóa nên tối thiểu là 2048
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(8192, sr);

            // Khởi tạo cặp khóa
            KeyPair kp = kpg.genKeyPair();
            // PublicKey
            PublicKey publicKey = kp.getPublic();
            // PrivateKey
            PrivateKey privateKey = kp.getPrivate();

            File publicKeyFile = createKeyFile(new File("src\\RSA\\publicKey.rsa"));
            File privateKeyFile = createKeyFile(new File("src\\RSA\\privateKey.rsa"));

            // Lưu Public Key
            FileOutputStream fos = new FileOutputStream(publicKeyFile);
            fos.write(publicKey.getEncoded());
            fos.close();

            // Lưu Private Key
            fos = new FileOutputStream(privateKeyFile);
            fos.write(privateKey.getEncoded());
            fos.close();

            System.out.println("Generate key successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File createKeyFile(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
        return file;
    }

    public  String MaHoaDuLieu(String msg){
        String strEncrypt = null;
        try {
            // Đọc file chứa public key
            FileInputStream fis = new FileInputStream("src\\RSA\\publicKey.rsa");
            byte[] b = new byte[fis.available()];
            fis.read(b);
            fis.close();

            // Tạo public key
            X509EncodedKeySpec spec = new X509EncodedKeySpec(b);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = factory.generatePublic(spec);

            // Mã hoá dữ liệu
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.ENCRYPT_MODE, pubKey);
         //    msg = "helloworld";
            byte encryptOut[] = c.doFinal(msg.getBytes());
             strEncrypt = Base64.getEncoder().encodeToString(encryptOut);
           // System.out.println("Chuỗi sau khi mã hoá: " + strEncrypt);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        msg=" ";
        msg=strEncrypt;
        return msg;
    }
    public String giaiMa(String msg){

        try {
            // Đọc file chứa private key
            FileInputStream fis = new FileInputStream("src\\RSA\\privateKey.rsa");
            byte[] b = new byte[fis.available()];
            fis.read(b);
            fis.close();

            // Tạo private key
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = factory.generatePrivate(spec);

            // Giải mã dữ liệu
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.DECRYPT_MODE, priKey);
            byte decryptOut[] = c.doFinal(Base64.getDecoder().decode(
                    msg));
           // System.out.println("Dữ liệu sau khi giải mã: " + new String(decryptOut));
            msg=" ";
            msg=new String(decryptOut);
           // System.out.println("msg sau khi gai ma"+ msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return msg;
    }
}
