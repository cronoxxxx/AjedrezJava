package Piezas;

import Clases.*;

public class Torre extends Pieza {
    public Torre(int y, int x, String color) { //Establecer valores especificos
        super.setColor(color);
        super.setPosX(x);
        super.setPosY(y);
    }

    public int[][] posiblesMovimientos() {

        int[][] movimientos = new int[14][2]; //Matriz de coordenadas

        for (int i = 0; i < movimientos.length; i++) { //Almacenar movimientos, con -1 indico que aun no se ha determinado el movimiento
            movimientos[i][0] = -1;
            movimientos[i][1] = -1;
        }

        int x = super.getPosX(), y = super.getPosY(), cont = 0; //Cont hace un seguimiento de los movimientos encontrados
        //Obtener las posiciones actuales de la torre


        /*Se exploran las posiciones hacia arriba desde la posición actual de la torre hasta
        el límite superior del tablero (Tablero.minColumnas).
        Las coordenadas se almacenan en la matriz de movimientos.*/
        y--;
        while (y >= Tablero.minColumnas) {
            movimientos[cont][0] = super.getPosX();
            movimientos[cont][1] = y;
            y--;
            cont++;
        }

        //posiciones hacia la derecha desde la posición actual de la torre hasta el límite derecho del tablero
        y = super.getPosY();
        x++;
        while (x <= Tablero.maxColumnas) {
            movimientos[cont][0] = x;
            movimientos[cont][1] = super.getPosY();
            x++;
            cont++;
        }

        //posiciones hacia abajo desde la posición actual de la torre hasta el límite inferior del tablero
        x = super.getPosX();
        y++;
        while (y <= Tablero.maxColumnas) {
            movimientos[cont][0] = super.getPosX();
            movimientos[cont][1] = y;
            y++;
            cont++;
        }

        //posiciones hacia la izquierda desde la posición actual de la torre hasta el límite izquierdo del tablero
        y = super.getPosY();
        x--;
        while (x >= Tablero.minColumnas) {
            movimientos[cont][0] = x;
            movimientos[cont][1] = super.getPosY();
            x--;
            cont++;
        }

        return movimientos;
    }

    public String toString() {
        if (super.getColor().equals("B")) {
            return "♖";
        } else {
            return "♜";
        }
    }
}
