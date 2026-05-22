# Upgradeable Utilities

Upgradeable Utilities is a Fabric mod focused on one idea: your utility blocks should scale with your world progression instead of getting left behind.

Right now, the mod adds upgradeable furnace tiers that keep vanilla behavior but improve smelting speed as you move up in materials.

## What It Adds

- Copper Furnace
- Iron Furnace
- Gold Furnace
- Diamond Furnace
- Netherite Furnace

Each furnace tier:

- Uses vanilla-style crafting and upgrade paths
- Keeps familiar furnace interaction and UI
- Smelts faster than the previous tier

## Current Upgrade Path

- Furnace -> Copper Furnace
- Copper Furnace -> Iron Furnace
- Iron Furnace -> Gold Furnace
- Gold Furnace -> Diamond Furnace
- Diamond Furnace -> Netherite Furnace

Base recipes and upgrade recipes are both supported through datagen.

## Planned Utilities

The same progression system is planned for:

- Grindstones
- Blast Furnaces
- Smokers

## Tech Stack

- Minecraft `26.1.2`
- Fabric Loader `0.19.2`
- Fabric API `0.149.1+26.1.2`
- Java `25`

## Development

Build:

```powershell
.\gradlew.bat build
```

Run datagen:

```powershell
.\gradlew.bat runDatagen
```

## Project Status

This mod is in active development. Furnace progression is implemented, and additional upgradeable utility blocks are planned next.

## License

CC0-1.0
