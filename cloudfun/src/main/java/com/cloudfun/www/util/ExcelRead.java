package com.cloudfun.www.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloudfun.www.util.ReadOption;
import com.cloudfun.www.util.CellRef;
import com.cloudfun.www.util.FileType;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelRead {

    public static List<Map<String, String>> read(ReadOption readOption) {

        /*
         * 엑셀 파일 자체
         * 엑셀파일을 읽어 들인다.
         * FileType.getWorkbook() : 파일의 확장자를 구분해서 가져온다.
         */
        Workbook wb = FileType.getWorkbook(readOption.getFilePath());

        // 엑셀 파일에서 첫번째 시트를 가지고 온다.
        Sheet sheet = wb.getSheetAt(0);

        /*
         * 시트에서 유효한(데이터가 있는) 행의 갯수를 가져온다.
         */
        int numOfRows = sheet.getPhysicalNumberOfRows();
        int numOfCells = 0;

        Row row = null;
        Cell cell = null;

        String cellName = "";

        // key : 컬럼 / value: 데이터
        /*
         * 각 Row마다의 값을 저장할 맴 객체
         * 저장되는 형식은 다음과 같다
         * put("A","이름");
         * put("B","게임명");
         */
        Map<String, String> map = null;

        /*
         * 각 Row를 리스트에 담는다
         * 하나의 Row는 하나의 Map으로 표현되며
         * List에는 모든 Row가 포함될 것이다.
         */
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        /*
         * Row만큼 반복을 한다.
         */
        for(int rowIndex = readOption.getStartRow() - 1; rowIndex < numOfRows; rowIndex++) {

            /*
             * 워크북에서 가져온 시트에서 rowIndex에 해당하는 Row를 가져온다.
             * 하나의 Row는 여러개의 Cell을 가진다.
             */
            row = sheet.getRow(rowIndex);

            if(row != null) {

                // 유효한 셀의 갯수
                /*
                 * 가져온 Row의 Cell의 갯수를 구한다.
                 */
                numOfCells = row.getLastCellNum();

                /*
                 * 데이터를 담을 맵 객체 초기화
                 */
                map = new HashMap<String, String>();

                /*
                 * Cell의 수 만큼 반복
                 */
                for(int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {

                    /*
                     * Row에서 CellIndex에 해당하는 Cell을 가져온다.
                     */
                    cell = row.getCell(cellIndex);

                    /*
                     * 현재 Cell의 이름을 가져온다.
                     * 이름의 예 : A,B,C,D,E.....
                     */
                    cellName = CellRef.getName(cell, cellIndex);

                    /*
                     * 추출 대상 컬럼인지 확인한다.
                     * 추출 대생 컬럼이 아니라면
                     * for로 다시 올라간다.
                     */
                    if( !readOption.getOutputColumns().contains(cellName) ) {
                        continue;
                    }

                    /*
                     * 맵 객체의 Cell의 이름을 키(Key)로 데이터를 담는다.
                     */
                    map.put(cellName, CellRef.getValue(cell));
                }

                /*
                 * 만들어진 Map 객체를 List에 넣는다.
                 */
                result.add(map);

            }

        }

        return result;

    }



   /* public static void main(String[] args) {

        ReadOption ro = new ReadOption();
        ro.setFilePath("D:/game.xlsx");
        ro.setOutputColumns("A", "B");
        ro.setStartRow(1);

        // Map을 리스트로 만들기
        List<Map<String, String>> result = ExcelRead.read(ro);

        for(Map<String, String> map : result) {
            System.out.println(map.get("A"));
        }
    }*/

}

