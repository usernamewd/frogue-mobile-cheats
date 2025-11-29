# Frogue Cheat System Documentation

## Overview

The Frogue Cheat System is a comprehensive modification system that adds various gameplay enhancements and debugging features to the Frogue FPS game. The system is fully integrated with the game's existing architecture and provides both subtle and dramatic gameplay modifications.

## Legal Notice

This cheat system is created for educational and entertainment purposes using the game's open-source MIT license. The original Frogue game is released under the MIT License, which explicitly permits modification and distribution of the code.

## Features

### Core Cheat Categories

1. **Player Enhancement Cheats**
   - God Mode (Infinite Health)
   - Speed Hacks
   - Jump Enhancement
   - No Gravity

2. **Combat Enhancement Cheats**
   - Unlimited Ammo
   - No Recoil
   - Instant Kill
   - Fast Reload

3. **Utility Cheats**
   - Teleporter
   - Wall Hack
   - All Weapons
   - Max Ammo

4. **System Cheats**
   - Demo Mode
   - Reset Functions
   - Debug Information

## Controls Reference

### Global Controls
| Key | Function |
|-----|----------|
| F2 | Toggle Debug Mode |
| F3 | Toggle All Cheats |
| F4 | Show Cheat Help |

### Cheat Toggles
| Key | Cheat | Description |
|-----|-------|-------------|
| F5 | God Mode | Infinite health and invincibility |
| F6 | Unlimited Ammo | Weapons never run out of ammo |
| F7 | Speed Hack | Enhanced movement speed |
| F8 | No Recoil | Eliminates weapon recoil |
| F9 | Instant Kill | One-shot kills on enemies |
| F10 | Fast Reload | Very rapid weapon reloading |
| F11 | Teleporter | Enable teleportation system |
| F12 | No Gravity | Disable gravity effects |

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
| Num 9 | Kill All | Eliminate all enemies* |
| Num 0 | Demo Mode | Enable all cheats with max settings |

*Requires Instant Kill to be enabled

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

### 5. Instant Kill (F9)
- **Effect**: Enemies die with single hit
- **Details**:
  - Can be combined with Kill All (Num 9) for mass elimination
  - Works on all enemy types including bosses
  - Useful for testing and gameplay acceleration
- **Usage**: Press F9 to toggle on/off

### 6. Fast Reload (F10)
- **Effect**: Dramatically reduces weapon reload time
- **Details**:
  - Reload speed increased to 10x normal
  - Makes reloading almost instantaneous
  - Works with all reloadable weapons
- **Usage**: Press F10 to toggle on/off

### 7. Teleporter (F11)
- **Effect**: Enable teleportation to predefined locations
- **Details**:
  - Multiple teleport locations available
  - Use Num 3 to cycle through locations
  - Useful for navigation and exploration
- **Usage**: Press F11 to enable, then use Num 3 to teleport

### 8. No Gravity (F12)
- **Effect**: Disables gravity for the player
- **Details**:
  - Player can float and fly
  - Enhanced jumping capabilities
  - Useful for exploration and reaching high places
- **Usage**: Press F12 to toggle on/off

### 9. Demo Mode (Num 0)
- **Effect**: Enables all cheats with maximum settings
- **Details**:
  - Activates every cheat simultaneously
  - Sets extreme values for all multipliers
  - Perfect for showcasing or testing
- **Usage**: Press Num 0 to activate

## Implementation Details

### File Structure
```
core/src/main/java/io/github/necrashter/natural_revenge/
├── CheatSystem.java          # Main cheat system implementation
├── Main.java                 # Game initialization (modified)
├── GameScreen.java          # Game loop integration (modified)
└── world/player/Player.java # Player mechanics integration
```

### Integration Points
1. **Main.java**: Initializes cheat system on game startup
2. **GameScreen.java**: Updates cheats every frame during gameplay
3. **Player.java**: Applies health, movement, and physics cheats
4. **Firearm.java**: Handles weapon-related cheats (ammo, recoil, reload)

### Performance Impact
- Minimal performance overhead
- Checks only during active gameplay
- Efficient boolean flag system
- No impact when debug mode is disabled

## Testing Guide

### Prerequisites
- Frogue game source code
- Java Development Kit (JDK)
- Gradle build system

### Build Process
```bash
# Navigate to game directory
cd frogue

# Build the game
./gradlew lwjgl3:build

# Run in debug mode
./gradlew lwjgl3:run --args debug
```

### Test Procedure
1. Start the game with debug mode enabled
2. Begin a level or practice mode
3. Press F3 to enable main cheats
4. Press F4 to view help menu
5. Test individual cheats using function keys
6. Verify effects through gameplay
7. Use quick actions (number keys) for specific tasks

### Expected Results
- God Mode: Player takes no damage
- Speed Hack: Faster movement
- Unlimited Ammo: No ammo depletion
- All cheats should provide immediate feedback

## Troubleshooting

### Common Issues
1. **Cheats not working**: Ensure debug mode is enabled (F2)
2. **No console messages**: Check terminal for [CHEAT] prefixed messages
3. **Performance issues**: Disable cheats or restart game
4. **Controls not responding**: Verify key bindings haven't changed

### Debug Mode
- Always enable debug mode for cheat functionality
- Check console output for confirmation messages
- Use F4 help menu to verify key bindings

## Advanced Usage

### Customization
The cheat system can be easily modified by editing `CheatSystem.java`:
- Add new cheat types
- Modify key bindings
- Change teleport locations
- Adjust multiplier values

### Adding New Cheats
1. Add boolean flag in CheatSystem class
2. Implement toggle logic in handleCheatKeys()
3. Add application logic in applyCheats()
4. Update help documentation

### Integration with Other Mods
The cheat system is designed to be compatible with other modifications:
- Minimal global namespace usage
- Modular architecture
- Clear integration points

## Legal and Ethical Considerations

### Educational Purpose
This cheat system is designed for:
- Learning game modification techniques
- Understanding game architecture
- Experimentation with gameplay mechanics

### Respectful Usage
- Use in single-player mode
- Don't use in competitive multiplayer
- Respect other players' experiences
- Follow game's terms of service

### Open Source Compliance
The system complies with the MIT License:
- Original code remains under MIT License
- Modifications are clearly marked
- Attribution is maintained
- Free redistribution is permitted

## Conclusion

The Frogue Cheat System provides a comprehensive framework for game modification and experimentation. It demonstrates how open-source games can be enhanced and customized while maintaining legal compliance and respecting the original developers' intentions.

The system is designed to be both powerful for experimentation and accessible for learning, making it an excellent tool for understanding game development and modification techniques.

---

*Last Updated: 2025-11-29*
*Version: 1.0*
*Compatible with: Frogue GitHub Repository (https://github.com/necrashter/frogue)*