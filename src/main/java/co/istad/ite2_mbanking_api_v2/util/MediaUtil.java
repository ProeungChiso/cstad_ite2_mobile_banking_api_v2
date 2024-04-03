package co.istad.ite2_mbanking_api_v2.util;

public class MediaUtil {
    public static String extractExtension(String mediaName){
        int lastDotIndex = mediaName.lastIndexOf(".");
        return mediaName.substring(lastDotIndex + 1);
    }
}
