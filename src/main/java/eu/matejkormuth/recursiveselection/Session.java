package eu.matejkormuth.recursiveselection;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Session {

    /**
     * Weak reference to player associated with this session.
     */
    private final WeakReference<Player> player;

    /**
     * Template for name of schematic.
     */
    @Getter
    private String schematicName;

    /**
     * Counter of created schematics.
     */
    private final AtomicInteger schematicCounter = new AtomicInteger(0);

    /**
     * Selection tool.
     */
    @Getter
    @Setter
    private Material selectionTool = Material.WOOD_SWORD;

    /**
     * Whether to auto save found region to schematic.
     */
    @Getter
    @Setter
    private boolean autoSave = true;

    /**
     * This player's constraint.
     */
    @Getter
    @Setter
    private Constraints constraints;

    /**
     * Primitive logger.
     */
    private Consumer<String> logger;

    public Session(Player player) {
        this.player = new WeakReference<>(player);
        schematicName = player.getName() + "_schematic";
        constraints = new Constraints();
        logger = s -> {
            Player p = this.player.get();
            if (p != null) {
                p.sendMessage(s);
            }
        };
    }

    /**
     * Set's the new schematic name and resets the schematic counter.
     *
     * @param schematicName new schematic template name
     */
    public void setSchematicName(String schematicName) {
        if (!this.schematicName.equals(schematicName)) {
            this.schematicName = schematicName;
            this.schematicCounter.set(0);
        }
    }

    /**
     * Triggers recursive selection search starting from specified block.
     *
     * @param clickedBlock the clicked block
     */
    public void triggerSearch(Block clickedBlock) {
        RecursiveSearch search = new RecursiveSearch(clickedBlock, constraints);
        BoundingBox box;
        try {
            box = search.search();
        } catch (RecursiveException e) {
            logger.accept(Colors.red("Error: " + e.getMessage()));
            return;
        }

        if (autoSave) {
            // Create schematic.

        } else {
            // Set WE selection of player.
            WESupport.setSelection(box, player.get());
            logger.accept(Colors.green("WorldEdit selection set!"));
        }
    }
}
