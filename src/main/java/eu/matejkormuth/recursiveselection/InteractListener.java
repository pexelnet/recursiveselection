package eu.matejkormuth.recursiveselection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

    private final RecursiveSelection plugin;

    public InteractListener(RecursiveSelection plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onPlayerInteractWithBlock(final PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (event.getItem() != null) {
                    validate(event);
                }
            }
        }
    }

    // Validates event.
    private void validate(PlayerInteractEvent event) {
        Session session = plugin.getSessionProvider().getSession(event.getPlayer());

        if (event.getItem().getType() == session.getSelectionTool()) {
            session.triggerSearch(event.getClickedBlock());
        }
    }
}
