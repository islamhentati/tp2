package Serveur;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import Objet.Operation;
public class Serveur {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Serveur en attente...");

            Socket s = ss.accept();
            System.out.println("Client connecté");

            ObjectOutputStream out = new ObjectOutputStream(
                    s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(
                    s.getInputStream());

            // Recevoir l'objet
            Operation op = (Operation) in.readObject();
            System.out.println("Objet reçu");

            double a = op.getA();
            String operateur = op.getOperateur();
            double b = op.getB();
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
                        out.writeObject("Erreur : division par zéro");
                        out.flush();
                        s.close();
                        ss.close();
                        return;
                    }
                    resultat = a / b;
                    break;
                default:
                    out.writeObject("Erreur : opérateur invalide");
                    out.flush();
                    s.close();
                    ss.close();
                    return;
            }

            // Envoyer le résultat
            out.writeObject("Résultat = " + resultat);
            out.flush();

            s.close();
            ss.close();

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
