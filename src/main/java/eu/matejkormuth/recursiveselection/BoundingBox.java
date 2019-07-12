package eu.matejkormuth.recursiveselection;

import lombok.Data;
import org.bukkit.block.Block;

@Data
public class BoundingBox {
    int minX;
    int minY;
    int minZ;

    int maxX;
    int maxY;
    int maxZ;

    public void expandToInclude(Block block) {
        this.minX = Math.min(minX, block.getX());
        this.minY = Math.min(minY, block.getY());
        this.minZ = Math.min(minZ, block.getZ());

        this.maxX = Math.max(maxX, block.getX());
        this.maxY = Math.max(maxY, block.getY());
        this.maxZ = Math.max(maxZ, block.getZ());
    }
}
