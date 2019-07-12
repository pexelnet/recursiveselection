package eu.matejkormuth.recursiveselection;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.WeakHashMap;

public class SessionProvider {

    /**
     * Session cache.
     */
    private final Map<Player, Session> cache = new WeakHashMap<>();

    /**
     * Returns the cached session or creates new session for specified player.
     *
     * @param player player to return session for
     * @return session associated with specified player
     */
    public Session getSession(Player player) {
        return cache.putIfAbsent(player, create(player));
    }

    private Session create(Player player) {
        return new Session(player);
    }
}
