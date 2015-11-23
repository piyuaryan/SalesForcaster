package au.com.salesforecaster.service;

import au.com.salesforecaster.dao.RestaurantsDAO;
import au.com.salesforecaster.dao.RestaurantsSaleDAO;
import au.com.salesforecaster.dao.WeeklyProfitsDAO;
import au.com.salesforecaster.model.Restaurant;
import au.com.salesforecaster.model.RestaurantsSale;
import au.com.salesforecaster.model.WeeklyProfit;
import au.com.salesforecaster.util.AppUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
@Transactional
public class ImportServiceImpl implements ImportService {

    final static Logger logger = LoggerFactory.getLogger(ImportServiceImpl.class);

    @Autowired
    private WeeklyProfitsDAO weeklyProfits;

    @Autowired
    private RestaurantsDAO restaurant;

    @Autowired
    private RestaurantsSaleDAO sales;

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

    private Sheet getSheet(MultipartFile file) throws Exception {
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
        return worksheet;
    }


    @Transactional
    public void saveWeeklyProfit(String fileTag, MultipartFile file) throws Exception {
        try {
            Sheet worksheet = getSheet(file);

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

    @Override
    public void saveFoodHackDetails(String fileTag, MultipartFile file) throws Exception {
        try {
            Sheet worksheet = getSheet(file);

            Integer noOfEntries = worksheet.getPhysicalNumberOfRows();
            logger.info(noOfEntries.toString());
            for (int rowIndex = 1; rowIndex < noOfEntries; rowIndex++) {
                Row entry = worksheet.getRow(rowIndex);
                if (entry != null) {
                    String name = entry.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String address = entry.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
                    String location = entry.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
                    Double lat = entry.getCell(3, Row.RETURN_BLANK_AS_NULL) != null ? entry.getCell(3).getNumericCellValue() : 0;
                    Double lon = entry.getCell(4, Row.RETURN_BLANK_AS_NULL) != null ? entry.getCell(4).getNumericCellValue() : 0;

                    String cakeType = entry.getCell(5, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
                    Date saleDate = entry.getCell(6, Row.RETURN_BLANK_AS_NULL) != null ? entry.getCell(6).getDateCellValue() : null;
                    String day = entry.getCell(7, Row.CREATE_NULL_AS_BLANK).getStringCellValue();

                    Integer itmesMade = (entry.getCell(8, Row.RETURN_BLANK_AS_NULL) != null) ? ((Double) entry.getCell(8).getNumericCellValue()).intValue() : -1;
                    Integer itmesLeft = (entry.getCell(9, Row.RETURN_BLANK_AS_NULL) != null) ? ((Double) entry.getCell(9).getNumericCellValue()).intValue() : -1;
                    Double saleAmt = (entry.getCell(10, Row.RETURN_BLANK_AS_NULL) != null) ? entry.getCell(10).getNumericCellValue() : -1;


                    // Create Unique Restaurant Entry
                    Restaurant r = null;
                    try {
                        r = restaurant.findRestaurantByName(name);
                    } catch (NoResultException | EmptyResultDataAccessException e) {
                        logger.debug("Restaurant Not Found");
                    }

                    if (r == null) {
                        r = new Restaurant();
                        r.setName(name);
                        r.setAddress(address);
                        r.setLocation(location);
                        r.setLat(lat);
                        r.setLon(lon);

                        Long id = restaurant.save(r);
                        logger.info("Saved : " + id);
                        System.out.println("###### Restaurant Saved : " + id);
                    }

                    // Create Sale Entry
                    RestaurantsSale rSale = null;
                    try {
                        rSale = sales.findSalesByDateAndType(r, saleDate, cakeType);
                    } catch (NoResultException | EmptyResultDataAccessException e) {
                        logger.info("Sales Record not found.");
                    }

                    if (rSale == null) {
                        rSale = new RestaurantsSale();
                    }
                    rSale.setRestaurant(r);
                    rSale.setCakeType(cakeType);
                    rSale.setSaleDate(saleDate);
                    rSale.setDay(day);
                    if (saleDate != null) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(saleDate);
                        rSale.setMonth(new SimpleDateFormat("MMM").format(cal.getTime()));
                    }
                    rSale.setItemsMade(itmesMade);
                    rSale.setItemsLeft(itmesLeft);
                    rSale.setSaleAmt(saleAmt);

                    Long id = sales.save(rSale);
                    logger.info("Saved : " + id);
                    System.out.println("###### Sale Saved : " + id);
                }
            }

        } catch (Exception e) {
            logger.info(e.getMessage() + " " + e.getCause());
            // throw new MultipartException("Constraints Violated");
            e.printStackTrace();
            throw e;
        }
    }
}
