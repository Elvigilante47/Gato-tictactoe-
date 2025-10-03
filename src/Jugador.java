
/*
 * Clase jugador que contiene los atributos del mismo.
 * @params
 * nombre - nombre del jugador
 * adversarios - numero de adversarios enfrentados
 * totalPartidas - total de partidas jugadas
 * ganadas - total de partidas ganadas
 * perdidas - total de partidas perdidas 
 * empatados - total de partidas empatadas
 * puntaje - puntaje total obtenido por partidas
 */
 class Jugador {
    private String nombre;
    private int adversarios = 0;
    private int totalPartidas = 0;
    private int ganadas = 0;
    private int perdidas = 0;
    private int empatados = 0;
    private int puntaje = 0;
    private char ficha = ' ';

    /*
     * Constructor jugador
     * @params
     * nombre - Captura el nombre de entrada  
     */
   public Jugador(String nombre) {
    this.nombre = nombre;
    }


    /*
     * Metodo que regresa el nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

     /*
     * Aumenta el numero de contrincantes en 1
     */
    public void aumentarAdversarios() {
        adversarios++;
    }

    /*
     * Aumenta el total de partidas en 1
     */
    public void aumentarPartidas() {
        totalPartidas++;
    }

    /*
     * Aumenta el total de partidas ganadas en 1
     * Aumenta el puntaje en 2
     */
    public void registrarVictoria() {
        ganadas = ganadas + 1;
        puntaje = puntaje + 2;
    }

    /*
     * Aumenta el total de partidas perdidas en 1
     * Disminuye el puntaje en 1
     */
    public void registrarDerrota() {
        perdidas = perdidas + 1;
        puntaje = puntaje - 1;
    }

    /*
     * Aumenta el total de partidas empatadas en 1
     */
    public void registrarEmpate() {
        empatados = empatados + 1;
    }

    public void resetearPuntaje() {
        puntaje = 0;
    }
    

    public char getFicha() {
            return ficha;
        }

    public void setFicha(char nuevaFicha) {
        ficha = nuevaFicha;
    }

    // Regresa el puntaje actual del jugador
    public int getPuntaje() {
        return puntaje;
    }

    /*
     * Regresa un String con todos los datos del jugador
     */
    @Override
    public String toString() {
        //String datosJugador = "nombre: " + nombre + "\n adversarios enfrentados:" +  adversarios + "\n total de partidas: " + totalPartidas + "\n Partidas ganadas: " + ganadas + "\n Partidas perdidas: " + perdidas + "\n Partidas empatadas: " + empatados + "\n puntaje total: " + puntaje + "\n ficha: " + ficha;

        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\n")
        .append("Ficha: ").append(ficha).append("\n")
        .append("Adversarios: ").append(adversarios).append("\n")
        .append("Total Partidas: ").append(totalPartidas).append("\n")
        .append("Victorias: ").append(ganadas).append("\n")
        .append("Derrotas: ").append(perdidas).append("\n")
        .append("Empates: ").append(empatados).append("\n")
        .append("Puntaje: ").append(puntaje).append("\n");
        return sb.toString();
        

    }

}

