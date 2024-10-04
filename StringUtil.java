package blockchain;

import java.security.MessageDigest;

/**
 * Uma classe utilitária para funções criptográficas relacionadas a strings.
 * Esta classe fornece métodos para hash de strings usando o algoritmo SHA-256.
 */
public class StringUtil {
    
    /**
     * Aplica a função de hash SHA-256 a uma string de entrada fornecida.
     *
     * @param input a string a ser hashada
     * @return a representação hexadecimal do hash SHA-256
     * @throws RuntimeException se ocorrer um erro durante o cálculo do hash
     */
    public static String applySha256(String input) {
        try {
            // Cria uma instância de MessageDigest para SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Calcula o hash da string de entrada
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            
            // Converte o array de bytes em uma string hexadecimal
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                // Adiciona zero à esquerda para valores hexadecimais de um dígito
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método principal para demonstrar o uso do método applySha256.
     *
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Exemplo de uso do método applySha256
        System.out.println(StringUtil.applySha256("123"));
    }
}
