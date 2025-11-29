#!/bin/bash

echo "=== FROGUE CHEAT SYSTEM TEST SCRIPT ==="
echo "This script demonstrates how to test the cheat system"
echo

cd /workspace/frogue

echo "1. Building the game with cheats..."
echo "   Run: ./gradlew lwjgl3:build"
echo

echo "2. Running the game in debug mode..."
echo "   Run: ./gradlew lwjgl3:run --args debug"
echo "   Or manually add 'debug' argument when starting the game"
echo

echo "3. Cheat Controls (when game is running):"
echo "   F2 - Toggle Debug Mode"
echo "   F3 - Toggle All Cheats"
echo "   F4 - Show Cheat Help"
echo "   F5 - God Mode (Infinite Health)"
echo "   F6 - Unlimited Ammo"
echo "   F7 - Speed Hack"
echo "   F8 - No Recoil"
echo "   F9 - Instant Kill"
echo "   F10 - Fast Reload"
echo "   F11 - Teleporter"
echo "   F12 - No Gravity"
echo

echo "4. Quick Actions:"
echo "   Num 1 - Toggle Speed Multiplier (1x/2x)"
echo "   Num 2 - Toggle Jump Multiplier (1x/2x)"
echo "   Num 3 - Teleport to Next Position"
echo "   Num 4 - Heal Player"
echo "   Num 5 - Give All Weapons"
echo "   Num 6 - Max All Ammo"
echo "   Num 7 - Toggle Wall Hack"
echo "   Num 8 - Reset All Cheats"
echo "   Num 9 - Kill All Enemies (requires Instant Kill)"
echo "   Num 0 - Demo Mode (All cheats max)"
echo

echo "5. Testing Steps:"
echo "   a) Start the game"
echo "   b) Press F3 to enable main cheats"
echo "   c) Press F4 to see all available commands"
echo "   d) Try F5 (God Mode) - you should become invincible"
echo "   e) Try F6 (Unlimited Ammo) - weapons won't run out of ammo"
echo "   f) Try F7 (Speed Hack) - move much faster"
echo "   g) Try Num 4 to heal if needed"
echo "   h) Try Num 0 for Demo Mode (all cheats enabled)"
echo

echo "6. Game Console Messages:"
echo "   Check the terminal/console for cheat activation messages"
echo "   Look for lines starting with '[CHEAT]'"
echo

echo "=== END OF CHEAT SYSTEM GUIDE ==="