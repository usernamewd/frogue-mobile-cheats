# 🎮 Frogue Mobile Cheats

[![Android Build](https://github.com/usernamewd/frogue-mobile-cheats/actions/workflows/build-android.yml/badge.svg)](https://github.com/usernamewd/frogue-mobile-cheats/actions/workflows/build-android.yml)
[![Test Mobile](https://github.com/usernamewd/frogue-mobile-cheats/actions/workflows/test-mobile.yml/badge.svg)](https://github.com/usernamewd/frogue-mobile-cheats/actions/workflows/test-mobile.yml)
[![Release](https://github.com/usernamewd/frogue-mobile-cheats/actions/workflows/release-mobile.yml/badge.svg)](https://github.com/usernamewd/frogue-mobile-cheats/actions/workflows/release-mobile.yml)

**Enhanced mobile cheat system for the Frogue FPS game** - Features touch-optimized UI, gesture controls, and mobile-specific aimbot with comprehensive cheat management.

## ✨ Features

### 🎯 Mobile Aimbot System
- **Automatic Targeting**: Detects nearest enemies within 150 units
- **Smooth Tracking**: Mathematical interpolation for realistic aiming
- **Auto-Fire**: Automatically shoots when target is acquired
- **Gesture Controls**: Double-tap anywhere to toggle auto-aimbot
- **Visual Feedback**: Button color changes indicate active state

### 📱 Touch-Optimized Interface
- **Floating Action Buttons**: Menu (≡) and Aimbot (🎯) buttons for easy access
- **Large Touch Targets**: All buttons sized for mobile interaction
- **Semi-transparent Overlay**: Non-intrusive cheat management interface
- **Status Feedback**: Real-time messages show current actions
- **Portrait/Landscape**: UI adapts to screen orientation

### 🛠️ Complete Cheat System
- **🛡️ God Mode**: Infinite health and invincibility
- **🔫 Unlimited Ammo**: Weapons never run out of ammunition
- **⚡ Speed Hack**: Enhanced movement speed (2x)
- **🎯 No Recoil**: Eliminates weapon recoil
- **🔄 Fast Reload**: Very rapid weapon reloading
- **🚪 Teleporter**: Quick location changes
- **👁️ Wall Hack**: See through walls for strategy

## 🚀 Quick Start

### 1. Download APK
Choose from the [releases page](https://github.com/usernamewd/frogue-mobile-cheats/releases):
- **Debug APK**: Ready to install immediately
- **Release APK**: Production build (requires signing)

### 2. Install on Android
1. Enable "Unknown Sources" in Android settings
2. Download and install the APK
3. Launch Frogue on your device

### 3. Use Mobile Cheats
- **Open Menu**: Tap menu button (≡) in bottom-left
- **Enable Aimbot**: Tap aimbot button (🎯) or double-tap screen
- **Toggle Cheats**: Tap any cheat button in the menu
- **Status Messages**: Watch bottom-left corner for feedback

## 📱 Mobile Controls

### Gesture Controls
| Gesture | Action | Description |
|---------|--------|-------------|
| **Double-Tap Screen** | Toggle Auto-Aimbot | Quick aimbot activation |
| **Tap Menu Button (≡)** | Open Cheat Menu | Access all cheat options |
| **Tap Aimbot Button (🎯)** | Toggle Aimbot | Standard aimbot on/off |
| **Tap Cheat Button** | Enable/Disable Cheat | Toggle specific cheat |

### Floating Buttons
- **Menu Button**: Bottom-left corner (≡)
- **Aimbot Button**: Bottom-right corner (🎯)
- **Status Bar**: Bottom-left corner (shows current actions)

## 🔧 Technical Details

### System Requirements
- **Android**: API Level 21+ (Android 5.0+)
- **RAM**: 2GB minimum, 4GB recommended
- **Storage**: 500MB free space
- **Architecture**: ARM64 or ARM32

### Build Information
- **JDK**: 8
- **Android SDK**: API 30
- **Build Tools**: 30.0.3
- **Target SDK**: 30
- **Framework**: libGDX

### Mobile Optimizations
- **Range**: 150 units (optimized for mobile screens)
- **Smoothness**: 0.2f (enhanced interpolation)
- **Performance**: Delta time handling for 60fps
- **Battery**: Power-efficient operations

## 📖 Documentation

### Comprehensive Guides
- **[Mobile Cheat System Documentation](MOBILE_CHEAT_SYSTEM_DOCUMENTATION.md)** - Complete mobile features guide
- **[Test Guide](test_mobile_cheats.sh)** - Testing instructions and verification
- **[Build Guide](.github/workflows/build-android.yml)** - CI/CD workflow documentation

### API Reference
- **MobileCheatSystem.java** - Main mobile cheat implementation
- **CheatSystem.java** - Core cheat logic and aimbot
- **GameScreen.java** - Mobile integration and input handling

## 🛠️ Development

### Setup Environment
```bash
# Clone repository
git clone https://github.com/usernamewd/frogue-mobile-cheats.git
cd frogue-mobile-cheats

# Setup Android development
# - Install Android Studio
# - Setup Android SDK 30
# - Configure Java JDK 8
```

### Build Commands
```bash
# Clean and build debug APK
./gradlew clean android:assembleDebug

# Build release APK
./gradlew clean android:assembleRelease

# Run tests
./gradlew :core:test

# Test mobile system
./test_mobile_cheats.sh
```

### CI/CD Workflows
- **[Build Android APK](.github/workflows/build-android.yml)** - Automated Android builds
- **[Test Mobile](.github/workflows/test-mobile.yml)** - Mobile system testing
- **[Release Management](.github/workflows/release-mobile.yml)** - Release creation

## 🧪 Testing

### Mobile Testing
Run the comprehensive test suite:
```bash
# Make test script executable
chmod +x test_mobile_cheats.sh

# Run mobile tests
./test_mobile_cheats.sh
```

### Test Coverage
- ✅ Mobile detection and initialization
- ✅ Touch UI elements and responsiveness
- ✅ Cheat menu system and navigation
- ✅ Aimbot functionality and gesture controls
- ✅ Performance and battery optimization
- ✅ Device compatibility and orientation

### Verification Steps
1. **Launch Game**: Verify mobile detection in logs
2. **UI Elements**: Check floating buttons are visible
3. **Menu System**: Test menu open/close functionality
4. **Cheat Toggles**: Verify all cheats work via touch
5. **Aimbot Testing**: Test button and gesture controls
6. **Performance**: Monitor FPS and battery usage

## 🔒 Security & Privacy

### Local Operation
- **No Data Transmission**: All processing happens locally
- **No Tracking**: Zero user analytics or data collection
- **Open Source**: Code transparency for security review
- **Minimal Permissions**: Only required Android permissions

### Permission Requirements
- **Internet**: For game content loading
- **Storage**: For saves and settings
- **Network State**: For connectivity checks

## 📊 Performance

### Optimizations
- **Delta Time Handling**: Smooth 60fps gameplay
- **Efficient Targeting**: Limited enemy scan range
- **Memory Management**: Proper UI disposal
- **Battery Efficiency**: Optimized mobile operations

### Recommended Settings
- **Single Aimbot**: Avoid multiple targeting systems
- **Menu Management**: Close menu during active gameplay
- **Device Monitoring**: Watch for performance impact

## 🐛 Troubleshooting

### Common Issues
| Issue | Solution |
|-------|----------|
| Menu not opening | Check Main.isMobile() detection |
| Buttons unresponsive | Verify touch input registration |
| Aimbot not working | Ensure enemies within 150 units |
| Performance lag | Disable unused cheats |
| Device overheating | Monitor extended aimbot usage |

### Debug Steps
1. **Check Logs**: Look for "Mobile cheat system initialized"
2. **Verify UI**: Ensure all buttons are visible
3. **Test Touch**: Verify responsiveness in all areas
4. **Monitor Performance**: Check FPS during operations

## 📞 Support

### Getting Help
- **GitHub Issues**: Report bugs and request features
- **Documentation**: Check comprehensive guides
- **Test Scripts**: Run verification tools

### Contributing
- **Code Improvements**: Submit pull requests
- **Documentation**: Help improve guides
- **Testing**: Report on different devices

## 📄 License

This project is licensed under the MIT License.

**Original Game**: Frogue by İlker Işık & Ömer Işık (MIT License)
**Mobile Enhancements**: Enhanced by MiniMax Agent (November 2025)

## 🎉 Credits

### Original Frogue Game
- **Developers**: İlker Işık & Ömer Işık
- **Framework**: libGDX
- **License**: MIT

### Mobile Cheat System
- **Enhancement**: MiniMax Agent
- **Features**: Touch UI, Mobile Aimbot, Gesture Controls
- **Development**: November 2025

---

## 📱 Screenshots

*Note: Screenshots would be added here showing the mobile interface*

## 🔗 Links

- **[GitHub Repository](https://github.com/usernamewd/frogue-mobile-cheats)**
- **[Releases](https://github.com/usernamewd/frogue-mobile-cheats/releases)**
- **[Issues](https://github.com/usernamewd/frogue-mobile-cheats/issues)**
- **[Actions](https://github.com/usernamewd/frogue-mobile-cheats/actions)**

---

**🎮 Enjoy enhanced mobile gaming with Frogue! 📱**

*For the best mobile gaming experience, use the debug APK for immediate installation or sign the release APK for production use.*