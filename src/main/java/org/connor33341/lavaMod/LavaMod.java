package org.connor33341.lavaMod;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public final class LavaMod extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Starting LavaMod");
        new BukkitRunnable() {
            @Override
            public void run() {
                System.out.println("Ticking Lava");
                raiseLava();
            }
        }.runTaskTimer(this, 0, 300);
    }

    private void raiseLava() {
        World world = Bukkit.getWorld("world"); // Change "world" to your world name if different
        if (world == null) return;
        boolean Logging = false;
        int worldBorderSize = (int) world.getWorldBorder().getSize() / 2;
        System.out.println(worldBorderSize);
        if (worldBorderSize >= 2000){
            worldBorderSize = 150;
        }
        int minX = -worldBorderSize;
        int maxX = worldBorderSize;
        // int minZ = -worldBorderSize;
        // int maxZ = worldBorderSize;

        for (int x = minX; x <= maxX; x++) {
            for (int z = minX; z <= maxX; z++) {
                for (int y = world.getMaxHeight(); y > -64; y--) {
                    Block block = world.getBlockAt(x, y, z);
                    if (block.getType() == Material.LAVA) {
                        if (Logging){
                            System.out.println("Lava X: "+x+" Y: "+y+" Z: "+z);
                        }
                        Block above = world.getBlockAt(x, y + 1, z);
                        if (above.getType() != Material.LAVA) {
                            if (Logging){
                                System.out.println("Not Lava");
                            }
                            above.setType(Material.LAVA);
                            if (above.getType() != Material.AIR) {
                                above.breakNaturally();
                            }
                        }
                    }
                }
            }
        }
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
