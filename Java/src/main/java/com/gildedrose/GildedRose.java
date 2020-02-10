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
        updateQuality(item);
        updateSellIn(item);
    }

    private void updateSellIn(Item item) {
        item.sellIn -= 1;
    }

    private void updateQuality(Item item) {
        if (isBrie(item) || isBackstagePass(item)) {
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
        } else if (qualityAboveZero(item)) {
            item.quality -= 1;
        }
        if (item.sellIn < 1) {
            if (isBrie(item)) {
                if (underMax(item)) {
                    item.quality += 1;
                }
            } else {
                if (isBackstagePass(item)) {
                    item.quality = 0;
                } else {
                    if (qualityAboveZero(item)) {
                        item.quality -= 1;
                    }
                }
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
