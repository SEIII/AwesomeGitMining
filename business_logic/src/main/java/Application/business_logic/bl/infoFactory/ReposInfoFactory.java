package Application.business_logic.bl.infoFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.common.DTO.BasicRepositoryInfo;
import Application.common.data_service.SearchRepositoryService;
import Application.common.info.ReposInfo;

@Component
public class ReposInfoFactory extends InfoFactory<ReposInfo> {
    @Autowired
    SearchRepositoryService repositoryService;

    @Override
    public ReposInfo createOne(Object obj) {
        // TODO Auto-generated method stub
        BasicRepositoryInfo basicRepositoryInfo = (BasicRepositoryInfo) obj;
        return new ReposInfo(basicRepositoryInfo);
    }
}
