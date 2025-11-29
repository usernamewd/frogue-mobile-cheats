package io.github.necrashter.natural_revenge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.necrashter.natural_revenge.world.player.Player;
import io.github.necrashter.natural_revenge.world.entities.GameEntity;

/**
 * Mobile-optimized cheat system for Frogue
 * Features touch-friendly UI controls, gesture support, and mobile-specific aimbot
 */
public class MobileCheatSystem {
    
    // Core cheat states (synchronized with CheatSystem)
    public static boolean godMode = false;
    public static boolean unlimitedAmmo = false;
    public static boolean speedHack = false;
    public static boolean noRecoil = false;
    public static boolean fastReload = false;
    public static boolean aimbot = false;
    public static boolean teleporter = false;
    public static boolean wallHack = false;
    
    // Mobile-specific settings
    private static float speedMultiplier = 1.0f;
    private static Stage uiStage;
    private static boolean mobileMenuVisible = false;
    private static boolean touchAimbotMode = false;
    private static Vector3 lastTouchPosition = new Vector3();
    private static float touchSensitivity = 0.5f;
    
    // UI Components
    private static Table mainMenuTable;
    private static ScrollPane cheatScrollPane;
    private static Label statusLabel;
    private static ImageButton aimbotButton;
    private static ImageButton menuButton;
    private static ImageButton godModeButton;
    private static ImageButton ammoButton;
    private static ImageButton speedButton;
    private static ImageButton recoilButton;
    private static ImageButton reloadButton;
    private static ImageButton teleportButton;
    private static ImageButton wallHackButton;
    
    // Touch gesture tracking
    private static boolean isTouching = false;
    private static float lastTouchTime = 0f;
    private static int tapCount = 0;
    private static final float DOUBLE_TAP_THRESHOLD = 0.3f;
    
    // Mobile aimbot settings
    private static boolean autoAimbot = false;
    private static float mobileAimbotRange = 150f; // Slightly reduced for mobile
    private static float mobileAimbotSmoothness = 0.2f; // Smoother for mobile
    private static GameEntity currentMobileTarget = null;
    
    /**
     * Initialize mobile cheat system with touch UI
     */
    public static void initializeMobile(Stage stage) {
        uiStage = stage;
        createMobileUI();
        setupMobileInput();
        
        Gdx.app.log("MobileCheatSystem", "Mobile cheat system initialized with touch controls");
    }
    
    /**
     * Create mobile-friendly cheat UI
     */
    private static void createMobileUI() {
        // Create main menu table
        mainMenuTable = new Table();
        mainMenuTable.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        // Create cheat buttons with large touch targets
        createCheatButtons();
        
        // Create status indicator
        statusLabel = new Label("Cheats Ready", new Label.LabelStyle(
            new com.badlogic.gdx.graphics.g2d.BitmapFont(),
            Color.WHITE
        ));
        statusLabel.setFontScale(1.5f);
        statusLabel.setPosition(10, Gdx.graphics.getHeight() - 40);
        
        // Hide menu initially
        mainMenuTable.setVisible(false);
        
        if (uiStage != null) {
            uiStage.addActor(mainMenuTable);
            uiStage.addActor(statusLabel);
        }
    }
    
    /**
     * Create individual cheat buttons optimized for mobile touch
     */
    private static void createCheatButtons() {
        // Menu toggle button (floating in corner)
        menuButton = createTouchButton("≡", "Cheat Menu", () -> toggleMobileMenu());
        menuButton.setPosition(10, 10);
        menuButton.setSize(60, 60);
        
        // Aimbot toggle button (always visible)
        aimbotButton = createTouchButton("🎯", "Aimbot", () -> toggleCheat("aimbot"));
        aimbotButton.setPosition(Gdx.graphics.getWidth() - 80, 10);
        aimbotButton.setSize(70, 70);
        
        // Add floating buttons to stage
        if (uiStage != null) {
            uiStage.addActor(menuButton);
            uiStage.addActor(aimbotButton);
        }
        
        // Create main cheat menu (initially hidden)
        createCheatMenu();
    }
    
    /**
     * Create main cheat menu for mobile
     */
    private static void createCheatMenu() {
        Table cheatTable = new Table();
        cheatTable.setSize(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight() * 0.6f);
        cheatTable.setPosition(Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.2f);
        
        // Title
        Label titleLabel = new Label("Frogue Cheats", new Label.LabelStyle(
            new com.badlogic.gdx.graphics.g2d.BitmapFont(),
            Color.YELLOW
        ));
        titleLabel.setFontScale(2f);
        titleLabel.setAlignment(Align.center);
        
        // Cheat buttons in scrollable list
        VerticalGroup cheatGroup = new VerticalGroup();
        cheatGroup.space(15);
        
        // Add each cheat as a large button
        godModeButton = createCheatButton("🛡️ God Mode", "godMode");
        ammoButton = createCheatButton("🔫 Unlimited Ammo", "unlimitedAmmo");
        speedButton = createCheatButton("⚡ Speed Hack", "speedHack");
        recoilButton = createCheatButton("🎯 No Recoil", "noRecoil");
        reloadButton = createCheatButton("🔄 Fast Reload", "fastReload");
        teleportButton = createCheatButton("🚪 Teleporter", "teleporter");
        wallHackButton = createCheatButton("👁️ Wall Hack", "wallHack");
        
        cheatGroup.addActor(godModeButton);
        cheatGroup.addActor(ammoButton);
        cheatGroup.addActor(speedButton);
        cheatGroup.addActor(recoilButton);
        cheatGroup.addActor(reloadButton);
        cheatGroup.addActor(teleportButton);
        cheatGroup.addActor(wallHackButton);
        
        // Scroll pane for cheat list
        cheatScrollPane = new ScrollPane(cheatGroup);
        cheatScrollPane.setSize(Gdx.graphics.getWidth() * 0.7f, Gdx.graphics.getHeight() * 0.4f);
        
        // Close button
        TextButton closeButton = new TextButton("Close", new TextButton.TextButtonStyle());
        closeButton.getStyle().fontScale = 1.5f;
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toggleMobileMenu();
            }
        });
        
        // Add to table
        cheatTable.add(titleLabel).padBottom(20);
        cheatTable.row();
        cheatTable.add(cheatScrollPane);
        cheatTable.row();
        cheatTable.add(closeButton).padTop(20);
        
        mainMenuTable.clearChildren();
        mainMenuTable.add(cheatTable);
    }
    
    /**
     * Create a touch-friendly button
     */
    private static ImageButton createTouchButton(String text, String tooltip, Runnable action) {
        Label buttonLabel = new Label(text, new Label.LabelStyle(
            new com.badlogic.gdx.graphics.g2d.BitmapFont(),
            Color.WHITE
        ));
        buttonLabel.setFontScale(2f);
        
        ImageButton button = new ImageButton(new TextureRegionDrawable());
        button.add(buttonLabel);
        
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.run();
                updateStatus("Toggled: " + tooltip);
            }
        });
        
        return button;
    }
    
    /**
     * Create a cheat toggle button
     */
    private static ImageButton createCheatButton(String text, String cheatType) {
        Label buttonLabel = new Label(text + " [OFF]", new Label.LabelStyle(
            new com.badlogic.gdx.graphics.g2d.BitmapFont(),
            Color.RED
        ));
        buttonLabel.setFontScale(1.8f);
        
        ImageButton button = new ImageButton(new TextureRegionDrawable());
        button.add(buttonLabel);
        
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toggleCheat(cheatType);
                
                // Update button appearance
                boolean isOn = getCheatState(cheatType);
                buttonLabel.setColor(isOn ? Color.GREEN : Color.RED);
                String[] parts = text.split(" ");
                String newText = parts[0] + " " + parts[1] + " " + parts[2] + " [" + (isOn ? "ON" : "OFF") + "]";
                buttonLabel.setText(newText);
            }
        });
        
        return button;
    }
    
    /**
     * Setup mobile-specific input handling
     */
    private static void setupMobileInput() {
        // Track double taps for quick aimbot
        // This would be implemented in the game screen's touch handling
    }
    
    /**
     * Update mobile cheat system
     */
    public static void update(float delta, Player player) {
        if (uiStage != null) {
            uiStage.act(delta);
        }
        
        // Update mobile aimbot if enabled
        if (autoAimbot && player != null) {
            updateMobileAimbot(player, delta);
        }
        
        // Update button states
        updateButtonStates();
    }
    
    /**
     * Mobile-specific aimbot with touch optimization
     */
    private static void updateMobileAimbot(Player player, float delta) {
        GameEntity target = findNearestEnemyMobile(player, mobileAimbotRange);
        
        if (target != null) {
            // Smooth aim towards target
            Vector3 playerPos = player.getPosition();
            Vector3 targetPos = target.getPosition();
            
            Vector3 direction = targetPos.sub(playerPos).nor();
            Vector3 currentAim = player.getLookDirection();
            
            // Smooth interpolation for mobile
            float lerpFactor = Math.min(delta * (1f / mobileAimbotSmoothness), 1f);
            Vector3 newAim = currentAim.lerp(direction, lerpFactor);
            
            player.setLookDirection(newAim);
            currentMobileTarget = target;
            
            // Auto-fire if target is close enough
            float distance = playerPos.dst(targetPos);
            if (distance < mobileAimbotRange * 0.5f) {
                // Trigger auto-fire (this would integrate with weapon system)
                Gdx.app.log("MobileAimbot", "Auto-firing at target: " + target.getName());
            }
        } else {
            currentMobileTarget = null;
        }
    }
    
    /**
     * Find nearest enemy optimized for mobile
     */
    private static GameEntity findNearestEnemyMobile(Player player, float maxDistance) {
        // This would integrate with the game's entity system
        // For now, return null - this would be implemented based on the actual game world
        return null;
    }
    
    /**
     * Handle mobile touch gestures for cheats
     */
    public static boolean handleTouch(float x, float y, int pointer, int button) {
        Vector3 touchPos = new Vector3(x, y, 0);
        
        // Double-tap detection for aimbot toggle
        float currentTime = System.currentTimeMillis() / 1000f;
        if (touchPos.equals(lastTouchPosition)) {
            if (currentTime - lastTouchTime < DOUBLE_TAP_THRESHOLD) {
                tapCount++;
                if (tapCount == 2) {
                    toggleAutoAimbot();
                    tapCount = 0;
                    return true;
                }
            } else {
                tapCount = 1;
            }
        }
        
        lastTouchTime = currentTime;
        lastTouchPosition.set(touchPos);
        
        return false;
    }
    
    /**
     * Toggle mobile menu visibility
     */
    public static void toggleMobileMenu() {
        mobileMenuVisible = !mobileMenuVisible;
        mainMenuTable.setVisible(mobileMenuVisible);
        updateStatus(mobileMenuVisible ? "Menu Opened" : "Menu Closed");
    }
    
    /**
     * Toggle a specific cheat
     */
    private static void toggleCheat(String cheatType) {
        boolean newState = false;
        
        switch (cheatType) {
            case "godMode":
                godMode = !godMode;
                newState = godMode;
                break;
            case "unlimitedAmmo":
                unlimitedAmmo = !unlimitedAmmo;
                newState = unlimitedAmmo;
                break;
            case "speedHack":
                speedHack = !speedHack;
                newState = speedHack;
                speedMultiplier = speedHack ? 2.0f : 1.0f;
                break;
            case "noRecoil":
                noRecoil = !noRecoil;
                newState = noRecoil;
                break;
            case "fastReload":
                fastReload = !fastReload;
                newState = fastReload;
                break;
            case "aimbot":
                aimbot = !aimbot;
                newState = aimbot;
                break;
            case "teleporter":
                teleporter = !teleporter;
                newState = teleporter;
                break;
            case "wallHack":
                wallHack = !wallHack;
                newState = wallHack;
                break;
        }
        
        // Also sync with main CheatSystem
        syncWithMainCheatSystem(cheatType, newState);
    }
    
    /**
     * Sync mobile cheats with main CheatSystem
     */
    private static void syncWithMainCheatSystem(String cheatType, boolean newState) {
        try {
            // Use reflection to sync with main CheatSystem
            java.lang.reflect.Field field = CheatSystem.class.getField(cheatType);
            field.set(null, newState);
        } catch (Exception e) {
            Gdx.app.error("MobileCheatSystem", "Failed to sync cheat: " + cheatType, e);
        }
    }
    
    /**
     * Get cheat state
     */
    private static boolean getCheatState(String cheatType) {
        try {
            java.lang.reflect.Field field = MobileCheatSystem.class.getField(cheatType);
            return field.getBoolean(null);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Toggle auto aimbot mode
     */
    private static void toggleAutoAimbot() {
        autoAimbot = !autoAimbot;
        if (autoAimbot) {
            aimbot = true;
            syncWithMainCheatSystem("aimbot", true);
            updateStatus("Auto Aimbot: ON");
        } else {
            updateStatus("Auto Aimbot: OFF");
        }
    }
    
    /**
     * Update button states based on cheat states
     */
    private static void updateButtonStates() {
        if (aimbotButton != null) {
            // Update aimbot button appearance based on state
            if (aimbot) {
                aimbotButton.setColor(Color.GREEN);
            } else {
                aimbotButton.setColor(Color.WHITE);
            }
        }
    }
    
    /**
     * Update status message
     */
    private static void updateStatus(String message) {
        if (statusLabel != null) {
            statusLabel.setText(message);
            // Auto-clear after 3 seconds
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    Gdx.app.postRunnable(() -> {
                        if (statusLabel != null) {
                            statusLabel.setText("Cheats Ready");
                        }
                    });
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
    
    /**
     * Get mobile UI Stage
     */
    public static Stage getUiStage() {
        return uiStage;
    }
    
    /**
     * Render mobile cheat UI
     */
    public static void render(SpriteBatch batch) {
        if (uiStage != null && mobileMenuVisible) {
            uiStage.draw();
        }
    }
    
    /**
     * Dispose mobile UI
     */
    public static void dispose() {
        if (uiStage != null) {
            uiStage.dispose();
            uiStage = null;
        }
    }
}