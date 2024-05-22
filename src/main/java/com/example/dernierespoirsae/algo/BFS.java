package com.example.dernierespoirsae.algo;

import java.util.*;

public class BFS {

    private static final int[] dLigne = {-1, 1, 0, 0};
    private static final int[] dColonne = {0, 0, -1, 1};

    public static List<Point> bfs2D(int[][] grille, Point src, Point dest) {
        int ligne = grille.length;
        int colonne = grille[0].length;
        boolean[][] visite = new boolean[ligne][colonne];

        // Création de la file pour BFS
        LinkedList<Point> fifo = new LinkedList<>();
        fifo.add(src);
        visite[src.getX()][src.getY()] = true;

        // Pour garder la trace des parents pour reconstruire le chemin
        Map<Point, Point> parentMap = new HashMap<>();
        parentMap.put(src, null);

        // Boucle principale de BFS
        while (!fifo.isEmpty()) {
            Point actuel = fifo.poll();
            int x = actuel.getX();
            int y = actuel.getY();

            // Vérifier si nous avons atteint la position de destination
            if (actuel.equals(dest)) {
                return constructChemin(parentMap, dest);
            }

            // Exploration des voisins
            for (int i = 0; i < 4; i++) {
                int nx = x + dLigne[i];
                int ny = y + dColonne[i];

                // Vérifier si le voisin est dans les limites du tableau et non visité
                if (nx >= 0 && nx < ligne && ny >= 0 && ny < colonne && !visite[nx][ny] && grille[nx][ny] == 232) {
                    Point voisin = new Point(nx, ny);
                    fifo.add(voisin);
                    visite[nx][ny] = true;
                    parentMap.put(voisin, actuel);
                }
            }
        }

        // Retourner une liste vide si aucun chemin n'est trouvé
        return new ArrayList<>();
    }

    // Méthode pour reconstruire le chemin à partir de la destination en utilisant le parentMap
    private static List<Point> constructChemin(Map<Point, Point> parentMap, Point dest) {
        LinkedList<Point> chemin = new LinkedList<>();
        for (Point actuel = dest; actuel != null; actuel = parentMap.get(actuel)) {
            chemin.add(0, actuel);
        }
        return chemin;
    }

    private static int[][] convertListTo2DArray(ArrayList<Integer> liste, int ligne, int colonne) {
        if (liste.size() != ligne * colonne) {
            throw new IllegalArgumentException("La taille de la liste ne correspond pas aux dimensions du tableau 2D.");
        }

        int[][] tab = new int[ligne][colonne];
        int index = 0;

        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                tab[i][j] = liste.get(index++);
            }
        }

        return tab;
    }

    public static int[] prochainMouvement(ArrayList<Integer> terrain, int lignes, int colonnes, int[] src, int[] dest){
        int[][] grille = convertListTo2DArray(terrain, colonnes, lignes);
        List<Point> chemin = bfs2D(grille, new Point(src[0],src[1]), new Point(dest[0], dest[1]));
        if (chemin.size() > 1)
            return new int[] {chemin.get(chemin.size()-2).getX(), chemin.get(chemin.size()-2).getY()};
        return null;
    }
}