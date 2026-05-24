package com.example.lightcrew.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class CloudinaryService {

    private final Cloudinary cloudinary;

    // ✅ STANDART FAYL YÜKLƏMƏ (AUTO)
    public String uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                    "resource_type", "auto",
                    "secure", true // ✅ HTTPS link qaytarır
            ));
            return (String) uploadResult.get("secure_url");
        } catch (IOException e) {
            throw new RuntimeException("Fayl yüklənə bilmədi: " + e.getMessage());
        }
    }

    // ✅ VİDEO YÜKLƏMƏ (XÜSUSİ)
    public Map uploadVideo(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) throw new IllegalArgumentException("Video boşdur");

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "resource_type", "video", // ✅ Video olduğu dəqiqləşdirilir
                "folder", "cinechord/videos",
                "secure", true // ✅ HTTPS link qaytarır
        ));

        log.info("Video yükləndi: {}", uploadResult.get("secure_url"));
        return uploadResult;
    }

    // ✅ ŞƏKİL YÜKLƏMƏ
    public Map uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) throw new IllegalArgumentException("Şəkil boşdur");

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "resource_type", "image",
                "folder", "cinechord/images",
                "secure", true // ✅ HTTPS link qaytarır
        ));

        return uploadResult;
    }

    // ✅ SİLMƏ FUNKSİYASI
    public void deleteFile(String fileUrl) {
        if (fileUrl == null || fileUrl.isEmpty()) return;
        try {
            String publicId = extractPublicIdFromUrl(fileUrl);
            // URL-in tərkibində "/video/" varsa video kimi sil, yoxsa image kimi
            String resourceType = fileUrl.contains("/video/") ? "video" : "image";

            cloudinary.uploader().destroy(publicId, ObjectUtils.asMap(
                    "resource_type", resourceType
            ));
        } catch (Exception e) {
            log.error("Silinmə xətası: {}", e.getMessage());
        }
    }

    private String extractPublicIdFromUrl(String url) {
        try {
            String[] parts = url.split("/");
            int uploadIndex = -1;
            for (int i = 0; i < parts.length; i++) {
                if ("upload".equals(parts[i])) {
                    uploadIndex = i;
                    break;
                }
            }
            if (uploadIndex == -1) return "";
            StringBuilder publicId = new StringBuilder();
            for (int i = uploadIndex + 2; i < parts.length; i++) {
                if (i > uploadIndex + 2) publicId.append("/");
                publicId.append(parts[i]);
            }
            String fullPath = publicId.toString();
            int lastDotIndex = fullPath.lastIndexOf('.');
            if (lastDotIndex > 0) return fullPath.substring(0, lastDotIndex);
            return fullPath;
        } catch (Exception e) {
            return "";
        }
    }
}

