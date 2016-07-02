package Application.business_logic.bl.stat;

import java.util.List;

import org.springframework.stereotype.Component;

import Application.common.blService.SearchService;
import Application.common.configInfoService.ConfigInfoService;
import Application.common.info.AbstractInfo;

/**
 * @author 申彬
 * 配置info对象，使之可以延迟加载
 */
@Component
public class ConfigServiceImpl implements ConfigInfoService{

	SearchService service;

	@Override
	public <T extends AbstractInfo> void configInfo(List<T> infoList) {
		for(T info : infoList){
			configInfo(info);
		}
	}

	public <T extends AbstractInfo> void configInfo(T info){
		info.setService(service);
	}

}
