# ADK-Bundles
Here is a copy of the default config, it should be fairly self explanitory

NOTE: if you wish to have no enchants on an item, leave an empty - line, do not remove it entirely
```
world-whitelist:
  - world
  - world_nether
bundle-model-numbers: #Template: MATERIAL-CustomModelDataNumberRegular-CustomModelDataNumberMega
  - COBBLESTONE-1-3
  - SPRUCE_PLANKS-2-4
recipe:
  - STRING:BUNDLE_ITEM:STRING
  - BUNDLE_ITEM:BUNDLE_ITEM:BUNDLE_ITEM
  - STRING:BUNDLE_ITEM:STRING
#################################################
bundle-name: '&f%item% Bundle'
mega-bundle-name: '&f%item% Mega Bundle'
#################################################
bundle-lore: #DO NOT LEAVE THIS EMPTY --- likely will cause issues
  - "&fLore 1"
  - "&fLore 2"
  - "&fLore 3"
  - "&fBundle"
mega-bundle-lore:
  - "&fLore 1"
  - "&fLore 2"
  - "&fLore 3"
  - "&fMega Bundle"
#################################################
bundle-enchantments:
  - DURABILITY:1
bundle-hide-enchantments: true
#################################################
mega-bundle-enchantments:
  - DURABILITY:1
  - FIRE_ASPECT:2
mega-bundle-hide-enchantments: true
