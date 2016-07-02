package Application.common.configInfoService;

import java.util.List;

import Application.common.info.AbstractInfo;

/**
 * @author 申彬
 *	配置从远程传来的info对象的服务
 */
public interface ConfigInfoService {
	public <T extends AbstractInfo> void configInfo(List<T> infoList);
}
