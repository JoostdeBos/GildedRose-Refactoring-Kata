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
        else if (isBrie(item)) {
            updateBrie(item);
        } else if (isBackstagePass(item)) {
            updateBackstagePass(item);
        } else {
            updateDefault(item);
        }
        updateSellIn(item);
    }

    private void updateDefault(Item item) {
        decreaseQuality(item);
        if (item.sellIn < 1) {
            decreaseQuality(item);
        }
    }

    private void decreaseQuality(Item item) {
        if (qualityAboveZero(item)) item.quality -= 1;
    }

    private void updateBackstagePass(Item item) {
        increaseQuality(item, 1);
        if (item.sellIn <= 10) increaseQuality(item, 1);
        if (item.sellIn <= 5) increaseQuality(item, 1);
        if (item.sellIn <= 0) item.quality = 0;
    }

    private void increaseQuality(Item item, int i) {
        if (underMax(item)) item.quality += i;
    }

    private void updateBrie(Item item) {
        if (item.sellIn < 1) {
            increaseQuality(item, 2);
        } else {
            increaseQuality(item, 1);
        }
    }

    private void updateSellIn(Item item) {
        item.sellIn -= 1;
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
