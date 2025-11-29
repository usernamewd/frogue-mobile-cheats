package io.github.necrashter.natural_revenge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import io.github.necrashter.natural_revenge.world.player.Player;
import io.github.necrashter.natural_revenge.world.player.Firearm;
import io.github.necrashter.natural_revenge.world.player.PlayerWeapon;
import io.github.necrashter.natural_revenge.world.entities.GameEntity;

/**
 * Enhanced cheat system for Frogue with aimbot and visual menu
 * Provides various gameplay modifications and debugging features
 */
public class CheatSystem {
    
    // Cheat states (removed instantKill and noGravity)
    public static boolean godMode = false;
    public static boolean unlimitedAmmo = false;
    public static boolean infiniteHealth = false;
    public static boolean speedHack = false;
    public static boolean noRecoil = false;
    public static boolean fastReload = false;
    public static boolean aimbot = false;
    public static boolean teleporter = false;
    public static boolean wallHack = false;
    
    // Movement cheats
    public static float speedMultiplier = 1.0f;
    public static float jumpMultiplier = 1.0f;
    
    // Teleporter positions
    private static final float[][] TELEPORT_POSITIONS = {
        {0, 5, 0},      // Spawn
        {50, 5, 50},    // Far corner 1
        {-50, 5, -50},  // Far corner 2
        {100, 10, 0},   // Elevated position
        {0, 20, 100}    // High altitude
    };
    private static int currentTeleportIndex = 0;
    
    // Aimbot settings
    private static float aimbotRange = 200f;
    private static float aimbotSmoothness = 0.1f;
    private static GameEntity currentTarget = null;
    private static float lastAimbotTime = 0f;
    private static final float AIMBOT_COOLDOWN = 0.1f; // Fire rate limit
    
    // Menu system
    public static boolean showCheatMenu = false;
    private static int selectedMenuItem = 0;
    private static final String[] menuItems = {
        "God Mode", "Unlimited Ammo", "Speed Hack", "No Recoil", 
        "Fast Reload", "Aimbot", "Teleporter", "Wall Hack"
    };
    
    // Key binding constants
    private static final int[] NUM_KEYS = {
        Input.Keys.NUM_1, Input.Keys.NUM_2, Input.Keys.NUM_3, Input.Keys.NUM_4,
        Input.Keys.NUM_5, Input.Keys.NUM_6, Input.Keys.NUM_7, Input.Keys.NUM_8,
        Input.Keys.NUM_9, Input.Keys.NUM_0
    };
    
    /**
     * Initialize the cheat system
     */
    public static void initialize() {
        System.out.println("=== FROGUE CHEAT SYSTEM LOADED ===");
        System.out.println("Press F2 to toggle debug mode");
        System.out.println("Press F3 to toggle cheats");
        System.out.println("Press F4 to show/hide cheat help");
        System.out.println("Press F11 to show/hide cheat menu");
        System.out.println("=================================");
        
        // Enable debug mode by default for cheats
        Main.debugMode = true;
    }
    
    /**
     * Update cheat system - call this every frame
     */
    public static void update(float delta, Player player) {
        handleGlobalKeys();
        handleMenuControls();
        handleCheatKeys(player);
        applyCheats(player, delta);
        updateAimbot(player, delta);
    }
    
    /**
     * Handle global keys (F2, F3, F4, F11)
     */
    private static void handleGlobalKeys() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
            Main.debugMode = !Main.debugMode;
            showMessage("Debug Mode: " + (Main.debugMode ? "ON" : "OFF"));
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
            toggleCheatsEnabled();
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.F4)) {
            showCheatHelp();
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.F11)) {
            showCheatMenu = !showCheatMenu;
            showMessage("Cheat Menu: " + (showCheatMenu ? "SHOW" : "HIDE"));
        }
    }
    
    /**
     * Handle menu navigation controls
     */
    private static void handleMenuControls() {
        if (!showCheatMenu) return;
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            selectedMenuItem = Math.max(0, selectedMenuItem - 1);
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            selectedMenuItem = Math.min(menuItems.length - 1, selectedMenuItem + 1);
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || 
            Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            toggleCheatByIndex(selectedMenuItem);
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            showCheatMenu = false;
        }
    }
    
    /**
     * Toggle cheat by menu index
     */
    private static void toggleCheatByIndex(int index) {
        switch (index) {
            case 0: godMode = !godMode; showMessage("God Mode: " + (godMode ? "ON" : "OFF")); break;
            case 1: unlimitedAmmo = !unlimitedAmmo; showMessage("Unlimited Ammo: " + (unlimitedAmmo ? "ON" : "OFF")); break;
            case 2: speedHack = !speedHack; showMessage("Speed Hack: " + (speedHack ? "ON" : "OFF")); break;
            case 3: noRecoil = !noRecoil; showMessage("No Recoil: " + (noRecoil ? "ON" : "OFF")); break;
            case 4: fastReload = !fastReload; showMessage("Fast Reload: " + (fastReload ? "ON" : "OFF")); break;
            case 5: aimbot = !aimbot; showMessage("Aimbot: " + (aimbot ? "ON" : "OFF")); break;
            case 6: teleporter = !teleporter; showMessage("Teleporter: " + (teleporter ? "ON" : "OFF")); break;
            case 7: wallHack = !wallHack; showMessage("Wall Hack: " + (wallHack ? "ON" : "OFF")); break;
        }
    }
    
    /**
     * Handle cheat-specific keys (F5-F10 and number keys)
     */
    private static void handleCheatKeys(Player player) {
        if (!Main.debugMode) return;
        
        // Function keys for main cheats (removed F9, F12)
        if (Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
            godMode = !godMode;
            if (godMode) infiniteHealth = true;
            showMessage("God Mode: " + (godMode ? "ON" : "OFF"));
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.F6)) {
            unlimitedAmmo = !unlimitedAmmo;
            showMessage("Unlimited Ammo: " + (unlimitedAmmo ? "ON" : "OFF"));
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.F7)) {
            speedHack = !speedHack;
            showMessage("Speed Hack: " + (speedHack ? "ON" : "OFF"));
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.F8)) {
            noRecoil = !noRecoil;
            showMessage("No Recoil: " + (noRecoil ? "ON" : "OFF"));
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.F9)) {
            aimbot = !aimbot;
            showMessage("Aimbot: " + (aimbot ? "ON" : "OFF"));
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.F10)) {
            fastReload = !fastReload;
            showMessage("Fast Reload: " + (fastReload ? "ON" : "OFF"));
        }
        
        // Number keys for quick settings (adjusted for removed cheats)
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            speedMultiplier = speedMultiplier == 1.0f ? 2.0f : 1.0f;
            showMessage("Speed Multiplier: " + speedMultiplier);
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            jumpMultiplier = jumpMultiplier == 1.0f ? 2.0f : 1.0f;
            showMessage("Jump Multiplier: " + jumpMultiplier);
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            if (teleporter) {
                teleportPlayer(player);
            }
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            if (player.health > 0 && !infiniteHealth) {
                player.heal(player.maxHealth);
                showMessage("Player Healed");
            }
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
            giveAllWeapons(player);
            showMessage("All Weapons Given");
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) {
            maxAllAmmo(player);
            showMessage("All Ammo Maxed");
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)) {
            wallHack = !wallHack;
            showMessage("Wall Hack: " + (wallHack ? "ON" : "OFF"));
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)) {
            resetAllCheats();
            showMessage("All Cheats Reset");
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)) {
            // Kill nearest enemy (requires aimbot)
            if (aimbot) {
                killNearestEnemy(player);
            }
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
            enableDemoMode(player);
            showMessage("Demo Mode Enabled");
        }
    }
    
    /**
     * Update aimbot functionality
     */
    private static void updateAimbot(Player player, float delta) {
        if (!aimbot || player.activeWeapon == null) return;
        
        lastAimbotTime += delta;
        
        // Find closest enemy
        GameEntity closestEnemy = findClosestEnemy(player);
        if (closestEnemy == null) return;
        
        currentTarget = closestEnemy;
        
        // Calculate aim direction
        Vector3 playerPos = player.hitBox.position;
        Vector3 targetPos = closestEnemy.hitBox.position;
        Vector3 direction = targetPos.cpy().sub(playerPos).nor();
        
        // Apply smoothing
        float smoothingFactor = Math.min(1f, aimbotSmoothness * delta * 60f);
        Vector3 currentForward = player.forward.cpy();
        currentForward.lerp(direction, smoothingFactor);
        player.forward.set(currentForward.nor());
        
        // Auto-fire if in range and cooldown passed
        float distance = playerPos.dst(targetPos);
        if (distance <= aimbotRange && lastAimbotTime >= AIMBOT_COOLDOWN) {
            player.firing1 = true;
            lastAimbotTime = 0f;
        } else {
            player.firing1 = false;
        }
    }
    
    /**
     * Find the closest enemy to the player
     */
    private static GameEntity findClosestEnemy(Player player) {
        GameEntity closest = null;
        float closestDistance = Float.MAX_VALUE;
        
        Vector3 playerPos = player.hitBox.position;
        
        // This would need access to the world's entity list
        // For now, return a placeholder - this would need integration with the game's entity system
        // Implementation depends on how entities are stored in GameWorld
        
        return closest;
    }
    
    /**
     * Kill nearest enemy (requires aimbot enabled)
     */
    private static void killNearestEnemy(Player player) {
        GameEntity target = findClosestEnemy(player);
        if (target != null && !target.dead) {
            target.takeDamage(target.health + 100f, null, null); // Overkill to ensure death
            showMessage("Nearest enemy eliminated");
        }
    }
    
    /**
     * Apply all active cheats to the player
     */
    private static void applyCheats(Player player, float delta) {
        // God mode / infinite health
        if (infiniteHealth) {
            player.health = player.maxHealth;
        }
        
        // Speed hack
        if (speedHack) {
            player.movementSpeed = 4f * speedMultiplier;
        } else {
            player.movementSpeed = 4f;
        }
        
        // Jump multiplier
        if (speedHack) {
            player.jumpVelocity = 6f * jumpMultiplier;
        } else {
            player.jumpVelocity = 6f;
        }
        
        // Weapon modifications
        if (player.activeWeapon instanceof Firearm) {
            Firearm firearm = (Firearm) player.activeWeapon;
            
            // Unlimited ammo
            if (unlimitedAmmo) {
                firearm.ammoInClip = firearm.maxAmmoInClip;
            }
            
            // Fast reload
            if (fastReload) {
                firearm.reloadSpeed = 10f; // Very fast reload
            } else {
                firearm.reloadSpeed = 2f; // Normal reload speed
            }
            
            // No recoil
            if (noRecoil) {
                firearm.knockForward = 0f;
                firearm.knockback = 0f;
            } else {
                firearm.knockForward = -0.375f;
                firearm.knockback = 2f;
            }
        }
    }
    
    /**
     * Toggle all cheats on/off
     */
    private static void toggleCheatsEnabled() {
        boolean anyActive = godMode || unlimitedAmmo || speedHack || noRecoil || 
                           aimbot || fastReload || teleporter;
        
        if (anyActive) {
            // Turn all cheats off
            godMode = unlimitedAmmo = speedHack = noRecoil = 
                     aimbot = fastReload = teleporter = wallHack = false;
            speedMultiplier = jumpMultiplier = 1.0f;
            showMessage("All Cheats Disabled");
        } else {
            // Turn main cheats on
            godMode = unlimitedAmmo = speedHack = true;
            showMessage("Main Cheats Enabled");
        }
    }
    
    /**
     * Teleport player to next position
     */
    private static void teleportPlayer(Player player) {
        currentTeleportIndex = (currentTeleportIndex + 1) % TELEPORT_POSITIONS.length;
        float[] pos = TELEPORT_POSITIONS[currentTeleportIndex];
        
        player.hitBox.position.set(pos[0], pos[1], pos[2]);
        showMessage("Teleported to position " + (currentTeleportIndex + 1));
    }
    
    /**
     * Give player all available weapons
     */
    private static void giveAllWeapons(Player player) {
        // This would require access to weapon creation methods
        // For now, just fill inventory with random weapons
        while (player.weapons.size < player.maximumWeapons) {
            // Add logic to create and add weapons
            // This is a placeholder - would need proper weapon creation
        }
    }
    
    /**
     * Max out all weapon ammo
     */
    private static void maxAllAmmo(Player player) {
        for (PlayerWeapon weapon : player.weapons) {
            if (weapon instanceof Firearm) {
                ((Firearm) weapon).ammoInClip = ((Firearm) weapon).maxAmmoInClip;
            }
        }
    }
    
    /**
     * Enable demo mode (all cheats on)
     */
    private static void enableDemoMode(Player player) {
        godMode = unlimitedAmmo = speedHack = noRecoil = fastReload = 
                 aimbot = teleporter = wallHack = true;
        speedMultiplier = 3.0f;
        jumpMultiplier = 2.0f;
        showMessage("Demo Mode: All cheats enabled with extreme settings!");
    }
    
    /**
     * Reset all cheats to default state
     */
    private static void resetAllCheats() {
        godMode = unlimitedAmmo = infiniteHealth = speedHack = noRecoil = 
                 fastReload = aimbot = teleporter = wallHack = false;
        speedMultiplier = jumpMultiplier = 1.0f;
        currentTeleportIndex = 0;
        currentTarget = null;
    }
    
    /**
     * Display a message to the player
     */
    private static void showMessage(String message) {
        System.out.println("[CHEAT] " + message);
        // Could also display on screen using UI elements
    }
    
    /**
     * Show cheat help
     */
    private static void showCheatHelp() {
        System.out.println("\n=== FROGUE CHEAT CONTROLS ===");
        System.out.println("F2 - Toggle Debug Mode");
        System.out.println("F3 - Toggle All Cheats");
        System.out.println("F4 - Show This Help");
        System.out.println("F11 - Show/Hide Cheat Menu");
        System.out.println();
        System.out.println("CHEAT TOGGLES:");
        System.out.println("F5 - God Mode (Infinite Health)");
        System.out.println("F6 - Unlimited Ammo");
        System.out.println("F7 - Speed Hack");
        System.out.println("F8 - No Recoil");
        System.out.println("F9 - Aimbot (Auto-aim and fire)");
        System.out.println("F10 - Fast Reload");
        System.out.println();
        System.out.println("QUICK ACTIONS:");
        System.out.println("Num 1 - Toggle Speed Multiplier (1x/2x)");
        System.out.println("Num 2 - Toggle Jump Multiplier (1x/2x)");
        System.out.println("Num 3 - Teleport to Next Position");
        System.out.println("Num 4 - Heal Player");
        System.out.println("Num 5 - Give All Weapons");
        System.out.println("Num 6 - Max All Ammo");
        System.out.println("Num 7 - Toggle Wall Hack");
        System.out.println("Num 8 - Reset All Cheats");
        System.out.println("Num 9 - Kill Nearest Enemy (requires Aimbot)");
        System.out.println("Num 0 - Demo Mode (All cheats max)");
        System.out.println();
        System.out.println("MENU CONTROLS (F11 to show menu):");
        System.out.println("UP/DOWN - Navigate menu");
        System.out.println("ENTER/SPACE - Toggle selected cheat");
        System.out.println("ESC - Close menu");
        System.out.println("============================\n");
    }
    
    /**
     * Get cheat status for UI display
     */
    public static String getCheatStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== CHEAT STATUS ===\n");
        sb.append("Debug Mode: ").append(Main.debugMode ? "ON" : "OFF").append("\n");
        sb.append("God Mode: ").append(godMode ? "ON" : "OFF").append("\n");
        sb.append("Unlimited Ammo: ").append(unlimitedAmmo ? "ON" : "OFF").append("\n");
        sb.append("Speed Hack: ").append(speedHack ? "ON" : "OFF").append("\n");
        sb.append("Speed Multiplier: ").append(speedMultiplier).append("x\n");
        sb.append("No Recoil: ").append(noRecoil ? "ON" : "OFF").append("\n");
        sb.append("Aimbot: ").append(aimbot ? "ON" : "OFF").append("\n");
        sb.append("Fast Reload: ").append(fastReload ? "ON" : "OFF").append("\n");
        sb.append("Teleporter: ").append(teleporter ? "ON" : "OFF").append("\n");
        sb.append("Wall Hack: ").append(wallHack ? "ON" : "OFF").append("\n");
        sb.append("===================");
        return sb.toString();
    }
    
    /**
     * Get menu items for display
     */
    public static String[] getMenuItems() {
        return menuItems.clone();
    }
    
    /**
     * Get current menu selection
     */
    public static int getSelectedMenuItem() {
        return selectedMenuItem;
    }
    
    /**
     * Get cheat states for menu display
     */
    public static boolean[] getCheatStates() {
        return new boolean[] {
            godMode, unlimitedAmmo, speedHack,
            noRecoil, fastReload, aimbot, teleporter,
            wallHack
        };
    }
    
    /**
     * Render the cheat menu (for UI integration)
     */
    public static String renderMenu() {
        if (!showCheatMenu) return "";
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== FROGUE CHEAT MENU ===\n\n");
        
        boolean[] states = getCheatStates();
        for (int i = 0; i < menuItems.length; i++) {
            String status = states[i] ? " [ON]" : " [OFF]";
            String arrow = (i == selectedMenuItem) ? ">> " : "   ";
            sb.append(arrow).append(menuItems[i]).append(status).append("\n");
        }
        
        sb.append("\nNavigation: UP/DOWN | Toggle: ENTER/SPACE | Close: ESC");
        return sb.toString();
    }
}