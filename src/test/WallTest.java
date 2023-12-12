package test;

import main.Block;
import main.Wall;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WallTest {

    @Test
    void testFindBlockByColor() {
        // Arrange
        List<Block> blocks = Arrays.asList(
                new ExampleBlock("Grey", "Brick"),
                new ExampleBlock("Grey", "Brick"),
                new ExampleBlock("Blue", "Wood"),
                new ExampleBlock("Blue", "Wood"),
                new ExampleBlock("Blue", "Wood")
        );
        Wall wall = new Wall(blocks);

        // Act
        Optional<Block> foundBlock = wall.findBlockByColor("grey");

        // Assert
        Assertions.assertTrue(foundBlock.isPresent());
        assertEquals("Grey", foundBlock.get().color());
        assertEquals("Brick", foundBlock.get().material());
    }

    @Test
    void testFindBlocksByMaterial() {
        // Arrange
        List<Block> blocks = Arrays.asList(
                new ExampleBlock("Grey", "Brick"),
                new ExampleBlock("Blue", "Wood"),
                new ExampleBlock("Green", "Brick")
        );
        Wall wall = new Wall(blocks);

        // Act
        List<Block> foundBlocks = wall.findBlocksByMaterial("brick");

        // Assert
        assertEquals(2, foundBlocks.size());
        assertEquals("Grey", foundBlocks.get(0).color());
        assertEquals("Green", foundBlocks.get(1).color());
    }

    record ExampleBlock(String color, String material) implements Block {
    }
}

