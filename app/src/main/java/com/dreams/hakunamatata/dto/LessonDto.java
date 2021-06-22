package com.dreams.hakunamatata.dto;

public class LessonDto {
    String lesson,pdf_link,audio_link,order;

    public LessonDto(String lesson, String pdf_link, String audio_link, String order) {
        this.lesson = lesson;
        this.pdf_link = pdf_link;
        this.audio_link = audio_link;
        this.order = order;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getPdf_link() {
        return pdf_link;
    }

    public void setPdf_link(String pdf_link) {
        this.pdf_link = pdf_link;
    }

    public String getAudio_link() {
        return audio_link;
    }

    public void setAudio_link(String audio_link) {
        this.audio_link = audio_link;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
