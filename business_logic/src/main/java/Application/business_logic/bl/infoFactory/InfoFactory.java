package Application.business_logic.bl.infoFactory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import Application.common.blService.SearchService;

/**
 * @author 申彬
 * 制造逻辑层dto的factory抽象类
 */
public abstract class InfoFactory<T> {
	
	@Autowired
	protected SearchService searchService;
	
	public abstract T createOne(Object basicInfo);
	
	@SuppressWarnings("rawtypes")
	public List<T> createList(List basicInfoList){
		List<T> resultList = new ArrayList<>();
		
		for(Object basicInfo:basicInfoList){
			resultList.add(createOne(basicInfo));
		}
		return resultList;
	}

}
