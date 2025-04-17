package library;

public class MergeSort {

    public static int comparacoes = 0;

    public static String[] ordenar(String[] oldArr) {
        comparacoes = 0;
        return ordenarRec(oldArr.clone());
    }

    private static String[] ordenarRec(String[] arr) {
        if (arr.length < 2) {
            return arr;
        }

        int meio = arr.length / 2;
        String[] esquerda = new String[meio];
        String[] direita = new String[arr.length - meio];

        System.arraycopy(arr, 0, esquerda, 0, meio);
        System.arraycopy(arr, meio, direita, 0, arr.length - meio);

        ordenarRec(esquerda);
        ordenarRec(direita);

        merge(arr, esquerda, direita);

        return arr;
    }

    private static void merge(String[] arr, String[] esquerda, String[] direita) {
        int i = 0, j = 0, k = 0;

        while (i < esquerda.length && j < direita.length) {
            comparacoes++;
            if (esquerda[i].compareTo(direita[j]) <= 0) {
                arr[k++] = esquerda[i++];
            } else {
                arr[k++] = direita[j++];
            }
        }

        while (i < esquerda.length) {
            arr[k++] = esquerda[i++];
        }

        while (j < direita.length) {
            arr[k++] = direita[j++];
        }
    }
}
