package com.crm4j.base.util;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/6/8
 */
public class PageConverter {

    public static PageDto toPageDto(Page page, Integer sEcho) {
        PageDto pageDto = new PageDto();
        pageDto.setDraw(sEcho);
        pageDto.setData(page.getResults());
        pageDto.setRecordsFiltered(Long.valueOf(page.getTotalRecord()));
        pageDto.setRecordsTotal(Long.valueOf(page.getTotalRecord()));

        return pageDto;
    }
}
