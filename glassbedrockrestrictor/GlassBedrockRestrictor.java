package glassbedrockrestrictor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EnumSet;
import java.util.Set;

public class GlassBedrockRestrictor extends JavaPlugin implements Listener {

    private static final Set<Material> RESTRICTED_BLOCKS = EnumSet.of(
        Material.GLASS,
        Material.BEDROCK
    );

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("GlassBedrockRestrictor enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("GlassBedrockRestrictor disabled.");
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp() && RESTRICTED_BLOCKS.contains(event.getBlock().getType())) {
            event.setCancelled(true);
            player.sendMessage("§cYou are not allowed to place this block.");
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp() && RESTRICTED_BLOCKS.contains(event.getBlock().getType())) {
            event.setCancelled(true);
            player.sendMessage("§cYou are not allowed to break this block.");
        }
    }
}
