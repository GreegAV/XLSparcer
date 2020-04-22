

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.binary.XSSFBSharedStringsTable;
import org.apache.poi.xssf.binary.XSSFBSheetHandler;
import org.apache.poi.xssf.binary.XSSFBStylesTable;
import org.apache.poi.xssf.eventusermodel.XSSFBReader;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.SAXException;

import javax.swing.*;
import java.io.*;
import java.util.*;


// Класс для загрузки данных из файла
class FileUtils {

    private static JFileChooser fc;

    // Получаем имя папки с файлами отчетов.
    static String getDirName() {
        fc = new JFileChooser();
        String path = null;
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(FileUtils.fc);

        if (returnVal < 1) {
            File file = fc.getSelectedFile();
            path = file.getPath();
        } else {
            System.exit(0);
        }
        return path;
    }

    // получаем список файлов в папке и возвращаем отсортированный список
    static List<String> getListFiles(String path) {
        List<String> listOfFiles = new ArrayList<>();
        File[] files = new File(path).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                listOfFiles.add(file.getAbsolutePath());
            }
        }
        return listOfFiles;
    }

    // загружаем данные из файла
    static void loadFromFile() throws Exception {
        //Получаем имя папки

//        String path = getDirName();
        String path = "D:\\!!_XLS";
        List<String> fileList = getListFiles(path);

        //Получаем и печатаем список файлов в полученной папке
        getListFiles(path);

        for (String tmpFileName : fileList) {
            System.out.println(tmpFileName);
            if (tmpFileName.toLowerCase().endsWith("xlsb")) {
                processXSLBFile(tmpFileName);
            } else {
                processXSLXFile(tmpFileName);
            }
        }
    }

    private static void processXSLBFile(String fileName) {
        try (OPCPackage pkg = OPCPackage.open(fileName)) {
            XSSFBReader r = new XSSFBReader(pkg);
            XSSFBSharedStringsTable sst = new XSSFBSharedStringsTable(pkg);
            XSSFBStylesTable xssfbStylesTable = r.getXSSFBStylesTable();
            XSSFBReader.SheetIterator it = (XSSFBReader.SheetIterator) r.getSheetsData();

            while (it.hasNext()) {
                InputStream is = it.next();
                TestSheetHandler testSheetHandler = new TestSheetHandler();
                XSSFBSheetHandler sheetHandler = new XSSFBSheetHandler(is,
                        xssfbStylesTable,
                        it.getXSSFBSheetComments(),
                        sst, testSheetHandler,
                        new DataFormatter(),
                        false);
                sheetHandler.parse();
                System.out.println(testSheetHandler.toString());
            }
        } catch (IOException | OpenXML4JException | SAXException e) {
            e.printStackTrace();
        }
    }

    private static void processXSLXFile(String fname) throws Exception {
        File file = new File(fname);
        String fio;
        String data;
        String expences;
        String expencesOther;
        XSSFCell cell;
        // Read XSL file
        FileInputStream inputStream = new FileInputStream(file);

        // Get the workbook instance for XLS file
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        // Get first sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Get data from certain cell
        // wb.getSheetAt(0).getRow(9).getCell(CellReference.convertColStringToIndex("E"));
        cell = sheet.getRow(Integer.parseInt(XLSData.fioRow) - 1).getCell(Integer.parseInt(XLSData.fioCol) - 1);
        fio = cell.getStringCellValue();

        cell = sheet.getRow(Integer.parseInt(XLSData.dataRow) - 1).getCell(Integer.parseInt(XLSData.dataCol) - 1);
        data = String.valueOf(cell.getDateCellValue());

        cell = sheet.getRow(Integer.parseInt(XLSData.creditCellSpendsRow) - 1).getCell(Integer.parseInt(XLSData.creditCellSpendsCol) - 1);
        expences = cell.getRawValue();

        cell = sheet.getRow(Integer.parseInt(XLSData.creditCellSpendsOtherRow) - 1).getCell(Integer.parseInt(XLSData.creditCellSpendsOtherCol) - 1);
        expencesOther = cell.getRawValue();

        inputStream.close();
        System.out.println("fio " + fio);
        System.out.println("data " + data);
        System.out.println("expences " + expences);
        System.out.println("expencesOther " + expencesOther);
    }

//    private void processXLSBFile(File file) {
//        WorkBook workBook = new WorkBook();
//        String filePath = file.getAbsolutePath();
//        if (FilenameUtils.getExtension(filePath).equalsIgnoreCase((Static.XLSB_EXT))) {
//            try {
//                workBook.readXLSB(new java.io.FileInputStream(filePath));
//                filePath = filePath.replaceAll("(?i)".concat(Static.XLSB),
//                        Static.XLSX_EXT.toLowerCase());
//                workBook.writeXLSX(new java.io.FileOutputStream(filePath));
//                final File xlsb = new File(filePath);
//                file = xlsb;
//            } catch (Exception e) {
//                MensajesJSFUtil
//                        .mostrarMensajeNegocio(new GTMException(e, ClaveMensaje.COMANDAS_ADJUNTAR_XLSBFILE_READERROR));
//            }
//        }
//    }
}
