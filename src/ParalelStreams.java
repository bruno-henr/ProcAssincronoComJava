import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        // skip
        /*
        * skip: pula um elemento da lista
        * limit: limita o total de consultas
        *
        * com essa abordagem os elementos devem está sincronizados para seja possivel
        * fazer o skip e limitar as consultas, pois assim se perde performace na aplicacao
        * OBS: para resolver essa gargalo, é recomendado utilizar a funcao .unordered() !!
        * Entao o skip vai poder pular quaisquer elemento da lista
        * */
        list.parallelStream()
                .skip(1).limit(2)
                .findAny().ifPresent(System.out::println);

        // Collectors
        /*
        * toMap irá criar um map para cada elemento da lista e ao final juntar tudo em
        * um só mapa
        * Porem utilizando * toConcurrentMap * ira criar apenas um map e os elementos irao
        * fazer suas insercoes dentro dessa Map, assim consumindo menos memoria
        * */
        Map<Integer, Boolean> collect = list.parallelStream()
                .collect(
                        Collectors.toMap(n -> n,n -> n % 2 == 0)
                );
        System.out.println(collect);
        //groupinBy
        /**
         * Como o novo ja diz, ele vai agrupar por alguma coisa, nesse caso por numeros
         * pares e impares
         * */
        Map<Boolean, List<Integer>> collect2 = list.parallelStream()
                .collect(
                        Collectors.groupingBy(n -> n % 2 == 0)
                );
        System.out.println(collect2);
    }
}
