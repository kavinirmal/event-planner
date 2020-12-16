package com.example.hakunamatata.dto;

public class OfferDto {

    String offer_name;
    String offer_description;

    public OfferDto(String offer_name, String offer_description) {
        this.offer_name = offer_name;
        this.offer_description = offer_description;
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
}
