package com.example.dernierespoirsae.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstPresentRayonTest {

    @Test
    void estPresentDansRayonPixel() {
        assertTrue(EstPresentRayon.estPresentDansRayonPixel(15,30,30,30,30),"acteur 1 au même coord que acteur 2 avec rayon 15");
        assertFalse(EstPresentRayon.estPresentDansRayonPixel(15,30,30,14,14),"acteur 1 à 16px de acteur 2 avec rayon 15");
        assertTrue(EstPresentRayon.estPresentDansRayonPixel(15,30,30,28,32),"acteur 1 à 2 pixel de acteur 2 avec rayon 15");

        assertTrue(EstPresentRayon.estPresentDansRayonPixel(0,30,30,30,30),"acteur 1 au même coord que acteur 2 avec rayon 0");
        assertFalse(EstPresentRayon.estPresentDansRayonPixel(0,30,30,30,31),"acteur 1 à 1px de acteur 2 avec rayon 0");
    }
}