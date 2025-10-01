/*
 * Titulo: Juego del gato (tic tac toe) 
 * Descripcion: Programa que muestra el juego del gato 
 * @autor: Jared Eliezer Baldenegro Gomez
 * Materia: tecnologias de programacion
 * Profesor: Dra. María Lucia Barrón Estrada
 * Fecha: 26-09-2025
 */

import java.security.Key;

public class App {
    public static void main(String[] args) throws Exception {

        //Variable utilizada para romper ciclos
        boolean romperCiclo = false;
        final int puntaje_VICTORIA = 10;
        
        System.out.println("Este es el juego del gato (tic tac toe)");
        System.out.println("Los jugadores pueden elegir cual es su simbolo (X o O)");
        System.out.println("--------------------------------------------------------------------");

        Gato tableroGato = new Gato(); //Instancia de la clase Gato

        tableroGato.vaciarTablero();

        System.out.print("Nombre del jugador 1: ");
        Jugador j1 = new Jugador (tableroGato.ingresarNombre());

        System.out.print("Nombre del jugador 2: ");
        Jugador j2 = new Jugador (tableroGato.ingresarNombre());

        tableroGato.agregarJugador(j1,0);
        tableroGato.agregarJugador(j2,1);
        
        //////////////////////////////////////////////////////////////
        // Seleccion de fichas
        /////////////////////////////////////////////////////////////
        do {
            try {
                System.out.println("El jugador " + tableroGato.nombreJugador(0) + " seleccionara su ficha, el segundo se le asignara automaticamente la sobrante:");
                System.out.println("Ficha (ingresar en mayuscula): 'X' y 'O' (vocal)");
            
                // Se asegura de que se ingrese un solo carácter
                String entrada = Keyboard.readString().trim();
                
                if (entrada.length() !=1) {
                    throw new IllegalArgumentException("Debe ingresar un solo carácter.");

                }

                char ficha = Character.toUpperCase(entrada.charAt(0));

                // Dependiendo de la ficha, se le asigna el simbolo correspondiente
                if (ficha == 'X' || ficha == 'O') {
                    tableroGato.ingresarficha(ficha, 0);
                    tableroGato.ingresarficha((ficha == 'X') ? 'O' : 'X', 1);
                    romperCiclo = true;
                } else {
                    throw new IllegalArgumentException("Ficha inválida. Solo se permite 'X' u 'O'.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
            
            
        } while (!romperCiclo);


        romperCiclo = false; //Se reinicia la variable para usarla más tarde

        System.out.println("-----------------------------------------------");
        System.out.println("\nInstrucciones: ");
        System.out.println("Debe ingresar los numeros del 1 al 9 para colocar su ficha en el tablero.");
        System.out.println("[1][2][3] \n[4][5][6] \n[7][8][9] \n");


        // Turnos jugadores
        int turno = 0; // turno para saber que jugador va; Empieza jugador [0]
        boolean ganador = false; // Variable para saber si alguien gano. Si es true, se rompe el ciclo y se muestra el ganador
                                 // Si es false, se sigue jugando. Si se pasa de 9 turnos, se rompe el ciclo y se muestra el empate 

            
        //Los 9 turnos antes de que el tablero este lleno
        for (int i=0; i <= 9; i++) {
            do {
                tableroGato.mostrarTablero();
                System.out.println("Turno:" + tableroGato.nombreJugador(turno));

                System.out.println("Ingrese el numero de la casilla que desea ocupar: ");
                romperCiclo = tableroGato.ingresarCasilla(tableroGato.mostrarfichaJugador(turno));
                
            } while (!romperCiclo);

            romperCiclo = false;
            // Calcular victoria del jugador actual
            ganador = tableroGato.calcularVictoria(tableroGato.mostrarfichaJugador(turno));
            
            if (ganador) {
                tableroGato.mostrarTablero();
                tableroGato.ganador(turno);
                System.out.println("El ganador es: " + tableroGato.nombreJugador(turno));
                break;
            }
            // Cambiar turno de jugador
            turno = (turno + 1) % 2;

        }
        // Mostrar mensaje de empate Solo si no hubo ganador
        if (!ganador) {
            tableroGato.mostrarTablero();
            tableroGato.empate();
            System.out.println("Empate.");
        }

        System.out.println("Gracias por jugar!");
        
    }
}
