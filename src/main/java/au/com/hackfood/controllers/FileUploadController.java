package au.com.hackfood.controllers;

import au.com.hackfood.service.ImportService;
import au.com.hackfood.util.AppUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    ImportService importService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView uploadPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("upload");
        return model;
    }

    @RequestMapping(value = "/xls", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("fileTag") String fileTag,
                                   @RequestParam("file") MultipartFile file) {
        return importService.importXls(fileTag, file);
    }

    @RequestMapping(value = "/xls2", method = RequestMethod.POST)
    public ResponseEntity<String> handleFormUpload2(@RequestParam("fileTag") String fileTag,
                                                    @RequestParam("file") MultipartFile file) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        JSONObject json = new JSONObject();
        try {
            importService.saveWeeklyProfit(fileTag, file);

            json.put("status", AppUtils.SUCCESS);
            return new ResponseEntity<>(json.toString(), headers, HttpStatus.OK);
        } catch (Exception e) {
            json.put("status", AppUtils.FAIL);
            json.put("errorMsg", e.getMessage());
            return new ResponseEntity<>(json.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/foodhack", method = RequestMethod.POST)
    public ResponseEntity<String> saveFoodHackData(@RequestParam("fileTag") String fileTag,
                                                   @RequestParam("file") MultipartFile file) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        JSONObject json = new JSONObject();
        try {
            importService.saveFoodHackDetails(fileTag, file);

            json.put("status", AppUtils.SUCCESS);
            return new ResponseEntity<>(json.toString(), headers, HttpStatus.OK);
        } catch (Exception e) {
            json.put("status", AppUtils.FAIL);
            json.put("errorMsg", e.getMessage());
            return new ResponseEntity<>(json.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
