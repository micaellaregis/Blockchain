package blockchain;

import java.util.ArrayList;

/**
 * A classe BlockChainList representa uma lista de blocos em uma 
 * blockchain simples. Ela contém métodos para criar blocos, calcular 
 * hashes e validar a integridade da cadeia de blocos.
 * 
 * <p>A classe usa um ArrayList para armazenar os blocos e possui um 
 * nível de dificuldade fixo para a mineração dos blocos.</p>
 * 
 * <p>Os blocos são criados sequencialmente, onde cada bloco contém 
 * um hash do bloco anterior para garantir a integridade da cadeia.</p>
 */
public class BlockChainList {
    /** A lista que armazena os blocos da blockchain. */
    public static ArrayList<Block> blockChain = new ArrayList<Block>();

    /** O nível de dificuldade para mineração de blocos. */
    public static int difficulty = 5;

    /**
     * O ponto de entrada do programa. Cria os três primeiros blocos 
     * da blockchain e exibe seus hashes.
     * 
     * @param args Os argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        // Primeiro bloco
        Block firstBlock = new Block("Primeiro", "0");
        System.out.println("Hash para o bloco 1: " + firstBlock.hash);

        // Segundo bloco
        Block secondBlock = new Block("segundo", firstBlock.hash);
        System.out.println("Hash para o bloco 2: " + secondBlock.hash);

        // Terceiro bloco
        Block thirdBlock = new Block("Terceiro", secondBlock.hash);
        System.out.println("Hash para o bloco 3: " + thirdBlock.hash);
    }

    /**
     * Verifica a validade da blockchain.
     * 
     * <p>Esse método verifica se os hashes dos blocos estão corretos, 
     * se o hash anterior de cada bloco corresponde ao hash do bloco 
     * anterior e se o hash do bloco cumpre a condição de dificuldade.</p>
     * 
     * @return true se a blockchain for válida, false caso contrário.
     */
    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        boolean flag = true;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockChain.size(); i++) {
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i - 1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("O hash do bloco não é igual.");
                flag = false;
            }

            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("O hash anterior não é igual.");
                flag = false;
            }

            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("Esse bloco não foi minerado.");
                flag = false;
            }
        }

        return flag;
    }
}
