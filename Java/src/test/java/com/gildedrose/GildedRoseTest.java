package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {


    private final String backstage_passes = "Backstage passes to a TAFKAL80ETC concert";
    private final String aged_brie = "Aged Brie";
    private final String sulfuras = "Sulfuras, Hand of Ragnaros";

    @Test
    void quality_never_negative() {
        Item item = makeItemAndPassDay("foo", -10, 0);
        assertThat(item.quality).isGreaterThanOrEqualTo(0);
    }

    @Test
    void quality_decreases_faster_when_sell_by_has_passed(){
        Item item = makeItemAndPassDay("foo", -10, 10);
        assertThat(item.quality).isEqualTo(8);
    }

    @Test
    void quality_decreases_when_day_passed(){
        Item item = makeItemAndPassDay("foo", 10, 10);
        assertThat(item.quality).isEqualTo(9);
    }

    @Test
    void quality_increases_when_day_passed_for_aged_brie(){
        Item item = makeItemAndPassDay(aged_brie, 10, 10);
        assertThat(item.quality).isEqualTo(11);
    }

    @Test
    void sellIn_decreases_when_day_passes(){
        Item item = makeItemAndPassDay(aged_brie, 10, 10);
        assertThat(item.sellIn).isEqualTo(9);
    }

    @Test
    void sellIn_decreases_after_0(){
        Item item = makeItemAndPassDay(aged_brie, 0, 10);
        assertThat(item.sellIn).isEqualTo(-1);
    }

    @Test
    void quality_never_increase_above_50(){
        Item item = makeItemAndPassDay(aged_brie, 0, 50);
        assertThat(item.quality).isEqualTo(50);
    }

    @Test
    void aged_brie_increases_faster_beyond_zero_sellIn(){
        Item item = makeItemAndPassDay(aged_brie, 0, 10);
        assertThat(item.quality).isEqualTo(12);
    }

    @Test
    void sulfuras_never_increase_or_decrease_quality(){
        Item item = makeItemAndPassDay(sulfuras, 0, 10);
        assertThat(item.quality).isEqualTo(10);
    }

    @Test
    void sulfuras_never_increase_or_decrease_sellin(){
        Item item = makeItemAndPassDay(sulfuras, 1, 10);
        assertThat(item.sellIn).isEqualTo(1);
    }

    @Test
    void backstage_pass_quality_increases_with_nearing_sellIn(){
        Item item = makeItemAndPassDay(backstage_passes, 12, 10);
        assertThat(item.quality).isEqualTo(11);
    }
    
    @Test
    void backstage_pass_quality_increases_with_sellIn_under_11(){
        Item item = makeItemAndPassDay(backstage_passes, 10, 10);
        assertThat(item.quality).isEqualTo(12);
    }

    @Test
    void backstage_pass_quality_increases_with_sellIn_under_6(){
        Item item = makeItemAndPassDay(backstage_passes, 5, 10);
        assertThat(item.quality).isEqualTo(13);
    }

    @Test
    void backstage_pass_quality_void_with_sellIn_under_0(){
        Item item = makeItemAndPassDay(backstage_passes, 0, 10);
        assertThat(item.quality).isEqualTo(0);
    }


    private Item makeItemAndPassDay(String name, int sellIn, int quality) {
        Item item = new Item(name, sellIn, quality);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return item;
    }
}
