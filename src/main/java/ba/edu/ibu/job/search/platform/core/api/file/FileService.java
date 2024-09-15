package ba.edu.ibu.job.search.platform.core.api.file;

import ba.edu.ibu.job.search.platform.rest.dto.FileUploadResponseDTO;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public FileUploadResponseDTO uploadFile(MultipartFile multipartFile) throws FileUploadException;
    public String getFileUrl(String fileName);
}
