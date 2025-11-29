# Frogue Enhanced Cheat System Documentation

## Overview

The Frogue Enhanced Cheat System is a comprehensive modification system that adds various gameplay enhancements, debugging features, and an advanced aimbot to the Frogue FPS game. The system features both keyboard controls and an interactive visual menu for seamless cheat management.

## Legal Notice

This cheat system is created for educational and entertainment purposes using the game's open-source MIT license. The original Frogue game is released under the MIT License, which explicitly permits modification and distribution of the code.

## 🆕 What's New

### Added Features
- **Aimbot System** - Automatic targeting and shooting
- **Visual Menu** - Interactive cheat management interface
- **Enhanced Controls** - Improved key bindings and navigation

### Removed Features
- **Instant Kill** - Removed from cheat selection
- **No Gravity** - Removed from cheat selection

## Features

### Core Cheat Categories

1. **Player Enhancement Cheats**
   - God Mode (Infinite Health)
   - Speed Hacks
   - Jump Enhancement

2. **Combat Enhancement Cheats**
   - Unlimited Ammo
   - No Recoil
   - 🆕 Aimbot (Auto-aim and fire)
   - Fast Reload

3. **Utility Cheats**
   - Teleporter
   - Wall Hack
   - All Weapons
   - Max Ammo

4. **System Cheats**
   - 🆕 Visual Menu System
   - Demo Mode
   - Reset Functions
   - Debug Information

### 🆕 Aimbot System
- **Automatic targeting** of nearest enemies within range
- **Smooth aim correction** for realistic targeting behavior
- **Auto-fire functionality** with cooldown management
- **Configurable range** (200 units) and smoothness settings
- **Real-time targeting** with visual feedback

### 🆕 Visual Menu System
- **Interactive cheat menu** (Press F11 to toggle)
- **Arrow key navigation** (UP/DOWN to move selection)
- **Toggle cheats with ENTER/SPACE**
- **Real-time status display** showing ON/OFF states
- **Visual feedback** with selection highlighting (>> indicator)
- **ESC to close** menu quickly

## Controls Reference

### Global Controls
| Key | Function |
|-----|----------|
| F2 | Toggle Debug Mode |
| F3 | Toggle All Cheats |
| F4 | Show Cheat Help |
| F11 | Show/Hide Cheat Menu |

### Cheat Toggles
| Key | Cheat | Description |
|-----|-------|-------------|
| F5 | God Mode | Infinite health and invincibility |
| F6 | Unlimited Ammo | Weapons never run out of ammo |
| F7 | Speed Hack | Enhanced movement speed |
| F8 | No Recoil | Eliminates weapon recoil |
| F9 | 🆕 Aimbot | Automatic targeting and firing |
| F10 | Fast Reload | Very rapid weapon reloading |
| Num 7 | Wall Hack | Toggle wall visibility |

### 🆕 Menu Controls (F11)
| Key | Function |
|-----|----------|
| UP | Navigate up in menu |
| DOWN | Navigate down in menu |
| ENTER/SPACE | Toggle selected cheat |
| ESC | Close menu |

### Quick Actions
| Key | Action | Description |
|-----|--------|-------------|
| Num 1 | Speed Toggle | Switch between 1x and 2x speed |
| Num 2 | Jump Toggle | Switch between 1x and 2x jump height |
| Num 3 | Teleport | Move to next teleport location |
| Num 4 | Heal | Restore player to full health |
| Num 5 | All Weapons | Give player all available weapons |
| Num 6 | Max Ammo | Refill all weapon ammunition |
| Num 7 | Wall Hack | Toggle wall visibility |
| Num 8 | Reset | Reset all cheats to defaults |
| Num 9 | 🆕 Kill Nearest | Eliminate nearest enemy (requires Aimbot) |
| Num 0 | Demo Mode | Enable all cheats with max settings |

## Detailed Cheat Descriptions

### 1. God Mode (F5)
- **Effect**: Makes player completely invincible
- **Details**: 
  - Health is automatically restored to maximum
  - No damage is taken from any source
  - Works in conjunction with infinite health
- **Usage**: Press F5 to toggle on/off

### 2. Unlimited Ammo (F6)
- **Effect**: Weapons never run out of ammunition
- **Details**:
  - Current clip is automatically refilled
  - Can be combined with Max Ammo (Num 6) for full effect
  - Affects all weapons in inventory
- **Usage**: Press F6 to toggle on/off

### 3. Speed Hack (F7)
- **Effect**: Significantly increases player movement speed
- **Details**:
  - Can be combined with Num 1 for even faster movement
  - Affects both walking and strafing speed
  - Works with all movement directions
- **Usage**: Press F7 to toggle on/off, then use Num 1 to adjust multiplier

### 4. No Recoil (F8)
- **Effect**: Eliminates weapon recoil and kickback
- **Details**:
  - Weapons stay steady when firing
  - Improves accuracy significantly
  - Works with all weapon types
- **Usage**: Press F8 to toggle on/off

### 5. 🆕 Aimbot (F9)
- **Effect**: Automatically aims and fires at enemies
- **Details**:
  - Finds nearest enemy within 200 units
  - Smoothly adjusts aim towards target
  - Automatically fires when in range
  - Includes cooldown to prevent rapid-fire
  - Can be combined with Num 9 for instant kill
- **Usage**: Press F9 to toggle on/off
- **Advanced**: Works best with No Recoil for maximum accuracy

### 6. Fast Reload (F10)
- **Effect**: Dramatically reduces weapon reload time
- **Details**:
  - Reload speed increased to 10x normal
  - Makes reloading almost instantaneous
  - Works with all reloadable weapons
- **Usage**: Press F10 to toggle on/off

### 7. Teleporter (Num 3)
- **Effect**: Enable teleportation to predefined locations
- **Details**:
  - Multiple teleport locations available
  - Cycle through 5 different positions
  - Useful for navigation and exploration
- **Usage**: Press F11 to enable teleporter, then use Num 3 to teleport

### 8. Wall Hack (Num 7)
- **Effect**: Toggle wall visibility
- **Details**:
  - Visual enhancement for debugging
  - Shows enemy positions through walls
  - Useful for understanding enemy behavior
- **Usage**: Press Num 7 to toggle on/off

## 🆕 Menu System Usage

### Opening the Menu
1. Press **F11** to show/hide the cheat menu
2. Menu appears on the left side of the screen
3. Shows all available cheats with current status

### Navigation
- **UP/DOWN arrows**: Navigate through menu items
- **ENTER or SPACE**: Toggle selected cheat
- **ESC**: Close menu quickly

### Menu Display
```
=== FROGUE CHEAT MENU ===

>> God Mode [ON]
   Unlimited Ammo [OFF]
   Speed Hack [OFF]
   No Recoil [OFF]
   Fast Reload [OFF]
   Aimbot [OFF]
   Teleporter [OFF]
   Wall Hack [OFF]

Navigation: UP/DOWN | Toggle: ENTER/SPACE | Close: ESC
```

### Status Indicators
- **>>** indicates currently selected item
- **[ON]/[OFF]** shows cheat status
- Real-time updates when toggling

## Aimbot Technical Details

### How It Works
1. **Target Acquisition**: Scans for nearest enemy within 200-unit range
2. **Aim Calculation**: Computes direction vector to target
3. **Smooth Aiming**: Applies gradual aim correction for realism
4. **Auto-Fire**: Automatically fires weapon when target is in range
5. **Cooldown Management**: Limits firing rate to prevent excessive spam

### Configuration Options
- **Range**: 200 units (configurable in code)
- **Smoothness**: 0.1 factor (adjustable)
- **Cooldown**: 0.1 seconds between shots
- **Target Selection**: Nearest enemy algorithm

### Best Practices
- Enable **No Recoil** for maximum accuracy
- Use with **Unlimited Ammo** for continuous operation
- Combine with **Speed Hack** for tactical positioning
- Works best in open areas with clear sight lines

## Implementation Details

### File Structure
```
core/src/main/java/io/github/necrashter/natural_revenge/
├── CheatSystem.java          # Enhanced cheat system with aimbot and menu
├── Main.java                 # Game initialization (modified)
├── GameScreen.java          # Game loop integration (modified)
└── world/player/Player.java # Player mechanics integration
```

### Integration Points
1. **Main.java**: Initializes enhanced cheat system on game startup
2. **GameScreen.java**: Updates cheats and renders menu UI every frame
3. **Player.java**: Applies health, movement, and physics cheats
4. **Firearm.java**: Handles weapon-related cheats (ammo, recoil, reload)
5. **Aimbot system**: Integrated with Player class for targeting and firing

### Performance Impact
- **Minimal overhead** for most cheats
- **Aimbot processing** only when enabled
- **Menu rendering** only when visible
- **Efficient targeting** algorithm with range limiting

## Testing Guide

### Prerequisites
- Frogue game source code with enhanced cheat system
- Java Development Kit (JDK)
- Gradle build system

### Build Process
```bash
# Navigate to game directory
cd frogue

# Build the game with enhanced cheats
./gradlew lwjgl3:build

# Run in debug mode
./gradlew lwjgl3:run --args debug
```

### Test Procedure
1. Start the game with debug mode enabled
2. Press **F11** to open the cheat menu
3. Navigate with UP/DOWN arrows
4. Toggle cheats with ENTER/SPACE
5. Test aimbot:
   - Press F9 to enable aimbot
   - Look at enemies to see automatic targeting
   - Verify smooth aim correction
   - Test auto-fire functionality
6. Use keyboard shortcuts for quick access
7. Press ESC to close menu

### Expected Results
- **God Mode**: Player takes no damage
- **Speed Hack**: Faster movement
- **Unlimited Ammo**: No ammo depletion
- **Aimbot**: Automatic targeting and firing
- **Menu System**: Interactive interface with real-time updates
- All cheats provide immediate visual feedback

## Troubleshooting

### Common Issues
1. **Aimbot not working**: Ensure enemies are within 200-unit range
2. **Menu not appearing**: Check that debug mode is enabled (F2)
3. **Controls not responding**: Verify key bindings and debug mode
4. **Performance issues**: Disable aimbot or reduce range
5. **No targeting**: Check line of sight and enemy visibility

### Aimbot Debugging
- Check console for target acquisition messages
- Verify enemy detection range
- Ensure weapon is equipped
- Test with No Recoil enabled for better results

### Menu System
- Use F11 to toggle menu visibility
- Arrow keys navigate, ENTER/SPACE toggles
- ESC closes menu immediately
- Status updates in real-time

## Advanced Usage

### Customization
The enhanced cheat system can be easily modified:

#### Adjusting Aimbot Settings
```java
// In CheatSystem.java
private static float aimbotRange = 200f;        // Detection range
private static float aimbotSmoothness = 0.1f;   // Aim smoothing
private static final float AIMBOT_COOLDOWN = 0.1f; // Fire rate limit
```

#### Adding New Cheats
1. Add boolean flag in CheatSystem class
2. Add to menuItems array
3. Implement toggle logic in handleCheatKeys()
4. Add application logic in applyCheats()
5. Update menu rendering

#### Menu Customization
- Modify menuItems array to add/remove options
- Adjust menu layout in renderMenu() method
- Change navigation key bindings
- Customize visual appearance

### Integration with Other Mods
The enhanced system is designed for compatibility:
- **Modular architecture** allows easy integration
- **Clear separation** between cheat types
- **Event-driven menu** system
- **Minimal global namespace** usage

## Legal and Ethical Considerations

### Educational Purpose
This enhanced cheat system demonstrates:
- **Game modification techniques**
- **Real-time targeting algorithms**
- **UI integration with game engines**
- **Performance optimization strategies**

### Respectful Usage
- Use in **single-player mode** only
- **Don't use** in competitive multiplayer
- **Respect** other players' experiences
- **Follow** game's terms of service

### Open Source Compliance
The system maintains MIT License compliance:
- **Original code** remains under MIT License
- **Modifications** are clearly documented
- **Attribution** is maintained
- **Free redistribution** is permitted

## Comparison: Original vs Enhanced

| Feature | Original System | Enhanced System |
|---------|----------------|-----------------|
| Cheat Count | 10 cheats | 8 cheats (improved quality) |
| Aimbot | ❌ None | ✅ Advanced targeting |
| Menu System | ❌ Console only | ✅ Interactive visual menu |
| Navigation | ❌ Key combinations | ✅ Arrow key navigation |
| Feedback | ❌ Text only | ✅ Real-time visual status |
| Controls | ❌ Complex combos | ✅ Simplified with menu |
| Performance | ❌ Basic | ✅ Optimized targeting |

## Conclusion

The Frogue Enhanced Cheat System represents a significant upgrade from the original implementation. The addition of the **aimbot system** and **visual menu interface** provides both powerful functionality and user-friendly operation.

Key improvements include:
- **Professional-grade aimbot** with smooth targeting
- **Intuitive menu system** for easy cheat management
- **Real-time visual feedback** for all operations
- **Enhanced user experience** with simplified controls
- **Optimized performance** with intelligent processing

The system serves as an excellent demonstration of:
- **Game modification techniques**
- **Real-time algorithm implementation**
- **UI integration with game engines**
- **Performance optimization strategies**

Whether used for learning, experimentation, or entertainment, the enhanced cheat system provides a comprehensive and accessible framework for game modification within the bounds of the MIT License.

---

*Last Updated: 2025-11-29*
*Version: 2.0 Enhanced*
*Compatible with: Frogue GitHub Repository (https://github.com/necrashter/frogue)*