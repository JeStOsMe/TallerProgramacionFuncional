package calculadora;

import java.text.ParseException;
import java.util.Scanner;

import interfaces.Operaciones;

public class Calculadora {

    public static Scanner sc = new Scanner(System.in);
    public static Operaciones operaciones;

    public static void main(String[] args) {

        boolean enBucle = true;
        int opcion;

        do{
            System.out.println("\n     ***** Calculadora en Paradigma Funcional *****");
            System.out.println("Por indicación, solo se trabajarán números enteros.");
            System.out.println("\nOpciones");
            System.out.println("\t1. Sumar");
            System.out.println("\t2. Restar");
            System.out.println("\t3. Multiplicar");
            System.out.println("\t4. Dividir");
            System.out.println("\t5. Salir");
            System.out.print("\n\tOpción ---> ");
            
            try{
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception ex){
                System.out.println("\nERROR: solo se admiten números. Entrando en bucle...");
                continue;
            }

            switch(opcion){
                case 1:
                    sumar();
                    break;
                case 2:
                    restar();
                    break;
                case 3:
                    multiplicar();
                    break;
                case 4:
                    dividir();
                    break;
                case 5:
                    System.out.println("\nSaliendo del bucle...");
                    enBucle = false;
                    break;
                default:
                    System.out.println("\nERROR: opción no permitida. Entrando en bucle...");
                    break;
            }

        } while(enBucle);
    }

    public static Integer verificarEntero(String num){
        int aux;
        try{
            aux = Integer.parseInt(num);
            return aux;
        } catch (Exception ex){
            System.out.println("\nERROR: solo se admiten números enteros. ");
            return null;
        }
    }

    public static void dividir() {
    }

    public static void multiplicar() {
    }

    public static void restar() {
    }

    public static void sumar() {
        Integer num1, num2;

        System.out.println("\n\tSumas");
        
        System.out.print("\n\t\tIngrese el primer número: ");
        num1 = verificarEntero(sc.nextLine());

        if (num1 == null){ return; }

        System.out.print("\n\t\tIngrese el segundo número: ");
        num2 = verificarEntero(sc.nextLine());

        if (num2 == null){ return; }

        operaciones = new Operaciones() {

            @Override
            public Integer hacerOperacion(Integer num1, Integer num2) {
                return (num1 + num2);
            }
        };

        System.out.println("\n\n\t\t" + 
            num1 + " + " + num2 + " = " + 
            operaciones.hacerOperacion(num1, num2));
    }
    
}
