package io.github.necrashter.natural_revenge;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.github.necrashter.natural_revenge.world.levels.Level1Swamp;
import io.github.necrashter.natural_revenge.world.levels.Level2Flying;
import io.github.necrashter.natural_revenge.world.levels.LevelBossRush;
import io.github.necrashter.natural_revenge.world.player.EnumeratingRoller;
import io.github.necrashter.natural_revenge.world.player.RandomRoller;

public class Main extends Game {
    public static boolean debugMode = false;
    public static AssetManager2 assets;
    public static RandomRoller randomRoller;
    public static MusicManager music;
    public static Preferences preferences;
    public static float sfxVolume = 1.0f;
    public static boolean invertMouseY = false;
    public static float mouseSensitivity = 1.0f; // defaults
    public static float fov = 90f; // default FOV
    private final PostInit postInit;
    public static Skin skin;
    public static Skin skin2;
    
    // Low-end device optimizations
    public static boolean isLowEndDevice = false;
    public static int targetFPS = 60;
    public static float renderScale = 1.0f;
    public static boolean useLowQuality = false;
    private long lastFrameTime = 0; // For FPS limiting

    public abstract static class PostInit {
        public abstract void run(Main main);
    }

    TextureAtlas skinAtlas;

    public Main(PostInit postInit) {
        super();
        this.postInit = postInit;
    }

    private static float uiScale = 1.0f;
    public static ScreenViewport viewport;
    public static ScreenViewport createViewport() {
        viewport = new ScreenViewport();
        viewport.setUnitsPerPixel(1.0f/uiScale);
        return viewport;
    }

    public static float getUiScale() {
        return uiScale;
    }

    public static void setUiScale(float uiScale) {
        if (viewport != null) {
            viewport.setUnitsPerPixel(1.0f/uiScale);
        }
        Main.uiScale = uiScale;
    }

    public static void loadPreferences() {
        preferences = Gdx.app.getPreferences("Preferences");
        Main.sfxVolume = preferences.getFloat("sfxVolume", Main.sfxVolume);
        Main.music.setVolume(preferences.getFloat("musicVolume", Main.music.getVolume()));
        Main.invertMouseY = preferences.getBoolean("invertMouseY", Main.invertMouseY);
        Main.mouseSensitivity = preferences.getFloat("mouseSensitivity", Main.mouseSensitivity);
        Main.fov = preferences.getFloat("fov", Main.fov);
    }

    @Override
    public void create () {
        skinAtlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
        skin = new Skin();
        skin.add("default-font", new BitmapFont(Gdx.files.internal("ui/mono.fnt")), BitmapFont.class);
        skin.addRegions(skinAtlas);
        skin.load(Gdx.files.internal("uiskin.json"));
        skin2 = new Skin(Gdx.files.internal("biological-attack/biological-attack-ui.json"));

        assets = new AssetManager2();
        music = new MusicManager();
        randomRoller = new RandomRoller();
        
        // Initialize cheat system
        CheatSystem.initialize();
        
        // Detect low-end device and apply optimizations
        detectLowEndDevice();
        applyLowEndOptimizations();
        
        loadPreferences();
        if (Main.isMobile()) {
            uiScale = MathUtils.clamp(((Gdx.graphics.getDensity() / .5783681f) - 1.0f) / 2f + 1f, .5f, 2f);
        }
        // loading
        while (!assets.update());
        assets.done();

        if (postInit != null) {
            postInit.run(this);
        } else {
            this.setScreen(new MenuScreen(this));
        }
    }

    @Override
    public void render () {
        float delta = Gdx.graphics.getDeltaTime();
        music.update(delta);
        
        if (screen != null) screen.render(delta);
    }

    @Override
    public void dispose () {
        skin.dispose();
        skinAtlas.dispose();
        assets.dispose();
    }

    public static boolean isMobile() {
        return Gdx.app.getType().equals(Application.ApplicationType.Android) || Gdx.app.getType().equals(Application.ApplicationType.iOS);
    }

    public Screen getLevel(int level, float easiness) {
        switch (level) {
            case 1: return new GameScreen(this, new Level1Swamp(this, 1, easiness));
            case 2: return new GameScreen(this, new Level2Flying(this, 2, easiness));
            case 3: return new GameScreen(this, new LevelBossRush(this, 3, easiness));
            default: return new MenuScreen(this);
        }
    }

    public static String float2Decimals(float f) {
        int decimal = MathUtils.round((f - (int) f) * 100f);
        if (decimal < 10) {
            return (int) f + ".0" + decimal;
        } else {
            return String.valueOf((int)f) + '.' + decimal;
        }
    }
    public static String float1Decimal(float f) {
        int decimal = MathUtils.round((f - (int) f) * 10f);
        return String.valueOf((int)f) + '.' + decimal;
    }
    
    /**
     * Detect if this is a low-end device based on hardware specs
     */
    private void detectLowEndDevice() {
        if (!isMobile()) return;
        
        // Get device information safely
        String manufacturer = "unknown";
        String model = "unknown";
        String product = "unknown";
        
        if (Gdx.app.getType() == com.badlogic.gdx.Application.ApplicationType.Android) {
            try {
                Class<?> buildClass = Class.forName("android.os.Build");
                manufacturer = ((String) buildClass.getField("MANUFACTURER").get(null)).toLowerCase();
                model = ((String) buildClass.getField("MODEL").get(null)).toLowerCase();
                product = ((String) buildClass.getField("PRODUCT").get(null)).toLowerCase();
            } catch (Exception e) {
                // Safe fallback - use memory-based detection
                long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);
                manufacturer = maxMemory <= 768 ? "huawei" : "unknown";
                model = maxMemory <= 768 ? "kob2" : "unknown";
                product = "unknown";
            }
        } else {
            // Desktop fallback - use memory-based detection for low-end identification
            long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);
            if (maxMemory <= 768) {
                manufacturer = "huawei"; // Assume for low memory devices
                model = "kob2"; // Assume for testing
                product = "unknown";
            }
        }
        
        // Known low-end device patterns
        boolean isLowEndPattern = manufacturer.contains("huawei") && model.contains("kob2") ||
                                 manufacturer.contains("huawei") && (model.contains("t") || model.contains("lite")) ||
                                 manufacturer.contains("xiaomi") && model.contains("redmi") ||
                                 manufacturer.contains("samsung") && model.contains("a") ||
                                 model.contains("mt6765") || model.contains("mt6739") || model.contains("mt6580");
        
        // Check if device has limited memory or other limiting factors
        boolean limitedMemory = java.lang.Runtime.getRuntime().maxMemory() < 128 * 1024 * 1024; // < 128MB
        boolean smallScreen = Gdx.graphics.getWidth() < 1000;
        
        // Only consider extremely limited memory devices as low-end
        long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);
        isLowEndDevice = maxMemory <= 256; // Only truly low-memory devices
        
        Gdx.app.log("Frogue", "Low-end device detected: " + isLowEndDevice);
        if (isLowEndDevice) {
            Gdx.app.log("Frogue", "Manufacturer: " + manufacturer + ", Model: " + model + ", MaxMemory: " + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + "MB");
        }
    }
    
    /**
     * Apply performance optimizations for low-end devices
     */
    private void applyLowEndOptimizations() {
        if (!isLowEndDevice) return;
        
        // For Huawei MatePad T and similar devices
        boolean isHuaweiMatePadT = false;
        if (Gdx.app.getType() == com.badlogic.gdx.Application.ApplicationType.Android) {
            try {
                String model = (String) Class.forName("android.os.Build").getField("MODEL").get(null);
                isHuaweiMatePadT = model.toLowerCase().contains("kob2");
            } catch (Exception e) {
                // Safe fallback - use memory-based detection
                long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);
                isHuaweiMatePadT = maxMemory <= 768; // Most Huawei MatePad T devices have ~512MB heap
            }
        } else {
            // Desktop fallback - use memory-based detection
            long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);
            isHuaweiMatePadT = maxMemory <= 768;
        }
        
        if (isHuaweiMatePadT) {
            // Minimal optimizations - only essential ones
            useLowQuality = true; // Only this optimization - reduces texture quality
            
            Gdx.app.log("Frogue", "Applied minimal Huawei MatePad T optimizations - texture quality reduction only");
        } else {
            // Keep default settings for other devices
            renderScale = 1.0f;
            targetFPS = 60;
            useLowQuality = false;
        }
    }
}
