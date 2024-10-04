package blockchain;

/**
 * A classe Block representa um bloco na blockchain.
 * Cada bloco contém um hash, o hash do bloco anterior, dados,
 * um timestamp e um nonce para o processo de mineração.
 */
public class Block {

    public String hash;          // Hash do bloco atual
    public String previousHash;  // Hash do bloco anterior
    private String data;         // Dados armazenados no bloco
    private long timeStamp;      // Timestamp do bloco
    private int nonce;           // Número usado para minerar o bloco

    /**
     * Construtor da classe Block com hash e hash do bloco anterior.
     *
     * @param hash o hash do bloco
     * @param previousHash o hash do bloco anterior
     * @param data os dados a serem armazenados no bloco
     */
    public Block(String hash, String previousHash, String data) {
        this.hash = hash;
        this.previousHash = previousHash;
        this.data = data;
    }

    /**
     * Construtor da classe Block que gera um novo bloco a partir de dados e do hash do bloco anterior.
     *
     * @param data os dados a serem armazenados no bloco
     * @param previousHash o hash do bloco anterior
     */
    public Block(String data, String previousHash) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = System.currentTimeMillis(); // Obtém o timestamp atual
        this.hash = calculateHash(); // Calcula o hash do bloco
    }

    /**
     * Calcula o hash do bloco atual com base no hash do bloco anterior,
     * no timestamp, no nonce e nos dados.
     *
     * @return o hash calculado como uma string
     */
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                Long.toString(timeStamp) +
                Integer.toString(nonce) +
                data);
        return calculatedhash;
    }

    /**
     * Realiza o processo de mineração do bloco, ajustando o nonce até
     * que o hash do bloco atenda à dificuldade especificada.
     *
     * @param difficulty o número de zeros iniciais que o hash deve ter
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); 
        while(!hash.substring(0, difficulty).equals(target)) {
            nonce++; // Incrementa o nonce
            hash = calculateHash(); // Calcula o novo hash
        }
        System.out.println("Bloco minerado: " + hash);
    }
}

