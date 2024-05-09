package com.yupi.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.apicommon.model.entity.InterfaceInfo;

/**
* @author XDUwj
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-04-29 10:07:52
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
