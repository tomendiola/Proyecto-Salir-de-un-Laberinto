
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;

import java.util.Stack;
import javax.swing.BorderFactory;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

public class Laberinto extends JFrame {

    private static final long serialVersionUID = 1L;
    private final int filas = 10;
    private final int columnas = 10;
    private final JPanel panelLaberinto;
    private final int[][] laberinto = new int[][]{
        {1, 1, 1, 1, 1, 1, 1, 3, 1, 1}, //0.0-9
        {1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 1, 0, 0, 1, 0, 1},
        {1, 1, 1, 1, 0, 0, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
        {1, 1, 0, 1, 1, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 1, 0, 5},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    private JLabel[][] etiquetas;
    private Point posicionActual;
    private int x, y;

    public Laberinto() {
        super("Laberinto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el título del laberinto
        JLabel titulo = new JLabel("Laberinto", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true);
        titulo.setBackground(Color.BLUE);

        // Crear el panel del laberinto
        panelLaberinto = new JPanel(new GridLayout(filas, columnas));
        panelLaberinto.setBackground(Color.BLACK);
        panelLaberinto.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // Dibujar el laberinto en el panel
        dibujarLaberinto();

        // Agregar el panel y el título al JFrame
        add(titulo, "North");
        add(panelLaberinto, "Center");
        pack();
        setLocationRelativeTo(null);
    }

    public void encontrarInicio() {
        boolean encontrado = false;
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[0].length; j++) {
                if (laberinto[i][j] == 3) {
                    x = i;
                    y = j;
                    System.out.println(x + " " + y);
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {
                break;
            }
        }
    }

 public void recorrerLaberinto() {
        encontrarInicio();

        // Buscamos el inicio del recorrido
        int filaActual = x;
        int columnaActual = y;
        int actualIndex = filaActual * columnas + columnaActual;
        JLabel actual = (JLabel) panelLaberinto.getComponent(actualIndex);
        actual.setBackground(Color.RED);
        actual.setForeground(Color.RED);

        // Creamos una pila para el backtracking y un conjunto para los nodos visitados
        Stack<Integer> pila = new Stack<>();
        boolean[][] visitados = new boolean[filas][columnas];
        boolean llegoMeta = false; // Variable para controlar si se llegó a la meta

        // Creamos un ciclo que recorre los ceros del laberinto hasta llega a la meta
        while (!llegoMeta) {

            if (laberinto[filaActual][columnaActual] == 5) {
                return;
            }

            try {
                // Agregamos una pausa de 500 milisegundos para que se vea el recorrido
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Cambiamos el fondo y fuente de la casilla actual a blanco
            actual.setBackground(Color.WHITE);
            actual.setForeground(Color.WHITE);

            // Buscamos la siguiente casilla que sea cero
            int vecinoIndex = getVecino(actualIndex, "arriba");
            if (vecinoIndex != -1 && !visitados[vecinoIndex / columnas][vecinoIndex % columnas]) {
                // Si encontramos un vecino no visitado, lo agregamos a la pila
                pila.push(actualIndex);
                visitados[vecinoIndex / columnas][vecinoIndex % columnas] = true;
                actual = (JLabel) panelLaberinto.getComponent(vecinoIndex);
                actual.setBackground(Color.RED);
                actual.setForeground(Color.RED);
                actualIndex = vecinoIndex;
                filaActual = actualIndex / columnas;
                columnaActual = actualIndex % columnas;

                // Si llegamos a la meta, terminamos el recorrido
//                if (filaActual == 8 && columnaActual == 8) {
//                    llegoMeta = true;
//
//                }
            } else {
                // Si no encontramos un vecino hacia arriba, buscamos hacia abajo
                vecinoIndex = getVecino(actualIndex, "abajo");
                if (vecinoIndex != -1 && !visitados[vecinoIndex / columnas][vecinoIndex % columnas]) {
                    // Si encontramos un vecino no visitado, lo agregamos a la pila
                    pila.push(actualIndex);
                    visitados[vecinoIndex / columnas][vecinoIndex % columnas] = true;
                    actual = (JLabel) panelLaberinto.getComponent(vecinoIndex);
                    actual.setBackground(Color.RED);
                    actual.setForeground(Color.RED);
                    actualIndex = vecinoIndex;
                    filaActual = actualIndex / columnas;
                    columnaActual = actualIndex % columnas;

                    // Si llegamos a la meta, terminamos el recorrido
//                    if (filaActual == 8 && columnaActual == 8) {
//                        llegoMeta = true;
//
//                    }
                } else {
                    // Si no encontramos un vecino hacia abajo, buscamos hacia la izquierda
                    vecinoIndex = getVecino(actualIndex, "izquierda");
                    if (vecinoIndex != -1 && !visitados[vecinoIndex / columnas][vecinoIndex % columnas]) {
// Si encontramos un vecino no visitado, lo agregamos a la pila
                        pila.push(actualIndex);
                        visitados[vecinoIndex / columnas][vecinoIndex % columnas] = true;
                        actual = (JLabel) panelLaberinto.getComponent(vecinoIndex);
                        actual.setBackground(Color.RED);
                        actual.setForeground(Color.RED);
                        actualIndex = vecinoIndex;
                        filaActual = actualIndex / columnas;
                        columnaActual = actualIndex % columnas;
// Si llegamos a la meta, terminamos el recorrido
//                        if (filaActual == 8 && columnaActual == 8) {
//                            llegoMeta = true;
//
//                        }
                    } else {
// Si no encontramos un vecino hacia la izquierda, buscamos hacia la derecha
                        vecinoIndex = getVecino(actualIndex, "derecha");
                        if (vecinoIndex != -1 && !visitados[vecinoIndex / columnas][vecinoIndex % columnas]) {
// Si encontramos un vecino no visitado, lo agregamos a la pila
                            pila.push(actualIndex);
                            visitados[vecinoIndex / columnas][vecinoIndex % columnas] = true;
                            actual = (JLabel) panelLaberinto.getComponent(vecinoIndex);
                            actual.setBackground(Color.RED);
                            actual.setForeground(Color.RED);
                            actualIndex = vecinoIndex;
                            filaActual = actualIndex / columnas;
                            columnaActual = actualIndex % columnas;
                            // Si llegamos a la meta, terminamos el recorrido
//                            if (filaActual == 8 && columnaActual == 8) {
//                                llegoMeta = true;
//
//                            }
                        } else {
                            // Si no encontramos un vecino hacia la derecha, hacemos backtracking
                            actualIndex = pila.pop();
                            actual = (JLabel) panelLaberinto.getComponent(actualIndex);

                            actual.setBackground(Color.YELLOW);
                            actual.setForeground(Color.YELLOW);

                        }
                    }
                }
            }

        }
    }

    private void dibujarLaberinto() {
        etiquetas = new JLabel[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                JLabel etiqueta = new JLabel();
                etiqueta.setOpaque(true);
                etiqueta.setHorizontalAlignment(JLabel.CENTER);
                etiqueta.setVerticalAlignment(JLabel.CENTER);
                etiqueta.setFont(new Font("Arial", Font.BOLD, 20));
                etiqueta.setPreferredSize(new java.awt.Dimension(40, 40));
                etiqueta.setBorder(BorderFactory.createLineBorder(Color.WHITE));

                switch (laberinto[i][j]) {
                    case 0:
                        etiqueta.setBackground(Color.WHITE);
                        break;
                    case 1:
                        etiqueta.setBackground(Color.BLACK);
                        break;
                    case 5:
                        etiqueta.setBackground(Color.GREEN);
                        posicionActual = new Point(i, j);
                        break;
                    case 3:
                        etiqueta.setBackground(Color.RED);
                        break;
                    default:
                        break;
                }

                etiquetas[i][j] = etiqueta;
                panelLaberinto.add(etiqueta);
            }
        }
    }

    private int getVecino(int actualIndex, String direccion) {
        int filaActual = actualIndex / columnas;
        int columnaActual = actualIndex % columnas;

        switch (direccion) {
            case "arriba":
                if (filaActual > 0 && (laberinto[filaActual - 1][columnaActual] == 0 || laberinto[filaActual - 1][columnaActual] == 5)) {
                    return (filaActual - 1) * columnas + columnaActual;
                }
                break;
            case "abajo":
                if (filaActual < filas - 1 && (laberinto[filaActual + 1][columnaActual] == 0 || laberinto[filaActual + 1][columnaActual] == 5)) {
                    return (filaActual + 1) * columnas + columnaActual;
                }
                break;
            case "izquierda":
                if (columnaActual > 0 && laberinto[filaActual][columnaActual - 1] == 0 || laberinto[filaActual][columnaActual - 1] == 5) {
                    return filaActual * columnas + columnaActual - 1;
                }
                break;
            case "derecha":
                if (columnaActual < columnas - 1 && (laberinto[filaActual][columnaActual + 1] == 0 || laberinto[filaActual][columnaActual + 1] == 5)) {
                    return filaActual * columnas + columnaActual + 1;
                }
                break;
            default:
                break;
        }

// Si llegamos aquí es porque no se encontró ningún vecino en esa dirección
        return -1;
    }
}
