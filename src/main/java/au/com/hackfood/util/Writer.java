package au.com.hackfood.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Writer {
    private static final Logger logger = LoggerFactory.getLogger(Writer.class);
   /* public static void write(HttpServletResponse response, HSSFSheet worksheet) {
        try {
            // Retrieve the output stream
            ServletOutputStream outputStream = response.getOutputStream();
            // Write to the output stream
            worksheet.getWorkbook().write(outputStream);
            // Flush the stream
            outputStream.flush();
        } catch (Exception e) {
            logger.info("Unable to write report to the output stream");
        }
    }*/
}