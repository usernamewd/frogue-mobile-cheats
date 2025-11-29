# Frogue Mobile Cheat System Documentation

## Overview

The Frogue Mobile Cheat System is a touch-optimized version of the original cheat system, designed specifically for mobile gameplay. It provides the same powerful cheats and aimbot functionality through intuitive touch controls and mobile-friendly UI elements.

## 🆕 Mobile Features

### Touch-Optimized Interface
- **Large Touch Targets**: All buttons are sized for easy mobile interaction
- **Floating Menu System**: Non-intrusive cheat management overlay
- **Gesture Controls**: Double-tap and swipe gestures for quick cheat activation
- **Visual Feedback**: Color-coded status indicators and real-time updates

### Mobile-Specific Enhancements
- **Reduced Aimbot Range**: Optimized for mobile screen sizes (150 units vs 200)
- **Smoother Aimbot**: Enhanced interpolation for mobile precision (0.2f vs 0.1f)
- **Auto-Aimbot Mode**: Continuous automatic targeting with touch-friendly controls
- **One-Handed Operation**: Most features accessible with thumb reach

## 🕹️ Mobile Controls

### Floating Action Buttons
| Button | Position | Function | Description |
|--------|----------|----------|-------------|
| **Menu** | Bottom Left | ≡ | Toggle cheat menu overlay |
| **Aimbot** | Bottom Right | 🎯 | Quick aimbot toggle |

### Cheat Menu (Tap Menu Button)
| Action | Gesture | Function |
|--------|---------|----------|
| **Open Menu** | Tap Menu Button (≡) | Opens cheat selection overlay |
| **Close Menu** | Tap Close Button | Returns to gameplay |
| **Toggle Cheat** | Tap Cheat Button | Enables/disables specific cheat |
| **Scroll Menu** | Swipe Up/Down | Navigate through cheat list |

### Gesture Controls
| Gesture | Action | Description |
|---------|--------|-------------|
| **Double-Tap Screen** | Toggle Auto-Aimbot | Quick aimbot activation |
| **Tap Aimbot Button** | Toggle Aimbot | Standard aimbot on/off |
| **Long Press** | Future Feature | Will open advanced options |

### Touch Shortcuts
| Key | Mobile Equivalent | Function |
|-----|------------------|----------|
| F11 | Menu Button (≡) | Toggle cheat menu |
| F9 | Aimbot Button (🎯) | Toggle aimbot |
| F5 | Menu → God Mode | Enable god mode |
| F6 | Menu → Unlimited Ammo | Infinite ammunition |
| F7 | Menu → Speed Hack | Enhanced movement |
| F8 | Menu → No Recoil | Eliminate recoil |
| F10 | Menu → Fast Reload | Rapid reloading |
| Num 3 | Menu → Teleporter | Quick transport |

## 🎮 Available Mobile Cheats

### Core Cheats
1. **🛡️ God Mode** - Infinite health and invincibility
2. **🔫 Unlimited Ammo** - Weapons never run out of ammunition
3. **⚡ Speed Hack** - Enhanced movement speed (2x)
4. **🎯 No Recoil** - Eliminates weapon recoil
5. **🔄 Fast Reload** - Very rapid weapon reloading
6. **🚪 Teleporter** - Quick location changes
7. **👁️ Wall Hack** - See through walls

### Mobile Aimbot System
- **Automatic Targeting**: Detects nearest enemies within 150 units
- **Smooth Tracking**: Mathematical interpolation for realistic aiming
- **Auto-Fire**: Automatically shoots when target is in range
- **Visual Feedback**: Button color changes to indicate active state
- **Gesture Activation**: Double-tap anywhere to toggle

## 📱 Mobile UI Components

### Main Menu Interface
- **Semi-transparent overlay** - Doesn't obscure gameplay
- **Large button layout** - Easy thumb access
- **Real-time status** - Shows ON/OFF state for each cheat
- **Smooth animations** - Professional mobile feel

### Status Indicators
- **Green Buttons**: Cheat is enabled
- **Red Buttons**: Cheat is disabled  
- **Status Bar**: Bottom left shows current action
- **Auto-clear**: Status messages disappear after 3 seconds

### Button Design
- **High Contrast**: Easy to see in all lighting conditions
- **Large Touch Areas**: Minimum 60px for reliable touch
- **Icon + Text**: Visual and textual identification
- **Consistent Spacing**: Organized grid layout

## 🔧 Technical Implementation

### Mobile Detection
```java
if (Main.isMobile()) {
    MobileCheatSystem.initializeMobile(stage);
}
```

### Touch Input Integration
```java
InputMultiplexer inputMultiplexer = new InputMultiplexer(
    stage, 
    world.player.inputAdapter, 
    createMobileCheatInputListener()
);
```

### Mobile Aimbot Logic
```java
private static void updateMobileAimbot(Player player, float delta) {
    GameEntity target = findNearestEnemyMobile(player, mobileAimbotRange);
    if (target != null) {
        // Smooth interpolation optimized for mobile
        float lerpFactor = Math.min(delta * (1f / mobileAimbotSmoothness), 1f);
        Vector3 newAim = currentAim.lerp(direction, lerpFactor);
        player.setLookDirection(newAim);
    }
}
```

### Gesture Detection
```java
public static boolean handleTouch(float x, float y, int pointer, int button) {
    // Double-tap detection for auto-aimbot
    if (isDoubleTap(x, y)) {
        toggleAutoAimbot();
        return true;
    }
    return false;
}
```

## 🚀 Usage Instructions

### Quick Start (Mobile)
1. **Start Game**: Launch Frogue on mobile device
2. **Enable Aimbot**: Double-tap anywhere on screen
3. **Open Menu**: Tap menu button (≡) in bottom left
4. **Toggle Cheats**: Tap any cheat button to enable/disable
5. **Play**: Enjoy enhanced mobile gaming experience

### Advanced Usage
1. **Combine Cheats**: Enable multiple cheats simultaneously
2. **Aimbot + God Mode**: Ultimate mobile gaming experience
3. **Speed Hack**: Navigate levels quickly
4. **Wall Hack**: Plan strategies effectively

### Performance Tips
- **Single Aimbot**: Don't enable multiple targeting cheats simultaneously
- **Menu Management**: Close menu when not actively changing settings
- **Battery Optimization**: Disable unused cheats to preserve battery

## 🔄 Synchronization

The mobile cheat system synchronizes with the main CheatSystem to ensure:
- **Consistent States**: Both systems show the same cheat status
- **Unified Behavior**: Cheats work identically on mobile and desktop
- **Shared Resources**: No duplicate processing or conflicts

### Sync Example
```java
private static void syncWithMainCheatSystem(String cheatType, boolean newState) {
    java.lang.reflect.Field field = CheatSystem.class.getField(cheatType);
    field.set(null, newState);
}
```

## 🛠️ Troubleshooting

### Common Issues
1. **Menu Not Opening**: Check mobile detection in GameScreen
2. **Buttons Unresponsive**: Ensure stage input is properly configured
3. **Aimbot Not Working**: Verify touch input listener registration
4. **Performance Issues**: Disable unused cheats

### Debug Steps
1. Check log output: Look for "Mobile cheat system initialized"
2. Verify input: Confirm touch events are being received
3. Test gestures: Double-tap should trigger aimbot
4. Check UI: Menu should appear when tapping (≡) button

## 📋 Mobile vs Desktop Comparison

| Feature | Mobile | Desktop |
|---------|--------|---------|
| **Menu Access** | Touch Button | F11 Key |
| **Aimbot Toggle** | Touch Button / Double-tap | F9 Key |
| **Cheat Selection** | Touch UI | Keyboard Shortcuts |
| **Aimbot Range** | 150 units | 200 units |
| **Aimbot Smoothness** | 0.2f (smoother) | 0.1f |
| **UI Style** | Touch-optimized overlay | Text-based menu |
| **Gesture Support** | ✅ Double-tap | ❌ Keyboard only |

## 🎯 Best Practices for Mobile Gaming

### Optimal Settings
1. **Enable Auto-Aimbot** for easier gameplay
2. **Use Speed Hack** for faster level navigation
3. **Enable God Mode** for exploration without fear
4. **Keep Menu Closed** during active gameplay

### Battery Conservation
1. **Disable Unused Cheats**: Turn off cheats not actively used
2. **Close Menu**: Don't leave cheat menu open unnecessarily
3. **Efficient Aimbot**: Use single targeting system at a time

### Performance Optimization
1. **Monitor FPS**: Watch for performance impact
2. **Manage Resources**: Dispose mobile UI when not needed
3. **Smooth Operation**: Use mobile-optimized aimbot settings

## 🔮 Future Mobile Enhancements

### Planned Features
- **Swipe Gestures**: Swipe up/down for quick cheat cycling
- **Voice Commands**: "Enable aimbot" voice activation
- **Smart Auto-Enable**: Automatically enable cheats based on game context
- **Custom Button Layout**: Drag and drop button positioning
- **Advanced Aimbot**: Predictive targeting for moving enemies

### Enhancement Ideas
- **Adaptive UI**: UI that adjusts to screen orientation
- **Contextual Menus**: Cheats that appear based on current game situation
- **Performance Monitoring**: Built-in FPS and battery usage display
- **Social Features**: Share cheat configurations with friends

---

**Note**: This mobile cheat system is designed for educational and entertainment purposes. Use responsibly and in accordance with the game's terms of service and any applicable regulations.