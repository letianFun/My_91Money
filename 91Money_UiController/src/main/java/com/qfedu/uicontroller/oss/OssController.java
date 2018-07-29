package com.qfedu.uicontroller.oss;


import com.qfedu.core.util.FileUtils;
import com.qfedu.core.util.OSSUtil;
import com.qfedu.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@RestController
public class OssController {

    @Autowired
    private OSSUtil ossUtil;

    @PostMapping("/fileup")
    public Result fileup(CommonsMultipartFile file){
       String fn=FileUtils.createFileName(file.getName());
       String url=ossUtil.fileUp(fn,file.getBytes());
       return new Result(0,fn+"@"+url,null);
    }
}
