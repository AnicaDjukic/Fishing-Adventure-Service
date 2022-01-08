package isa.FishingAdventure.dto;

public class DeleteRequestDto {

    private String content;

    public DeleteRequestDto() {
    }

    public DeleteRequestDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
