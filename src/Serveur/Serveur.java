package Serveur;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Serveur {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Serveur en attente...");

            Socket s = ss.accept();
            System.out.println("Client connecté");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(s.getOutputStream()));

            // Recevoir l'opération
            String op = in.readLine();
            System.out.println("Opération reçue : " + op);

            String[] p = op.split(" ");
            double a = Double.parseDouble(p[0]);
            String operateur = p[1];
            double b = Double.parseDouble(p[2]);
            double resultat = 0;

            switch (operateur) {
                case "+":
                    resultat = a + b;
                    break;
                case "-":
                    resultat = a - b;
                    break;
                case "*":
                    resultat = a * b;
                    break;
                case "/":
                    if (b == 0) {
                        out.write("Erreur : division par zéro");
                        out.newLine();
                        out.flush();
                        s.close();
                        ss.close();
                        return;
                    }
                    resultat = a / b;
                    break;
                default:
                    out.write("Opérateur invalide");
                    out.newLine();
                    out.flush();
                    s.close();
                    ss.close();
                    return;
            }

            // Envoyer le résultat
            out.write("Résultat = " + resultat);
            out.newLine();
            out.flush();

            s.close();
            ss.close();

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
