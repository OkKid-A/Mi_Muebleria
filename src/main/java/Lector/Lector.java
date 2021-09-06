package Lector;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Lector {

    private Reader lectorBase;
    private BufferedReader lectorBuff;

    public Lector(){
    }

    public ArrayList<String> leerDocumentoPorLineas(BufferedReader red) {
        ArrayList<String> objetosLeidos = new ArrayList<>();
        try {
            lectorBuff = red;
            String linea = lectorBuff.readLine();
            while (linea != null){
                System.out.println(linea);
                linea = cambiarQuote(linea);
                System.out.println(linea);
                objetosLeidos.add(linea);
                linea = lectorBuff.readLine();
            }
            lectorBuff.close();
            //objetosLeidos = (ArrayList<String>) Files.readAllLines(Paths.get(archivo), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objetosLeidos;
    }

    public String cambiarQuote(String aCambiar){
        char[] deconstruida = aCambiar.toCharArray();
        char[] nueva = new char[deconstruida.length];
        int cont = 0;
        for (int k = 0; k < deconstruida.length;k++){
            if (deconstruida[k] == '“'|| deconstruida[k] == '”'){
                deconstruida[k] = '\'';
            } else {
                nueva[cont] = deconstruida[k];
                cont++;
            }
        }
        char[] completa = new char[cont];
        for (int i = 0; i < completa.length;i++) {
            completa[i] = nueva[i];
        }
        String cambiada = String.valueOf(completa);
        return cambiada;
    }
}
