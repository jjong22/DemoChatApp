package com.semaproject.chatapp2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFChart;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.security.auth.login.LoginException;

// .xls 파일을 불러오는 코드입니다. .xlsx을 불러오는 방식은 약간 다르다고 합니다.

public class ExcelActivity extends AppCompatActivity {
    String TAG ="main";
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel);
        textView = (TextView) findViewById(R.id.tvExcel);
        readExcelFileFromAssets();
    }
    public void readExcelFileFromAssets() {
        try {
            InputStream myInput;
            // initialize asset manager
            AssetManager assetManager = getAssets();
            //  open excel sheet
            myInput = assetManager.open("earth.xls");
            // Create a POI File System object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            // We now need something to iterate through the cells.
            Iterator<Row> rowIter = mySheet.rowIterator();
            int rowno =0;
            textView.append("\n");
            while (rowIter.hasNext()) {
                Log.e(TAG, " row no "+ rowno );
                HSSFRow myRow = (HSSFRow) rowIter.next();
                if(rowno !=0) {
                    Iterator<Cell> cellIter = myRow.cellIterator();
                    int colno =0;
                    String num="", name="", def="";
                    while (cellIter.hasNext()) {
                        HSSFCell myCell = (HSSFCell) cellIter.next();
                        if (colno==0){
                            num = myCell.toString();
                        }else if (colno==1){
                            name = myCell.toString();
                        }else if (colno==2){
                            def = myCell.toString();
                        }
                        colno++;
                        Log.e(TAG, " Index :" + myCell.getColumnIndex() + " -- " + myCell.toString());
                    }
                    textView.append(num + " -- "+ name+ "  -- "+ def+"\n");
                }
                rowno++;
            }
        } catch (Exception e) {
            Log.e(TAG, "error "+ e.toString());
        }
    }
}