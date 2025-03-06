package uz.salikhdev.todo.dto;

import lombok.Builder;

@Builder
public class TodoDto {
    private Long id;
    private String title;
    private String description;
    private boolean isCompleted;
}
