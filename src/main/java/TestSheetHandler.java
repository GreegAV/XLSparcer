import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


class TestSheetHandler implements XSSFSheetXMLHandler.SheetContentsHandler {

    Data extractedData = new Data();

//    public void startSheet(String sheetName) {
//        sb.append("\n<table>");
//    }

//    public void endSheet() {
//        sb.append("\n</table>\n");
//    }

    @Override
    public void startRow(int rowNum) {
//        sb.append("\n<tr num=\"").append(rowNum).append("\">");
    }

    @Override
    public void endRow(int rowNum) {
//        sb.append("</tr num=\"").append(rowNum).append("\">");
    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {
        formattedValue = (formattedValue == null) ? "" : formattedValue;
        if (cellReference.equalsIgnoreCase(XLSData.creditCellOther)) {
            extractedData.setSummOther(Integer.parseInt(formattedValue.replace((char)0xa0,(char)0x20).replace(" ","")));
        }

        if (cellReference.equalsIgnoreCase(XLSData.creditCell)) {
            extractedData.setSumm(Integer.parseInt(formattedValue.replace((char)0xa0,(char)0x20).replace(" ","")));
        }

        if (cellReference.equalsIgnoreCase(XLSData.fioCell)) {
            extractedData.setFio(formattedValue);
        }

        if (cellReference.equalsIgnoreCase(XLSData.dataCell)) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy");
            try {
                extractedData.setDate(formatter.parse(formattedValue.substring(4)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    public Data getExtractedData(){
        return extractedData;
    }
    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
//        if (isHeader) {
//            sb.append("<header tagName=\"").append(tagName).append("\">").append(text).append("</header>");
//        } else {
//            sb.append("<footer tagName=\"").append(tagName).append("\">").append(text).append("</footer>");
//        }
    }

    @Override
    public String toString() {
        return extractedData.toString();
    }
}