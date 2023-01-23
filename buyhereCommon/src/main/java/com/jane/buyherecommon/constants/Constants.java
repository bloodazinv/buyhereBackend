/**
 * FileName: Constants
 * Author: jane
 * Date: 2023/1/10 15:58
 * Description:
 * Version:
 */

package com.jane.buyherecommon.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

    private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);

    public static final String S3_BASE_URI;

    static {
        // String bucketName = "buyhere-files";
        // String region = "eu-west-1";
        String bucketName = System.getenv("AWS_BUCKET_NAME");
        String region = System.getenv("AWS_REGION");
        String pattern = "https://%s.s3.%s.amazonaws.com";

        LOGGER.info("BuyhereCommon | Constants | bucketName : " + bucketName);
        LOGGER.info("BuyhereCommon | Constants | region : " + region);

        S3_BASE_URI = bucketName == null ? "" : String.format(pattern, bucketName, region);

        LOGGER.info("BuyhereCommon | S3_BASE_URI | S3_BASE_URI : " + S3_BASE_URI);
    }

}
