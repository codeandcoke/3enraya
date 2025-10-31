void main() {
    final String PIEZA_USUARIO = "X";
    final String PIEZA_ORDENADOR = "0";
    final String CASILLA_VACIA = " ";

    // Definir un tablero vacio
    String[][] tablero = new String[3][3];
    // Inicializar todas las casilla del tablero al caracter espacio
    for (int i = 0; i < tablero.length; i++) {
        Arrays.fill(tablero[i], CASILLA_VACIA);
    }

    boolean ganaUsuario = false, ganaOrdenador = false, tableroLleno = false;
    // Repetir/Jugar ->
    do {
        // Turno del jugdor
        Scanner teclado = new Scanner(System.in);
        IO.println("Introduce las coordenadas donde quieres mover, primero la fila y luego la columna");

        boolean casillaOcupada = true;
        int fila, columna;
        do {
            IO.print("Fila: ");
            fila = Integer.parseInt(teclado.nextLine()) - 1;
            IO.print("Columna: ");
            columna = Integer.parseInt(teclado.nextLine()) - 1;
            casillaOcupada = !tablero[fila][columna].equals(CASILLA_VACIA);
            if (casillaOcupada) {
                IO.println("La casilla está ocupada. Prueba con otra");
            }
        } while (casillaOcupada);

        // Se coloca la pieza del usuario en el tablero
        tablero[fila][columna] = PIEZA_USUARIO;
        mostrarTablero(tablero);

        // Comprobar si el usuario ha hecho linea. Si hace, se termina la partida
        // Lineas horizontales
        ganaUsuario = comprobarLineas(tablero, PIEZA_USUARIO);
        if (ganaUsuario) {
            continue;
        }

        IO.println("Turno del ordenador");
        // Turno del ordenador
        // TODO Comprobar si la casilla ya está ocupada
        // TODO Añadir cierta inteligencia al ordenador para que coloque la casilla con intención de ganar
        Random generador = new Random();
        fila = generador.nextInt(3);
        columna = generador.nextInt(3);

        // Se coloca la pieza del ordenador en el tablero
        tablero[fila][columna] = PIEZA_ORDENADOR;
        mostrarTablero(tablero);

        // Comprobar si el ordenador ha hecho linea. Si hace, se termina la partida
        // Lineas horizontales
        ganaOrdenador = comprobarLineas(tablero, PIEZA_ORDENADOR);

        tableroLleno = true;
        // TODO Rescribir con un while para comprobar mejor cuando detener el bucle
        for (int i = 0; i < tablero.length && tableroLleno; i++) {
            for (int j = 0; j < tablero[i].length && tableroLleno; j++) {
                if (!tablero[i][j].equals(CASILLA_VACIA)) {
                    tableroLleno = false;
                }
            }
        }
    } while (!ganaUsuario && !ganaOrdenador && !tableroLleno);

    // TODO Mejorar
    if (ganaUsuario) {
        IO.println("El usuario ha ganado");
    }

    if (ganaOrdenador) {
        IO.println("El ordenador ha ganado");
    }

    if (tableroLleno) {
        IO.println("No ha ganado nadie. Se ha llenado el tablero");
    }
}

boolean comprobarLineas(String[][] tablero, String pieza) {
    // Lineas horizontales
    for (int i = 0; i < tablero.length; i++) {
        if (tablero[i][0].equals(pieza) && (tablero[i][1].equals(pieza) &&
                (tablero[i][2].equals(pieza)))) {
            return true;
        }
    }

    // Lineas verticales
    for (int i = 0; i < tablero.length; i++) {
        if (tablero[0][i].equals(pieza) && (tablero[1][i].equals(pieza) &&
                (tablero[2][i].equals(pieza)))) {
            return true;
        }
    }

    // TODO Lineas diagonales

    return false;
}

void mostrarTablero(String[][] tablero) {
    // TODO Mejorar
    for (int i = 0; i < tablero.length; i++) {
        IO.println(Arrays.toString(tablero[i]));
    }
}
