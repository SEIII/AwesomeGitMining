//package Application.rmi;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.remoting.rmi.RmiServiceExporter;
//import Application.common.blService.ClassifyService;
//import Application.common.blService.SearchService;
//import Application.common.blService.statService.StatSingleUserService;
//import Application.common.blService.statService.StatSingleRepoService;
//import Application.common.blService.statService.StatTotalReposService;
//import Application.common.blService.TitleService;
//import Application.common.blService.statService.StatTotalUserService;
//import Application.common.blService.netService.*;
//
//
//@Configuration
//public class RMIConfig {
//
//	protected int servicePort = 2098;
//
//	@Bean
//	protected RmiServiceExporter ClassifyService(ClassifyService classifyService){
//		return configExporter(classifyService, ClassifyService.class);
//	}
//
//	@Bean
//	protected RmiServiceExporter SearchService(SearchService searchService){
//		return configExporter(searchService, SearchService.class);
//	}
//
//	@Bean
//	protected RmiServiceExporter StatSingleRepoService(StatSingleRepoService statSingleRepoService){
//		return configExporter(statSingleRepoService, StatSingleRepoService.class);
//	}
//	
//	@Bean
//	protected RmiServiceExporter StatSingleUserService(StatSingleRepoService statSingleUserService){
//	    return configExporter(statSingleUserService, StatSingleUserService.class);
//	}
//
//	@Bean
//	protected RmiServiceExporter StatTotalReposService(StatTotalReposService statTotalReposService){
//		return configExporter(statTotalReposService, StatTotalReposService.class);
//	}
//
//	@Bean
//	protected RmiServiceExporter StatTotalUserService(StatTotalUserService statTotalUserService){
//		return configExporter(statTotalUserService, StatTotalUserService.class);
//	}
//
//	@Bean
//	protected RmiServiceExporter TitleService(TitleService titleService){
//		return configExporter(titleService, TitleService.class);
//	}
//	
//	@Bean
//	protected RmiServiceExporter RefreshBufferService(RefreshBufferService refreshBufferService){
//		return configExporter(refreshBufferService, RefreshBufferService.class);
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected RmiServiceExporter configExporter(Object service,Class interfaceClass){
//		RmiServiceExporter exporter = new RmiServiceExporter();
//		exporter.setService(service);
//		exporter.setServicePort(servicePort);
//		exporter.setServiceInterface(interfaceClass);
//		exporter.setServiceName(interfaceClass.getSimpleName());
//		return exporter;
//	}
//
//}
