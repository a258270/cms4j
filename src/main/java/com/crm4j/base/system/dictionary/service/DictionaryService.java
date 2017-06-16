package com.crm4j.base.system.dictionary.service;

import com.crm4j.base.dao.DaoSupport;
import com.crm4j.base.util.DataMap;
import com.crm4j.base.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description: 字典Service
 *
 * @author: zmj
 * @create: 2017/6/8
 */
@Service
public class DictionaryService {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * 根据Id获取字典
     * @param dataMap
     * @return
     * @throws Exception
     */
    public DataMap getDictionaryById(DataMap dataMap) throws Exception {
        return (DataMap) daoSupport.findForObject("DictionaryMapper.getDictionaryById", dataMap);
    }

    /**
     * 获取字典分页数据
     * @param page
     * @return
     * @throws Exception
     */
    public List<DataMap> getDictionaries(Page page) throws Exception {
        return (List<DataMap>) daoSupport.findForList("DictionaryMapper.getDictionaries", page);
    }

    /**
     * 获取所有顶级字典
     * @return
     * @throws Exception
     */
    public List<DataMap> getAllFatherDictionaries() throws Exception {
        return (List<DataMap>) daoSupport.findForList("DictionaryMapper.getAllFatherDictionaries");
    }

    /**
     * 新增字典
     * @param dataMap
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void addDictionary(DataMap dataMap) throws Exception {
        daoSupport.save("DictionaryMapper.addDictionary", dataMap);
    }

    /**
     * 批量删除字典
     * @param objs 拼接id的String
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchRemoveDictionaries(String objs) throws Exception {

        String[] objArr = objs.split(",");
        List<String> objList = Arrays.asList(objArr);

        daoSupport.batchDelete("DictionaryMapper.batchRemoveDictionaries", objList);

        //删除子节点
        for (String obj : objList) {
            DataMap param = new DataMap();
            param.put("DIC_ID", obj);
            List<DataMap> sons = getDictionariesByFatherId(param);
            if(sons != null) {
                String sonIds = "";
                for(DataMap son : sons) {
                    sonIds += son.getString("DIC_ID") + ",";
                }

                if(StringUtils.isBlank(sonIds)){
                    continue;
                }

                sonIds = sonIds.substring(0, sonIds.length() - 1);
                batchRemoveDictionaries(sonIds);//递归删除该字典下的子节点
            }
        }
    }

    /**
     * 删除字典
     * @param dataMap
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void removeDictionary(DataMap dataMap) throws Exception {
        daoSupport.delete("DictionaryMapper.removeDictionary", dataMap);
    }

    /**
     * 根据父级id获取字典
     * @param dataMap
     * @return
     * @throws Exception
     */
    public List<DataMap> getDictionariesByFatherId(DataMap dataMap) throws Exception {
        return (List<DataMap>) daoSupport.findForList("DictionaryMapper.getDictionariesByFatherId", dataMap);
    }

    /**
     * 修改字典
     * @param dataMap
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateDictionary(DataMap dataMap) throws Exception {
        daoSupport.update("DictionaryMapper.updateDictionary", dataMap);
    }

    /**
     * 根据字典内码获取字典
     * @param dataMap
     * @return
     * @throws Exception
     */
    public DataMap getDictionaryByCode(DataMap dataMap) throws Exception {
        return (DataMap) daoSupport.findForObject("DictionaryMapper.getDictionaryByCode", dataMap);
    }
}
