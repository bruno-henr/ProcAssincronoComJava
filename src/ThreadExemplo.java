public class ThreadExemplo {
    public static void main(String[] args) {
//        BarraDeCarregamento2 b2 = new BarraDeCarregamento2(2000);// 0
//        BarraDeCarregamento2 b22 = new BarraDeCarregamento2(1000); // 1
//
//        b2.start();
//        b22.start();
        // Esse trecho vai executar antes do sout dentro da thread executar
//        System.out.println(b2.getName());
//        System.out.println(b22.getName());
//        Thread gerarPDF = new Thread(new GerarPDF());
//        gerarPDF.start();
        GerarPDF gerarPDF = new GerarPDF();
        Thread barraDeCarregamento = new Thread(new BarraDeCarregamento(gerarPDF));
        barraDeCarregamento.start();
    }
}

class GerarPDF implements Runnable {
    @Override
    public void run() {
        System.out.println("Gerando PDF...");
    }
}

class BarraDeCarregamento implements Runnable {
    GerarPDF gerarPDF;

    public BarraDeCarregamento (GerarPDF gerarPDF) {
        this.gerarPDF = gerarPDF;
    }
    @Override
    public void run() {
        System.out.println("Loading...");
        System.out.println("0%");


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
