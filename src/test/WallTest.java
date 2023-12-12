package test;

import main.Block;
import main.CompositeBlock;
import main.NotFoundException;
import main.Wall;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void testCount() {
        // Arrange
        List<Block> blocks = Arrays.asList(
                new ExampleBlock("Grey", "Brick"),
                new ExampleBlock("Blue", "Wood"),
                new CompositeBlockImpl(
                        Arrays.asList(
                                new ExampleBlock("Green", "Stone"),
                                new ExampleBlock("Yellow", "Stone"),
                                new ExampleBlock("Yellow", "Stone"),
                                new ExampleBlock("Yellow", "Stone"),
                                new ExampleBlock("Yellow", "Stone")
                        )
                )
        );
        Wall wall = new Wall(blocks);

        // Act
        int totalCount = wall.count();

        // Assert
        assertEquals(7, totalCount);
    }

    @Test
    void testException() {
        //Arrange
        List<Block> blocks = List.of();
        Wall wall = new Wall(blocks);

        //Act & Assert
        RuntimeException exception=assertThrows(NotFoundException.class, () -> wall.findBlockByColor("red"));
        assertEquals("Block with color red not found", exception.getMessage());
    }

    record ExampleBlock(String color, String material) implements Block {
    }

    static class CompositeBlockImpl implements CompositeBlock {
        private final List<Block> elements;

        public CompositeBlockImpl(List<Block> elements) {
            this.elements = elements;
        }

        @Override
        public List<Block> getBlocks() {
            return elements;
        }

        @Override
        public String color() {
            return elements.get(0).color();
        }

        @Override
        public String material() {
            return elements.get(0).material();
        }
    }
}

