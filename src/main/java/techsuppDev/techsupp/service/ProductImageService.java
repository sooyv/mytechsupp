package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;
import techsuppDev.techsupp.DTO.ProductDTO;
import techsuppDev.techsupp.DTO.ProductImgDTO;
import techsuppDev.techsupp.domain.Image;
import techsuppDev.techsupp.repository.ProductImageRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Log
@Service
@RequiredArgsConstructor
public class ProductImageService {

//    private String imgLocation = "/Users/rladn/IdeaProjects/techsupp/src/main/resources/static/file/product";
    @Value("${imgLocation}")
    String imgLocation;

    private final ProductImageRepository productImageRepository;

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
//        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = System.currentTimeMillis() + "_" + originalFileName;
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;

        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();

        return savedFileName;
    }
    public void deleteImageData(Long imgId) {
        productImageRepository.delete(productImageRepository.findById(imgId).get());
    }

    @Transactional
    public void saveImg(Image image, MultipartFile multipartFile) throws Exception {
        String originImgName = multipartFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if (!StringUtils.isEmpty(originImgName)) {
            imgName = uploadFile(imgLocation, originImgName, multipartFile.getBytes());
            imgUrl = "/upload/product/" + imgName;
        }

        image.updateProductImg(originImgName, imgName, imgUrl);
        productImageRepository.save(image);
    }



}
