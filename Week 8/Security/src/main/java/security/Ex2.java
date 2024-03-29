/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author ndupo
 */
public class Ex2 {
    
     private static final String ALGORITHM = "AES";
    
    private static final String CIFER = 
            "NmxhSedpfrayg4OsgKNWSjVp68E0xa76H5bOa+LEgt3fvVWPM/QHX48ySecVpyEPO/xVRaa2URbzEglWugmPpji8Q6ClwoMYHmX6qtimZ7I=";
    
    public static void main( String[] args ) {
        
        int k = 0; // which k yields the right result
        String key = "passwordabcd"+k; // has to be 128 bit/16 bytes
        
        String dec = decrypt(CIFER, key);
        System.out.println( "Decrypted: " + dec );
      }

    public static String encrypt(final String valueEnc, final String secKey) {
      String encryptedVal = null;
      try {
        final Key key = generateKeyFromString( secKey );
        final Cipher c = Cipher.getInstance( ALGORITHM );
        c.init( Cipher.ENCRYPT_MODE, key );
        final byte[] encValue = c.doFinal( valueEnc.getBytes() );
        encryptedVal = new String( Base64.getEncoder().encode( encValue ) );
        }
      catch ( Exception ex ) {
        System.out.println( "The Exception is=" + ex );
        }
      return encryptedVal;
      }

    public static String decrypt(final String encryptedValue, final String secretKey) {
      String decryptedValue = null;
      try {
        final Key key = generateKeyFromString( secretKey );
        final Cipher c = Cipher.getInstance( ALGORITHM );
        c.init( Cipher.DECRYPT_MODE, key );
        final byte[] decorVal = Base64.getDecoder().decode( encryptedValue );
        // System.out.println(decorVal.length);
        final byte[] decValue = c.doFinal( decorVal );
        decryptedValue = new String( decValue );
        } 
      catch ( Exception ex ) {
        System.out.println( "The Exception is=" + ex );
          // ex.printStackTrace();
        }
      return decryptedValue;
      }

    private static Key generateKeyFromString(final String secKey) throws Exception {
      final byte[] keyVal = secKey.getBytes();
      final Key key = new SecretKeySpec(keyVal, ALGORITHM);
      return key;
      }
}
