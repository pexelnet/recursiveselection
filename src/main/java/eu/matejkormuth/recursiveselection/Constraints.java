package eu.matejkormuth.recursiveselection;

import lombok.Data;

@Data
public class Constraints {

    /**
     * No constraint.
     */
    public static final int INFINITY = -1;

    private int maxRecurCalls = 1024;
    private int maxBlocks = 256 * 256 * 16;
    private int airThreshold = 1;

    private int minX = INFINITY;
    private int minY = INFINITY;
    private int minZ = INFINITY;

    private int maxX = INFINITY;
    private int maxY = INFINITY;
    private int maxZ = INFINITY;
}
