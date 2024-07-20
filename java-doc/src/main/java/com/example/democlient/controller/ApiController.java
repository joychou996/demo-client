package com.example.democlient.controller;

import com.example.democlient.conf.DataConfig;
import com.example.democlient.domain.ResponseBodyVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @GetMapping("/read")
    public ResponseBodyVo Read() {
        return new ResponseBodyVo(DataConfig.data);
    }


    @PostMapping("/save")
    public ResponseBodyVo SetData(@RequestBody ResponseBodyVo vo) {

        Object txt = vo.getData();
        String da = "";
        if (txt != null && txt.toString().length() > 0) {

            Integer iLength = txt.toString().length();
            if (iLength > 20000) {
                iLength = 20000;
            }
            da = txt.toString().substring(0, iLength);
        }
        DataConfig.data = da;
        return new ResponseBodyVo("保存成功");
    }
}
