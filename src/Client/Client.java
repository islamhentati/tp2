package Client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5000);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(s.getOutputStream()));

            Scanner clavier = new Scanner(System.in);

            System.out.print("Entrez une opération (ex: 55 * 25) : ");
            String op = clavier.nextLine();

            // Envoyer l'opération au serveur
            out.write(op);
            out.newLine();
            out.flush();

            // Recevoir et afficher le résultat
            String resultat = in.readLine();
            System.out.println(resultat);

            s.close();
            clavier.close();

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}