package appprolog;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import jpl.Query;

public class AppProlog {

    static Scanner sc = new Scanner(System.in);
    static String consulta = "consult('c:/prolog/parientes.pl').";
    static Query resultado = new Query(consulta);
    static ArrayList<String> progenitores = new ArrayList<>();
    static ArrayList<String> hijos = new ArrayList<>();
    static String[] llaves = {"progenitor", "hombre", "mujer", "descendiente", "padre", "hermana", "nieto", "tio", "papa", "mama", "esposo"};
    static String var1 = "X";
    static String var2 = "Y";
    static String parentesco = "";

    public static void main(String[] args) {

        if (resultado.hasSolution()) {
            System.out.println("Conexión exitosa");
            consultarParentesco();
            int op = 0;

            while (op != 3) {
                op = menu();
                switch (op) {
                    case 1:
                        consultar();
                        break;
                    case 2:
                        for (int i = 0; i < progenitores.size(); i++) {
                            System.out.println(progenitores.get(i) + " es progenitor de " + hijos.get(i));
                        }
                        break;
                    case 3:
                        System.out.println("Orales!");
                        break;
                }
                
                sc.nextLine();
                sc.nextLine();
            }
        }
    }

    public static void consultar(){
        //String pregunta = llave + "(" + var1 + "," + var2 + ").";
        String pregunta = "progenitor(pedro,jorge).";
        Query myConsult = new Query(pregunta);
        System.out.println("Proceso: " + myConsult.hasSolution());
    }
    
    public static void consultarParentesco() {
        parentesco = "progenitor";
        var1 = "X";
        var2 = "Y";
        String pregunta = parentesco + "(" + var1 + "," + var2 + ").";
        Query myConsult = new Query(pregunta);
        Hashtable solucion;
        while (myConsult.hasMoreSolutions()) {
            solucion = myConsult.nextSolution();
            progenitores.add(solucion.get("X").toString());
            hijos.add(solucion.get("Y").toString());
            //System.out.println(solucion.get("X").toString().toUpperCase() + " es progenitor de " + solucion.get("Y").toString().toUpperCase());
        }
    }

    public static int menu() {
        System.out.println("MENÚ");
        System.out.println("1. Nueva pregunta");
        System.out.println("2. Ver parentesco");
        System.out.println("3. Salir");

        int op = sc.nextInt();
        return op;
    }
}
