/*
 * Titulo: Juego del gato (tic tac toe) 
 * Descripcion: Programa que muestra el juego del gato 
 * @autor: Jared Eliezer Baldenegro Gomez
 * Materia: tecnologias de programacion
 * Profesor: Dra. María Lucia Barrón Estrada
 * Fecha: 26-09-2025
 */

public class App {
    public static void main(String[] args) throws Exception {

        //Variable utilizada para romper ciclos
        boolean salirJuego = false;
        final int PUNTUAJE_VICTORIA = 10;
        int partida = 1;
        
        System.out.println("Este es el juego del gato (tic tac toe)");
        System.out.println("Los jugadores pueden elegir cual es su simbolo (X o O)");
        System.out.println("--------------------------------------------------------------------");

        Gato tableroGato = new Gato(); //Instancia de la clase Gato

        
        tableroGato.crearJugadores();
        
        //////////////////////////////////////////////////////////////
        // Seleccion de fichas
        /////////////////////////////////////////////////////////////
        tableroGato.asignarFicha();


        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("\nInstrucciones: ");
        System.out.println("[1][2][3] \n[4][5][6] \n[7][8][9] \n");
        System.out.println("\n 1.- Debe ingresar los numeros del 1 al 9 para colocar su ficha en el tablero.");
        System.out.println("2.- Si ingresa un numero que no este en el tablero, se le informara de ello.");
        System.out.println("3.- Si ingresa un numero que este ocupado, se le informara de ello.");
        System.out.println("------------------------------------------------------------------------------------");

        

        /*
         * Repetir Ciclo mientras se jueguen nuevas partidas
         */
        while (!salirJuego) {
            int turno = 0; // turno para saber que jugador va; Empieza jugador [0]
            int ronda = 1;
            /*
            * Ciclo para jugar el juego
            * Se repetira hasta que un jugador alcanze el puntuaje maximo (PUNTUAJE_VICTORIA)
            */
            while(tableroGato.mostrarpuntajeJugador(0) < PUNTUAJE_VICTORIA && tableroGato.mostrarpuntajeJugador(1) < PUNTUAJE_VICTORIA) {

                boolean huboGanador = false;

                tableroGato.mostrarDatosJugadores();

                //Los 9 turnos antes de que el tablero este lleno
                for (int i=1; i <= 9; i++) {
                    
                    //No avanza el turno si la casilla esta ocupada o no se ingreso un numero del 1 al 9
                    do {
                        System.out.println("--------------------------------------------------------------------");
                        System.out.println("Partida: " + partida);
                        System.out.println("Ronda: " + ronda);
                        System.out.println("Turno:" + tableroGato.nombreJugador(turno));
                        tableroGato.mostrarTablero();
                        System.out.println("Ingrese el numero de la casilla que desea ocupar: ");
                        
                    } while (!tableroGato.ingresarCasilla(tableroGato.mostrarfichaJugador(turno))); //!romperCiclo

                    
                    // Si alguien gano se rompe el ciclo
                    if (tableroGato.calcularVictoria(tableroGato.mostrarfichaJugador(turno))) { //ganador == true
                        tableroGato.mostrarTablero();
                        tableroGato.ganador(turno);
                        System.out.println("El ganador es: " + tableroGato.nombreJugador(turno));
                        huboGanador = true;
                        break;
                    } else {
                    // Cambiar turno de jugador
                    turno = (turno + 1) % 2;
                    }
                    
                  

                }
                // Mostrar mensaje de empate Solo si no hubo ganador
                if (!huboGanador) { //!ganador
                    tableroGato.mostrarTablero();
                    tableroGato.empate();
                    System.out.println("Empate.");
                }


                tableroGato.vaciarTablero();
                
                ronda++;

            }

            

            tableroGato.agregarHistorial(partida);

            boolean respuestaValida = false;
            do {
                try {
                    System.out.println("¿jugar otra partida? (s/n)");
                    
                    String entrada = Keyboard.readString().trim();
                        
                        if (entrada.length() !=1) {
                            throw new IllegalArgumentException("Debe ingresar un solo carácter.");
                        }

                        char respuesta = Character.toUpperCase(entrada.charAt(0));

                    
                    if (respuesta == 'S' || respuesta == 'N') {
                        if (respuesta== 'S') {
                            partida++;
                            tableroGato.reiniciarConGanador(turno);
                        } else if (respuesta == 'N') {
                            System.out.println("Gracias por jugar!");
                            tableroGato.mostrarHistorialParticipantes();
                            salirJuego = true;
                        }
                        respuestaValida = true;
                    }
                    
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error inesperado: " + e.getMessage());
                }
            
            } while (!respuestaValida);
            

        }
    
    }
}
