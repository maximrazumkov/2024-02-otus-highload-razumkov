package ru.otus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    @JsonProperty("sender_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String senderId;
    @JsonProperty("sender_name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String senderName;
    @JsonProperty("message_text")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String messageText;
}
