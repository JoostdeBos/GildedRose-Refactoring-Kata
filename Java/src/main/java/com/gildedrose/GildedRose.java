package com.gildedrose;

import java.util.Arrays;
import java.util.Collections;

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

        if (isBrie(item)
                || isBackstagePass(item)) {
            if (item.quality < 50) {
                item.quality += 1;

                if (isBackstagePass(item)) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality += 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality += 1;
                        }
                    }
                }
            }
        } else {
            if (item.quality > 0) {
                item.quality -= 1;
            }
        }

        item.sellIn -= 1;

        if (item.sellIn < 0) {
            if (isBrie(item)) {
                if (item.quality < 50) {
                    item.quality += 1;
                }
            } else {
                if (isBackstagePass(item)) {
                    item.quality = 0;
                } else {
                    if (item.quality > 0) {

                        item.quality -= 1;

                    }
                }
            }
        }
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
