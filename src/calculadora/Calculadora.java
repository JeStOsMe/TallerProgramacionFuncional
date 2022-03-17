package calculadora;

import java.util.Scanner;

import interfaces.OperacionesMD;
import interfaces.OperacionesSM;

public class Calculadora {

    public static Scanner sc = new Scanner(System.in);
    public static OperacionesSM operacionesSM;
    public static OperacionesMD operacionesMD;

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
            System.out.println("\t4. Dividir (división entera)");
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
        Integer num1, num2, contador, resultado;
        boolean num1Negativo = false, num2Negativo = false, signosIguales = false;

        System.out.println("\n\tDivisiones");
        
        System.out.print("\n\t\tIngrese el primer número: ");
        num1 = verificarEntero(sc.nextLine());

        if (num1 == null){ return; }

        System.out.print("\n\t\tIngrese el segundo número: ");
        num2 = verificarEntero(sc.nextLine());

        if (num2 == null){ return; }

        if( num1 < 0){
            num1Negativo = true;
            num1 *= -1;
        }

        if( num2 < 0){
            num2Negativo = true;
            num2 *= -1;
        }

        contador = 0;

        //Si ambos números tienen el mismo signo, se asigna verdadero
        signosIguales = (num1Negativo == num2Negativo);

        operacionesMD = new OperacionesMD() {

            @Override
            public Integer hacerOperacion(Integer num1, Integer num2, Integer contador) {
                Integer acumulado = num1;

                if (num2 == 0){
                    return 0;
                }
                if (num2 == 1){
                    return acumulado;
                }

                acumulado = restar(num1, num2);

                contador++;

                if(acumulado < num2){
                    return contador;
                }

                contador = hacerOperacion(acumulado, num2, contador);

                return contador;

            }
        };

        resultado = operacionesMD.hacerOperacion(num1, num2, contador);

        //Si es verdadero, el resultado será positivo. Si no, será negativo.
        if (signosIguales){
            resultado = Math.abs(resultado);
        } else{
            resultado *= -1;
        }

        if (num1Negativo){
            num1 *= -1;
        }
        if (num2Negativo){
            num2 *= -1;
        }

        System.out.println("\n\n\t\t" + 
                num1 + " / " + num2 + " = " + 
                resultado);
    }

    public static void multiplicar() {
        Integer num1, num2, contador, resultado;
        boolean num1Negativo = false, num2Negativo = false, signosIguales = false;

        System.out.println("\n\tMultiplicaciones");
        
        System.out.print("\n\t\tIngrese el primer número: ");
        num1 = verificarEntero(sc.nextLine());

        if (num1 == null){ return; }

        System.out.print("\n\t\tIngrese el segundo número: ");
        num2 = verificarEntero(sc.nextLine());

        if (num2 == null){ return; }

        if( num1 < 0){
            num1Negativo = true;
            num1 *= -1;
        }

        if( num2 < 0){
            num2Negativo = true;
            num2 *= -1;
        }

        contador = num2;

        //Si ambos números tienen el mismo signo, se asigna verdadero
        signosIguales = (num1Negativo == num2Negativo);

        operacionesMD = new OperacionesMD() {

            @Override
            public Integer hacerOperacion(Integer num1, Integer num2, Integer contador) {
                Integer acumulado = num1;

                if (num2 == 0){
                    return 0;
                }
                if (num2 == 1){
                    return acumulado;
                }

                acumulado = sumar(num1, 0);

                contador--;

                if(contador == 0){
                    return acumulado;
                }

                acumulado += hacerOperacion(num1, num2, contador);

                return acumulado;

            }
        };

        resultado = operacionesMD.hacerOperacion(num1, num2, contador);

        //Si es verdadero, el resultado será positivo. Si no, será negativo.
        if (signosIguales){
            resultado = Math.abs(resultado);
        } else{
            resultado *= -1;
        }

        if (num1Negativo){
            num1 *= -1;
        }
        if (num2Negativo){
            num2 *= -1;
        }

        System.out.println("\n\n\t\t" + 
                num1 + " * " + num2 + " = " + 
                resultado);
        
    }

    public static void restar() {
        Integer num1, num2;

        System.out.println("\n\tRestas");
        
        System.out.print("\n\t\tIngrese el primer número: ");
        num1 = verificarEntero(sc.nextLine());

        if (num1 == null){ return; }

        System.out.print("\n\t\tIngrese el segundo número: ");
        num2 = verificarEntero(sc.nextLine());

        if (num2 == null){ return; }

        operacionesSM = new OperacionesSM() {

            @Override
            public Integer hacerOperacion(Integer num1, Integer num2) {
                return (num1 - num2);
            }
        };

        System.out.println("\n\n\t\t" + 
            num1 + " - " + num2 + " = " + 
            operacionesSM.hacerOperacion(num1, num2));
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

        operacionesSM = new OperacionesSM() {

            @Override
            public Integer hacerOperacion(Integer num1, Integer num2) {
                return (num1 + num2);
            }
        };

        System.out.println("\n\n\t\t" + 
            num1 + " + " + num2 + " = " + 
            operacionesSM.hacerOperacion(num1, num2));
    }

    public static Integer sumar(Integer num1, Integer num2) {
        
        operacionesSM = new OperacionesSM() {

            @Override
            public Integer hacerOperacion(Integer num1, Integer num2) {
                if (num2 == 1){
                    return num1;
                } else{
                    return (num1 + num2);
                }
            }
        };

        return operacionesSM.hacerOperacion(num1, num2);
    }

    public static Integer restar(Integer num1, Integer num2) {
        
        operacionesSM = new OperacionesSM() {

            @Override
            public Integer hacerOperacion(Integer num1, Integer num2) {
                return (num1 - num2);
            }
        };

        return operacionesSM.hacerOperacion(num1, num2);
    }
    
}
