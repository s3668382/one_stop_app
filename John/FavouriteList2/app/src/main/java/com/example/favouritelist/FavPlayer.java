package com.example.favouritelist;

public class FavPlayer {

    private String player_title;
    private String key_id;
    private int item_image;

    public FavPlayer() {
    }

    public FavPlayer(String player_title, String key_id, int item_image) {
        this.player_title = player_title;
        this.key_id = key_id;
        this.item_image = item_image;
    }

    public String getPlayer_title() {
        return player_title;
    }

    public void setPlayer_title(String player_title) {
        this.player_title = player_title;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }
}
