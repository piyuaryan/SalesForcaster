package au.com.hackfood.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImportService {
    String importXls(String fileTag, MultipartFile file);

    void saveWeeklyProfit(String fileTag, MultipartFile file) throws Exception;

    // TODO: Piyush: Change this later
    void saveFoodHackDetails(String fileTag, MultipartFile file) throws Exception;

}
