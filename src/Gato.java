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
    //private Jugador jugador2;
    private String participantes;

    public Gato(){
        this.tablero = tablero;
        //this.tableroMulticolumna = tableroMulticolumna;
        this.jugadores = jugadores;
        //this.jugador2 = jugador2;
        this.participantes = participantes;
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
            if (nombre.isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacio.");
            } else {
                romperCiclo = true;
            }
        } while (!romperCiclo);

        return nombre;
        
        }

    public void agregarJugador (Jugador jugador, int posicion) {
        jugadores[posicion] = jugador;
    }

    public String nombreJugador (int posicion) {
        return jugadores[posicion].getNombre();
    }

    public void ingresarficha (char ficha, int posicion) {
        jugadores[posicion].setFicha(ficha);
    }

    public char mostrarfichaJugador (int posicion) {
        return jugadores[posicion].getFicha();
    }

    public int mostrarpuntajeJugador (int posicion) {
        return jugadores[posicion].getPuntaje();
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
        
        for(int[] v:victorias) {
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
                System.out.print(tableroMulticolumna[i][j]);
            }
            System.out.println();
        }

        
     }

    /*
     * 
     */
    public void actualizarTableroMulticolumna(int casilla, char simbolo) {
    
        switch (casilla) {
            case 1:
                dibujarSimbolo(1,1, simbolo);
                break;

            case 2:
                dibujarSimbolo(1,5, simbolo);
                break;

            case 3:
                dibujarSimbolo(1,9, simbolo);
                break;

            case 4:
                dibujarSimbolo(5,1, simbolo);
                break;

            case 5:
                dibujarSimbolo(5,5, simbolo);
                break;

            case 6:
                dibujarSimbolo(5,9, simbolo);
                break;

            case 7:
                dibujarSimbolo(9,1, simbolo);
                break;

            case 8:
                dibujarSimbolo(9,5, simbolo);
                break;

            case 9:
                dibujarSimbolo(9,9, simbolo);
                break;
        
            default:
                break;
        
        }
        
        /*
        dibujarSimbolo(1,1, tablero[0]);
        dibujarSimbolo(1,5, tablero[1]);
        dibujarSimbolo(1,9, tablero[2]);
        dibujarSimbolo(5,1, tablero[3]);
        dibujarSimbolo(5,5, tablero[4]);
        dibujarSimbolo(5,9, tablero[5]);
        dibujarSimbolo(9,1, tablero[6]);
        dibujarSimbolo(9,5, tablero[7]);
        dibujarSimbolo(9,9, tablero[8]);
         */
  
    }

    public void dibujarSimbolo(int casilla, int columna, char simbolo) {

        if (simbolo == 'X') {
            for (int i=-1; i<=1; i++) {
                for (int j=-1; j<=1; j++) {
                    if ((i != 0 && j != 0)|| (i == 0 && j == 0)) {
                         tableroMulticolumna[casilla+i][columna+j] = 'X';
                    }
                   
                }
            }
            
        } else if (simbolo == 'O') {
            for (int i=-1; i<=1; i++) {
                for (int j=-1; j<=1; j++) {
                    if ((i == 0 && j == 0)) {
                         continue;
                    } else {
                        tableroMulticolumna[casilla+i][columna+j] = 'O';
                    }
                   
                }
            }
            
        }
        
    }


}