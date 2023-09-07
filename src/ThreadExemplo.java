public class ThreadExemplo {
    public static void main(String[] args) {
        BarraDeCarregamento2 b2 = new BarraDeCarregamento2(2000);// 0
        BarraDeCarregamento2 b22 = new BarraDeCarregamento2(1000); // 1

        b2.start();
        b22.start();
    }
}

class GerarPDF implements Runnable {
    @Override
    public void run() {
        System.out.println("Gerar PDF");
    }
}

class BarraDeCarregamento implements Runnable {
    @Override
    public void run() {
        System.out.println("Loading...");
    }
}

class BarraDeCarregamento2 extends Thread {
    int tempo;
    @Override
    public void run() {

        super.run();


        try {
            Thread.sleep(this.tempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("rodei: " + this.getName());
    }

    public BarraDeCarregamento2(int tempo) {
        this.tempo = tempo;
    }
}
