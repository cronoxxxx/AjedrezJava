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


    public int[][] posiblesMovimientos(){

        int movimientos[][] = new int[4][2];

        for(int i=0; i<movimientos.length; i++)
        {
            movimientos[i][0]=-1;
            movimientos[i][1]=-1;
        }

        if( super.getColor().equals("N") ){
            if( super.getPosY()==1 ){ //Si esta en la fila 1, se mueve de uno o de 2 ;) (Problema resuelto)
                movimientos[1][0]=(super.getPosX());
                movimientos[1][1]=(super.getPosY()+2);


                movimientos[0][0]=(super.getPosX());
                movimientos[0][1]=(super.getPosY()+1);
            }
            if(  super.getPosY()<Tablero.maxColumnas ){


                //Posiciones para ataque
                movimientos[2][0]=(super.getPosX()-1);
                movimientos[2][1]=(super.getPosY()+1);

                movimientos[3][0]=(super.getPosX()+1);
                movimientos[3][1]=(super.getPosY()+1);
            }

        }
        else{
            if( super.getPosY()==6 ){
                movimientos[1][0]=(super.getPosX());
                movimientos[1][1]=(super.getPosY()-2);


                movimientos[0][0]=(super.getPosX());
                movimientos[0][1]=(super.getPosY()-1);
            }
            if( super.getPosY()>Tablero.minColumnas ){


                //Posiciones para ataque
                movimientos[2][0]=(super.getPosX()-1);
                movimientos[2][1]=(super.getPosY()-1);

                movimientos[3][0]=(super.getPosX()+1);
                movimientos[3][1]=(super.getPosY()-1);
            }
        }

        return movimientos;
    }

    public String toString(){
        if(super.getColor().equals("B")){
            return "♙";
        }
        else{
            return "♟";
        }
    }

}
