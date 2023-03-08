package com.zzh.Service;

import com.zzh.vo.CategoryVo;
import com.zzh.vo.Result;

public interface CategoryService {

    CategoryVo findCategoryById(Long id);

    Result findAll();

    Result findAllDetail();

    Result categoryDetailById(Long id);

}
