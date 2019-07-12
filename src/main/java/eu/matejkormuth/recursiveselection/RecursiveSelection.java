package eu.matejkormuth.recursiveselection;

import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class RecursiveSelection extends JavaPlugin {

    private static RecursiveSelection instance;

    /**
     * Returns the instance of recursive selection plugin.
     *
     * @return instance of recursive selection plugin
     */
    public static RecursiveSelection getInstance() {
        return instance;
    }

    /**
     * Session provider.
     */
    @Getter
    private SessionProvider sessionProvider;

    @Override
    public void onEnable() {
        // Set static instance.
        instance = this;
        // Initialize components.
        sessionProvider = new SessionProvider();

        // Initialize listeners.
        listener(new InteractListener(this));
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    /**
     * Registers new listener with this plugin.
     *
     * @param listener new listener to register
     */
    private void listener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
