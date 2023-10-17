package lab06.ex3;

public class BasicPrinter {
    
    private int inkAmount; 
    private int paperAmount; 

    // simula que impressora pode imprimir 4 documentos antes de terminar tinta e papel
    public BasicPrinter() {
        this.inkAmount = 4; 
        this.paperAmount = 4; 
    }

    public boolean print(String[] content) {
        if (this.hasInk() && this.hasPaper()) {
            // simula duração da impressão (1 segundo)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Document printed: \"" + content[0].substring(0, Math.min(20, content[0].length()-1)) + "...\"");
            this.inkAmount--;
            this.paperAmount--;
            if (this.inkAmount==0 || this.paperAmount == 0) {
                System.out.println("Please refill printer.");
            }
            return true;
        }
        return false;
    }

    public boolean hasInk() {
        return this.inkAmount > 0;
    } 

    public boolean hasPaper() {
        return this.paperAmount > 0;
    } 

    public void addInk() {
        this.inkAmount += 4;
    } 

    public void addPaper() {
        this.paperAmount += 4;
    } 

    // método auxiliar para simular a reposição de tinta e papel
    public void refill() {
        this.addInk();
        this.addPaper();
        System.out.println("Printer refilled.");
    } 
}
