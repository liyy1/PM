package com.domor.controller.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.domor.common.MyException;
import com.domor.model.Result;
import com.domor.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.domor.service.basic.DictService;
import com.domor.utils.ParamUtils;

@Controller
@RequestMapping("dict")
public class DictController {

    @Autowired
    private DictService service;

    @RequestMapping("/index")
    public String index() {
        return "/basic/dict/index";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView insertGet(HttpServletRequest request) {
        return new ModelAndView("/basic/dict/dict_add");
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Object updateGet(int id) {
        return new ModelAndView("/basic/dict/dict_edit", "dict", service.getById(id));
    }

    @ResponseBody
    @RequestMapping("/query")
    public Object query(HttpServletRequest request) {
        return service.query(ParamUtils.getParameterMap(request));
    }

    @ResponseBody
    @RequestMapping("/add")
    public Object save(HttpServletRequest request) {
        Map<String, Object> params = ParamUtils.getParameterMap(request);
        int repeat = service.getRepeat(params);
        if (repeat > 0)
            throw new MyException("键不允许重复！");
        service.save(params);
        return Result.success();
    }

    @ResponseBody
    @RequestMapping("/edit")
    public Object edit(HttpServletRequest request) {
        Map<String, Object> params = ParamUtils.getParameterMap(request);
        int repeat = service.getRepeat(params);
        if (repeat > 0)
            throw new MyException("键不允许重复！");
        service.update(params);
        return Result.success();
    }

    @ResponseBody
    @RequestMapping("/getByType")
    public Object getByType(int hasBlank, String type) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (hasBlank == 1) {
            Map<String, Object> blankMap = new HashMap<String, Object>();
            blankMap.put("id", "0");
            blankMap.put("text", "--请选择--");
            blankMap.put("selected", true);
            result.add(blankMap);
        }

        List<Map<String,Object>> dicts = service.getByType(type);
        for (Map<String,Object> dict : dicts) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", MapUtils.getStringValue(dict,"key"));
            map.put("text", MapUtils.getStringValue(dict,"value"));
            result.add(map);
        }
        return result;
    }

}
