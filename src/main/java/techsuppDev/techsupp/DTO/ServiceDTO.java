package techsuppDev.techsupp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ServiceDTO {
    private Long postId;
    private String userEmail;
    private String userName;
    private String postTitle;
    private String postContents;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String originalFileName;
    private String storedFileName;
    private int fileAttached;
}
