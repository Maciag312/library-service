package pwr.awt.demo.infrastructure.security;

public interface AES {
    String encrypt(String message);
    String decrypt(String message);
}
