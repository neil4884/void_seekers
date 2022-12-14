package com.game.void_seekers;

import com.game.void_seekers.character.base.PlayableCharacter;
import com.game.void_seekers.character.derived.PlayerTest;
import com.game.void_seekers.item.base.Active;
import com.game.void_seekers.item.base.Passive;
import com.game.void_seekers.item.base.Trinket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestPlayer {
    PlayableCharacter p1;
    Active at1;
    Active at2;
    Passive pt1;
    Trinket tt1;
    Trinket tt2;

    @BeforeEach
    void setUp() {
        p1 = new PlayerTest();
        at1 = new Active("active1", 6) {
            @Override
            public void draw() {}
        };
        at2 = new Active("active2", 6) {
            @Override
            public void draw() {}
        };
        pt1 = new Passive("passive1", "sad it can't be removed") {
            @Override
            public void draw() {}
        };
        tt1 = new Trinket("trinket1", "droppable as it is") {
            @Override
            public void draw() {}
        };
        tt2 = new Trinket("trinket2", "droppable as it is") {
            @Override
            public void draw() {}
        };

    }

    @Test
    void testPlayerConstructor() {
        assertEquals(6, p1.getAbsoluteTotalHealth());
    }

    @Test
    void testAddEffectItem() {
        p1.add(at1);
        assertEquals("active1", p1.getActive().getName());
        assertEquals(0, p1.getNumberOfPassiveItem());
        assertEquals(1, p1.getNumberOfPickedEffectItem());
        p1.add(pt1);
        assertEquals(1, p1.getNumberOfPassiveItem());
        assertEquals(2, p1.getNumberOfPickedEffectItem());
        p1.add(tt1);
        assertEquals("trinket1", p1.getTrinket().getName());
        assertEquals(3, p1.getNumberOfPickedEffectItem());
    }

    @Test
    void testAddSwapActives() {
        p1.add(at1);
        assertEquals(at1, p1.getActive());
        assertEquals(at1, p1.add(at2));
        assertEquals(at2, p1.add(at1));
    }
    @Test
    void testAddSwapTrinkets() {
        p1.add(tt1);
        assertEquals(tt1, p1.getTrinket());
        assertEquals(tt1, p1.add(tt2));
        assertEquals(tt2, p1.add(tt1));
    }
    @Test
    void testDropTrinket() {
        p1.add(tt1);
        assertEquals(tt1, p1.getTrinket());
        assertEquals(tt1, p1.dropTrinket());
        assertNull(p1.getTrinket());
    }

    @Test
    void addMultiplePassive() {
        p1.add(pt1);
        assertEquals(1, p1.getNumberOfPassiveItem());
        p1.add(pt1);
        assertEquals(1, p1.getNumberOfPassiveItem());
        p1.add(pt1);
        assertEquals(1, p1.getNumberOfPassiveItem());
    }
    @Test
    void addMultipleActive() {
        p1.add(at1);
        assertEquals(1, p1.getNumberOfPickedEffectItem());
        p1.add(at1);
        assertEquals(1, p1.getNumberOfPickedEffectItem());
        p1.add(at1);
        assertEquals(1, p1.getNumberOfPickedEffectItem());
    }

    @Test
    void addMultiplePassiveWithActive() {
        assertEquals(0, p1.getNumberOfPassiveItem());
        assertEquals(0, p1.getNumberOfPickedEffectItem());
        p1.add(pt1);
        assertEquals(1, p1.getNumberOfPassiveItem());
        assertEquals(1, p1.getNumberOfPickedEffectItem());
        p1.add(pt1);
        assertEquals(1, p1.getNumberOfPassiveItem());
        assertEquals(1, p1.getNumberOfPickedEffectItem());
        p1.add(at1);
        assertEquals(1, p1.getNumberOfPassiveItem());
        assertEquals(2, p1.getNumberOfPickedEffectItem());
    }
    @Test
    void testCumulativeActive() {
        Active at3 = new Active("", 0) {
            @Override
            public void draw() {}
        };
        Active at4 = new Active("", 0) {
            @Override
            public void draw() {}
        };
        Active at5 = new Active("", 0) {
            @Override
            public void draw() {}
        };
        assertEquals(0, p1.getNumberOfPickedEffectItem());
        p1.add(at1);
        assertEquals(at1, p1.getActive());
        assertEquals(at1, p1.add(at2));
        assertEquals(at2, p1.add(at3));
        assertEquals(at3, p1.add(at4));
        assertEquals(at4, p1.add(at5));
        assertEquals(at5, p1.getActive());
        assertEquals(5, p1.getNumberOfPickedEffectItem());
    }
    @Test
    void addMultipleItems() {
        assertEquals(0, p1.getNumberOfPickedEffectItem());
        p1.add(pt1);
        assertEquals(1, p1.getNumberOfPickedEffectItem());
        p1.add(pt1);
        assertEquals(1, p1.getNumberOfPickedEffectItem());
        p1.add(at1);
        assertEquals(2, p1.getNumberOfPickedEffectItem());
        assertEquals(1, p1.getNumberOfPassiveItem());
        p1.add(tt1);
        assertEquals(1, p1.getNumberOfPassiveItem());
        assertEquals(3, p1.getNumberOfPickedEffectItem());
    }

}
