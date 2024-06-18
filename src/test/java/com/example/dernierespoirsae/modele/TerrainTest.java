package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.controleur.LoadJSON;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TerrainTest {
    private Terrain terrain;
    @BeforeEach
    void setUp() {
        LoadJSON loadJSON = new LoadJSON("src/main/resources/com/example/dernierespoirsae/terrain0.json");

        this.terrain = new Terrain();
        this.terrain.setTerrain(loadJSON.getMapColision()); //récupère le terrain avec les collisions

    }

    @Test
    void estObstacle() {
        assertFalse(this.terrain.estObstacle(1),"tuile 1 : tuile sans obstacle");
        assertTrue(this.terrain.estObstacle(99),"valeur 99 : tuile avec un obstacle");
    }

    @Test
    void estDestructible() {
        assertFalse(this.terrain.estDestructible(99),"valeur 99 : tuile avec une cloture (un mur) donc indestructible");
        assertTrue(this.terrain.estDestructible(9898),"valeur : 9898 : tuile avec une caisse (en bas à droite de la map), donc destructible");
        assertTrue(this.terrain.estDestructible(80),"valeur 80 : tuile avec un arbre, donc destructible");
        assertTrue(this.terrain.estDestructible(473),"valeur 473 : tuile avec un buisson, donc destructible");
    }
}