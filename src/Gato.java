/*
 * Titulo: Juego del gato (tic tac toe) 
 * Descripcion: Programa que muestra el juego del gato 
 * @autor: Jared Eliezer Baldenegro Gomez
 * Materia: tecnologias de programacion
 * Profesor: Dra. María Lucia Barrón Estrada
 * Fecha: 26-09-2025
 */

public class Gato {
    private char[] tablero = new char[9];
    private char[][] tableroMulticolumna = new char[12][12];
    private Jugador[] jugadores = new Jugador[2];
    private StringBuilder historialParticipantes = new StringBuilder();
    
    public Gato(){
        vaciarTablero();
    }
    
    /*
     * Pide el nombre del jugador y se asegura que no se introdusca un nombre vacio
     * Regresa un String con el nombre del jugador
     * @params
     * romperCiclo - variable para romper el ciclo
     * nombre - nombre del jugador
     */
    public String ingresarNombre () {
        boolean romperCiclo = false; 
        String nombre;
        do {
            System.out.print("Ingrese su nombre: ");
            nombre = Keyboard.readString();
            
            if (nombre == null || nombre.trim().isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacio.");
            } else {
                romperCiclo = true;
            }
        } while (!romperCiclo);

        return nombre;
        
    }


    /*
     * Metodo para crear a los jugadores
     */
    public void crearJugadores() {
        System.out.println("Ingrese el nombre de los jugadores");
        System.out.println("Jugador 1");
        jugadores[0] = new Jugador(ingresarNombre());
        jugadores[0].aumentarAdversarios();
        System.out.println("Jugador 2");
        jugadores[1] = new Jugador(ingresarNombre());
        jugadores[1].aumentarAdversarios();
    }

    /*
     * Metodo que asigna las fichas a los jugadores
     */
    public void asignarFicha() {
        boolean romperCiclo = false;

        do{
            try {
                System.out.println("--------------------------------------------------------------------");
                System.out.println("El jugador " + nombreJugador(0) + " seleccionara su ficha, el segundo se le asignara automaticamente la sobrante:");
                System.out.println("Ficha (ingresar en mayuscula): 'X' y 'O' (vocal)");
            
                // Se asegura de que se ingrese un solo carácter
                char ficha = Character.toUpperCase(Keyboard.readChar());

                // Dependiendo de la ficha, se le asigna el simbolo correspondiente
                if (ficha == 'X' || ficha == 'O') {
                    jugadores[0].setFicha(ficha);
                    jugadores[1].setFicha((ficha == 'X') ? 'O' : 'X');
                    romperCiclo = true;

                    
                } else {
                    throw new IllegalArgumentException("Ficha inválida. Solo se permite 'X' u 'O'.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }

        }while (!romperCiclo);
            

    }

    /*
     * Metodo para terminar el juego
     */
    public boolean terminarJuego (int posicion) {
        
        System.out.println("---------------------------------------------------");
        do{
            
            System.out.println("¿jugar otra partida? (s/n)");

                char respuesta =Character.toUpperCase(Keyboard.readChar());

                if (respuesta== 'S') {
                    //partida++;
                    reiniciarConGanador(posicion);
                    return true;
                } else if (respuesta == 'N') {
                    System.out.println("Gracias por jugar!");
                    mostrarHistorialParticipantes();
                    return false;
                } else {
                    System.out.println("Respuesta inválida. Por favor, ingrese 'S' o 'N'.");
                }
      
                
        }while(true);
    }

    /*
     * Imprime en pantalla el historial de partidas
     */
    public void mostrarHistorialParticipantes () {
        System.out.println(historialParticipantes);
    }

    public void mostrarDatosJugadores () {
        System.out.println("---------------------------------------------------");
        System.out.println("Jugadores: \n");

        System.out.println(jugadores[0]);
        System.out.println("\n VS \n");

        System.out.println(jugadores[1]);
        System.out.println("---------------------------------------------------");
    }

    public void agregarHistorial (int partida) {
        historialParticipantes.append("\n--------------------------------------------------- \n")
        .append("Enfrentamiento: ").append(partida).append("\n ")
        .append(jugadores[0]).append("\n")
        .append(jugadores[1]).append("\n")
        .append("---------------------------------------------------\n");
        
    }


    public String nombreJugador (int posicion) {
        return jugadores[posicion].getNombre();
    }

    /*
    public void ingresarficha (char ficha, int posicion) {
        jugadores[posicion].setFicha(ficha);
    }
    */

    public char mostrarfichaJugador (int posicion) {
        return jugadores[posicion].getFicha();
    }

    public int mostrarpuntajeJugador (int posicion) {
        return jugadores[posicion].getPuntaje();
    }

    public void aumentarPartidas() {
        jugadores[0].aumentarPartidas();
        jugadores[1].aumentarPartidas();
    }

    /*
     * Reinicia el tablero y el puntaje de los jugadores
     */
    public void reiniciarConGanador(int posicion) {
        // Se limpia el tablero para tenerlo listo para una nueva partida
        vaciarTablero();
        jugadores[posicion].resetearPuntaje();
        jugadores[posicion].aumentarAdversarios();
        //Se mueve el jugador ganador a la primera posicion
        jugadores[0] = jugadores[posicion];
        System.out.println("---------------------------------------------------");
        System.out.println("Nuevo rival");
        //Se crea un nuevo rival
        jugadores[1] = new Jugador(ingresarNombre());
        jugadores[1].aumentarAdversarios();
        asignarFicha();

    }

    /*
     * Metodo para ingresar un numero y que se asegure que sea un numero del 1 al 9
     * Si la casilla esta ocupada se le informa al jugador
     * Regresa un booleano: True si se logro ingresar un numero del 1 al 9
     * False si no
     * @params
     * romperCiclo - variable para romper el ciclo
     * numero - numero ingresado
     */
    public boolean ingresarCasilla (char ficha) {
        int numero = 0;
        boolean romperCiclo = false;
        do {
            
            try {
                System.out.print("Ingrese un numero del 1 al 9: ");
                numero = Keyboard.readInt();
                if (numero < 1 || numero > 9) {
                System.out.println("Error: El numero debe estar entre 1 y 9.");
                }
                else if (numero >=1 && numero <= 9) {
                    romperCiclo = true;
                }
                
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un numero.");
            }            
            
        } while (!romperCiclo);

        if(tablero[numero - 1] == ' ') {
            tablero[numero - 1] = ficha;
            actualizarTableroMulticolumna(numero, ficha);
            return true;
            } else {
                System.out.println("Error: La casilla ya esta ocupada.");
                return false;
            }

        
    }



    /*
     * Se asegura que el jugador actual haya ganado
     */
    public boolean calcularVictoria(char p) {
        int[][] victorias = { {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6} };
        
        for(int[] v: victorias) {
            if (tablero[v[0]] == p && tablero[v[1]] == p && tablero[v[2]] == p) {
                return true;    
            }
        }
        return false;
    }

    /*
     * Metodo que hace que el jugador actual obtenga la victoria y el otro la derrota
     */
    public void ganador (int posicion) {
        jugadores[posicion].registrarVictoria();
        jugadores[(posicion == 1) ? 0 : 1].registrarDerrota();
    
    }

    public void empate () {
        jugadores[0].registrarEmpate();
        jugadores[1].registrarEmpate();
    }

    /*
     * Regresa un String con el tablero
     * @params
     * juego - tablero
     */

     public void vaciarTablero() {
        // llenar el tablero con espacios en blanco
        for (int i = 0; i < tableroMulticolumna.length; i++) {
            for (int j = 0; j < tableroMulticolumna[i].length; j++) {
                tableroMulticolumna[i][j] = ' ';
            }
        }

        /* Hacer las divisiones del tablero
         * (3,j) y (7,j) son las lineas verticales
         * (i,3) y (i,7) son las lineas horizontales
         */
        for(int i=0; i<tableroMulticolumna.length; i++) {
            for(int j=0; j<tableroMulticolumna[i].length; j++) {
                
                if (i == 3 || i == 7) {
                    tableroMulticolumna[i][j] = '-';
                }
                if (j == 3 || j == 7) {
                    tableroMulticolumna[i][j] = '|';
                }
            }
        }

        for(int i=0; i<tablero.length; i++) {
            tablero[i] = ' ';
        }

     }

    /*
     * regresa un string con el tablero multicolumna diseñado.
     */
     public void mostrarTablero() {

        for (int i=0; i<tableroMulticolumna.length; i++) {
            for (int j=0; j<tableroMulticolumna[i].length; j++) {
                System.out.printf("%-2s",tableroMulticolumna[i][j]);
            }
            System.out.println();
        }

        
     }

    /*
     * Segun la casilla que se ingrese, se actualiza una zona
     * determinada de el tablero multicolumna
     */
    public void actualizarTableroMulticolumna(int casilla, char simbolo) {
    
        switch (casilla) {
            case 1: dibujarSimbolo(1,1, simbolo); break;
            case 2: dibujarSimbolo(1,5, simbolo); break;
            case 3: dibujarSimbolo(1,9, simbolo); break;
            case 4: dibujarSimbolo(5,1, simbolo); break;
            case 5: dibujarSimbolo(5,5, simbolo); break;
            case 6: dibujarSimbolo(5,9, simbolo); break;
            case 7: dibujarSimbolo(9,1, simbolo); break;
            case 8: dibujarSimbolo(9,5, simbolo); break;
            case 9: dibujarSimbolo(9,9, simbolo); break;
            default: break;
        }
        
  
    }

    /*
     * Dibuja el simbolo ('X' o 'O') en el tablero multicolumna
     */
    public void dibujarSimbolo(int filaCentro, int columnaCentro, char simbolo) {

        final char[][] MATRIZ_X = { {'X', ' ', 'X'}, {' ', 'X', ' '}, {'X', ' ', 'X'}};
        final char[][] MATRIZ_O = { {'O', 'O', 'O'}, {'O', ' ', 'O'}, {'O', 'O', 'O'}};

        char[][] MATRIZ_PLANTILLA = (simbolo == 'X') ? MATRIZ_X : MATRIZ_O;

        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                tableroMulticolumna[filaCentro+i][columnaCentro+j] = MATRIZ_PLANTILLA[i+1][j+1];
            }
        }
        
    }


}