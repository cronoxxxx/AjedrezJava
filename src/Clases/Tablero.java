package Clases;

import Piezas.*;


public class Tablero {

    public static final int maxColumnas = 7;
    public static final int minColumnas = 0;
    static Pieza[][] casillas = new Pieza[8][8];

    public Tablero(){

        for(int i=0; i<=maxColumnas; i++){
            //Torres
            if(i==0 || i==7){
                casillas[0][i] = new Torre(0,i,"N");
                casillas[7][i] = new Torre(7,i,"B");
            }
            //Caballos
            if(i==1 || i==6){
                casillas[0][i] = new Caballo(0,i,"N");
                casillas[7][i] = new Caballo(7,i,"B");
            }
            //Alfiles
            if(i==2 || i==5){
                casillas[0][i] = new Alfil(0,i,"N");
                casillas[7][i] = new Alfil(7,i,"B");
            }
            //Reina/Dama
            if(i==3) {
                casillas[0][i] = new Reina(0,i,"N");
                casillas[7][i] = new Reina(7,i,"B");
            }
            //Rey
            if(i==4){
                casillas[0][i] = new Rey(0,i,"N");
                casillas[7][i] = new Rey(7,i,"B");
            }
            //Peones Blancos
            casillas[6][i] = new Peon(6,i,"B");

            //Peones Negros
            casillas[1][i] = new Peon(1,i,"N");
        }
    }

    public String toString() {
        String cadena = "  ADRIAN SAAVEDRA\n";
        cadena += "   A   B   C  D  E   F   G   H\n";

        for (int i = casillas.length - 1; i >= 0; i--) {
            cadena += (i+1) + "|";

            for (int j = 0; j < casillas[i].length; j++) {
                if (casillas[i][j] == null) {
                    cadena += "-   ";
                } else {
                    cadena += " " + casillas[i][j] + " ";
                }
            }
            cadena += "|\n";
        }

        cadena += " +----------------\n";
        cadena += "  1DAM\n";

        return cadena;
    }

    public int[][]movsPosibles(Pieza p){
        int[][] movs = p.posiblesMovimientos(); //movimientos posibles de las piezas

        if(p instanceof Caballo || p instanceof Rey){ //si es caballo o rey
            for(int i=0; i<movs.length; i++){
                if(movs[i][0] != -1 && movs[i][1] != -1){ //SI es distinto de -1, es decir, que hay movimientos posibles
                    if( llenaYMismoColor(p, movs[i][0], movs[i][1]) ){ //Se verifica que hay una pieza en la posicion correspondiente, y si hay del mismo color, se desvalida con -1
                        movs[i][0]=-1;
                        movs[i][1]=-1;
                    }
                }
            }
        }

        if(p instanceof Peon){
            for(int i=0; i<movs.length; i++){
                if(movs[i][0] != -1 && movs[i][0] != -1){
                    if( llenaYMismoColor(p, movs[i][0], movs[i][1]) || peonNoAtaque(p, movs[i][0], movs[i][1]) ){
                        movs[i][0]=-1;
                        movs[i][1]=-1;
                    }
                }
            }
        }


        if (p instanceof Torre || p instanceof Reina){ //Deshabilita movimientos
            for(int i=0; i<movs.length; i++){
                if(movs[i][0] != -1 && movs[i][1] != -1){ //SI es distinto de -1, es decir, que hay movimientos posibles
                    // de 3,5 a 3,3)
                    //Arriba
                    if (movs[i][0] == p.getPosX() && movs[i][1] < p.getPosY()) {
                        if (llenaYMismoColor(p, movs[i][0], movs[i][1])) {
                            for (int j = i; j < movs.length; j++) {
                                if (movs[j][0] == p.getPosX() && movs[j][1] < p.getPosY()) { //Si la casilla en la posición del movimiento está ocupada por una pieza del mismo color que la pieza actual, se deshabilitan todas las casillas desde la posición actual hasta la posición del movimiento, ya que la pieza no puede moverse más allá de una pieza de su mismo color.
                                    movs[j][0] = -1;
                                    movs[j][1] = -1;
                                }
                            }
                        } else if (llenaYDistintoColor(p, movs[i][0], movs[i][1])){
                            // Si hay alguna pieza de distinto color en algunas de esas posiciones

                            for (int j = i + 1; j < movs.length; j++){
                                if (movs[j][0] == p.getPosX() && movs[j][1] < p.getPosY()) { //Si la casilla en la posición del movimiento está ocupada por una pieza de distinto color, se deshabilitan todas las casillas desde la posición siguiente al movimiento hasta el final del arreglo de movimientos. Esto se hace para evitar que la pieza contraria pueda pasar por encima de la pieza actual hasta llegar a la posición del movimiento.
                                    movs[j][0] = -1;
                                    movs[j][1] = -1;

                                }
                            }
                        }
                    }

                    //Derecha
                    if(movs[i][0]>p.getPosX() && movs[i][1]==p.getPosY()){
                        if(llenaYMismoColor(p, movs[i][0], movs[i][1])){
                            for(int j=i; j<movs.length; j++){
                                if(movs[j][0]>p.getPosX() && movs[j][1]==p.getPosY()){
                                    movs[j][0]=-1;
                                    movs[j][1]=-1;
                                }
                            }
                        } else if(llenaYDistintoColor(p, movs[i][0], movs[i][1])){
                            for(int j=i+1; j<movs.length; j++){
                                if(movs[j][0]>p.getPosX() && movs[j][1]==p.getPosY()){
                                    movs[j][0]=-1;
                                    movs[j][1]=-1;
                                }
                            }
                        }
                    }

                    //Abajo
                    if(movs[i][0]==p.getPosX() && movs[i][1]>p.getPosY()){
                        if(llenaYMismoColor(p, movs[i][0], movs[i][1])){
                            for(int j=i; j<movs.length; j++){
                                if(movs[j][0]==p.getPosX() && movs[j][1]>p.getPosY()){
                                    movs[j][0]=-1;
                                    movs[j][1]=-1;
                                }
                            }
                        } else if(llenaYDistintoColor(p, movs[i][0], movs[i][1])){
                            for(int j=i+1; j<movs.length; j++){
                                if(movs[j][0]==p.getPosX() && movs[j][1]>p.getPosY()){
                                    movs[j][0]=-1;
                                    movs[j][1]=-1;
                                }
                            }
                        }
                    }

                    //Derecha
                    if(movs[i][0]<p.getPosX() && movs[i][1]==p.getPosY()){
                        if(llenaYMismoColor(p, movs[i][0], movs[i][1])){
                            for(int j=i; j<movs.length; j++){
                                if(movs[j][0]<p.getPosX() && movs[j][1]==p.getPosY()){
                                    movs[j][0]=-1;
                                    movs[j][1]=-1;
                                }
                            }
                        } else if(llenaYDistintoColor(p, movs[i][0], movs[i][1])){
                            for(int j=i+1; j<movs.length; j++){
                                if(movs[j][0]<p.getPosX() && movs[j][1]==p.getPosY()){
                                    movs[j][0]=-1;
                                    movs[j][1]=-1;
                                }
                            }
                        }
                    }
                }
            }
        }

        if(p instanceof Alfil || p instanceof Reina){
            for(int i=0; i<movs.length; i++){
                if(movs[i][0] != -1 ){
                    //arriba izquierda
                    if(movs[i][0]<p.getPosX() && movs[i][1]<p.getPosY()){
                        if(llenaYMismoColor(p, movs[i][0], movs[i][1])){
                            for(int j=i; j<movs.length; j++){
                                if(movs[j][0]<p.getPosX() && movs[j][1]<p.getPosY()){
                                    movs[j][0]=-1;
                                    movs[j][1]=-1;
                                }
                            }
                        } else if(llenaYDistintoColor(p, movs[i][0], movs[i][1])){
                            for(int j=i+1; j<movs.length; j++){
                                if(movs[j][0]<p.getPosX() && movs[j][1]<p.getPosY()){
                                    movs[j][0]=-1;
                                    movs[j][1]=-1;
                                }
                            }
                        }
                    }
                    //arriba derecha
                    if(movs[i][0]>p.getPosX() && movs[i][1]<p.getPosY()){
                        if(llenaYMismoColor(p, movs[i][0], movs[i][1])){
                            for(int j=i; j<movs.length; j++){
                                if(movs[j][0]>p.getPosX() && movs[j][1]<p.getPosY()){
                                    movs[j][0]=-1;
                                    movs[j][1]=-1;
                                }
                            }
                        } else if(llenaYDistintoColor(p, movs[i][0], movs[i][1])){
                            for(int j=i+1; j<movs.length; j++){
                                if(movs[j][0]>p.getPosX() && movs[j][1]<p.getPosY()){
                                    movs[j][0]=-1;
                                    movs[j][1]=-1;
                                }
                            }
                        }
                    }
                    //abajo derecha
                    if(movs[i][0]>p.getPosX() && movs[i][1]>p.getPosY()){
                        if(llenaYMismoColor(p, movs[i][0], movs[i][1])){
                            for(int j=i; j<movs.length; j++){
                                if(movs[j][0]>p.getPosX() && movs[j][1]>p.getPosY()){
                                    movs[j][0]=-1;
                                    movs[j][1]=-1;
                                }
                            }
                        } else if(llenaYDistintoColor(p, movs[i][0], movs[i][1])){
                            for(int j=i+1; j<movs.length; j++){
                                if(movs[j][0]>p.getPosX() && movs[j][1]>p.getPosY()){
                                    movs[j][0]=-1;
                                    movs[j][1]=-1;
                                }
                            }
                        }
                    }
                    //abajo izquierda
                    if(movs[i][0]<p.getPosX() && movs[i][1]>p.getPosY()){
                        if(llenaYMismoColor(p, movs[i][0], movs[i][1])){
                            for(int j=i; j<movs.length; j++){
                                if(movs[j][0]<p.getPosX() && movs[j][1]>p.getPosY()){
                                    movs[j][0]=-1;
                                    movs[j][1]=-1;
                                }
                            }
                        } else if(llenaYDistintoColor(p, movs[i][0], movs[i][1])){
                            for(int j=i+1; j<movs.length; j++){
                                if(movs[j][0]<p.getPosX() && movs[j][1]>p.getPosY()){
                                    movs[j][0]=-1;
                                    movs[j][1]=-1;
                                }
                            }
                        }
                    }
                }
            }
        }

        return movs;
    }

    private boolean llenaYMismoColor(Pieza p, int x, int y) {
        // Asegurarse de que las coordenadas estén dentro de los límites
        if (x >= 0 && x < casillas[0].length && y >= 0 && y < casillas.length) {
            if (casillas[y][x] != null && p.getColor().equals(casillas[y][x].getColor())) {
                return true; //Validar que hay una posicion del mismo color
            }
        }
        return false;
    }


    private boolean llenaYDistintoColor(Pieza p, int x, int y) {
        // Asegurarse de que las coordenadas estén dentro de los límites
        if (x >= 0 && x < casillas[0].length && y >= 0 && y < casillas.length) {
            if (casillas[y][x] != null && !p.getColor().equals(casillas[y][x].getColor())) {
                return true;
            }
        }
        return false;
    }

    private boolean peonNoAtaque(Pieza p, int x, int y) {

        // Asegurarse de que las coordenadas estén dentro de los límites
        if (x >= 0 && x < casillas[0].length && y >= 0 && y < casillas.length) {
            if (casillas[y][x] == null && x != p.getPosX() ) { //Si casilla esta nula y se encuentra en la misma columna (A)
                return true;
            }
        }
        return false;
    }


    /*
    * En el código que has proporcionado, la matriz movs se utiliza para almacenar las coordenadas de los posibles movimientos de una pieza en el tablero de ajedrez. La convención utilizada para representar estas coordenadas es almacenarlas en pares (x, y). En el código, estas coordenadas se almacenan en la forma de movs[i][0] y movs[i][1], donde i es el índice del movimiento.

La elección de tener dos columnas en movs (movs[i][0] y movs[i][1]) se debe a que se está utilizando un enfoque bidimensional para representar las coordenadas de los movimientos posibles. La columna movs[i][0] se utiliza para almacenar la coordenada x (columna en el tablero), y la columna movs[i][1] se utiliza para almacenar la coordenada y (fila en el tablero).

Por ejemplo, si tienes un movimiento posible en la posición (3, 2), este se almacenaría en movs[i][0] = 3 y movs[i][1] = 2. El uso de movs[i][0] y movs[i][1] es simplemente una convención para organizar las coordenadas x e y de los movimientos en un formato fácilmente accesible.

La inicialización de movs[i][0] y movs[i][1] a -1 en la creación de la matriz permite que se detecten movimientos no válidos o sin movimientos para evitar iteraciones innecesarias al procesar la matriz.
    *
    * */

    public static boolean puedeMover(int num, int[][] movs) {
        if(movs[num][0] != -1 && movs[num][1] != -1){
            return true;
        }
        else{
            System.out.println("Numero incorrecto");
            return false;
        }
    }



    public static void moverMatar(int x, int y, Pieza p) {

        //Creo la pieza que se mueve en la casilla que toca
        if(p instanceof Peon){
            casillas[y][x] = new Peon(y ,x ,p.getColor());
        }else{
            if(p instanceof Torre){
                casillas[y][x] = new Torre(y ,x ,p.getColor());
            }else{
                if(p instanceof Caballo){
                    casillas[y][x] = new Caballo(y ,x ,p.getColor());
                }else{
                    if(p instanceof Alfil){
                        casillas[y][x] = new Alfil(y ,x ,p.getColor());
                    }else{
                        if(p instanceof Reina){
                            casillas[y][x] = new Reina(y ,x ,p.getColor());
                        }else{
                            casillas[y][x] = new Rey(y ,x ,p.getColor());
                        }
                    }
                }
            }
        }
        //Borrar pieza
        casillas[p.getPosY()][p.getPosX()] = null;


    }


}
