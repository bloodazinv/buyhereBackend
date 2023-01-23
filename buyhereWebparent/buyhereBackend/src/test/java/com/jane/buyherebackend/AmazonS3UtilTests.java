/**
 * FileName: amazons3test
 * Author: jane
 * Date: 2023/1/22 18:06
 * Description:
 * Version:
 */

package com.jane.buyherebackend;

import com.jane.buyherebackend.util.AmazonS3Util;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class AmazonS3UtilTests {

    @Test
    public void testListFolder() {
        String folderName = "user-photos";
        List<String> listKeys = AmazonS3Util.listFolder(folderName);
        listKeys.forEach(System.out::println);
    }

    @Test
    public void testUploadFile() throws FileNotFoundException {
        String folderName = "test-upload";
        String fileName = "tokyo_2020.PNG";
        String filePath = "C:\\Users\\Noyan\\Pictures\\" + fileName;

        InputStream inputStream = new FileInputStream(filePath);

        AmazonS3Util.uploadFile(folderName, fileName, inputStream);
    }

    @Test
    public void testDeleteFile() {
        String fileName = "test-upload/tokyo_2020.PNG";
        AmazonS3Util.deleteFile(fileName);
    }

    @Test
    public void testRemoveFolder() {
        String folderName = "test-upload";
        AmazonS3Util.removeFolder(folderName);
    }
}
