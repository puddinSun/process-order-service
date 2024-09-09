package com.assessment.order.service;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import lombok.experimental.UtilityClass;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

@UtilityClass
public class RSAKeyBouncyCastleHandler {

  static {
    Security.addProvider(new BouncyCastleProvider());
  }

  public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048);
    return keyPairGenerator.generateKeyPair();
  }

  public static String generateRSAEncodedPrivateKey() throws NoSuchAlgorithmException {
    KeyPair keyPair = generateRSAKeyPair();
    byte[] encoded = keyPair.getPrivate().getEncoded();
    return Base64.getEncoder().encodeToString(encoded);
  }

  public static PrivateKey getRSAPrivateKeyFromString(String encodedPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
    // Decode the Base64-encoded string
    byte[] keyBytes = Base64.getDecoder().decode(encodedPrivateKey);
    // Create key specification
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
    // Generate the private key from the specification
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePrivate(spec);
  }

}
