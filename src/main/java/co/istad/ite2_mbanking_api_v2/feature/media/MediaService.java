package co.istad.ite2_mbanking_api_v2.feature.media;

import co.istad.ite2_mbanking_api_v2.feature.media.dto.MediaResponse;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface MediaService {
    MediaResponse uploadSingle(MultipartFile file,String folderName);
    List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName);
    MediaResponse loadMediaByName(String mediaName, String folderName);
    List<MediaResponse> loadAllMedia(String folderName);
    MediaResponse deleteMediaByName(String mediaName, String folderName);

}
