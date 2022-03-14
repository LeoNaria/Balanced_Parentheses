import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author Leonardo
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Stack<Character> pilha;
        InputStream inputstream = new FileInputStream("Dados.txt");
        InputStreamReader reader = new InputStreamReader(inputstream);
        BufferedReader br = new BufferedReader(reader);
        Boolean autenticador = true;
        String line = br.readLine();
        List<String> listaResultado = new ArrayList<>();
        while (line != null) {
            pilha = new Stack<Character>();
            autenticador = true;
            Boolean validaAbertura = true;
            for (char letra : line.toCharArray()) {
                validaAbertura = verificaSeEAbertura(letra);
                if (validaAbertura) {
                    pilha.push(letra);
                } else {
                    if (pilha.empty()) {
                        autenticador = false;
                    } else {
                        if (letra == ')' && pilha.peek() != '(') {
                            autenticador = false;
                        } else if (letra == '}' && pilha.peek() != '{') {
                            autenticador = false;
                        } else if (letra == ']' && pilha.peek() != '[') {
                            autenticador = false;
                        } else if (letra == '>' && pilha.peek() != '<') {
                            autenticador = false;
                        } else {
                            pilha.pop();
                        }
                    }
                }
            }

            if(!autenticador)
               listaResultado.add(line + " inválido");
            else 
               listaResultado.add(line + " válido");
            line = br.readLine();
        }
        OutputStreamWriter bufferOut = new OutputStreamWriter(
        new FileOutputStream("Resultado"), "UTF-8");

        for (String word : listaResultado) {
            System.out.println(word);
            bufferOut.write(word + "\n");
        }
        bufferOut.close();
    }
    
    public static Boolean verificaSeEAbertura(char letra) {
        if(letra == '{' ||
           letra == '(' ||
           letra == '[' ||
           letra == '<') {
            return true;
        }
        else
            return false;
    }
}