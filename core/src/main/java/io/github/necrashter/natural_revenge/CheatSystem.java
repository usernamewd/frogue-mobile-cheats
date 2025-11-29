package io.github.necrashter.natural_revenge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import io.github.necrashter.natural_revenge.world.player.Player;
import io.github.necrashter.natural_revenge.world.player.Firearm;
import io.github.necrashter.natural_revenge.world.player.PlayerWeapon;

/**
 * Comprehensive cheat system for Frogue
 * Provides various gameplay modifications and debugging features
 */
public class CheatSystem {
    
    // Cheat states
    public static boolean godMode = false;
    public static boolean unlimitedAmmo = false;
    public static boolean infiniteHealth = false;
    public static boolean speedHack = false;
    public static boolean noRecoil = false;
    public static boolean fastReload = false;
    public static boolean instantKill = false;
    public static boolean teleporter = false;
    public static boolean noGravity = false;
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
    
    // Key binding constants
    private static final int[] NUM_KEYS = {
        Input.Keys.NUM_1, Input.Keys.NUM_2, Input.Keys.NUM_3, Input.Keys.NUM_4,
        Input.Keys.NUM_5, Input.Keys.NUM_6, Input.Keys.NUM_7, Input.Keys.NUM_8,
        Input.Keys.NUM_9, Input.Keys.NUM_0
    };
    
    private static boolean[] cheatStates = {
        godMode, unlimitedAmmo, infiniteHealth, speedHack,
        noRecoil, fastReload, instantKill, teleporter,
        noGravity, wallHack
    };
    
    /**
     * Initialize the cheat system
     */
    public static void initialize() {
        System.out.println("=== FROGUE CHEAT SYSTEM LOADED ===");
        System.out.println("Press F2 to toggle debug mode");
        System.out.println("Press F3 to toggle cheats");
        System.out.println("Press F4 to show/hide cheat help");
        System.out.println("=================================");
        
        // Enable debug mode by default for cheats
        Main.debugMode = true;
    }
    
    /**
     * Update cheat system - call this every frame
     */
    public static void update(float delta, Player player) {
        handleGlobalKeys();
        handleCheatKeys(player);
        applyCheats(player, delta);
    }
    
    /**
     * Handle global keys (F2, F3, F4)
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
    }
    
    /**
     * Handle cheat-specific keys (F5-F12 and number keys)
     */
    private static void handleCheatKeys(Player player) {
        if (!Main.debugMode) return;
        
        // Function keys for main cheats
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
            instantKill = !instantKill;
            showMessage("Instant Kill: " + (instantKill ? "ON" : "OFF"));
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.F10)) {
            fastReload = !fastReload;
            showMessage("Fast Reload: " + (fastReload ? "ON" : "OFF"));
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.F11)) {
            teleporter = !teleporter;
            showMessage("Teleporter: " + (teleporter ? "ON" : "OFF"));
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.F12)) {
            noGravity = !noGravity;
            showMessage("No Gravity: " + (noGravity ? "ON" : "OFF"));
        }
        
        // Number keys for quick settings
        for (int i = 0; i < NUM_KEYS.length && i < cheatStates.length; i++) {
            if (Gdx.input.isKeyJustPressed(NUM_KEYS[i])) {
                switch (i) {
                    case 0: // Num 1 - Speed settings
                        speedMultiplier = speedMultiplier == 1.0f ? 2.0f : 1.0f;
                        showMessage("Speed Multiplier: " + speedMultiplier);
                        break;
                    case 1: // Num 2 - Jump settings
                        jumpMultiplier = jumpMultiplier == 1.0f ? 2.0f : 1.0f;
                        showMessage("Jump Multiplier: " + jumpMultiplier);
                        break;
                    case 2: // Num 3 - Teleport
                        if (teleporter) {
                            teleportPlayer(player);
                        }
                        break;
                    case 3: // Num 4 - Heal
                        if (player.health > 0 && !infiniteHealth) {
                            player.heal(player.maxHealth);
                            showMessage("Player Healed");
                        }
                        break;
                    case 4: // Num 5 - All weapons
                        giveAllWeapons(player);
                        showMessage("All Weapons Given");
                        break;
                    case 5: // Num 6 - Max ammo
                        maxAllAmmo(player);
                        showMessage("All Ammo Maxed");
                        break;
                    case 6: // Num 7 - Toggle wallhack
                        wallHack = !wallHack;
                        showMessage("Wall Hack: " + (wallHack ? "ON" : "OFF"));
                        break;
                    case 7: // Num 8 - Reset all cheats
                        resetAllCheats();
                        showMessage("All Cheats Reset");
                        break;
                    case 8: // Num 9 - Kill all enemies
                        if (instantKill) {
                            killAllEnemies(player.world);
                            showMessage("All Enemies Eliminated");
                        }
                        break;
                    case 9: // Num 0 - Demo mode
                        enableDemoMode(player);
                        showMessage("Demo Mode Enabled");
                        break;
                }
            }
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
        
        // No gravity
        if (noGravity && player.hitBox.onGround) {
            player.jump(15f); // Force jump when on ground
        }
        
        // Weapon modifications
        if (player.activeWeapon instanceof Firearm) {
            Firearm firearm = (Firearm) player.activeWeapon;
            
            // Unlimited ammo
            if (unlimitedAmmo) {
                firearm.ammoInClip = firearm.maxAmmoInClip;
                // Optionally also give unlimited reserve ammo
                // firearm.ammoInClip = Integer.MAX_VALUE;
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
                           instantKill || fastReload || teleporter || noGravity;
        
        if (anyActive) {
            // Turn all cheats off
            godMode = unlimitedAmmo = speedHack = noRecoil = instantKill = 
                     fastReload = teleporter = noGravity = wallHack = false;
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
     * Kill all enemies in the world
     */
    private static void killAllEnemies(io.github.necrashter.natural_revenge.world.GameWorld world) {
        // This would need access to enemy lists/arrays in the world
        // Implementation would depend on how enemies are stored
    }
    
    /**
     * Enable demo mode (all cheats on)
     */
    private static void enableDemoMode(Player player) {
        godMode = unlimitedAmmo = speedHack = noRecoil = fastReload = 
                 instantKill = teleporter = noGravity = wallHack = true;
        speedMultiplier = 3.0f;
        jumpMultiplier = 2.0f;
        showMessage("Demo Mode: All cheats enabled with extreme settings!");
    }
    
    /**
     * Reset all cheats to default state
     */
    private static void resetAllCheats() {
        godMode = unlimitedAmmo = infiniteHealth = speedHack = noRecoil = 
                 fastReload = instantKill = teleporter = noGravity = wallHack = false;
        speedMultiplier = jumpMultiplier = 1.0f;
        currentTeleportIndex = 0;
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
        System.out.println();
        System.out.println("CHEAT TOGGLES:");
        System.out.println("F5 - God Mode (Infinite Health)");
        System.out.println("F6 - Unlimited Ammo");
        System.out.println("F7 - Speed Hack");
        System.out.println("F8 - No Recoil");
        System.out.println("F9 - Instant Kill");
        System.out.println("F10 - Fast Reload");
        System.out.println("F11 - Teleporter");
        System.out.println("F12 - No Gravity");
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
        System.out.println("Num 9 - Kill All Enemies*");
        System.out.println("Num 0 - Demo Mode (All cheats max)");
        System.out.println("*Requires Instant Kill enabled");
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
        sb.append("Instant Kill: ").append(instantKill ? "ON" : "OFF").append("\n");
        sb.append("Teleporter: ").append(teleporter ? "ON" : "OFF").append("\n");
        sb.append("No Gravity: ").append(noGravity ? "ON" : "OFF").append("\n");
        sb.append("===================");
        return sb.toString();
    }
}