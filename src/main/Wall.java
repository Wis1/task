package main;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Wall implements Structure {
    private final List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> block.color().equalsIgnoreCase(color))
                .findAny()
                .map(Optional::of)
                .orElseThrow(()->new NotFoundException("Block with color " + color + " not found"));
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .filter(block -> block.material().equalsIgnoreCase(material))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return blocks.stream()
                .mapToInt(block -> block instanceof CompositeBlock ?
                        ((CompositeBlock) block).getBlocks().size() :
                        1)
                .sum();
    }
}
