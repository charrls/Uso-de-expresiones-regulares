import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
/*
Comparativa del promedio de tiempos de los metodos para el conteo del archivo "divina_comedia.txt":
Metodo StringTokenizer = 70 milisegundos
Metodo StringSplit = 12 milisegundos


Comparativa de resultados:
Hubo diferencias en los resultados, mayormente el metodo StringSplit arroja valores menores que el metodo StringTokenizer,
seguramente sea una desventaja del algoritmo ya que probablemente no detecte al final de cada linea la ultima palabra
ya que no existe un espacio(" "), a lo que yo entiendo.
 */
public class ContadorTexto {
    public static void main(String[] args) throws IOException {

                if (args.length == 0) {
                    System.out.println("Falta el nombre del archivo");
                    System.exit(1);
                }

                String fileName = args[0];
                ContadorTexto st = new ContadorTexto();

                st.StringTokenizer(fileName);
                st.StringSplit(fileName);
    }


            public void StringTokenizer(String fname){
                FileReader fileReader = null;

                try {
                    fileReader = new FileReader(fname);
                } catch (FileNotFoundException e) {
                    System.out.println("El nombre del archivo no se encuentra");
                    System.exit(2);
                }

                StreamTokenizer st = new StreamTokenizer(fileReader);
                String textLine = null;
                int contadorPalabras = 0;
                int tipoToken = 0;

                long start = System.currentTimeMillis();
                try {
                    while ((tipoToken = st.nextToken()) != StreamTokenizer.TT_EOF) {
                        if( tipoToken == StreamTokenizer.TT_WORD) {
                            contadorPalabras++;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error al leer el archivo de entrada");
                    System.exit(3);
                } finally {
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                long time = System.currentTimeMillis() - start;
                System.out.println("Metodo StringTokenizer");
                System.out.printf("El archivo %s tiene %,8d palabras. " , fname, contadorPalabras );
                System.out.printf(" Numero de lineas: %,8d%n", st.lineno() );
                System.out.printf("Tiempo procesamiento (milisegundos): %d %n" , time);
            }

            public void StringSplit(String fName) throws IOException {
                String file = null;

                try {
                    file = new String(Files.readAllBytes(Paths.get(fName)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                long start = System.currentTimeMillis();
                int c = file.split(" ").length - 1;
                long time = System.currentTimeMillis() - start;

                System.out.println("\nMetodo StringSplit");
                System.out.printf("El archivo %s tiene %,8d palabras. " , fName, c );
                System.out.printf("Tiempo procesamiento (milisegundos): %d %n" , time);
            }

        }

