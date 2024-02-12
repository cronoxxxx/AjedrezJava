package Piezas;

import Clases.Tablero;

public class Peon extends Pieza {

    public Peon(){

    }
    public Peon(int y,int x, String color){
        super.setColor(color);
        super.setPosX(x);
        super.setPosY(y);
    }




    public int[][] posiblesMovimientos() {
        int movimientos[][] = new int[4][2];

        for (int i = 0; i < movimientos.length; i++) {
            movimientos[i][0] = -1;
            movimientos[i][1] = -1;
        }

        int direccionMovimiento = (super.getColor().equals("N")) ? 1 : -1;

        // Movimiento hacia adelante
        if (super.getPosY() + direccionMovimiento >= 0 && super.getPosY() + direccionMovimiento < Tablero.maxColumnas) {
            if (Tablero.casillas[super.getPosY() + direccionMovimiento][super.getPosX()] == null) { //Misma columna, fila hacia arriba
                movimientos[0][0] = super.getPosX();
                movimientos[0][1] = super.getPosY() + direccionMovimiento;

                // Movimiento doble hacia adelante si está en la posición inicial
                if ((super.getPosY() == 1 && super.getColor().equals("N")) || (super.getPosY() == 6 && super.getColor().equals("B"))) {
                    if (Tablero.casillas[super.getPosY() + 2 * direccionMovimiento][super.getPosX()] == null) {
                        movimientos[1][0] = super.getPosX();
                        movimientos[1][1] = super.getPosY() + 2 * direccionMovimiento;
                    }
                }
            }
        }

        // Verificar si hay piezas en las posiciones rectas antes de validar los movimientos diagonales
        if (Tablero.casillas[super.getPosY() + direccionMovimiento][super.getPosX()] != null) {
            // Posiciones para ataque
            movimientos[2][0] = super.getPosX() - 1;
            movimientos[2][1] = super.getPosY() + direccionMovimiento;

            movimientos[3][0] = super.getPosX() + 1;
            movimientos[3][1] = super.getPosY() + direccionMovimiento;
        }

        return movimientos;
    }


        public String toString() {
            if (super.getColor().equals("B")) {
                return "♙";
            } else {
                return "♟";
            }
        }

    }



