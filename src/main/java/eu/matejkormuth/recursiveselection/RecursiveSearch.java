package eu.matejkormuth.recursiveselection;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

/**
 * Represents class for basic recursive selection serach.
 */
public class RecursiveSearch {

    private final Block origin;
    private final Constraints constraints;

    public RecursiveSearch(Block origin, Constraints constraints) {
        this.origin = origin;
        this.constraints = constraints;
    }

    public BoundingBox search() throws RecursiveException {
        BoundingBox box = new BoundingBox();
        try {
            recurSearch(origin, box, 0, 0);
        } catch (StackOverflowError error) {
            throw new RecursiveException("Structure is too big!");
        }
        return box;
    }

    private void recurSearch(Block block, BoundingBox box, int depth, int airBlocks) {
        // Tails.
        if (depth > constraints.getMaxRecurCalls()) {
            return;
        }

        if (airBlocks > constraints.getAirThreshold()) {
            return;
        }

        if (block.getY() < constraints.getMinY() ||
                block.getY() > constraints.getMaxY()) {
            return;
        }

        // Expand box.
        box.expandToInclude(block);

        boolean isAir = block.getType() == Material.AIR;
        airBlocks = isAir ? airBlocks + 1 : 0;

        // Recursive calls.
        recurSearch(block.getRelative(BlockFace.DOWN), box, depth + 1, airBlocks);
        recurSearch(block.getRelative(BlockFace.UP), box, depth + 1, airBlocks);

        recurSearch(block.getRelative(BlockFace.EAST), box, depth + 1, airBlocks);
        recurSearch(block.getRelative(BlockFace.SOUTH), box, depth + 1, airBlocks);
        recurSearch(block.getRelative(BlockFace.WEST), box, depth + 1, airBlocks);
        recurSearch(block.getRelative(BlockFace.NORTH), box, depth + 1, airBlocks);

    }
}
