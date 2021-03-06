package com.hehanpeng.framework.cloudhhp.module.core.busi.workflow;

import com.hehanpeng.framework.cloudhhp.module.core.constant.WorkflowConstant;
import com.hehanpeng.framework.cloudhhp.module.core.domain.entity.core.CoreFlowContext;
import com.hehanpeng.framework.cloudhhp.module.core.frame.workflow.BusinessProcessor;
import com.hehanpeng.framework.cloudhhp.module.core.manage.CoreFlowContextManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 工作流：通用请求处理类
 *
 * @author hehanpeng
 */
@Slf4j
@Component("genericRequestProcessor")
public class GenericRequestProcessor implements BusinessProcessor {

    @Autowired
    CoreFlowContextManager coreFlowContextManager;

    @Override
    public void process(CoreFlowContext context) throws Exception {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("GenericRequestProcessor,{} start", methodName);
        //更新流程控制上下文
        context.setFlowStatus(WorkflowConstant.CONTEXT_STATUS_WAITING);
        //异步执行标识
        context.setProcessResult(WorkflowConstant.PROCESS_WAIT);
        coreFlowContextManager.updateContext(context);
        log.info("GenericRequestProcessor,{} end", methodName);
    }
}
