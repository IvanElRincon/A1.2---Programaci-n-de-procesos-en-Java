import java.io.IOException;
import java.util.Arrays;
// Remember this library
import java.util.concurrent.TimeUnit;
import java.io.*;

/*  - I would really like to return a Process o ProcessBuilder object
    through a function, could we try it in the next lesson?
    -After destroy(), waitfor() let us wait for the children kill?
 */


public class Operations {
    public void ejecutaComando(String command) {
            ProcessBuilder pb = new ProcessBuilder("CMD","/C",command);
            pb.inheritIO();
            try{
            Process p = pb.start();
            p.waitFor();
            }
            catch(IOException ioe){
                System.out.println("No funciona por IOException");
            }
            catch(InterruptedException ie){
                System.out.println("No funciona por InterruptedException");
            }
    }

    public void ejecutaComando(String [] command)  {
        var result=fuseCmdArray(command);

        ProcessBuilder pb = new ProcessBuilder(result);
        pb.inheritIO();
        try{
            Process p = pb.start();
            p.waitFor();
        }
        catch(IOException ioe){
            System.out.println("No funciona por IOException");
        }
        catch(InterruptedException ie){
            System.out.println("No funciona por InterruptedException");
        }
    }

    public void ejecutaComandoyComprueba (String command){
        ProcessBuilder pb = new ProcessBuilder("CMD","/C",command);
        pb.inheritIO();
        try{
            Process p = pb.start();
            p.waitFor();
            while(p.isAlive()){
                System.out.println("Esperando...");
                p.waitFor(5, TimeUnit.SECONDS);
            }
        }
        catch(IOException ioe){
            System.out.println("No funciona por IOException");
        }
        catch(InterruptedException ie){
            System.out.println("No funciona por InterruptedException");
        }
    }

    public void ejecutaComandoyComprueba (String [] command) throws IOException,InterruptedException{
        var result=fuseCmdArray(command);
        ProcessBuilder pb = new ProcessBuilder(result);
        pb.inheritIO();
        try{
            Process p = pb.start();
            p.waitFor();
            while(p.isAlive()){
                System.out.println("Esperando...");
                p.waitFor(5, TimeUnit.SECONDS);
            }
        }
        catch(IOException ioe){
            System.out.println("No funciona por IOException");
        }
        catch(InterruptedException ie){
            System.out.println("No funciona por InterruptedException");
        }
    }

    public void ejecutaComandoyEspera(String command)throws IOException,InterruptedException{
        ProcessBuilder pb = new ProcessBuilder("CMD","/C",command);
        pb.inheritIO();
        try {
            Process p = pb.start();
            p.waitFor(5,TimeUnit.SECONDS);
            if(p.isAlive()){
                p.waitFor();
                p.destroy();
            }
        }
        catch(IOException ioe){
            System.out.println("No funciona por IOException");
        }
        catch(InterruptedException ie){
            System.out.println("No funciona por InterruptedException");
        }

    }

    public void ejecutaComandoyEspera(String [] command)throws IOException,InterruptedException{
        var result=fuseCmdArray(command);
        ProcessBuilder pb = new ProcessBuilder(result);
        pb.inheritIO();
        try {
            Process p = pb.start();
            p.waitFor(5,TimeUnit.SECONDS);
            if(p.isAlive()){
                p.destroy();
                p.waitFor();
            }
        }
        catch(IOException ioe){
            System.out.println("No funciona por IOException");
        }
        catch(InterruptedException ie){
            System.out.println("No funciona por InterruptedException");
        }

    }

    public void ejecutaComandoDirectorio(String command, String directory){

        ProcessBuilder pb = new ProcessBuilder("CMD","/C","cd",directory,"&&",command);
        pb.inheritIO();
        ProcessBuilder print= new ProcessBuilder("CMD","/C","cd",directory,"&&","echo","%cd%");
        print.inheritIO();
        try{
            Process p = pb.start();
            p.waitFor();
            System.out.println("\n Comando ejecutado en:");
            Process prnt=print.start();
        }
        catch(IOException ioe){
            System.out.println("No funciona por IOException");
        }
        catch(InterruptedException ie){
            System.out.println("No funciona por InterruptedException");
        }
    }

    public void ejecutaComandoDirectorio(String[] command, String directory){
        String [] aux={"cd",directory};
        var temp=fuseCmdArray(aux);
        var cdDir=arrayStringfuse(temp,new String []{"&&"});
        var result=arrayStringfuse(cdDir,command);

        ProcessBuilder pb = new ProcessBuilder(result);
        pb.inheritIO();
        ProcessBuilder print= new ProcessBuilder("CMD","/C","echo","%cd%");
        print.inheritIO();
        try{
            Process p = pb.start();
            p.waitFor();
            System.out.println("Commando ejecutado en:");
            Process prnt=print.start();
        }
        catch(IOException ioe){
            System.out.println("No funciona por IOException");
        }
        catch(InterruptedException ie){
            System.out.println("No funciona por InterruptedException");
        }
    }

    public void buscayGuarda(File fFile,File sFile) throws IOException{
        var bR=new BufferedReader(new FileReader(fFile));
        var bW=new BufferedWriter(new FileWriter(sFile));
        var stLine=bR.readLine();

        ProcessBuilder pb = new ProcessBuilder("CMD","/C","find",stLine,"C");
        pb.inheritIO();

        try{
            Process p = pb.start();
            int i;

            while(bR.ready()){
            bW.write(p.inputReader().readLine());
            }
            p.waitFor();
        }
        catch(IOException ioe){
            System.out.println("No funciona por IOException");
        }
        catch(InterruptedException ie){
            System.out.println("No funciona por InterruptedException");
        }
    }


    private String[] fuseCmdArray(String [] command){
        String [] cmd={"CMD","/C"};
        int firLength=cmd.length;
        int secLength= command.length;
        String [] result=new String [firLength+secLength];

        System.arraycopy(cmd,0,result,0,firLength);
        System.arraycopy(command,0,result,firLength,secLength);
        return result;
    }

    private String[] arrayStringfuse(String [] firstArray, String [] secondArray){
        int firLength=firstArray.length;
        int secLength= secondArray.length;
        String [] result=new String [firLength+secLength];

        System.arraycopy(firstArray,0,result,0,firLength);
        System.arraycopy(secondArray,0,result,firLength,secLength);
        return result;
    }



}
