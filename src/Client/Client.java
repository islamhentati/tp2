package Client;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import Objet.Operation;
public class Client {

    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);

        System.out.print("Premier nombre : ");
        double a = clavier.nextDouble();

        System.out.print("Opérateur (+, -, *, /) : ");
        String operateur = clavier.next();

        System.out.print("Deuxième nombre : ");
        double b = clavier.nextDouble();

        if (!operateur.equals("+")
                && !operateur.equals("-")
                && !operateur.equals("*")
                && !operateur.equals("/")) {
            System.out.println("Opérateur invalide.");
            clavier.close();
            return;
        }

        if (operateur.equals("/") && b == 0) {
            System.out.println("Division par zéro impossible.");
            clavier.close();
            return;
        }

        try {
            Socket s = new Socket("localhost", 5000);

            ObjectOutputStream out =
                    new ObjectOutputStream(s.getOutputStream());

            ObjectInputStream in =
                    new ObjectInputStream(s.getInputStream());

            // Créer l'objet Operation
            Operation op = new Operation(a, operateur, b);

            // Envoyer l'objet au serveur
            out.writeObject(op);
            out.flush();

            // Recevoir le résultat
            Object reponse = in.readObject();

            if (reponse instanceof Double) {
                System.out.println("Résultat = " + reponse);
            } else {
                System.out.println(reponse);
            }

            s.close();

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        clavier.close();
    }
}