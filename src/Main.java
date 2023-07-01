import GerarArquivosBase.ClassificarPasswords;
import GerarArquivosBase.FiltrarSenhas;
import GerarArquivosBase.OrdenarArquivosAux;
import GerarArquivosBase.TransformarData;

public class Main {
    public static void main(String[] args) {
        
      

    /*  Para o código rodar perfeitamente, primeiro execute o médio caso, depois o 
     * melhor caso e por último o pior caso. As linhas dos comandos já estão na ordem
     * correta, basta remover os comentários (//). Não se esqueça de comentá-las depois.
     */
    //  O arquivo "passwords.csv" deve estar na pasta "Arquivos-Base".

    // Todos os arquivos de saída vão ser gerados na pasta Arquivos.


      ///////////ARQUIVOS BASE ///////////////

      // O primeiro arquivo que tem que ser gerado é o "password_classifier.csv"
      ClassificarPasswords cl = new ClassificarPasswords();
      //cl.classificarArquivo();

      // O segundo arquivo é o "password_formated_data.csv"
      TransformarData tr = new TransformarData();
      //tr.transformarDataArquivo();

      //O terceiro arquivo é o "passwords_classifier.csv"
      FiltrarSenhas fil = new FiltrarSenhas();
      //fil.filtrarArquivo();

      // Para as ordenações é necessário criar os arquivos de entrada para 
      // os piores casos. São eles: 
      OrdenarArquivosAux arqAux = new OrdenarArquivosAux();
      //arqAux.arquivoLength();
      //arqAux.arquivoMes();
      //arqAux.arquivoData();


     /////////////////////////SELECTION SORT//////////////////////////////////////
     SelectionSort s = new SelectionSort();
     // ORDENAR CAMPO LENGTH 
        //  s.medioCaso();
        //  s.melhorCaso();
        //  s.piorCaso();
    // ORDENAR PELO CAMPO MÊS 
        //  s.medioCasoMes();
        // s.melhorCasoMes();
        // s.piorCasoMes();
    // ORDENAR PELO CAMPO DATA
         s.medioCasoData();
        // s.melhorCasoData();
        // s.piorCasoData();



     /////////////////////////INSERTION SORT//////////////////////////////////////
     InsertionSort i = new InsertionSort();
     // ORDENAR CAMPO LENGTH 
        //   i.medioCaso();
        //   i.melhorCaso();
        //   i.piorCaso();
    // ORDENAR PELO CAMPO MÊS 
        //   i.medioCasoMes();
        //   i.melhorCasoMes();
        //   i.piorCasoMes();
    // ORDENAR PELO CAMPO DATA
        // i.medioCasoData();
        // i.melhorCasoData();
        // i.piorCasoData();


      /////////////////////////MERGE SORT//////////////////////////////////////
      MergeSort m = new MergeSort();
      // ORDENAR CAMPO LENGTH
        //   m.medioCaso();
        //   m.melhorCaso();
        //   m.piorCaso();
    // ORDENAR PELO CAMPO MÊS 
        //   m.medioCasoMes();
        //   m.melhorCasoMes();
        //   m.piorCasoMes();
    // ORDENAR PELO CAMPO DATA
        // m.medioCasoData();
        // m.melhorCasoData();
        // m.piorCasoData();

      /////////////////////////QUICKSORT//////////////////////////////////////
      QuickSort q = new QuickSort();
     // ORDENAR CAMPO LENGTH 
        //  q.medioCaso();
        //   q.melhorCaso();
        //   q.piorCaso();
     // ORDENAR PELO CAMPO MÊS 
        //  q.medioCasoMes();
        //   q.melhorCasoMes();
        //   q.piorCasoMes();
     // ORDENAR PELO CAMPO DATA
         q.medioCasoData();
        // q.melhorCasoData();
        // q.piorCasoData();


       /////////////////////////QUICKSORT COM MEDIANA DE 3//////////////////////////////////////
       QuickSortMedianaDeTres qM = new QuickSortMedianaDeTres();
      // ORDENAR CAMPO LENGTH
        //   qM.medioCaso();
        //   qM.melhorCaso();
        //   qM.piorCaso();
      // ORDENAR PELO CAMPO MÊS 
        //   qM.medioCasoMes();
        //   qM.melhorCasoMes();
        //   qM.piorCasoMes();
       // ORDENAR PELO CAMPO DATA
        // qM.medioCasoData();
        // qM.melhorCasoData();
        // qM.piorCasoData();


        /////////////////////////COUNTING//////////////////////////////////////
        Counting c = new Counting();
        // ORDENAR CAMPO LENGTH 
        //  c.medioCaso();
        //   c.melhorCaso();
        //   c.piorCaso();
      // ORDENAR PELO CAMPO MÊS 
        //  c.medioCasoMes();
        //  c.melhorCasoMes();
        //  c.piorCasoMes();
      // ORDENAR PELO CAMPO DATA
        // c.medioCasoData();
        // c.melhorCasoData();
        // c.piorCasoData();

      

        /////////////////////////HEAPSORT//////////////////////////////////////
        HeapSort h = new HeapSort();
       // ORDENAR CAMPO LENGTH 
        //   h.medioCaso();
        //   h.melhorCaso();
        //   h.piorCaso();
      // ORDENAR PELO CAMPO MÊS 
        //   h.medioCasoMes();
        //   h.melhorCasoMes();
        //   h.piorCasoMes();
      // ORDENAR PELO CAMPO DATA
        // h.medioCasoData();
        // h.melhorCasoData();
        // h.piorCasoData();

    }
}
