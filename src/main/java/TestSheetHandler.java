import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;

class TestSheetHandler implements XSSFSheetXMLHandler.SheetContentsHandler {

    private final StringBuilder sb = new StringBuilder();

    public void startSheet(String sheetName) {
//        sb.append("\n<table>");
    }

    public void endSheet() {
//        sb.append("\n</table>\n");
    }

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
        if (comment == null) {
            sb.append("\n<td ref=\"").append(cellReference).append("\">").append(formattedValue).append("</td>");
        } else {
            sb.append("\n<td ref=\"").append(cellReference).append("\">")
                    .append(formattedValue)
                    .append("<span type=\"comment\" author=\"")
                    .append(comment.getAuthor()).append("\">")
                    .append(comment.getString().toString().trim()).append("</span>")
                    .append("</td>");
        }
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        if (isHeader) {
            sb.append("<header tagName=\"").append(tagName).append("\">").append(text).append("</header>");
        } else {
            sb.append("<footer tagName=\"").append(tagName).append("\">").append(text).append("</footer>");
        }
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}