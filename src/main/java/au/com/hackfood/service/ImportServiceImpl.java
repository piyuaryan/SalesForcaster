package au.com.hackfood.service;

import au.com.hackfood.dao.WeeklyProfitsDAO;
import au.com.hackfood.model.WeeklyProfit;
import au.com.hackfood.util.AppUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@Transactional
public class ImportServiceImpl implements ImportService {

    final static Logger logger = LoggerFactory.getLogger(ImportServiceImpl.class);

    @Autowired
    private WeeklyProfitsDAO weeklyProfits;

    public String importXls(String fileTag, MultipartFile file) {

        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                // store the bytes somewhere
                String str = new String(bytes, StandardCharsets.UTF_8);
                // Apache POI converts  stream into String directly
                // String str = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                logger.info(str);

/*
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                Workbook workbook;

                if (fileBean.getFileData().getOriginalFilename().endsWith("xls")) {
                    workbook = new HSSFWorkbook(bis);
                } else if (fileBean.getFileData().getOriginalFilename().endsWith("xlsx")) {
                    workbook = new XSSFWorkbook(bis);
                } else {
                    throw new IllegalArgumentException("Received file does not have a standard excel extension.");
                }

                for (Row row : sheet) {
                    if (row.getRowNum() == 0) {
                        Iterator<Cell> cellIterator = row.cellIterator();
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            //go from cell to cell and do create sql based on the content
                        }
                    }
                }
*/
                return AppUtils.SUCCESS;
            }
        } catch (IOException e) {
            logger.error("Couldn't read file !!!", e);
        }
        return AppUtils.FAIL;

    }

    @Transactional
    public void saveWeeklyProfit(String fileTag, MultipartFile file) throws Exception {
        try {
            Workbook workbook;
            Sheet worksheet;
            if (file != null && file.getOriginalFilename().endsWith("xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (file != null && file.getOriginalFilename().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            } else {
                throw new IllegalArgumentException("Received file does not have a standard excel extension.");
            }
            worksheet = workbook.getSheetAt(0);

            if (worksheet == null) {
                throw new Exception("Couldn't get the sheet.");
            }

            Integer noOfEntries = worksheet.getPhysicalNumberOfRows();
            logger.info(noOfEntries.toString());
            for (int rowIndex = 1; rowIndex < noOfEntries; rowIndex++) {
                Row entry = worksheet.getRow(rowIndex);
                String name = entry.getCell(0).getStringCellValue();
                String nameOfBusiness = entry.getCell(1).getStringCellValue();
                Date weekEndingDt = entry.getCell(2).getDateCellValue();
                // Date date = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(openingDate);
                Double sale = entry.getCell(3).getNumericCellValue();
                Double approximateProfit = entry.getCell(4).getNumericCellValue();
                // Integer externalId=((Double)entry.getCell(0).getNumericCellValue()).intValue();
                String event = entry.getCell(5).getStringCellValue();
                logger.info("Row Contents:" + name + " " + nameOfBusiness + " " + weekEndingDt + " " + sale + " " + approximateProfit + " " + event);

                WeeklyProfit wp = weeklyProfits.findProfits(name, nameOfBusiness, weekEndingDt);

                if (wp == null) {
                    wp = new WeeklyProfit();
                }

                wp.setName(name);
                wp.setNameOfBusiness(nameOfBusiness);
                wp.setWeekEndingAt(weekEndingDt);
                wp.setSale(sale);
                wp.setApproximateProfit(approximateProfit);
                wp.setEvents(event);

                Long id = weeklyProfits.save(wp);
                logger.info("Saved : " + id);
                System.out.println("###### Saved : " + id);
            }

        } catch (Exception e) {
            logger.info(e.getMessage() + " " + e.getCause());
            // throw new MultipartException("Constraints Violated");
            throw e;
        }
    }
}
