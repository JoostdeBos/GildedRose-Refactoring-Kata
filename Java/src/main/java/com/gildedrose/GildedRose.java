package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    public void updateQuality() {
        Arrays.asList(items).forEach(this::update);
    }

    private void update(Item item) {
        if (isLegendary(item)) return;
        if (isBrie(item)) {
            updateBrie(item);
        } else if (isBackstagePass(item)) {
            updateBackstagePass(item);
        } else {
            updateDefault(item);
        }
        updateSellIn(item);
    }

    private void updateSellIn(Item item) {
        item.sellIn -= 1;
    }

    private void updateDefault(Item item) {
        if (qualityAboveZero(item)) {
            item.quality -= 1;
        }
        if (item.sellIn < 1) {
            if (qualityAboveZero(item)) {
                item.quality -= 1;
            }
        }
    }

    private boolean updateBackstagePass(Item item) {
        if (isBackstagePass(item)) {
            if (underMax(item)) {
                item.quality += 1;
                if (isBackstagePass(item)) {
                    if (item.sellIn < 11) {
                        if (underMax(item)) {
                            item.quality += 1;
                        }
                    }
                    if (item.sellIn < 6) {
                        if (underMax(item)) {
                            item.quality += 1;
                        }
                    }
                }
            }
            if (item.sellIn < 1) {
                item.quality = 0;
            }
            return true;
        }
        return false;
    }

    private void updateBrie(Item item) {
        if (underMax(item)) {
            if (item.sellIn < 1) {
                item.quality += 2;
            } else {
                item.quality += 1;
            }
        }
    }

    private boolean qualityAboveZero(Item item) {
        return item.quality > 0;
    }

    private boolean underMax(Item item) {
        return item.quality < 50;
    }

    private boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isLegendary(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }
}
