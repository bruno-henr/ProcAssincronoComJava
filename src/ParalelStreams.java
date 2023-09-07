import java.util.Arrays;
import java.util.List;

public class ParalelStreams {
    public static void main(String[] args) {
        // Criando uma lista de inteiros
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        // ForEach  vs forEachOrdenered
        System.out.println("ForEach  vs forEachOrdenered");
        list.parallelStream()
                .forEach(System.out::println);
        System.out.println("");
        list.parallelStream()
                .forEachOrdered(System.out::println);


        System.out.println("paralelStream vs stream");
        /*
        * Parallel Stream irá retornar um numero que esteja pronto para ser tratato
        * pq ao tratar numeros dentro de um parallel stream cada numero sera uma nova thread
        * e quando buscamos um valor aleatorio ele retorna o que estiver pronto no momento, por isso o valor é aleatorio
        * */
        list.parallelStream().findAny().ifPresent(System.out::println);
        /*
        * Já com stream o resultado é diferente, por que se trata de apenas uma thread, entao o valor será sempre o primeiro da lista
        * */
        list.stream().findAny().ifPresent(System.out::println);
    }
}
