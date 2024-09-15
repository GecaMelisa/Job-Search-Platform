package ba.edu.ibu.job.search.platform.rest.dto;

import java.time.LocalDateTime;

public class FileUploadResponseDTO {
    private String filePath;
    private LocalDateTime dateTime;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
