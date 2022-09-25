import java.io.*;
import java.sql.SQLOutput;
import java.text.FieldPosition;
import java.util.Arrays;

public class Procesos_franciscoIvanRamirezOrtega {
    public static void main (String [] args) throws IOException, InterruptedException {
        Operations o=new Operations();

        System.out.println("Metodos ejecutaComando");
        o.ejecutaComando("echo pepe1");
        o.ejecutaComando(new String[]{"dir"});
        darseUnTiempo();

        System.out.println("Metodos ejecutaComandoyComprueba");
        o.ejecutaComandoyComprueba("ver");
        o.ejecutaComandoyComprueba(new String[]{"netsh","wlan","show","profile"});
        darseUnTiempo();

        System.out.println("Metodos ejecutacomandoyEspera");
        o.ejecutaComandoyEspera("cd");
        o.ejecutaComandoyEspera(new String []{"echo","Metodo --> ejecutaComandoyEspera 2"});
        darseUnTiempo();

        System.out.println("Metodos ejecutaComandoDirectorio");
        // appears the disk serial number and volume
        o.ejecutaComandoDirectorio("dir","D:\\Users\\Francisco Ivan\\Escritorio\\DAM2\\Acceso a Datos\\Tema 1\\Actividades");
        o.ejecutaComandoDirectorio(new String[]{"ping","helloworld.com"},"D:\\Users\\Francisco Ivan\\Escritorio\\DAM2\\Diseño de interfaces");
        darseUnTiempo();

        /*
            System.out.println("Metodo buscayGuarda");
            File f1=new File(".");
            File f2=new File(".");
            BufferedWriter bw=new BufferedWriter(new FileWriter(f1));
            bw.write("ComprarWindows.TXT");
            o.buscayGuarda(f1,f2);
            BufferedReader bR=new BufferedReader(new FileReader(f2));
            int i;
            while((i=bR.read())!=-1){
                bR.read();
            }
        */



    }
    private static void darseUnTiempo(){
        for(int i=0;i<5;i++){
            System.out.println();
        }
    }
}
