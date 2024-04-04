package co.istad.ite2_mbanking_api_v2.feature.media;

import co.istad.ite2_mbanking_api_v2.feature.media.dto.MediaResponse;
import co.istad.ite2_mbanking_api_v2.util.MediaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    @Value("${media.server-path}")
    private String serverPath;
    @Value("${media.base-uri}")
    private String baseUri;
    private RestTemplate restTemplate;

    @Override
    public MediaResponse uploadSingle(MultipartFile file, String folderName) {
        // generate new unique name for file upload.
        // log.info(file.getContentType());
        String newName = UUID.randomUUID().toString();

        // Extract extension from file upload.
        // Assume profile.png
        String extension = MediaUtil.extractExtension(file.getOriginalFilename());
        newName = newName + "." + extension;

        // Copy file to server
        Path path = Paths.get(serverPath + folderName + "\\" + newName);

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        }

        return MediaResponse.builder()
                .name(newName)
                .contentType(file.getContentType())
                .extension(MediaUtil.extractExtension(newName))
                .size(file.getSize())
                .uri(String.format("%s%s/%s", baseUri, folderName, newName))
                .build();
    }

    @Override
    public List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName) {
        // Create empty array list, wait for adding loaded file
        List<MediaResponse> mediaResponses = new ArrayList<>();

        // use loop to upload
        files.forEach(file -> {
            MediaResponse mediaResponse = this.uploadSingle(file, folderName);
            mediaResponses.add(mediaResponse);
        });
        return mediaResponses;
    }

    @Override
    public MediaResponse loadMediaByName(String mediaName, String folderName) {
        // create absolute path each file
        Path path = Paths.get(serverPath + folderName + "\\" + mediaName);
        try {
            Resource resource = new UrlResource(path.toUri());
            log.info("Load resource: {}", resource.getFilename());
            if (!resource.exists()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Media has not been found!");
            }
            return MediaResponse.builder()
                    .name(mediaName)
                    .contentType(Files.probeContentType(path))
                    .extension(MediaUtil.extractExtension(mediaName))
                    .size(resource.contentLength())
                    .uri(String.format("%s%s/%s", baseUri, folderName, mediaName))
                    .build();
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MediaResponse> loadAllMedia(String folderName) {
        Path path = Paths.get(serverPath, folderName);
        log.info("path {}", path);
        try (Stream<Path> files = Files.list(path)) {
            List<MediaResponse> mediaResponses = files.map(media -> {
                try {
                    String mediaName = media.getFileName().toString();
                    Resource resource = new UrlResource(media.toUri());
                    if (!resource.exists() || !Files.isRegularFile(media)) {
                        throw new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Media has been not found!");
                    }
                    return MediaResponse.builder()
                            .name(mediaName)
                            .contentType(Files.probeContentType(media))
                            .extension(MediaUtil.extractExtension(mediaName))
                            .size(resource.contentLength())
                            .uri(String.format("%s%s/%s", baseUri, folderName, mediaName))
                            .build();
                } catch (IOException e) {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Media has been not found!"
                    );
                }
            }).collect(Collectors.toList());

            if (mediaResponses.isEmpty()) {
                log.warn("No media files found in folder: {}", folderName);
            }

            return mediaResponses;
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Media files in folder not found!"
            );
        }
    }


    @Override
    public MediaResponse deleteMediaByName(String mediaName, String folderName) {
        Path path = Paths.get(serverPath + folderName + "\\" + mediaName);
        try {
            long size = Files.size(path);
            if (Files.deleteIfExists(path)) {
                return MediaResponse.builder()
                        .name(mediaName)
                        .contentType(Files.probeContentType(path))
                        .extension(MediaUtil.extractExtension(mediaName))
                        .size(size)
                        .uri(String.format("%s%s/%s", baseUri, folderName, mediaName))
                        .build();
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Media has not been found");
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Media path %s cannot be deleted!", e.getLocalizedMessage()));
        }

    }
    @Override
    public Resource downloadMediaByName(String mediaName, String folderName) {
        Path path = Paths.get(serverPath + folderName + "\\" + mediaName);
        try {
            return new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Media has not been found!");
        }

    }


}
