package com.dreams.hakunamatata.dto;

public class OfferDto {

    String offer_name;
    String offer_description;
    String offer_id;
    boolean permission;
    String code;

    public OfferDto(String offer_name, String offer_description, String offer_id, boolean permission, String code) {
        this.offer_name = offer_name;
        this.offer_description = offer_description;
        this.offer_id = offer_id;
        this.permission = permission;
        this.code = code;
    }

    public String getOffer_name() {
        return offer_name;
    }

    public void setOffer_name(String offer_name) {
        this.offer_name = offer_name;
    }

    public String getOffer_description() {
        return offer_description;
    }

    public void setOffer_description(String offer_description) {
        this.offer_description = offer_description;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
