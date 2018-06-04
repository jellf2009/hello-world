package com.sql.controller;

import com.sql.dao.GoodsDao;
import com.sql.dto.ResponseDTO;
import com.sql.dto.SelectDTO;
import com.sql.dto.TableDTO;
import com.sql.dto.UpdateDTO;
import com.sql.pojo.Goods;
import com.sql.service.TableService;
import com.sql.service.TestService;
import com.sql.util.ResponseUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Controller
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private TableService tableService;


    @RequestMapping(value = "/")
    public String goSuccess() {

        return "success";
    }

    @RequestMapping(value = "/gosql")
    public String gosqlTest(Model model, @RequestParam(defaultValue = "goods") String tableName) {
        ResponseUtils.resPub(model, testService, tableService, tableName);
        return "/sqltest";
    }

    @RequestMapping(value = "/goExecute")
    public String getSqlAndExecute(String sql, Model model, @RequestParam(defaultValue = "goods") String tableName) {
        List<TableDTO> tableDTOS = ResponseUtils.resPub(model, testService, tableService, tableName);
        if (sql == null || "".equals(sql.trim())) {
            return "/sqltest";
        }
        for (int i = 0; i < 10; i++) {
            String genSql = genSql(tableDTOS);
            System.out.println("genSql====" + genSql);
        }
        long start = System.currentTimeMillis();
        ResponseDTO dto = testService.execute(sql.trim());
        long end = System.currentTimeMillis();
        dto.setTime(end - start);
        model.addAttribute("response", dto);
        return "/sqltest";
    }

    private String genSql(List<TableDTO> tableDTOS) {
        Random r = new Random();
        int i = r.nextInt(tableDTOS.size() + 1);
        TableDTO tableDTO = tableDTOS.get(i);
        List<String> columns = tableDTO.getColumns();
        int j = r.nextInt(columns.size());
        String genSql = "select";
        for (int k = 0; k <= j; k++) {
            String s = columns.get(k);
            if (k == j) {
                genSql = genSql + " " + s;
            } else {
                genSql = genSql + " " + s + ",";
            }
        }
        genSql = genSql + " from " + tableDTO.getName() + " where ";


        for (int k = 0; k <= j; k++) {
            String s = columns.get(k);
            if (k == 0) {
                genSql = genSql + s + " = " + r.nextInt(323);
            } else {
                genSql = genSql + " and " + s + " = " + r.nextInt(323);
            }
        }
        return genSql;
    }

    @RequestMapping(value = "/selectTest")
    public String showSeleteTest(SelectDTO selectDTO, Model model, @RequestParam(defaultValue = "goods") String tableName) {
        System.out.println(selectDTO);
        ResponseUtils.resPub(model, testService, tableService, tableName);
        ResponseDTO dto = null;
        try {
            long start = System.currentTimeMillis();
            dto = testService.selectTest(selectDTO);
            long end = System.currentTimeMillis();
            dto.setTime((end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("response", dto);

        return "/sqltest";
    }

    @RequestMapping(value = "/insertTest")
    public String showInsertTest(String count2, Model model, @RequestParam(defaultValue = "goods") String tableName, String insertTable) {
        if (StringUtils.isNotBlank(count2) && StringUtils.isNotBlank(insertTable.trim())) {
            try {
                int num = Integer.parseInt(count2);
                long start = System.currentTimeMillis();
                ResponseDTO responseDTO = testService.insertTest(num, insertTable);
                long end = System.currentTimeMillis();
                responseDTO.setTime(end - start);
                model.addAttribute("response", responseDTO);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ResponseUtils.resPub(model, testService, tableService, tableName);
            }
        }
        return "/sqltest";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void proxyPost(String sqls, HttpServletRequest request, HttpServletResponse response, String ipaddr, String ipport, String dbname, Integer isdev) throws IOException {
//        String sql = request.getParameter("sql").replace("%", "%25");
        System.out.println("sqls= " + sqls);
//        System.out.println("tab = " + tab);

        System.out.println("ipaddr= " + ipaddr);
        System.out.println("ipport= " + ipport);
        System.out.println("databasename= " + dbname);
        System.out.println("isdev = " + isdev);
//        String res = HttpRequestUtils.sendPost(URLConstant.URL, URLConstant.param);
//        System.out.println(res);
        System.out.println("------------------------------------------");
        response.setContentType("text/html;charset=utf8");
        if(sqls.contains("*")){
            response.getWriter().write("{\"status\":\"error\",\"detail\":\"错误信息  你错误了\"}");
        }else if(sqls.contains("name")){
            response.getWriter().write("{\"status\":\"warn\",\"detail\":\"提示预警!您确认需要提交这条sql,Y是N否\"}");
        }else {
            response.getWriter().write("{\"status\":\"ok\",\"detail\":\"通过提交\"}");
        }
    }

    @RequestMapping(value = "/getTables")
    @ResponseBody
    public List<String> getAllTable(String databaseName) {
        List<String> strings = tableService.testGetAllTables("323");
        return strings;
    }

    @RequestMapping(value = "/updateTest")
    public String updateTest(UpdateDTO updateDTO, Model model, @RequestParam(defaultValue = "goods") String tableName) {
        System.out.println(updateDTO);
        ResponseDTO dto = null;
        try {
            ResponseUtils.resPub(model, testService, tableService, tableName);
            long start = System.currentTimeMillis();
            dto = testService.updateTest(updateDTO);
            long end = System.currentTimeMillis();
            dto.setTime((end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("response", dto);
        return "/sqltest";
    }

    @RequestMapping(value = "/doubleSelectTest", method = RequestMethod.POST)
    public String doubleSelectTest(Integer doubleValue, Integer doubleStart, Integer doubleSum, Integer joinNum, Model model,
                                   @RequestParam(defaultValue = "goods") String tableName) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            long start = System.currentTimeMillis();
            responseDTO = testService.doubleSelectTest(doubleValue, doubleStart, doubleSum, joinNum);
            long end = System.currentTimeMillis();
            responseDTO.setTime((end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ResponseUtils.resPub(model, testService, tableService, tableName);
        }
        model.addAttribute("response", responseDTO);
        return "/sqltest";
    }
}
